/*
Условие:
    You will be given a string representing a username.
    The password will be that username reversed.
    Until you receive the correct password,
    print on the console "Incorrect password.
    Try again.". When you receive the correct password,
    print "User {username} logged in."
    However, on the fourth try,
    if the password is still not correct,
    print "User {username} blocked!" and end the program.
Examples:
    Acer
    login
    go
    let me in
    recA
    ->
    Incorrect password. Try again.
    Incorrect password. Try again.
    Incorrect password. Try again.
    User Acer logged in.

    momo
    omom
    ->
    User momo logged in.

    sunny
    rainy
    cloudy
    sunny
    not sunny
    ->
    Incorrect password. Try again.
    Incorrect password. Try again.
    Incorrect password. Try again.
    User sunny blocked!
*/
package SoftUni.Fundamentals.Exer1;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Login {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String username = scanner.nextLine();
        String password = generatePassword(username);
        int incorrectPasswordCount = 0;
        boolean isBlocked = false;
        String passwordAttempt = scanner.nextLine();
        while (!passwordAttempt.equals(password)) {
            incorrectPasswordCount ++;
            if (incorrectPasswordCount > 3) {
                out.printf("User %s blocked!", username);
                isBlocked = true;
                break;
            }
            else {
                out.println("Incorrect password. Try again.");
                passwordAttempt = scanner.nextLine();
            }
        }
        if (!isBlocked)
            out.printf("User %s logged in.", username);
    }

    private static String generatePassword(String username) {
        String password = "";
        for (int i=username.length()-1; i>=0; i--)
            password += username.charAt(i);
        return password;
    }
}
