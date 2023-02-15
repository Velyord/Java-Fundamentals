/*
Условие:
    Write a program that determines if an element exists in the array
    such that the sum of the elements on its left is equal to the sum of the elements on its right.
    If there are no elements to the left/right, their sum is considered to be 0.
    Print the index that satisfies the required condition or "no" if there is no such index.
Examples:
    1 2 3 3
    ->
    2
        At a[2] -> left sum = 3, right sum = 3
        a[0] + a[1] = a[3]
    1 2
    ->
    no
        At a[0] -> left sum = 0, right sum = 2
        At a[1] -> left sum = 1, right sum = 0
        No such index exists
    1
    ->
    0
        At a[0] -> left sum = 0, right sum = 0
    1 2 3
    ->
    no
        No such index exists
    10 5 5 99 3 4 2 5 1 1 4
    ->
    3
        At a[3] -> left sum = 20, right sum = 20
        a[0] + a[1] + a[2] = a[4] + a[5] + a[6] + a[7] + a[8] + a[9] + a[10]
*/
package Arrays.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class EqualSums {
    static Scanner scanner = new Scanner(in);
    static int stringCount = 0; // при въвеждане на низ, броячът нараства

    public static void main(String[] args) {
        boolean goalIndexIsFound = false;
        int[] numbers = Arrays
                .stream(setValue().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int currentIndex = 0; currentIndex < numbers.length; currentIndex++) {
            int leftSum = 0;
            int rightSum = 0;

            for (int leftSide = 0; leftSide < currentIndex; leftSide++) {
                leftSum += numbers[leftSide];
            }

            for (int rightSide = currentIndex + 1; rightSide < numbers.length; rightSide++) {
                rightSum += numbers[rightSide];
            }

            if (leftSum == rightSum) {
                out.println(currentIndex);
                goalIndexIsFound = true;
                break;
            }
        }

        if (!goalIndexIsFound) {
            out.println("no");
        }
    }

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
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; // може да се променят забранените символи
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