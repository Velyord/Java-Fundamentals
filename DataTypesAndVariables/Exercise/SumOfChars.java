/*
Условие:
    Write a program that sums the ASCII codes of n characters. Print the sum on the console.
Input
    • On the first line, you will receive n – the number of lines that follow.
    • On the next n lines – you will receive letters from the Latin alphabet.
Output:
    Print the total sum in the following format:
    "The sum equals: {totalSum}"
Constraints:
    • n will be in the interval [1…20].
    • The characters will always be either upper or lower-case letters from the English alphabet.
    • You will always receive one letter per line.
Examples:
    5
    A
    b
    C
    d
    E
    ->
    The sum equals: 399

    12
    S
    o
    f
    t
    U
    n
    i
    R
    u
    l
    z
    z
    ->
    The sum equals: 1263
*/
package DataTypesAndVariables.Exercise;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class SumOfChars {

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int n = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        for (int i=1; i<=n; i++) {
            char letter = scanner.nextLine().charAt(0);
            sum += letter;
        }
        out.println("The sum equals: " + sum);
    }
}