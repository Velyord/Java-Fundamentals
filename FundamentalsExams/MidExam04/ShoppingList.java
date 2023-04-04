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
