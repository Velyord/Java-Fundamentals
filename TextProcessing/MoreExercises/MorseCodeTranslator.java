/*
Task:
    Write a program that translates messages from Morse code to English (capital letters).
    Use this page to help you (without the numbers). The words will be separated by a space (' ').
    There will be a "|" character which you should replace with ' ' (space).
Examples:
    .. | -- .- -.. . | -.-- --- ..- | .-- .-. .. - . | .- | .-.. --- -. --. | -.-. --- -.. .
    ->
    I MADE YOU WRITE A LONG CODE

    .. | .... --- .--. . | -.-- --- ..- | .- .-. . | -. --- - | -- .- -..
    ->
    I HOPE YOU ARE NOT MAD
 */
package TextProcessing.MoreExercises;

import java.util.Scanner;

public class MorseCodeTranslator {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String userInput = scanner.nextLine();
        String[] words = userInput.split(" \\| ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String[] letters = word.split(" ");

            for (String letter : letters) {
                result.append(getLetter(letter));
            }

            result.append(" ");
        }

        System.out.println(result);
    }

    private static char getLetter(String letter) {
        switch (letter) {
            case ".-":   return 'A';
            case "-...": return 'B';
            case "-.-.": return 'C';
            case "-..":  return 'D';
            case ".":    return 'E';
            case "..-.": return 'F';
            case "--.":  return 'G';
            case "....": return 'H';
            case "..":   return 'I';
            case ".---": return 'J';
            case "-.-":  return 'K';
            case ".-..": return 'L';
            case "--":   return 'M';
            case "-.":   return 'N';
            case "---":  return 'O';
            case ".--.": return 'P';
            case "--.-": return 'Q';
            case ".-.":  return 'R';
            case "...":  return 'S';
            case "-":    return 'T';
            case "..-":  return 'U';
            case "...-": return 'V';
            case ".--":  return 'W';
            case "-..-": return 'X';
            case "-.--": return 'Y';
            case "--..": return 'Z';
            default:     return ' ';
        }
    }
}
