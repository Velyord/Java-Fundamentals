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
