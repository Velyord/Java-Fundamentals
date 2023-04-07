/*
Task:
    Every gamer knows what rage-quitting means.
    It’s basically when you’re just not good enough, and you blame everybody else for losing a game.
    You press the CAPS LOCK key on the keyboard
    and flood the chat with gibberish to show your frustration.
    John is a gamer and a bad one at that. He wants to be the most annoying kid on his team,
    so when he rage-quits, he wants something truly spectacular. He asks for your help.
    He’ll give you a series of strings followed by non-negative numbers, e.g., "a3";
    you need to print on the console each string repeated N times;
    convert the letters to uppercase beforehand. In the example, you need to write back "AAA".
    On the output, print first a statistic of the number of unique symbols used
    (the casing of letters is irrelevant, meaning that 'a' and 'A' are the same);
    the format should be "Unique symbols used {0}". Then, print the rage message itself.
    The strings and numbers will not be separated by anything.
    The input will always start with a string, and for each string, there will be a corresponding number.
    The entire input will be given on a single line; John is too lazy to make your job easier.
Input:
    • The input data should be read from the console.
    • It consists of a single line holding a series of string-number sequences.
    • The input data will always be valid and in the format described.
    There is no need to check it explicitly.
Output:
    • The output should be printed on the console. It should consist of exactly two lines.
    • On the first line, print the number of unique symbols used in the message.
    • On the second line, print the resulting rage message itself.
Constraints:
    • The count of string-number pairs will be in the range [1 … 20 000].
    • Each string will contain any character except digits.
    The length of each string will be in the range [1 … 20].
    • The repeat count for each string will be an integer in the range [0 … 20].
    • Allowed working time for your program: 0.3 seconds. Allowed memory: 64MB.
Examples:
    a3
    ->
    Unique symbols used: 1
    AAA
        We have just one string-number pair.
        The symbol is 'a', convert it to uppercase and repeat 3 times: AAA. Only one symbol is used ('A').
    aSd2&5s@1
    ->
    Unique symbols used: 5
    ASDASD&&&&&S@
        "aSd" is converted to "ASD" and repeated twice; "&" is repeated 5 times;
        "s@" is converted to "S@" and repeated once. 5 symbols are used: 'A', 'S', 'D', '&' and '@'.
 */
package RegularExpressions.MoreExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class RageQuit {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String userInput = scanner.nextLine().toUpperCase();
        String rageMessage = getRageMessage(userInput);
        printResult(rageMessage);
    }

    private static void printResult(String rageMessage) {
        int uniqueSymbolsCount = countUniqueSymbols(rageMessage);

        out.println("Unique symbols used: " + uniqueSymbolsCount);
        out.println(rageMessage);
    }

    private static String getRageMessage(String userInput) {
        String regex = "(?<toRepeat>[^0-9]+)(?<times>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        StringBuilder result = new StringBuilder();

        while(matcher.find()) {
            String toRepeat = matcher.group("toRepeat");
            int times = Integer.parseInt(matcher.group("times"));

            result.append(toRepeat.repeat(times));
        }

        return result.toString();
    }

    private static int countUniqueSymbols(String result) {
        List<Character> uniqueSymbols = new ArrayList<>();

        for (int i = 0; i < result.length(); i++) {
            if (!uniqueSymbols.contains(result.charAt(i))) {
                uniqueSymbols.add(result.charAt(i));
            }
        }

        return uniqueSymbols.size();
    }
}
