/*
Task:
    You will be given numbers (a list of integers) on the first input line.
    Until you receive "End" you will be given operations you must apply on the list.
    The possible commands are:
    • Add {number} - add number at the end
    • Insert {number} {index} - insert number at given index
    • Remove {index} - remove that index
    • Shift left {count} - first number becomes last 'count' times
    • Shift right {count} - last number becomes first 'count' times
    Note: The index given may be outside the array's bounds. In that case, print "Invalid index".
Examples:
    1 23 29 18 43 21 20
    Add 5
    Remove 5
    Shift left 3
    Shift left 1
    End
    ->
    43 20 5 1 23 29 18

    5 12 42 95 32 1
    Insert  3 0
    Remove 10
    Insert 8 6
    Shift right 1
    Shift left 2
    End
    ->
    Invalid index
    5 12 42 95 32 8 1 3
*/
package Lists.Exercise;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class ListOperations {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        List<Integer> numberList = Arrays
                .stream(setValue()
                        .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        executeCommands(numberList);
        numberList.forEach(number -> out.print(number + " "));
    }

    private static void executeCommands(List<Integer> numberList) {
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] commandParts = command.split(" ");
            String manipulation = commandParts[0];

            switch (manipulation) {
                case "Add":
                    int numberToAdd = Integer.parseInt(commandParts[1]);

                    numberList.add(numberToAdd);
                    break;
                case "Insert":
                    int numberToInsert = Integer.parseInt(commandParts[1]);
                    int indexToInsert = Integer.parseInt(commandParts[2]);

                    insert(numberToInsert, indexToInsert, numberList);
                    break;
                case "Remove":
                    int indexToRemove = Integer.parseInt(commandParts[1]);

                    remove(indexToRemove, numberList);
                    break;
                case "Shift":
                    String direction = commandParts[1];
                    int shiftCount = Integer.parseInt(commandParts[2]);

                    shift(numberList, direction, shiftCount);
                    break;
            }

            command = scanner.nextLine();
        }
    }

    private static void insert(int number, int index, List<Integer> list) {
        if (index < 0 || index >= list.size()) {
            out.println("Invalid index");
        } else {
            list.add(index, number);
        }
    }

    private static void remove(int index, List<Integer> list) {
        if (index < 0 || index >= list.size()) {
            out.println("Invalid index");
        } else {
            list.remove(index);
        }
    }

    private static void shift(List<Integer> list, String direction, int shiftCount) {
        int firstNumberIndex = 0;
        int lastNumberIndex = list.size() - 1;

        for (int i = 1; i <= shiftCount; i++) {
            int firstNumber = list.get(firstNumberIndex);
            int lastNumber = list.get(lastNumberIndex);

            if (direction.equals("left")) {
                list.remove(firstNumberIndex);
                list.add(firstNumber);
            } else if (direction.equals("right")) {
                list.remove(lastNumberIndex);
                list.add(0, lastNumber);
            }
        }
    }

    static int stringCount = 0; // при въвеждане на низ, броячът нараства

    // метод за откриване на грешни в низ от потребителя
    private static String setValue() {
        String value = scanner.nextLine();

        /* ако има забранени символи или не следва задените шаблони,
        низът на потребителя не се приема и трябва да се въведе нов */
        if (!hasValidChars(value) || !doesFollowTemplate(value)) {
            return setValue();
        } else {
            return value;
        }
    }

    // хващане на специални/забранени символи
    private static <T> boolean hasValidChars(T value) {
        // !#$%&'()*+,./:;<=>?@[]^_`{|}
        // 0123456789
        // abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; // може да се променят забранените символи
        boolean isSpecialChar = false;
        char specialChar = ' ';

        // Търсене на забранен символ чрез сравняване на входните данни с низът със забранени символи
        for (int i = 0; i < ((String) value).length(); i++) {
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i); // открит забранен символ
                break;
            }
        }

        // При грешка се показва на потребителя, кой от въведените му символи е забранен
        if (isSpecialChar) {
            if (specialChar == ' ') {
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            } else {
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            }

            return false;
        }

        return true;
    }

    // подтикване на потребителя да въвежда предварително зададени низове.
    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};

        // тук се нагласят шаблоните на низовете, ако имат такива.
        if (stringCount == 1) {
            requiredStrings = new String[]{}; // на първия низ. Празно ако няма такъв.
        } else if (stringCount == 2) {
            requiredStrings = new String[]{}; // на втория низ. Празно ако няма такъв.
        } else { // могат да се добавят и още шаблони преди else. Последния шаблон стои празен.
            requiredStrings = new String[]{};
        }

        // ако е зададен шаблон се изпълнява следния код.
        if (requiredStrings.length != 0) {
            List<String> requiredList = List.of(requiredStrings); // създава се списък със задължителни входни данни

            if (!requiredList.contains(value.toString())) { // ако се въведе нещо, различно от зададеното в шаблона
                out.println("Моля въведете един от следните избори:");

                // завърта се цикъл, който да покаже на потребителя, кои са възможните опции
                out.println(String.join(" | ", requiredStrings)); // разделител

                stringCount--; // не се брои сгрешения низ.

                return false;
            }
        }

        return true;
    }
}
