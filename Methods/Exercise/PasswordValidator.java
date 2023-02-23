/*
Task:
    Write a program that checks if a given password is valid. Password rules are:
    • 6 – 10 characters (inclusive);
    • Consists only of letters and digits;
    • Have at least 2 digits.
    If a password is valid, print "Password is valid". If it is not valid, for every unfulfilled rule,
    print a message:
    • "Password must be between 6 and 10 characters";
    • "Password must consist only of letters and digits";
    • "Password must have at least 2 digits".
Examples:
    logIn
    ->
    Password must be between 6 and 10 characters
    Password must have at least 2 digits

    MyPass123
    ->
    Password is valid

    Pa$s$s
    ->
    Password must consist only of letters and digits
    Password must have at least 2 digits
*/
package Methods.Exercise;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class PasswordValidator {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String password = scanner.nextLine();
        boolean isInRange = checkIfIsInRange(password);
        boolean isOnlyLettersAndDigits = checkIfConsistOnlyLettersAndDigits(password);
        boolean has2Digits = checkIfPasswordHas2Digits(password);

        if (isInRange && isOnlyLettersAndDigits && has2Digits) {
            out.println("Password is valid");
        }
    }

    private static boolean checkIfPasswordHas2Digits(String password) {
        String digits = "0123456789";
        int digitCount = 0;
        boolean hasEnoughDigits = false;

        for (int i = 0; i < password.length(); i++) {
            if (digits.contains(Character.toString(password.charAt(i)))) {
                digitCount++;

                if (digitCount == 2) {
                    hasEnoughDigits = true;
                    break;
                }
            }
        }

        if (!hasEnoughDigits) {
            out.println("Password must have at least 2 digits");
        }

        return hasEnoughDigits;
    }

    private static boolean checkIfConsistOnlyLettersAndDigits(String password) {
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}- ";

        for (int i = 0; i < password.length(); i++) {
            if (specialChars.contains(Character.toString(password.charAt(i)))) {
                out.println("Password must consist only of letters and digits");

                return false;
            }
        }

        return true;
    }

    private static boolean checkIfIsInRange(String password) {
        if (password.length() < 6 || password.length() > 10) {
            out.println("Password must be between 6 and 10 characters");

            return false;
        }

        return true;
    }
}