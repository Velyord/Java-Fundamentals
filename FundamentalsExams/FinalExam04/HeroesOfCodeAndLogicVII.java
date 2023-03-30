/*
Task:
    You got your hands on the most recent update on the best MMORPG of all time –
    Heroes of Code and Logic. You want to play it all day long!
    So cancel all other arrangements and create your party!
    On the first line of the standard input, you will receive an integer n –
    the number of heroes that you can choose for your party.
    On the next n lines, the heroes themselves will follow with their hit points and mana points
    separated by a single space in the following format:
    "{hero name} {HP} {MP}"
        • HP stands for hit points and MP for mana points
        • a hero can have a maximum of 100 HP and 200 MP
    After you have successfully picked your heroes, you can start playing the game.
    You will be receiving different commands, each on a new line, separated by " – ",
    until the "End" command is given.
    There are several actions that the heroes can perform:
    "CastSpell – {hero name} – {MP needed} – {spell name}"
        • If the hero has the required MP, he casts the spell, thus reducing his MP. Print this message:
            ◦ "{hero name} has successfully cast {spell name} and now has {mana points left} MP!"
        • If the hero is unable to cast the spell print:
            ◦ "{hero name} does not have enough MP to cast {spell name}!"
    "TakeDamage – {hero name} – {damage} – {attacker}"
        • Reduce the hero HP by the given damage amount.
        If the hero is still alive (his HP is greater than 0) print:
            ◦ "{hero name} was hit for {damage} HP by {attacker} and now has {current HP} HP left!"
        • If the hero has died, remove him from your party and print:
            ◦ "{hero name} has been killed by {attacker}!"
    "Recharge – {hero name} – {amount}"
        • The hero increases his MP. If it brings the MP of the hero above the maximum value (200),
        MP is increased to 200. (the MP can't go over the maximum value).
        •  Print the following message:
            ◦ "{hero name} recharged for {amount recovered} MP!"
    "Heal – {hero name} – {amount}"
        • The hero increases his HP. If a command is given that would bring the HP of the hero above the
        maximum value (100), HP is increased to 100 (the HP can't go over the maximum value).
        •  Print the following message:
            ◦ "{hero name} healed for {amount recovered} HP!"
Input:
    • On the first line of the standard input, you will receive an integer n
    • On the following n lines, the heroes themselves will follow with their hit points and mana points
    separated by a space in the following format
    • You will be receiving different commands, each on a new line, separated by " – ",
    until the "End" command is given
Output:
    • Print all members of your party who are still alive, in the following format
    (their HP/MP need to be indented 2 spaces):
    "{hero name}
      HP: {current HP}
      MP: {current MP}"
Constraints:
    • The starting HP/MP of the heroes will be valid, 32-bit integers will never be negative or exceed
    the respective limits.
    • The HP/MP amounts in the commands will never be negative.
    • The hero names in the commands will always be valid members of your party. No need to check that
    explicitly.
Examples:
    2
    Solmyr 85 120
    Kyrre 99 50
    Heal - Solmyr - 10
    Recharge - Solmyr - 50
    TakeDamage - Kyrre - 66 - Orc
    CastSpell - Kyrre - 15 - ViewEarth
    End
    ->
    Solmyr healed for 10 HP!
    Solmyr recharged for 50 MP!
    Kyrre was hit for 66 HP by Orc and now has 33 HP left!
    Kyrre has successfully cast ViewEarth and now has 35 MP!
    Solmyr
      HP: 95
      MP: 170
    Kyrre
      HP: 33
      MP: 35

    4
    Adela 90 150
    SirMullich 70 40
    Ivor 1 111
    Tyris 94 61
    Heal - SirMullich - 50
    Recharge - Adela - 100
    CastSpell - Tyris - 1000 - Fireball
    TakeDamage - Tyris - 99 - Fireball
    TakeDamage - Ivor - 3 - Mosquito
    End
    SirMullich healed for 30 HP!
    Adela recharged for 50 MP!
    Tyris does not have enough MP to cast Fireball!
    Tyris has been killed by Fireball!
    Ivor has been killed by Mosquito!
    Adela
      HP: 90
      MP: 200
    SirMullich
      HP: 100
      MP: 40
        Heal – SirMullich healed for 30 HP due to the HP max limit.
        Recharge – Adela recharged for 50 MP due to the MP max limit.
        CastSpell – Tyris does not have enough MP to cast the spell.
        TakeDamage – Tyris's HP is reduced by 99, thus becoming -5, which means he is dead.
        TakeDamage – Ivor's HP is now -2, so he is dead too.
        After the "End" command, we print the remaining living heroes.

 */
package FundamentalsExams.FinalExam04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeroesOfCodeAndLogicVII {
    private static class Hero {
        private final String name;
        private int hp;
        private int mp;

        public Hero(String name, int hp, int mp) {
            this.name = name;
            this.hp = hp;
            this.mp = mp;
        }

        public String getName() {
            return name;
        }

        public int getHp() {
            return hp;
        }

        public int getMp() {
            return mp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public void setMp(int mp) {
            this.mp = mp;
        }
    }

    private static final List<Hero> heroList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        populateHeroList();
        executeCommands();
        printHeroes();
    }

    private static void executeCommands() {
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] tokens = command.split(" - ");
            String action = tokens[0];
            String heroName = tokens[1];
            switch (action) {
                case "CastSpell":
                    int mpNeeded = Integer.parseInt(tokens[2]);
                    String spellName = tokens[3];
                    castSpell(heroName, mpNeeded, spellName);
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(tokens[2]);
                    String attacker = tokens[3];
                    takeDamage(heroName, damage, attacker);
                    break;
                case "Recharge":
                    int amount = Integer.parseInt(tokens[2]);
                    recharge(heroName, amount);
                    break;
                case "Heal":
                    int amountHP = Integer.parseInt(tokens[2]);
                    heal(heroName, amountHP);
                    break;
            }
            command = scanner.nextLine();
        }
    }

    private static void populateHeroList() {
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String heroName = input[0];
            int hp = Integer.parseInt(input[1]);
            int mp = Integer.parseInt(input[2]);
            Hero hero = new Hero(heroName, hp, mp);
            heroList.add(hero);
        }
    }

    private static void printHeroes() {
        for (Hero hero : heroList) {
            System.out.printf(
                "%s%n  HP: %d%n  MP: %d%n",
                hero.getName(), hero.getHp(), hero.getMp()
            );
        }
    }

    private static void heal(String heroName, int amountHP) {
        for (Hero hero : heroList) {
            if (hero.getName().equals(heroName)) {
                if (hero.getHp() + amountHP > 100) {
                    System.out.printf(
                            "%s healed for %d HP!%n",
                            heroName, 100 - hero.getHp()
                    );

                    hero.setHp(100);
                } else {
                    hero.setHp(hero.getHp() + amountHP);

                    System.out.printf(
                        "%s healed for %d HP!%n",
                        heroName, amountHP
                    );
                }
            }
        }
    }

    private static void recharge(String heroName, int amount) {
        for (Hero hero : heroList) {
            if (hero.getName().equals(heroName)) {
                if (hero.getMp() + amount > 200) {
                    System.out.printf(
                        "%s recharged for %d MP!%n",
                        heroName, 200 - hero.getMp()
                    );

                    hero.setMp(200);
                } else {
                    hero.setMp(hero.getMp() + amount);

                    System.out.printf(
                        "%s recharged for %d MP!%n",
                        heroName, amount
                    );
                }
            }
        }
    }

    private static void takeDamage(String heroName, int damage, String attacker) {
        for (Hero hero : heroList) {
            if (hero.getName().equals(heroName)) {
                if (hero.getHp() > damage) {
                    hero.setHp(hero.getHp() - damage);
                    System.out.printf(
                            "%s was hit for %d HP by %s and now has %d HP left!%n",
                            heroName, damage, attacker, hero.getHp()
                    );
                } else {
                    heroList.remove(hero);
                    System.out.printf("%s has been killed by %s!%n", heroName, attacker);
                }

                break;
            }
        }
    }

    private static void castSpell(String heroName, int mpNeeded, String spellName) {
        for (Hero hero : heroList) {
            if (hero.getName().equals(heroName)) {
                if (hero.getMp() >= mpNeeded) {
                    hero.setMp(hero.getMp() - mpNeeded);
                    System.out.printf("%s has successfully cast %s and now has %d MP!%n", heroName, spellName, hero.getMp());
                } else {
                    System.out.printf("%s does not have enough MP to cast %s!%n", heroName, spellName);
                }
            }
        }
    }
}