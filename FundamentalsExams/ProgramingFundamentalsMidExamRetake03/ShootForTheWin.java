/*
Условие:
    Write a program that helps you keep track of your shot targets.
    You will receive a sequence with integers, separated by a single space,
    representing targets and their value.
    Afterward, you will be receiving indices until the "End" command is given,
    and you need to print the targets and the count of shot targets.
    Every time you receive an index, you need to shoot the target on that index, if it is possible.
    Every time you shoot a target, its value becomes -1, and it is considered shot.
    Along with that, you also need to:
    • Reduce all the other targets, which have greater values than your current target,
    with its value.
    • Increase all the other targets, which have less than or equal value to the shot target,
    with its value.
    Keep in mind that you can't shoot a target, which is already shot.
    You also can't increase or reduce a target, which is considered shot.
    When you receive the "End" command, print the targets in their current state
    and the count of shot targets in the following format:
    "Shot targets: {count} -> {target1} {target2}… {targetn}"
Input / Constraints:
    • On the first line of input, you will receive a sequence of integers,
    separated by a single space – the targets sequence.
    • On the following lines, until the "End" command,
    you be receiving integers each on a single line – the index of the target to be shot.
Output:
    • The format of the output is described above in the problem description.
Examples:
    24 50 36 70
    0
    4
    3
    1
    End
    ->
    Shot targets 3 -> -1 -1 130 -1
        First, we shoot the target on index 0.
        It becomes equal to -1, and we start going through the rest of the targets.
        Since 50 is more than 24, we reduce it to 26 and 36 to 12 and 70 to 46.
        The sequence looks like that:
        -1 26 12 46
        The following index is invalid, so we don't do anything. Index 3 is valid,
        and after the operations, our sequence should look like that:
        -1 72 58 -1
        Then we take the first index with value 72, and our sequence looks like that:
        -1 -1 130 -1
        Then we print the result after the "End" command.
    30 30 12 60 54 66
    5
    2
    4
    0
    End
    ->
    Shot targets: 4 -> -1 120 -1 66 -1 -1
*/
package FundamentalsExams.ProgramingFundamentalsMidExamRetake03;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class ShootForTheWin {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int[] targets = Arrays
                .stream(setValue().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        String input = setValue();
        int targetsShot = 0;

        while (!input.equals("End")) {
            int toBeShot = Integer.parseInt(input);

            if (toBeShot >= 0 && toBeShot < targets.length) {
                if (targets[toBeShot] != -1) {
                    int targetPoints = targets[toBeShot];

                    targets[toBeShot] = -1;
                    targetsShot++;

                    for (int i = 0; i < targets.length; i++) {
                        if (targets[i] != -1) {
                            if (targets[i] > targetPoints) {
                                targets[i] -= targetPoints;
                            } else {
                                targets[i] += targetPoints;
                            }
                        }
                    }
                }
            }

            input = setValue();
        }

        displayResult(targetsShot, targets);
    }

    private static void displayResult(int targetsShot, int[] targets) {
        out.printf("Shot targets: %d -> ", targetsShot);
        for (int item : targets) {
            out.print(item + " ");
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
}