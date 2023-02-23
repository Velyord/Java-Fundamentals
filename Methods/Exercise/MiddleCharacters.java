/*
Task:
    You will receive a single string.
    Write a method that prints the middle character.
    If the length of the string is even, there are two middle characters.
Examples:
    aString -> r
    someText -> eT
    3245 -> 24
*/
package Methods.Exercise;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class MiddleCharacters {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String input = scanner.nextLine();

        if (input.length() < 3) {
            out.println(input);
        }
        else {
            if (input.length() % 2 == 0) {
                getMiddleCharacters(input, 2);
            } else {
                getMiddleCharacters(input, 1);
            }
        }
    }

    private static void getMiddleCharacters(String input, int charCount) {
        while (input.length() > charCount) {
            input = input.substring(1, input.length() - 1); // взима от първия до предпоследняи елемент
        }

        out.println(input);
    }
}