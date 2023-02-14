/*
Условие:
    Read an array from the console and sum only the even numbers.
Examples:
    1 2 3 4 5 6
    ->
    12

    3 5 7 9
    ->
    0

    2 4 6 8 10
    ->
    30
*/
package Arrays.Lab;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class SumEvenNumbers {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int[] numbers = Arrays
                .stream(setValue().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        displaySumOfEvenNumbers(numbers);
    }

    private static void displaySumOfEvenNumbers(int[] numbers) {
        int sum = 0;

        for(int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                sum += numbers[i];
            }
        }

        out.println(sum);
    }

    // метод за въвеждане на число в дадени граници
    @SuppressWarnings("unchecked")

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
            out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);

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
                out.print("Моля въведете един от следните избори: \n| ");

                // завърта се цикъл, който да покаже на потребителя, кои са възможните опции
                for (String requiredString : requiredStrings) {
                    out.print(requiredString + " | "); // разделител
                } out.println(); // нов ред

                stringCount--; // не се брои сгрешения низ.

                return false;
            }
        }

        return true;
    }
}