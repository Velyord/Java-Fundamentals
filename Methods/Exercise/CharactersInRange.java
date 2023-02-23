/*
Task:
    Write a method that receives two characters
     and prints all the characters in between them on a single line according to ASCII.
Examples:
    a
    d
    ->
    b c

    #
    :
    ->
    $ % & ' ( ) * + , - . / 0 1 2 3 4 5 6 7 8 9

    C
    #
    ->
    $ % & ' ( ) * + , - . / 0 1 2 3 4 5 6 7 8 9 : ; < = > ? @ A B
*/
package Methods.Exercise;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class CharactersInRange {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        char firstChar = scanner.nextLine().charAt(0);
        char secondChar = scanner.nextLine().charAt(0);

        getCharactersBetween(firstChar, secondChar);
    }

    private static void getCharactersBetween(char firstChar, char secondChar) {
        char startChar = (char) Math.min(firstChar, secondChar);
        char endChar = (char) Math.max(firstChar, secondChar);

        for (int i = startChar + 1; i < endChar; i++) {
            out.print((char) i + " ");
        }
    }
}