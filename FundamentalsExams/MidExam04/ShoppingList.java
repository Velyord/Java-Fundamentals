/*
Task:
    It's the end of the week, and it is time for you to go shopping,
    so you need to create a shopping list first.
Input
    You will receive an initial list with groceries separated by an exclamation mark "!".
    After that, you will be receiving 4 types of commands until you receive "Go Shopping!".
    • "Urgent {item}" - add the item at the start of the list.
    If the item already exists, skip this command.
    • "Unnecessary {item}" - remove the item with the given name, only if it exists in the list.
    Otherwise, skip this command.
    • "Correct {oldItem} {newItem}" - if the item with the given old name exists,
    change its name with the new one. Otherwise, skip this command.
    • "Rearrange {item}" - if the grocery exists in the list,
    remove it from its current position and add it at the end of the list. Otherwise, skip this command.
Constraints
    • There won't be any duplicate items in the initial list
Output
    • Print the list with all the groceries, joined by ", ":
    "{firstGrocery}, {secondGrocery}, … {nthGrocery}"
Examples:
    Tomatoes!Potatoes!Bread
    Unnecessary Milk
    Urgent Tomatoes
    Go Shopping!
    ->
    Tomatoes, Potatoes, Bread

    Milk!Pepper!Salt!Water!Banana
    Urgent Salt
    Unnecessary Grapes
    Correct Pepper Onion
    Rearrange Grapes
    Correct Tomatoes Potatoes
    Go Shopping!
    ->
    Milk, Onion, Salt, Water, Banana
 */
package FundamentalsExams.MidExam04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShoppingList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("!");
        List<String> shoppingList = new ArrayList<>(Arrays.asList(input));
        String command = scanner.nextLine();

        while (!command.equals("Go Shopping!")) {
            String[] tokens = command.split(" ");
            String action = tokens[0];
            String item = tokens[1];

            switch (action) {
                case "Urgent":
                    if (!shoppingList.contains(item)) {
                        shoppingList.add(0, item);
                    }
                    break;
                case "Unnecessary":
                    shoppingList.remove(item);
                    break;
                case "Correct":
                    String newItem = tokens[2];

                    if (shoppingList.contains(item)) {
                        int index = shoppingList.indexOf(item);
                        shoppingList.set(index, newItem);
                    }
                    break;
                case "Rearrange":
                    if (shoppingList.contains(item)) {
                        shoppingList.remove(item);
                        shoppingList.add(item);
                    }
                    break;
            }

            command = scanner.nextLine();
        }

        System.out.println(String.join(", ", shoppingList));
    }
}
