/*
Условие:
    Write a program that prints whether a given character is upper-case or lower-case.
Examples:
    L	upper-case
    f	lower-case
*/
package DataTypesAndVariables.Lab;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class LowerToUpper {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        char character = scanner.nextLine().charAt(0);
        if (character < 97)
            out.println("upper-case");
        else
            out.println("lower-case");
    }
}