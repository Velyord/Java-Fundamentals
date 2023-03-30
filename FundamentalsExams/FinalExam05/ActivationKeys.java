/*
Task:
    You are about to make some good money, but first,
    you need to think of a way to verify who paid for your product and who didn't.
    You have decided to let people use the software for a free trial period and then require an
    activation key to continue using the product.
    Before you can cash out, the last step is to design a program that creates unique activation keys
    for each user. So, waste no more time and start typing!
    The first line of the input will be your raw activation key.
    It will consist of letters and numbers only.
    After that, until the "Generate" command is given,
    you will be receiving strings with instructions for different operations that need to be performed
    upon the raw activation key.
    There are several types of instructions, split by ">>>":
    • "Contains>>>{substring}":
        ◦ If the raw activation key contains the given substring, prints:
        "{raw activation key} contains {substring}".
        ◦ Otherwise, prints: "Substring not found!"
    • "Flip>>>Upper/Lower>>>{startIndex}>>>{endIndex}":
        ◦ Changes the substring between the given indices (the end index is exclusive)
        to upper or lower case and then prints the activation key.
        ◦ All given indexes will be valid.
    • "Slice>>>{startIndex}>>>{endIndex}":
        ◦ Deletes the characters between the start and end indices (the end index is exclusive)
        and prints the activation key.
        ◦ Both indices will be valid.
Input:
    • The first line of the input will be a string consisting of letters and numbers only.
    • After the first line, until the "Generate" command is given, you will be receiving strings.
Output:
    • After the "Generate" command is received, print:
        ◦ "Your activation key is: {activation key}"
Examples:
    abcdefghijklmnopqrstuvwxyz
    Slice>>>2>>>6
    Flip>>>Upper>>>3>>>14
    Flip>>>Lower>>>5>>>7
    Contains>>>def
    Contains>>>deF
    Generate
    ->
    abghijklmnopqrstuvwxyz
    abgHIJKLMNOPQRstuvwxyz
    abgHIjkLMNOPQRstuvwxyz
    Substring not found!
    Substring not found!
    Your activation key is: abgHIjkLMNOPQRstuvwxyz
            1. Slice>>2>>6
        abcdefghijklmnopqrstuvwxyz becomes abghijklmnopqrstuvwxyz
            2. Flip>>>Upper>>>3>>>14
        abghijklmnopqrstuvwxyz becomes abgHIJKLMNOPQRstuvwxyz
            3. Flip>>>Lower>>>5>>>7
        abgHIJKLMNOPQRstuvwxyz becomes abgHIjkLMNOPQRstuvwxyz
            4. Contains>>>def
        abgHIjkLMNOPQRstuvwxyz does not contain def
            5. Contains>>>deF
        abgHIjkLMNOPQRstuvwxyz does not contain deF
        The final activation key is abgHIjkLMNOPQRstuvwxyz
    134softsf5ftuni2020rockz42
    Slice>>>3>>>7
    Contains>>>-rock
    Contains>>>-uni-
    Contains>>>-rocks
    Flip>>>Upper>>>2>>>8
    Flip>>>Lower>>>5>>>11
    Generate
    ->
    134sf5ftuni2020rockz42
    Substring not found!
    Substring not found!
    Substring not found!
    134SF5FTuni2020rockz42
    134SF5ftuni2020rockz42
    Your activation key is: 134SF5ftuni2020rockz42
 */
package FundamentalsExams.FinalExam05;

import java.util.Scanner;

public class ActivationKeys {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String rawActivationKey = scanner.nextLine();
        rawActivationKey = executeCommands(rawActivationKey);
        System.out.printf("Your activation key is: %s", rawActivationKey);
    }

    private static String executeCommands(String rawActivationKey) {
        String userInput = scanner.nextLine();

        while (!userInput.equals("Generate")) {
            String[] tokens = userInput.split(">>>");
            String command = tokens[0];

            switch (command) {
                case "Contains":
                    String substring = tokens[1];
                    rawActivationKey = containsCommand(rawActivationKey, substring);
                    break;
                case "Flip":
                    String upperOrLower = tokens[1];
                    int startIndex = Integer.parseInt(tokens[2]);
                    int endIndex = Integer.parseInt(tokens[3]);
                    rawActivationKey = flipCommand(rawActivationKey, upperOrLower, startIndex, endIndex);
                    break;
                case "Slice":
                    startIndex = Integer.parseInt(tokens[1]);
                    endIndex = Integer.parseInt(tokens[2]);
                    rawActivationKey = sliceCommand(rawActivationKey, startIndex, endIndex);

                    break;
            }

            userInput = scanner.nextLine();
        }

        return rawActivationKey;
    }

    private static String sliceCommand(String rawActivationKey, int startIndex, int endIndex) {
        rawActivationKey =
                rawActivationKey.substring(0, startIndex) + rawActivationKey.substring(endIndex);

        System.out.println(rawActivationKey);

        return rawActivationKey;
    }

    private static String flipCommand(
            String rawActivationKey,
            String upperOrLower,
            int startIndex,
            int endIndex
    ) {
        String substringToChange = rawActivationKey.substring(startIndex, endIndex);

        if (upperOrLower.equals("Upper")) {
            rawActivationKey =
                    rawActivationKey.replace(substringToChange, substringToChange.toUpperCase());
        } else {
            rawActivationKey =
                    rawActivationKey.replace(substringToChange, substringToChange.toLowerCase());
        }

        System.out.println(rawActivationKey);

        return rawActivationKey;
    }

    private static String containsCommand(String rawActivationKey, String substring) {
        if (rawActivationKey.contains(substring)) {
            System.out.printf("%s contains %s%n", rawActivationKey, substring);
        } else {
            System.out.println("Substring not found!");
        }

        return rawActivationKey;
    }
}
