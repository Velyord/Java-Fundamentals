/*
Task:
    Write a program that reads a string from the console
    and replaces any sequence of the same letters with a single corresponding letter.
Examples:
    aaaaabbbbbcdddeeeedssaa -> abcdedsa
    qqqwerqwecccwd -> qwerqwecwd
 */
package TextProcessing.Exercise;

import java.util.Scanner;

import static java.lang.System.out;

public class ReplaceRepeatingChars {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input = scanner.nextLine();
        StringBuilder result = new StringBuilder(" ");

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != result.charAt(result.length() - 1)) {
                result.append(input.charAt(i));
            }
        }

        out.println(result.substring(1)); // delete the first space character
    }
}
