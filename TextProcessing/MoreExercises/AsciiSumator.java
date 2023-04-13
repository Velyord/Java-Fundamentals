/*
Task:
    Write a program that prints a sum of all characters between
    two given characters (their ASCII code). In the first line,
    you will get a character. In the second line, you get another
    character. On the last line, you get a random string.
    Find all the characters between the two given and print their
    ASCII sum.
Examples
    .
    @
    dsg12gr5653feee5
    ->
    363

    ?
    E
    @ABCEF
    ->
    262
 */
package TextProcessing.MoreExercises;

import java.util.Scanner;

public class AsciiSumator {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        char firstChar = scan.nextLine().charAt(0);
        char secondChar = scan.nextLine().charAt(0);
        String input = scan.nextLine();

        int sum = 0;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            
            if (currentChar > firstChar && currentChar < secondChar) {
                sum += currentChar;
            }
        }

        System.out.println(sum);
    }
}
