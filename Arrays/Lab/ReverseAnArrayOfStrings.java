/*
Условие:
    Write a program to read an array of strings, reverse it and print its elements.
    The input consists of a sequence of space-separated strings.
    Print the output on a single line (space separated).
Examples:
    a b c d e
    ->
    e d c b a

    -1 hi ho w
    ->
    w ho hi -1
*/
package Arrays.Lab;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class ReverseAnArrayOfStrings {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String[] array = scanner.nextLine().split(" ");

        displayArrayInReverseOrder(array);
    }

    private static void displayArrayInReverseOrder(String[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            out.print(array[i] + " ");
        }
    }
}