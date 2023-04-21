/*
Task:
    Heroes III is the best game ever. Everyone loves it, and everyone plays it all the time.
    John is not an exclusion from this rule. His favorite units in the game are all types of dragons -
    black, red, gold, azure, etc.
    He likes them so much that he gives them names and keeps logs of their stats:
    damage, health, and armor. The process of aggregating all the data is quite tedious,
    so he would like to have a program doing it. Since he is no programmer, it's your task to help him.
    You need to categorize dragons by their type.
    For each dragon, identified by name, keep information about his stats.
    Type is preserved as in the input order, but dragons are sorted alphabetically by name.
    For each type, you should also print the average damage, health, and armor of the dragons.
    For each dragon, print his stats.
    There may be missing stats in the input, though.
    If a stat is missing, you should assign its default values.
    Default values are as follows: health 250, damage 45, and armor 10.
    Missing stat will be marked by null.
    The input is in the following format "{type} {name} {damage} {health} {armor}".
    Any of the integers may be assigned a null value.
    See the examples below for a better understanding of your task.
    If the same dragon is added a second time, the new stats should overwrite the previous ones.
    Two dragons are considered equal if they match by both name and type.
Input
    • On the first line, you are given the number N - the number of dragons to follow.
    • On the next N lines, you are given input in the format described above.
    There will be a single space separating each element.
Output
    • Print the aggregated data on the console.
    • For each type print average stats of its dragons in format "{Type}::({damage}/{health}/{armor})".
    • Damage, health, and armor should be rounded to two digits after the decimal separator.
    • For each dragon, print its stats in the format:
    "-{Name} -> damage: {damage}, health: {health}, armor: {armor}"
Constraints
    • N is in the range [1…100].
    • The dragon type and name are one word only, starting with a capital letter.
    • Damage, health, and armor are integers in the range [0 … 100000] or null.
Examples:
    5
    Red Bazgargal 100 2500 25
    Black Dargonax 200 3500 18
    Red Obsidion 220 2200 35
    Blue Kerizsa 60 2100 20
    Blue Algordox 65 1800 50
    ->
    Red::(160.00/2350.00/30.00)
    -Bazgargal -> damage: 100, health: 2500, armor: 25
    -Obsidion -> damage: 220, health: 2200, armor: 35
    Black::(200.00/3500.00/18.00)
    -Dargonax -> damage: 200, health: 3500, armor: 18
    Blue::(62.50/1950.00/35.00)
    -Algordox -> damage: 65, health: 1800, armor: 50
    -Kerizsa -> damage: 60, health: 2100, armor: 20

    4
    Gold Zzazx null 1000 10
    Gold Traxx 500 null 0
    Gold Xaarxx 250 1000 null
    Gold Ardrax 100 1055 50
    ->
    Gold::(223.75/826.25/17.50)
    -Ardrax -> damage: 100, health: 1055, armor: 50
    -Traxx -> damage: 500, health: 250, armor: 0
    -Xaarxx -> damage: 250, health: 1000, armor: 10
    -Zzazx -> damage: 45, health: 1000, armor: 10
 */
package AssociativeArraysLambdaStreamAPI.MoreExercises;

import java.util.*;
import java.util.stream.Collectors;

public class DragonArmy {
    static Scanner scanner = new Scanner(System.in);

    private static class Dragon {
        private final String type;
        private final String name;
        private final int damage;
        private final int health;
        private final int armor;

        public Dragon(String type, String name, int damage, int health, int armor) {
            this.type = type;
            this.name = name;
            this.damage = damage;
            this.health = health;
            this.armor = armor;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public int getDamage() {
            return damage;
        }

        public int getHealth() {
            return health;
        }

        public int getArmor() {
            return armor;
        }
    }

    private static final List<Dragon> dragons = new ArrayList<>();

    public static void main(String[] args) {
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String type = input[0];
            String name = input[1];
            int damage = 0;
            int health = 0;
            int armor = 0;

            if (input[2].equals("null")) {
                damage = 45;
            } else {
                damage = Integer.parseInt(input[2]);
            }

            if (input[3].equals("null")) {
                health = 250;
            } else {
                health = Integer.parseInt(input[3]);
            }

            if (input[4].equals("null")) {
                armor = 10;
            } else {
                armor = Integer.parseInt(input[4]);
            }

            Dragon newDragon = new Dragon(type, name, damage, health, armor);

            for (Dragon dragon : dragons) {
                if (dragon.getName().equals(newDragon.getName()) &&
                        dragon.getType().equals(newDragon.getType())) {
                    dragons.remove(dragon);
                    break;
                }
            }

            dragons.add(newDragon);
        }

        dragons.stream()
                .collect(Collectors.groupingBy(Dragon::getType))
                .forEach((type, dragons) -> {
                    double averageDamage = dragons.stream()
                            .mapToInt(Dragon::getDamage)
                            .average()
                            .orElse(0);
                    double averageHealth = dragons.stream()
                            .mapToInt(Dragon::getHealth)
                            .average()
                            .orElse(0);
                    double averageArmor = dragons.stream()
                            .mapToInt(Dragon::getArmor)
                            .average()
                            .orElse(0);

                    System.out.printf("%s::(%.2f/%.2f/%.2f)%n", type, averageDamage, averageHealth, averageArmor);
                    dragons.stream()
                            .sorted(Comparator.comparing(Dragon::getName))
                            .forEach(dragon -> System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n",
                                    dragon.getName(), dragon.getDamage(), dragon.getHealth(), dragon.getArmor()));
                });
    }
}
