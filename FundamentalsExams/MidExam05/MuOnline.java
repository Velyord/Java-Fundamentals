/*
Task:
    You have initial health 100 and initial bitcoins 0.
    You will be given a string representing the dungeon's rooms.
    Each room is separated with '|' (vertical bar): "room1|room2|room3…"
    Each room contains a command and a number, separated by space. The command can be:
    • "potion"
        ◦ You are healed with the number in the second part.
        But your health cannot exceed your initial health (100).
        ◦ First print: "You healed for {amount} hp."
        ◦ After that, print your current health: "Current health: {health} hp."
    • "chest"
        ◦ You've found some bitcoins, the number in the second part.
        ◦ Print: "You found {amount} bitcoins."
    • In any other case, you are facing a monster, which you will fight.
    The second part of the room contains the attack of the monster.
    You should remove the monster's attack from your health.
        ◦ If you are not dead (health <= 0), you've slain the monster, and you should print:
        "You slayed {monster}."
        ◦ If you've died, print "You died! Killed by {monster}." and your quest is over.
        Print the best room you've manage to reach: "Best room: {room}"
    If you managed to go through all the rooms in the dungeon, print on the following three lines:
    "You've made it!"
    "Bitcoins: {bitcoins}"
    "Health: {health}"
Input / Constraints:
    You receive a string representing the dungeon's rooms, separated with '|' (vertical bar):
    "room1|room2|room3…".
Output:
    Print the corresponding messages described above.
Examples:
    rat 10|bat 20|potion 10|rat 10|chest 100|boss 70|chest 1000
    ->
    You slayed rat.
    You slayed bat.
    You healed for 10 hp.
    Current health: 80 hp.
    You slayed rat.
    You found 100 bitcoins.
    You died! Killed by boss.
    Best room: 6

    cat 10|potion 30|orc 10|chest 10|snake 25|chest 110
    ->
    You slayed cat.
    You healed for 10 hp.
    Current health: 100 hp.
    You slayed orc.
    You found 10 bitcoins.
    You slayed snake.
    You found 110 bitcoins.
    You've made it!
    Bitcoins: 120
    Health: 65
 */
package FundamentalsExams.MidExam05;

import java.util.Scanner;

public class MuOnline {
    private static final Scanner scanner = new Scanner(System.in);
    private static int health = 100;
    private static int bitcoins = 0;
    private static int roomCounter = 0;
    private static boolean isDead = false;

    public static void main(String[] args) {
        String[] rooms = scanner.nextLine().split("\\|");

        goThroughRooms(rooms);

        if (!isDead) {
            System.out.printf("You've made it!\nBitcoins: %d\nHealth: %d\n", bitcoins, health);
        }
    }

    private static void goThroughRooms(String[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            roomCounter++;
            String currentRoom = rooms[i];
            String[] roomInfo = currentRoom.split(" ");
            String entity = roomInfo[0];
            int value = Integer.parseInt(roomInfo[1]);

            switch (entity) {
                case "potion":
                    heal(value);
                    break;
                case "chest":
                    loot(value);
                    break;
                default:
                    fight(entity, value);
                    break;
            }

            if (isDead) {
                break;
            }
        }
    }

    private static void fight(String entity, int value) {
        health -= value;

        if (health > 0) {
            System.out.printf("You slayed %s.%n", entity);
        } else {
            System.out.printf("You died! Killed by %s.%n", entity);
            System.out.printf("Best room: %d%n", roomCounter);
            isDead = true;
        }
    }

    private static void loot(int value) {
        bitcoins += value;
        System.out.printf("You found %d bitcoins.%n", value);
    }

    private static void heal(int value) {
        int healthBefore = health;
        health += value;

        if (health > 100) {
            health = 100;
        }

        System.out.printf("You healed for %d hp.%n", health - healthBefore);
        System.out.printf("Current health: %d hp.%n", health);
    }
}
