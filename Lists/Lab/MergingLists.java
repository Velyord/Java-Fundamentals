/*
Task:
    You are going to receive two lists with numbers.
    Create a result list that contains the numbers from both of the lists.
    The first element should be from the first list, the second from the second list, and so on.
    If the length of the two lists is not equal, just add the remaining elements at the end of the list.
Examples:
    3 5 2 43 12 3 54 10 23
    76 5 34 2 4 12
    ->
    3 76 5 5 2 34 43 2 12 4 3 12 54 10 23

    76 5 34 2 4 12
    3 5 2 43 12 3 54 10 23
    ->
    76 3 5 5 34 2 2 43 4 12 12 3 54 10 23
*/
package Lists.Lab;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class MergingLists {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        List<Integer> firstNumberList = Arrays
                .stream(setValue()
                        .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> secondNumberList = Arrays
                .stream(setValue()
                        .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> shuffledNumberList = new ArrayList<>();

        shuffleLists(firstNumberList, secondNumberList, shuffledNumberList);
        printList(shuffledNumberList, " ");
    }

    private static void shuffleLists(List<Integer> firstList, List<Integer> secondList, List<Integer> shuffledList) {
        int iterations = firstList.size() + secondList.size();

        for (int i = 0; i < iterations;) {
            if (firstList.size() > 0) {
                shuffledList.add(firstList.get(0));
                firstList.remove(0);
                i++;
            }
            if (secondList.size() > 0) {
                shuffledList.add(secondList.get(0));
                secondList.remove(0);
                i++;
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

    // принтира съдържанието на лист
    private static <T> void printList(List<T> targets, String delimiter) {
        System.out.println(
                targets.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(delimiter))
        );
    }
}
