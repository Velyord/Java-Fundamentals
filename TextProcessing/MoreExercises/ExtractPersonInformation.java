/*
Task:
    Write a program that reads n lines of strings and extracts
    the name and age of a given person. The person's name will
    be between "@" and "|". The person's age will be between "#"
    and "*".
Example:
    "Hello my name is @Peter| and I am #20* years old."
    For each found name and age, print a line in the following
    format "{name} is {age} years old."
Examples:
    2
    Here is a name @George| and an age #18*
    Another name @Billy| #35* is his age
    ->
    George is 18 years old.
    Billy is 35 years old.

    3
    random name @lilly| random digits #5* age
    @Marry| with age #19*
    here Comes @Garry| he is #48* years old
    ->
    lilly is 5 years old.
    Marry is 19 years old.
    Garry is 48 years old.
 */
package TextProcessing.MoreExercises;

import java.util.Scanner;

public class ExtractPersonInformation {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String name = input.substring(input.indexOf('@') + 1, input.indexOf('|'));
            String age = input.substring(input.indexOf('#') + 1, input.indexOf('*'));

            System.out.printf("%s is %s years old.%n", name, age);
        }

    }
}
