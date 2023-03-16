/*
Task:
    Write a program that reads user names on a single line (joined by ", ")
    and prints all valid usernames.
    A valid username is:
        • Has a length of between 3 and 16 characters.
        • It contains only letters, numbers, hyphens, and underscores.
Examples:
    sh, too_long_username, !lleg@l ch@rs, jeffbutt
    ->
    jeffbutt

    Jeff, john45, ab, cd, peter-ivanov, @smith
    ->
    Jeff
    John45
    peter-ivanov
 */
package TextProcessing.Exercise;

import java.util.Scanner;

import static java.lang.System.out;

public class ValidUsernames {
    private static final String specialChars = "!#$%&'()*+./;<=>?@[]^`{|} ";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] usernames = scanner.nextLine().split(", ");

        for (String username : usernames) {
            if (username.length() >= 3 && username.length() <= 16) {
                if (hasValidChars(username)) {
                    out.println(username);
                }
            }
        }
    }

    private static boolean hasValidChars(String username) {
        boolean isSpecialChar = false;

        for (int i = 0; i < username.length(); i++) { // Търсене на забранен символ чрез сравняване на входните данни с низът със забранени символи
            if (specialChars.contains(Character.toString(username.charAt(i)))) {
                isSpecialChar = true;
                break;
            }
        }

        return !isSpecialChar;
    }
}
