/*
Task:
    Write a program that reads a list of integers.
    Then until you receive "end", you will be given different commands:
    Add {number}: add a number to the end of the list
    Remove {number}: remove a number from the list
    RemoveAt {index}: remove a number at a given index
    Insert {number} {index}: insert a number at a given index
    Note: All the indices will be valid!
    When you receive the "end" command, print the final state of the list (separated by spaces).
Examples:
    4 19 2 53 6 43
    Add 3
    Remove 2
    RemoveAt 1
    Insert 8 3
    end
    ->
    4 53 6 8 43 3

    12 34 100 1 45 2 8
    Add 30
    Remove 12
    Remove 3
    RemoveAt 3
    Insert 2 3
    end
    ->
    34 100 1 2 2 8 30
*/
package Lists.Lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class ListManipulationBasics {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        List<Integer> numberList = Arrays
                .stream(setValue()
                        .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        executeCommands(numberList);
        printList(numberList, " ");
    }

    private static void executeCommands(List<Integer> numberList) {
        String input = setValue();

        while (!input.equals("end")) {
            String[] command = input.split(" ");
            String action = command[0];

            switch (action) {
                case "Add":
                    numberList.add(Integer.parseInt(command[1]));
                    break;
                case "Remove":
                    numberList.remove(Integer.valueOf(command[1]));
                    break;
                case "RemoveAt":
                    numberList.remove(Integer.parseInt(command[1]));
                    break;
                case "Insert":
                    int number = Integer.parseInt(command[1]);
                    int index = Integer.parseInt(command[2]);

                    numberList.add(index, number);
                    break;
                default:
                    break;
            }

            input = setValue();
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
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}"; // може да се променят забранените символи
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

    // принтира съдържанието на лист
    private static <T> void printList(List<T> targets, String delimiter) {
        System.out.println(
                targets.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(delimiter))
        );
    }
}