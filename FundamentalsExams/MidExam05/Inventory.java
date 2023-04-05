/*
Task:
    As a young traveler, you gather items and craft new items.
Input / Constraints:
    You will receive a journal with some collecting items, separated with a comma and a space (", ").
    After that, until receiving "Craft!" you will be receiving different commands split by " - ":
    • "Collect - {item}" - you should add the given item to your inventory.
    If the item already exists, you should skip this line.
    • "Drop - {item}" - you should remove the item from your inventory if it exists.
    • "Combine Items - {old_item}:{new_item}" - you should check if the old item exists.
    If so, add the new item after the old one. Otherwise, ignore the command.
    • "Renew – {item}" – if the given item exists,
    you should change its position and put it last in your inventory.
Output:
    After receiving "Craft!" print the items in your inventory, separated by ", ".
Examples:
    Iron, Wood, Sword
    Collect - Gold
    Drop - Wood
    Craft!
    ->
    Iron, Sword, Gold

    Iron, Sword
    Drop - Bronze
    Combine Items - Sword:Bow
    Renew - Iron
    Craft!
    ->
    Sword, Bow, Iron
 */
package FundamentalsExams.MidExam05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Inventory {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<String> items = Arrays
                .stream(scanner.nextLine()
                        .split(", "))
                .collect(Collectors.toList());

        executeCommands(items);

        System.out.println(String.join(", ", items));
    }

    private static void executeCommands(List<String> items) {
        String input = scanner.nextLine();

        while (!input.equals("Craft!")) {
            String[] tokens = input.split(" - ");
            String command = tokens[0];
            String item = tokens[1];

            switch (command) {
                case "Collect":
                    if (!items.contains(item)) {
                        items.add(item);
                    }
                    break;
                case "Drop":
                    items.remove(item);
                    break;
                case "Combine Items":
                    String[] itemsToCombine = item.split(":");
                    String oldItem = itemsToCombine[0];
                    String newItem = itemsToCombine[1];

                    if (items.contains(oldItem)) {
                        int index = items.indexOf(oldItem);
                        items.add(index + 1, newItem);
                    }
                    break;
                case "Renew":
                    if (items.contains(item)) {
                        items.remove(item);
                        items.add(item);
                    }
                    break;
            }

            input = scanner.nextLine();
        }
    }
}
