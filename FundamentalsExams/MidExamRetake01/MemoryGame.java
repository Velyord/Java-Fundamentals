/*
Условие:
    Write a program that recreates the Memory game.
    On the first line, you will receive a sequence of elements.
    Each element in the sequence will have a twin. Until the player receives "end" from the console,
    you will receive strings with two integers separated by a space,
    representing the indexes of elements in the sequence.
    If the player tries to cheat and enters two equal indexes
    or indexes which are out of bounds of the sequence,
    you should add two matching elements at the middle of the sequence in the following format:
    "-{number of moves until now}a"
    Then print this message on the console:
    "Invalid input! Adding additional elements to the board"
Input:
    • On the first line, you will receive a sequence of elements
    • On the following lines, you will receive integers until the command "end"
Output:
    • Every time the player hit two matching elements, you should remove them from the sequence and print on the console the following message:
    "Congrats! You have found matching elements - ${element}!"
    • If the player hit two different elements, you should print on the console the following message:
    "Try again!"
    • If the player hit all matching elements before he receives "end" from the console, you should print on the console the following message:
    "You have won in {number of moves until now} turns!"
    • If the player receives "end" before he hits all matching elements, you should print on the console the following message:
    "Sorry you lose :(
    {the current sequence's state}"
Constraints:
    • All elements in the sequence will always have a matching element.
Examples:
    1 1 2 2 3 3 4 4 5 5
    1 0
    -1 0
    1 0
    1 0
    1 0
    end
    ->
    Congrats! You have found matching elements - 1!
    Invalid input! Adding additional elements to the board
    Congrats! You have found matching elements - 2!
    Congrats! You have found matching elements - 3!
    Congrats! You have found matching elements - -2a!
    Sorry you lose :(
    4 4 5 5
        1)
        1 0
        1 1 2 2 3 3 4 4 5 5 –> 1 = 1, equal elements, so remove them. Moves: 1
        2)
        -1 0
        -1 is invalid index so we add additional elements
        2 2 3 3 -2а -2а 4 4 5 5, Moves: 2
        3)
        1 0
        2 2 3 3 -2а -2а 4 4 5 5 -> 2 = 2, equal elements, so remove them. Moves: 3
        4)
        1 0
        3 3 -2а -2а 4 4 5 5 -> 3 = 3, equal elements, so remove them. Moves: 4
        5)
        1 0
        -2а -2а 4 4 5 5 -> -2а = -2а, equal elements, so remove them. Moves: 5
        6)
        You receive the end command.
        There are still elements in the sequence, so the player loses the game.
        Final state - 4 4 5 5
    a 2 4 a 2 4
    0 3
    0 2
    0 1
    0 1
    end
    ->
    Congrats! You have found matching elements - a!
    Congrats! You have found matching elements - 2!
    Congrats! You have found matching elements - 4!
    You have won in 3 turns!

    a 2 4 a 2 4
    4 0
    0 2
    0 1
    0 1
    end
    ->
    Try again!
    Try again!
    Try again!
    Try again!
    Sorry you lose :(
    a 2 4 a 2 4
*/
package FundamentalsExams.MidExamRetake01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.in;
import static java.lang.System.out;

public class MemoryGame {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        ArrayList<String> elements = new ArrayList<>(Arrays.asList(setValue().split(" ")));

        String input = setValue();
        int moves = 0;
        boolean isWon = false;

        while (!input.equals("end")) {
            if (!isWon) {
                moves++;
                List<Integer> guessIndexes = Arrays
                        .stream(input.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                boolean hasCheated = checkForCheats(guessIndexes, elements, moves);

                if (!hasCheated) {
                    checkForMatches(guessIndexes, elements);
                }

                if (elements.isEmpty()) {
                    out.printf("You have won in %d turns!", moves);
                    isWon = true;
                }
            }

            input = setValue();
        }

        if (!isWon) {
            out.println("Sorry you lose :(");

            printList(elements);
        }
    }

    private static void checkForMatches(List<Integer> guessIndexes, List<String> elements) {
        int firstIndex = guessIndexes.get(0);
        int secondIndex = guessIndexes.get(1);

        if (elements.get(firstIndex).equals(elements.get(secondIndex))) {
            String guessedElement = elements.get(firstIndex);


            elements.remove(max(firstIndex, secondIndex));
            elements.remove(min(firstIndex, secondIndex));
            out.printf("Congrats! You have found matching elements - %s!\n", guessedElement);
        } else {
            out.println("Try again!");
        }
    }

    private static boolean checkForCheats(
            List<Integer> guessIndexes,
            List<String> elements,
            int moves
    ) {
        boolean cheating = false;
        int firstIndex = guessIndexes.get(0);
        int secondIndex = guessIndexes.get(1);

        boolean firstIndexOutOfBounds =
                firstIndex < 0 || firstIndex >= elements.size();
        boolean secondIndexOutOfBounds =
                secondIndex < 0 || secondIndex >= elements.size();
        boolean indexesAreEqual =
                firstIndex == secondIndex;

        if (firstIndexOutOfBounds || secondIndexOutOfBounds || indexesAreEqual) {
            cheating = true;
        }

        if (cheating) {
            out.println("Invalid input! Adding additional elements to the board");
            String penalty = "-" + moves + "a";

            elements.add(elements.size() / 2, penalty);
            elements.add(elements.size() / 2, penalty);

            return true;
        }

        return false;
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

    // принтира съдържанието на лист
    private static <T> void printList(List<T> targets) {
        System.out.println(
                targets.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }
}