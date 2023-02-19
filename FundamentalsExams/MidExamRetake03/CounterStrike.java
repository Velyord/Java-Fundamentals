/*
Условие:
    Write a program that keeps track of every won battle against an enemy.
    You will receive initial energy.
    Afterward, you will start receiving the distance you need to reach an enemy until the
    "End of battle" command is given, or you run out of energy.
    The energy you need for reaching an enemy is equal to the distance you receive.
    Each time you reach an enemy, you win a battle, and your energy is reduced.
    Otherwise, if you don't have enough energy to reach an enemy, end the program and print:
    "Not enough energy! Game ends with {count} won battles and {energy} energy".
    Every third won battle increases your energy with the value of your current count of won battles.
    Upon receiving the "End of battle" command,
    print the count of won battles in the following format:
    "Won battles: {count}. Energy left: {energy}"
Input / Constraints:
    • On the first line, you will receive initial energy – an integer [1-10000].
    • On the following lines, you will be receiving the distance of an enemy – an integer [1-10000]
Output:
    • The description contains the proper output messages for each case and the format they should be printed.
Examples:
100
10
10
10
1
2
3
73
10
->
Not enough energy! Game ends with 7 won battles and 0 energy
    The initial energy is 100. The first distance is 10, so we subtract 10 from 100,
    and we consider this a won battle. We are left with 90 energy. Next distance – 10,
    and 80 energy left.
    Next distance – 10, 3 won battles and 70 energy, but since we have 3 won battles,
    we increase the energy with the current count of won battles, in this case – 3, and it becomes 73.
    The last distance we receive – 10 is unreachable since we have 0 energy,
    so we print the appropriate message, and the program ends.
200
54
14
28
13
End of battle
->
Won battles: 4. Energy left: 94
*/
package FundamentalsExams.MidExamRetake03;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class CounterStrike {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int energy = Integer.parseInt(scanner.nextLine());
        String input = setValue();
        int wonBattles = 0;
        boolean isLost = false;

        while (!input.equals("End of battle")) {
            int distance = Integer.parseInt(input);

            if (energy >= distance) {
                energy -= distance;
                wonBattles++;

                if (wonBattles % 3 == 0) {
                    energy += wonBattles;
                }
            } else {
                out.printf(
                        "Not enough energy! Game ends with %d won battles and %d energy",
                        wonBattles, energy
                );
                isLost = true;
                break;
            }

            input = setValue();
        }

        if (!isLost) {
            out.printf("Won battles: %d. Energy left: %d", wonBattles, energy);
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