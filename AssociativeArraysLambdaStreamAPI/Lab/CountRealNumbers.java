/*
Task:
    Read a list of real numbers
    and print them in ascending order along with their number of occurrences.
Examples:
    8 2 2 8 2
    ->
    2 -> 3
    8 -> 2

    1 5 1 3
    ->
    1 -> 2
    3 -> 1
    5 -> 1

    -2 0 0 2
    ->
    -2 -> 1
    0 -> 2
    2 -> 1
*/
package AssociativeArraysLambdaStreamAPI.Lab;

import java.text.DecimalFormat;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class CountRealNumbers {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double[] numberArray = Arrays
                .stream(setValue()
                        .split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();

        Map<Double, Integer> numberTreeMap = new TreeMap<>();

        fillTreeMap(numberTreeMap, numberArray);
        printTreeMap(numberTreeMap);
    }

    private static void fillTreeMap(Map<Double, Integer> numberTreeMap, double[] numberArray) {
        for (int i = 0; i < numberArray.length; i++) {
            Double key = numberArray[i];
            if (numberTreeMap.containsKey(key)) {
                int value = numberTreeMap.get(key);
                numberTreeMap.put(key, value + 1);
            } else {
                numberTreeMap.put(key, 1);
            }
        }
    }

    private static void printTreeMap(Map<Double, Integer> numberTreeMap) {
        for (Map.Entry<Double, Integer> number : numberTreeMap.entrySet()) {
            DecimalFormat df = new DecimalFormat("#.#######");
            out.printf("%s -> %s%n", df.format(number.getKey()), number.getValue());
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
