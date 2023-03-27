/*
Task:
    You have plenty of free time, so you decide to write a program that conceals and reveals your received
    messages. Go ahead and type it in!
    On the first line of the input, you will receive the concealed message.
    After that, until the "Reveal" command is given,
    you will receive strings with instructions for different operations that need to be performed upon the
    concealed message to interpret it and reveal its actual content.
    There are several types of instructions, split by ":|:"
    • "InsertSpace:|:{index}":
        ◦ Inserts a single space at the given index. The given index will always be valid.
    • "Reverse:|:{substring}":
        ◦ If the message contains the given substring, cut it out,
        reverse it and add it at the end of the message.
        ◦ If not, print "error".
        ◦ This operation should replace only the first occurrence of the given substring if there are two
        or more occurrences.
    • "ChangeAll:|:{substring}:|:{replacement}":
        ◦ Changes all occurrences of the given substring with the replacement text.
Input / Constraints:
    • On the first line, you will receive a string with a message.
    • On the following lines, you will be receiving commands, split by ":|:".
Output:
    • After each set of instructions, print the resulting string.
    • After the "Reveal" command is received, print this message:
    "You have a new text message: {message}"
Examples:
    heVVodar!gniV
    ChangeAll:|:V:|:l
    Reverse:|:!gnil
    InsertSpace:|:5
    Reveal
    ->
    hellodar!gnil
    hellodarling!
    hello darling!
    You have a new text message: hello darling!
        ChangeAll:|:V:|:l
        heVVodar!gniV -> hellodar!gnil (We replace all occurrences of "V" with "l")
        Reverse:|:!gnil
        hellodar!gnil -> !gnil -> ling! -> hellodarling! (We reverse !gnil to ling! 
        And put it at the end of the string)
        InsertSpace:|:5
        hellodarling! -> hello.darling! (We insert a space at index 5)
        Finally, after receiving the "Reveal" command, we print the resulting message.
    Hiware?uiy
    ChangeAll:|:i:|:o
    Reverse:|:?uoy
    Reverse:|:jd
    InsertSpace:|:3
    InsertSpace:|:7
    Reveal
    ->
    Howare?uoy
    Howareyou?
    error
    How areyou?
    How are you?
    You have a new text message: How are you?
 */
package FundamentalsExams.FinalExamRetake03;

import java.util.Scanner;

import static java.lang.System.out;

public class SecretChat {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String concealedMessage = scanner.nextLine();
        String commands = scanner.nextLine();
        
        while (!commands.equals("Reveal")) {
            String[] commandParts = commands.split(":\\|:");
            String command = commandParts[0];
            
            switch (command) {
                case "InsertSpace": 
                    int index = Integer.parseInt(commandParts[1]);

                    concealedMessage = insertSpaceAt(index, concealedMessage);
                    break;
                case "Reverse":
                    String toBeReversedAtEnd = commandParts[1];

                    if (concealedMessage.contains(toBeReversedAtEnd)) {
                        concealedMessage = reverse(toBeReversedAtEnd, concealedMessage);
                    } else {
                        out.println("error");

                        commands = scanner.nextLine();
                        continue;
                    }
                    break;
                case "ChangeAll":
                    String toBeReplaced = commandParts[1];
                    String replacement = commandParts[2];

                    concealedMessage = concealedMessage.replaceAll(toBeReplaced, replacement);
                    break;
            }

            out.println(concealedMessage);

            commands = scanner.nextLine();
        }

        out.printf("You have a new text message: %s", concealedMessage);
    }

    private static String reverse(String toBeReversedAtEnd, String text) {
        String reversedText = reverseString(toBeReversedAtEnd);

        return text.substring(0, text.indexOf(toBeReversedAtEnd)) +
               text.substring(text.indexOf(toBeReversedAtEnd) + toBeReversedAtEnd.length()) +
               reversedText;
    }

    private static String insertSpaceAt(int index, String text) {
        return text.substring(0, index) + " " + text.substring(index);
    }

    private static String reverseString(String text) {
        StringBuilder reversedString = new StringBuilder();

        for (int i = text.length() - 1; i >= 0; i --) {
            reversedString.append(text.charAt(i));
        }

        return reversedString.toString();
    }
}