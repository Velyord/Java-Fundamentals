/*
Условие:
    Write a program that reads 3 lines of input. On each line, you get a single character.
    Combine all the characters into one string and print it on the console.
Examples
    a
    b
    c
    ->
    abc

    %
    2
    o
    ->
    %2o

    1
    5
    p
    ->
    15p
*/
package DataTypesAndVariables.Lab;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class CharsToString {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        char firstChar = scanner.nextLine().charAt(0);
        char secondChar = scanner.nextLine().charAt(0);
        char thirdChar = scanner.nextLine().charAt(0);

        out.printf("%c%c%c", firstChar, secondChar, thirdChar);
    }
}