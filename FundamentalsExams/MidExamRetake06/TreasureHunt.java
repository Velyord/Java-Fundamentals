/*
Task:
    The pirates need to carry a treasure chest safely back to the ship, looting along the way.
    Create a program that manages the state of the treasure chest along the way.
    On the first line, you will receive the initial loot of the treasure chest,
    which is a string of items separated by a "|".
    "{loot1}|{loot2}|{loot3} … {lootn}"
    The following lines represent commands until "Yohoho!" which ends the treasure hunt:
    • "Loot {item1} {item2}…{itemn}":
        ◦ Pick up treasure loot along the way. Insert the items at the beginning of the chest.
        ◦ If an item is already contained, don't insert it.
    • "Drop {index}":
        ◦ Remove the loot at the given position and add it at the end of the treasure chest.
        ◦ If the index is invalid, skip the command.
    • "Steal {count}":
        ◦ Someone steals the last count loot items.
        If there are fewer items than the given count, remove as much as there are.
        ◦ Print the stolen items separated by ", ":
    "{item1}, {item2}, {item3} … {itemn}"
    In the end, output the average treasure gain,
    which is the sum of all treasure items length divided by the count of all items inside the chest
    formatted to the second decimal point:
    "Average treasure gain: {averageGain} pirate credits."
    If the chest is empty, print the following message:
    "Failed treasure hunt."
Input:
    • On the 1st line, you are going to receive the initial treasure chest (loot separated by "|")
    • On the following lines, until "Yohoho!", you will be receiving commands.
Output:
    • Print the output in the format described above.
Constraints:
    • The loot items will be strings containing any ASCII code.
    • The indexes will be integers in the range [-200…200]
    • The count will be an integer in the range [1….100]
Examples:
    Gold|Silver|Bronze|Medallion|Cup
    Loot Wood Gold Coins
    Loot Silver Pistol
    Drop 3
    Steal 3
    Yohoho!
    ->
    Medallion, Cup, Gold
    Average treasure gain: 5.40 pirate credits.
        The first command "Loot Wood Gold Coins" adds Wood and Coins
        to the chest but omits Gold since it is already contained.
        The chest now has the following items:
        Coins Wood Gold Silver Bronze Medallion Cup
        The second command adds only Pistol to the chest
        The third command "Drop 3" removes the Gold from the chest, but immediately adds it at the end:
        Pistol Coins Wood Silver Bronze Medallion Cup Gold
        The fourth command "Steal 3" removes the last 3 items Medallion, Cup,
        Gold from the chest and prints them.
        In the end calculate the average treasure gain which is the sum of all items length Pistol(6) +
        Coins(5) + Wood(4)  + Silver(6) + Bronze(6) = 27 and divide it by the count 27 / 5 =
        5.4 and format it to the second decimal point.
    Diamonds|Silver|Shotgun|Gold
    Loot Silver Medals Coal
    Drop -1
    Drop 1
    Steal 6
    Yohoho!
    ->
    Coal, Diamonds, Silver, Shotgun, Gold, Medals
    Failed treasure hunt.
 */
package FundamentalsExams.MidExamRetake06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TreasureHunt {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> chest = Arrays
                .stream(scanner.nextLine().split("\\|"))
                .collect(Collectors.toList());

        executeCommands(chest);
        printResult(chest);
    }

    private static void printResult(List<String> chest) {
        if (chest.isEmpty()) {
            System.out.println("Failed treasure hunt.");
        } else {
            double sum = 0;

            for (String item : chest) {
                sum += item.length();
            }

            double averageGain = sum / chest.size();

            System.out.printf("Average treasure gain: %.2f pirate credits.", averageGain);
        }
    }

    private static void executeCommands(List<String> chest) {
        String input = scanner.nextLine();

        while (!input.equals("Yohoho!")) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            switch (command) {
                case "Loot":
                    for (int i = 1; i < tokens.length; i++) {
                        if (!chest.contains(tokens[i])) {
                            chest.add(0, tokens[i]);
                        }
                    }
                    break;
                case "Drop":
                    int index = Integer.parseInt(tokens[1]);

                    if (index >= 0 && index < chest.size()) {
                        String item = chest.get(index);

                        chest.remove(index);
                        chest.add(item);
                    }
                    break;
                case "Steal":
                    int count = Integer.parseInt(tokens[1]);
                    if (count >= chest.size()) {
                        System.out.println(String.join(", ", chest));

                        chest.clear();
                    } else {
                        List<String> stolenItems = new ArrayList<>();

                        for (int i = chest.size() - count; i < chest.size(); i++) {
                            stolenItems.add(chest.get(i));
                        }

                        System.out.println(String.join(", ", stolenItems));
                        chest.removeAll(stolenItems);
                    }
                    break;
            }

            input = scanner.nextLine();
        }
    }
}
