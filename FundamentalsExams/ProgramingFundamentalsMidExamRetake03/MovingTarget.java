/*
Условие:
    You are at the shooting gallery again,
    and you need a program that helps you keep track of moving targets.
    On the first line, you will receive a sequence of targets with their integer values,
    split by a single space.
    Then, you will start receiving commands for manipulating the targets until the "End" command.
    The commands are the following:
    • "Shoot {index} {power}"
        ◦ Shoot the target at the index if it exists by reducing its value by the given power
        (integer value).
        ◦ Remove the target if it is shot. A target is considered shot when its value reaches 0.
    • "Add {index} {value}"
        ◦ Insert a target with the received value at the received index if it exists.
        ◦ If not, print: "Invalid placement!"
    • "Strike {index} {radius}"
        ◦ Remove the target at the given index and the ones before and after it depending on the radius.
        ◦ If any of the indices in the range is invalid, print: "Strike missed!" and skip this command.
    Example:  "Strike 2 2"
                {radius}
                {radius}
                {strikeIndex}
                {radius}
                {radius}
    • "End"
        ◦ Print the sequence with targets in the following format and end the program:
    "{target1}|{target2}…|{targetn}"
Input / Constraints:
    • On the first line, you will receive the sequence of targets – integer values [1-10000].
    • On the following lines, until the "End" will be receiving the command described above – strings.
    • There will never be a case when the "Strike" command would empty the whole sequence.
Output:
    • Print the appropriate message in case of any command if necessary.
    • In the end, print the sequence of targets in the format described above.
Examples:
    52 74 23 44 96 110
    Shoot 5 10
    Shoot 1 80
    Strike 2 1
    Add 22 3
    End
    ->
    Invalid placement!
    52|100
        The first command is "Shoot", so we reduce the target on index 5, which is valid,
        with the given power – 10.
        Then we receive the same command, but we need to reduce the target on the 1st index,
        with power 80. The value of this target is 74, so it is considered shot, and we remove it.
        Then we receive the "Strike" command on the 2nd index,
        and we need to check if the range with radius 1 is valid:
        52 23 44 96 100
        And it is, so we remove the targets.
        At last, we receive the "Add" command, but the index is invalid,
        so we print the appropriate message, and in the end, we have the following result:
        52|100
    1 2 3 4 5
    Strike 0 1
    End
    ->
    Strike missed!
    1|2|3|4|5
*/
package FundamentalsExams.ProgramingFundamentalsMidExamRetake03;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class MovingTarget {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        List<Integer> targets = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String command = setValue();

        while (!command.equals("End")) {
            String[] commandParts = command.split("\\s+");
            int index = Integer.parseInt(commandParts[1]);

            switch (commandParts[0]) {
                case "Shoot":
                    int power = Integer.parseInt(commandParts[2]);
                    shoot(index, power, targets);
                    break;
                case "Add":
                    int value = Integer.parseInt(commandParts[2]);
                    add(index, value, targets);
                    break;
                case "Strike":
                    int radius = Integer.parseInt(commandParts[2]);
                    strike(index, radius, targets);
                    break;
                default:
                    out.println("Error! Bad command. Try again!");
                    break;
            }

            command = setValue();
        }

        printList(targets);
    }

    private static <T> void printList(List<T> targets) {
        System.out.println(
                targets.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("|"))
        );
    }

    private static boolean isInRange(int index, List<Integer> targets) {
        return index >= 0 && index < targets.size();
    }

    private static void strike(int index, int radius, List<Integer> targets) {
        if (isInRange(index, targets)) {
            if (index - radius >= 0 && index + radius < targets.size()) {
                int targetsRemoved = radius * 2 + 1;
                for (int i = 1; i <= targetsRemoved; i++) {
                    targets.remove(index - radius);
                }
            } else {
                out.println("Strike missed!");
            }
        }
    }

    private static void add(int index, int value, List<Integer> targets) {
        if (isInRange(index, targets)) {
            targets.add(index, value);
        } else {
            out.println("Invalid placement!");
        }
    }

    private static void shoot(int index, int power, List<Integer> targets) {
        if (isInRange(index, targets)) {
            int damage = targets.get(index) - power;

            targets.set(index, damage);

            if (targets.get(index) <= 0) {
                targets.remove(index);
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