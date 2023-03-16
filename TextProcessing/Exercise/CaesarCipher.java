/*
Task:
    Write a program that returns an encrypted version of the same text.
    Encrypt the text by shifting each character with three positions forward.
    For example, A would be replaced by D, B would become E, and so on. Print the encrypted text.
Examples:
    Programming is cool! -> Surjudpplqj#lv#frro$
    One year has 365 days. -> Rqh#|hdu#kdv#698#gd|v1
 */
package TextProcessing.Exercise;

import java.util.Scanner;

import static java.lang.System.out;

public class CaesarCipher {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input = scanner.nextLine();
        char[] inputToCharArray = input.toCharArray();

        for (char character : inputToCharArray) {
            character += 3;
            out.print(character);
        }
    }
}