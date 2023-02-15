/*
Условие:
    You are given an array with integers.
    Write a program to modify the elements after receiving the following commands:
    • "swap {index1} {index2}" takes two elements and swap their places.
    • "multiply {index1} {index2}" takes the element at the 1st index
    and multiplies it with the element at 2nd index. Save the product at the 1st index.
    • "decrease" decreases all elements in the array with 1.
Input
    On the first input line, you will be given the initial array values separated by a single space.
    On the next lines, you will receive commands until you receive the command "end".
    The commands are as follows:
    • "swap {index1} {index2}"
    • "multiply {index1} {index2}"
    • "decrease"
Output:
    The output should be printed on the console and consist of elements of the modified array –
    separated by a comma and a single space ", ".
Constraints:
    • Elements of the array will be integer numbers in the range [-231...231].
    • The count of the array elements will be in the range [2...100].
    • Indexes will always be in the range of the array.
Examples:
    23 -2 321 87 42 90 -123
    swap 1 3
    swap 3 6
    swap 1 0
    multiply 1 2
    multiply 2 1
    decrease
    end
    ->
    86, 7382, 2369942, -124, 41, 89, -3
        23 -2 321 87 42 90 -123 – initial values
        swap 1(-2) and 3(87) ▼
        23 87 321 -2 42 90 -123
        swap 3(-2) and 6(-123) ▼
        23 87 321 -123 42 90 -2
        swap 1(87) and 0(23) ▼
        87 23 321 -123 42 90 -2
        multiply 1(23) 2(321) = 7383 ▼
        87 7383 321 -123 42 290 -2
        multiply 2(321) 1(7383) = 2369943 ▼
        87 7383 2369943 -123 42 90 -2
        decrease – all - 1 ▼
        86 7383 2369942 -124 41 89 -3
    1 2 3 4
    swap 0 1
    swap 1 2
    swap 2 3
    multiply 1 2
    decrease
    end
    ->
    1, 11, 3, 0
*/
package Arrays.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class ArrayModifier {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int[] numberArray = Arrays
                .stream(setValue().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        String input = setValue();

        while (!input.equals("end")) {
            String[] inputArray = input.split(" ");

            switch (inputArray[0]) {
                case "swap": {
                    int firstIndex = Integer.parseInt(inputArray[1]);
                    int secondIndex = Integer.parseInt(inputArray[2]);
                    swap(firstIndex, secondIndex, numberArray);
                    break;
                }
                case "multiply": {
                    int firstIndex = Integer.parseInt(inputArray[1]);
                    int secondIndex = Integer.parseInt(inputArray[2]);
                    multiply(firstIndex, secondIndex, numberArray);
                    break;
                }
                case "decrease":
                    decreaseBy(1, numberArray);
                    break;
                default:
                    out.println("ERROR!");
                    break;
            }

            input = setValue();
        }

        printArray(numberArray);
    }

    private static void printArray(int[] numberArray) {
        String output = "";

        for (int item : numberArray) {
            output += item + ", ";
        }

        out.println(output.substring(0, output.length()-2));
    }

    private static void decreaseBy(int i, int[] numberArray) {
        for (int index = 0; index < numberArray.length; index++) {
            numberArray[index] -= i;
        }
    }

    private static void multiply(int firstIndex, int secondIndex, int[] numberArray) {
        numberArray[firstIndex] *= numberArray[secondIndex];
    }

    private static void swap(int firstIndex, int secondIndex, int[] numberArray) {
        int temp = numberArray[firstIndex];
        numberArray[firstIndex] = numberArray[secondIndex];
        numberArray[secondIndex] = temp;
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
}