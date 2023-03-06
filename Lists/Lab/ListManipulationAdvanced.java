/*
Task:
    Now we will implement more complicated list commands.
    Again, read a list, and until you receive "end" read commands:
    Contains {number} – check if the list contains the number.
    If yes, print "Yes", otherwise, print "No such number"
    Print even – print all the numbers that are even separated by a space
    Print odd – print all the numbers that are oddly separated by a space
    Get sum – print the sum of all the numbers
    Filter {condition} {number} – print all the numbers that fulfill that condition.
    The condition will be either '<', '>', ">=", "<="
Examples:
    2 13 43 876 342 23 543
    Contains 100
    Contains 543
    Print even
    Print odd
    Get sum
    Filter >= 43
    Filter < 100
    end
    ->
    No such number
    Yes
    2 876 342
    13 43 23 543
    1842
    43 876 342 543
    2 13 43 23

    12 3 123 546 222 45 7
    Contains 3
    Contains 121
    Print even
    Print odd
    Get sum
    Filter >= 100
    Filter < 45
    end
    ->
    Yes
    No such number
    12 546 222
    3 123 45 7
    958
    123 546 222
    12 3 7
*/
package Lists.Lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class ListManipulationAdvanced {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        List<Integer> numberList = Arrays
                .stream(setValue()
                        .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        executeCommands(numberList);
    }

    private static void executeCommands(List<Integer> numberList) {
        String input = setValue();

        while (!input.equals("end")) {
            String[] command = input.split(" ");
            String action = command[0];

            switch (action) {
                case "Contains":
                    if (numberList.contains(Integer.parseInt(command[1]))) {
                        out.println("Yes");
                    } else {
                        out.println("No such number");
                    } break;
                case "Print":
                    if (command[1].equals("even")) {
                        printEvenNumbersFrom(numberList);
                    } else if (command[1].equals("odd")) {
                        printOddNumbersFrom(numberList);
                    } break;
                case "Get":
                    if (command[1].equals("sum")) {
                        out.println(getSumOf(numberList));
                    } break;
                case "Filter":
                    String condition = command[1];
                    int number = Integer.parseInt(command[2]);

                    filterList(numberList, condition, number);
                    break;
                default:
                    break;
            }

            input = setValue();
        }
    }

    private static void filterList(List<Integer> numberList, String condition, int number) {
        for (int item : numberList) {
            switch (condition) {
                case "<":
                    if (item < number) {
                        out.print(item + " ");
                    }
                    break;
                case ">":
                    if (item > number) {
                        out.print(item + " ");
                    }
                    break;
                case ">=":
                    if (item >= number) {
                        out.print(item + " ");
                    }
                    break;
                case "<=":
                    if (item <= number) {
                        out.print(item + " ");
                    }
                    break;
            }
        }

        out.println();
    }

    private static int getSumOf(List<Integer> numberList) {
        int sum = 0;

        for (int i = 0; i < numberList.size(); i++) {
            int currentNumber = numberList.get(i);

            sum += currentNumber;
        }

        return sum;
    }

    private static void printOddNumbersFrom(List<Integer> numberList) {
        for (int i = 0; i < numberList.size(); i++) {
            int currentNumber = numberList.get(i);

            if (currentNumber % 2 != 0) {
                out.print(currentNumber + " ");
            }
        }

        out.println();
    }

    private static void printEvenNumbersFrom(List<Integer> numberList) {
        for (int i = 0; i < numberList.size(); i++) {
            int currentNumber = numberList.get(i);

            if (currentNumber % 2 == 0) {
                out.print(currentNumber + " ");
            }
        }

        out.println();
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
        String specialChars = "!#$%&'()*+,./:;?@[]^_`{|}"; // може да се променят забранените символи
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
