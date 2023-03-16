/*
Task:
    Create a method that takes two strings as arguments
    and returns the sum of their character codes multiplied (multiply str1[0] with str2[0]
    and add to the total sum). Then continue with the next two characters.
    If one of the strings is longer than the other,
    add the remaining character codes to the total sum without multiplication.
Examples:
    George Peter -> 52114
    123 522 -> 7647
    love SoftUni -> 45337
 */
package TextProcessing.Exercise;

import java.util.Scanner;

import static java.lang.System.out;

public class CharacterMultiplier {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] input = scanner.nextLine().split(" ");
        out.println(sumOfCharacters(input[0], input[1]));
    }

    private static int sumOfCharacters(String string1, String string2) {
        int shorterString = Math.min(string1.length(), string2.length());
        int longerString = Math.max(string1.length(), string2.length());

        int sum = 0;

        for (int i = 0; i < shorterString; i++) {
            int multipliedCodes = string1.charAt(i) * string2.charAt(i);
            sum += multipliedCodes;
        }

        for (int i = shorterString; i < longerString; i++) {
            if (longerString == string1.length()) {
                sum += string1.charAt(i);
            } else {
                sum += string2.charAt(i);
            }
        }

        return sum;
    }
}
