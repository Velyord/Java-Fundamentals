/*
Task:
    Write a program to sum all adjacent equal numbers in a
    list of decimal numbers, starting from left to right.
    	After two numbers are summed, the obtained result could
    be equal to some of its neighbors and should be summed as
    well (see the examples below).
    	Always sum the leftmost two equal neighbors (if
    several couples of equal neighbors are available).
Examples:
    3 3 6 1
    ->
    12 1
        3 3 6 1  6 6 1  12 1
    8 2 2 4 8 16
    -> 16 8 16
        8 2 2 4 8 16  8 4 4 8 16  8 8 8 16  16 8 16
    5 4 2 1 1 4
    -> 5 8 4
        5 4 2 1 1 4  5 4 2 2 4  5 4 4 4  5 8 4
    0.1 0.1 5 -5
    -> 0.2 5 -5
        0.1 0.1 5 -5  0.2 5 -5
*/
package Lists.Lab;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class SumAdjacentEqualNumbers {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        List<Double> numberList = Arrays
                .stream(setValue()
                .split(" "))
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        sumAdjacentEqualNumbers(numberList);

        for (double number : numberList) {
            DecimalFormat df = new DecimalFormat("0.#");
            out.print(df.format(number) + " ");
        }
    }

    private static void sumAdjacentEqualNumbers(List<Double> numberList) {
        for (int i = 0; i < numberList.size() - 1; i++) {
            if (numberList.get(i).equals(numberList.get(i + 1))) {
                numberList.set(i, numberList.get(i) * 2);
                numberList.remove(i + 1);
                i = -1;
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
        String specialChars = "!#$%&'()*+,/:;<=>?@[]^_`{|}abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; // може да се променят забранените символи
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
