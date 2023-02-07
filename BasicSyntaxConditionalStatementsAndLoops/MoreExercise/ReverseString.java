/*
Условие:
    Write a program that reverses a string and prints it on the console.
Examples:
    Hello -> olleH
    SoftUni -> inUtfoS
    1234 -> 4321
*/
package BasicSyntaxConditionalStatementsAndLoops.MoreExercise;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class ReverseString {

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String word = scanner.nextLine();
        String reversedWord = "";
        for (int i=word.length()-1; i>=0; i--)
            reversedWord += word.charAt(i);
        out.println(reversedWord);
    }
}
