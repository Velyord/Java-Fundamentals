/*
Task:
    The pirates encounter a huge Man-O-War at sea.
    Create a program that tracks the battle and either chooses a winner or prints a stalemate.
    On the first line, you will receive the status of the pirate ship,
    which is a string representing integer sections separated by ">".
    On the second line, you will receive the same type of status, but for the warship:
    "{section1}>{section2}>{section3}… {sectionn}"
    On the third line, you will receive the maximum health capacity a section of the ship can reach.
    The following lines represent commands until "Retire":
    • "Fire {index} {damage}" - the pirate ship attacks the warship with the given damage at that section.
    Check if the index is valid and if not, skip the command.
    If the section breaks (health <= 0) the warship sinks, print the following and stop the program:
    "You won! The enemy ship has sunken."
    • "Defend {startIndex} {endIndex} {damage}" -
    the warship attacks the pirate ship with the given damage at that range (indexes are inclusive).
    Check if both indexes are valid and if not, skip the command.
    If the section breaks (health <= 0) the pirate ship sinks, print the following and stop the program:
    "You lost! The pirate ship has sunken."
    • "Repair {index} {health}" - the crew repairs a section of the pirate ship with the given health.
    Check if the index is valid and if not, skip the command.
    The health of the section cannot exceed the maximum health capacity.
    • "Status" - prints the count of all sections of the pirate ship that need repair soon,
    which are all sections that are lower than 20% of the maximum health capacity. Print the following:
    "{count} sections need repair."
    In the end, if a stalemate occurs, print the status of both ships,
    which is the sum of their individual sections, in the following format:
    "Pirate ship status: {pirateShipSum}
    Warship status: {warshipSum}"
Input:
    • On the 1st line, you are going to receive the status of the pirate ship (integers separated by '>')
    • On the 2nd line, you are going to receive the status of the warship
    • On the 3rd line, you will receive the maximum health a section of a ship can reach.
    • On the following lines, until "Retire", you will be receiving commands.
Output:
    • Print the output in the format described above.
Constraints:
    • The section numbers will be integers in the range [1….1000]
    • The indexes will be integers [-200….200]
    • The damage will be an integer in the range [1….1000]
    • The health will be an integer in the range [1….1000]
Examples :
    12>13>11>20>66
    12>22>33>44>55>32>18
    70
    Fire 2 11
    Fire 8 100
    Defend 3 6 11
    Defend 0 3 5
    Repair 1 33
    Status
    Retire
    ->
    2 sections need repair.
    Pirate ship status: 135
    Warship status: 205
        First, we receive the command "Fire 2 11", and damage the warship at section index 2,
        which is currently 33, and after reduction, the status of the warship is the following:
        12 22 22 44 55 32 18
        The second and third commands have invalid indexes, so we skip them.
        The fourth command, "Defend 0 3 5" damages 4 sections of the pirate ship with 5,
        which results in the following states:
        7 8 6 15 66
        The fifth command, "Repair 1 33" repairs the pirate ship section and adds 33 health
        to the current 8, which results in 41
        Only 2 sections of the pirate ship (7 and 6) need repair soon.
        In the end, there is a stalemate, so we print both ship statuses (sum of all sections).
    2>3>4>5>2
    6>7>8>9>10>11
    20
    Status
    Fire 2 3
    Defend 0 4 11
    Repair 3 18
    Retire
    ->
    3 sections need repair.
    You lost! The pirate ship has sunken.
 */
package FundamentalsExams.MidExamRetake06;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManOWar {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean gameOver = false;

    public static void main(String[] args) {
        int[] pirateShip = Arrays.stream(scanner.nextLine().split(">")).mapToInt(Integer::parseInt).toArray();
        int[] warShip = Arrays.stream(scanner.nextLine().split(">")).mapToInt(Integer::parseInt).toArray();
        int maxHealth = Integer.parseInt(scanner.nextLine());

        executeCommands(pirateShip, warShip, maxHealth);
        printResult(pirateShip, warShip);
    }

    private static void printResult(int[] pirateShip, int[] warShip) {
        if (!gameOver) {
            int pirateShipStatus = Arrays.stream(pirateShip).sum();
            int warShipStatus = Arrays.stream(warShip).sum();

            System.out.println("Pirate ship status: " + pirateShipStatus);
            System.out.println("Warship status: " + warShipStatus);
        }
    }

    private static void executeCommands(int[] pirateShip, int[] warShip, int maxHealth) {
        String input = scanner.nextLine();

        while (!input.equals("Retire")) {
            String[] command = input.split(" ");

            switch (command[0]) {
                case "Fire":
                    int index = Integer.parseInt(command[1]);
                    int damage = Integer.parseInt(command[2]);

                    fire(warShip, index, damage);

                    if (gameOver) return;

                    break;
                case "Defend":
                    int startIndex = Integer.parseInt(command[1]);
                    int endIndex = Integer.parseInt(command[2]);
                    damage = Integer.parseInt(command[3]);

                    defend(pirateShip, startIndex, endIndex, damage);

                    if (gameOver) return;

                    break;
                case "Repair":
                    index = Integer.parseInt(command[1]);
                    int health = Integer.parseInt(command[2]);
                    repair(pirateShip, maxHealth, index, health);
                    break;
                case "Status":
                    displayStatus(pirateShip, maxHealth);
                    break;
            }

            input = scanner.nextLine();
        }
    }

    private static void fire(int[] warShip, int index, int damage) {
        if (index >= 0 && index < warShip.length) {
            warShip[index] = warShip[index] - damage;

            if (warShip[index] <= 0) {
                System.out.println("You won! The enemy ship has sunken.");

                gameOver = true;
            }
        }
    }

    private static void defend(int[] pirateShip, int startIndex, int endIndex, int damage) {
        if (startIndex >= 0 && startIndex < pirateShip.length && endIndex >= 0 && endIndex < pirateShip.length) {
            for (int i = startIndex; i <= endIndex; i++) {
                pirateShip[i] = pirateShip[i] - damage;

                if (pirateShip[i] <= 0) {
                    System.out.println("You lost! The pirate ship has sunken.");

                    gameOver = true;
                    break;
                }
            }
        }
    }

    private static void repair(int[] pirateShip, int maxHealth, int index, int health) {
        if (index >= 0 && index < pirateShip.length) {
            pirateShip[index] = pirateShip[index] + health;

            if (pirateShip[index] > maxHealth) {
                pirateShip[index] = maxHealth;
            }
        }
    }

    private static void displayStatus(int[] pirateShip, int maxHealth) {
        int count = 0;

        for (int section : pirateShip) {
            if (section < maxHealth * 0.2) {
                count++;
            }
        }

        System.out.println(count + " sections need repair.");
    }
}
