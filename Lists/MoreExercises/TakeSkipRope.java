/*
Task:
    Write a program that reads a string and skips through it, extracting a hidden message.
    The algorithm you have to implement is as follows:
    Let's take the string "skipTest_String044170" as an example.
    Take every digit from the string and store it somewhere.
    After that, remove all the digits from the string. After this
    operation, you should have two lists of items: the numbers list and the non-numbers list:
    • Numbers list: [0, 4, 4, 1, 7, 0]
    • Non-numbers: [s, k, i, p, T, e, s, t, _, S, t, r, i, n, g]
    After that, take every digit in the numbers list and split it up into a take list and a skip list,
    depending on whether
    the digit is in an even or an odd index:
    • Numbers list: [0, 4, 4, 1, 7, 0]
    • Take list: [0, 4, 7]
    • Skip list: [4, 1, 0]
    Afterward, iterate over both of the lists and skip {skipCount} characters from the non-numbers list,
    then take {takeCount} characters and store it in a result string.
    Note that the skipped characters are summed up as they go.
    The process would look like this on the aforementioned non-numbers list:
Example: "skipTest_String"
    1. Take 0 characters -> Taken: "", skip 4 characters ➔ Skipped: "skip" -> Result: ""
    2. Take 4 characters -> Taken: "Test", skip 1 characters ➔ Skipped: "_" -> Result: "Test"
    3. Take 7 characters -> Taken: "String", skip 0 characters ➔ Skipped: "" -> Result: "TestString"
    After that, just print the result string on the console.
Input:
    The encrypted message is a string.
Output:
    The decrypted message is a string.
Constraints:
    • The count of digits in the input string will always be even.
    • The encrypted message will contain any printable ASCII character.
Examples:
    Input:
        T2exs15ti23ng1_3cT1h3e0_Roppe
    Output:
        TestingTheRope
    Input:
        O{1ne1T2021wf312o13Th111xreve!!@!
    Output:
        OneTwoThree!!!
    Input:
        this forbidden mess of an age rating 0127504740
    Output:
        hidden message
 */
package Lists.MoreExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class TakeSkipRope {
    static Scanner scanner = new Scanner(System.in);
    private static String input = "";
    private static final List<Integer> digitsList = new ArrayList<>();
    private static final List<Character> nonDigitsList = new ArrayList<>();
    private static final StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        input = scanner.nextLine();

        populateDigitAndNonDigitLists();
        createResult();

        out.println(result);
    }

    private static void createResult() {
        for (int i = 0; i < digitsList.size(); i++) {
            if (i % 2 == 0) {
                int take = digitsList.get(i);

                for (int j = 0; j < take; j ++) {
                    if (nonDigitsList.size() > 0) {
                        result.append(nonDigitsList.get(0));
                        nonDigitsList.remove(0);
                    }
                }
            } else {
                int skip = digitsList.get(i);

                for (int j = 0; j < skip; j++) {
                    if (nonDigitsList.size() > 0) {
                        nonDigitsList.remove(0);
                    }
                }
            }
        }
    }

    private static void populateDigitAndNonDigitLists() {
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isDigit(currentChar)) {
                int currentCharToInt = Integer.parseInt(String.valueOf(currentChar));

                digitsList.add(currentCharToInt);
            } else {
                nonDigitsList.add(currentChar);
            }
        }
    }
}