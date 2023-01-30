/*
Условие:
    Write a program that takes 3 lines of characters and prints them in reversed order with a space between them.
Examples
    A
    B
    C
    ->
    C B A

    1
    L
    &
    -> & L 1
*/
package DataTypesAndVariables.Lab;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class ReversedChars {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        char firstCar =  scanner.nextLine().charAt(0);
        char secondCar = scanner.nextLine().charAt(0);
        char thirdCar =  scanner.nextLine().charAt(0);

        out.printf(
                "%c %c %c",
                thirdCar, secondCar, firstCar
        );
    }
}