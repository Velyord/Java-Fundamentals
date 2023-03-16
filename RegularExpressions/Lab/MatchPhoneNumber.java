/*
Task:
    Write a regular expression to match a valid phone number from Sofia.
    After you find all valid phones, print them on the console, separated by a comma and a space ", ".
    Compose the Regular Expression
    A valid number has the following characteristics:
    • It starts with "+359".
    • Then, it is followed by the area code (always 2).
    • After that, it's followed by the number itself:
        ◦ The number consists of 7 digits (separated into two groups of 3 and 4 digits, respectively).
    • The different parts are separated by either a space or a hyphen ('-').
    You can use the following RegEx properties to help with the matching:
    • Use quantifiers to match a specific number of digits.
    • Use a capturing group to ensure the delimiter is only one of the allowed characters
    (space or hyphen) and not a combination of both
    (e.g., +359 2-111 111 has mixed delimiters, it is invalid).
    Use a group back reference to achieve this.
    • Add a word boundary at the end of the match to avoid partial matches
    (the last example is on the right-hand side).
    • Ensure that before the '+' sign, there is either a space or the beginning of the string.
    You can use the following table of values to test your RegEx against:
    Match ALL of these
    Match NONE of these
    +359 2 222 2222
    +359-2-222-2222
    359-2-222-2222, +359/2/222/2222, +359-2 222 2222
    +359 2-222-2222, +359-2-222-222, +359-2-222-22222
    Implement the Solution in Java
    Now it's time to write the solution, so let's start writing!
    First, just like in the previous problem, put your RegEx in a variable:
    Again we need a Pattern and Matcher.
    We can also save our matches in a List if we need.
    After that, just print the valid phone number list using a string.Join():
Examples;
    +359 2 222 2222,359-2-222-2222, +359/2/222/2222, +359-2 222 2222 +359 2-222-2222, +359-2-222-222,
    +359-2-222-22222 +359-2-222-2222
    ->
    +359 2 222 2222, +359-2-222-2222

    +359 2 222 2222,359-2-222-2222, +359/2/222/2222, +359-2 222 2222 +359 2-222-2222, +359-2-222-222,
    +359-2-222-22222 +359-2-222-2222
    ->
    +359 2 222 2222, +359-2-222-2222
 */
package RegularExpressions.Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class MatchPhoneNumber {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> phoneBook = new ArrayList<>();
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\+359([ -])2\\1\\d{3}\\1\\d{4}\\b");
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {
            phoneBook.add(matcher.group());
        }

        out.println(String.join(", ", phoneBook));
    }
}
