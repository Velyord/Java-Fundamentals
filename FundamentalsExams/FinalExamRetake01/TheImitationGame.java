/*
Task:
    During World War 2, you are a mathematician who has joined the cryptography team to decipher the
    enemy's enigma code. Your job is to create a program to crack the codes.
    On the first line of the input, you will receive the encrypted message. After that, until the "Decode"
    command is given, you will be receiving strings with instructions for different operations that need
    to be performed upon the concealed message to interpret it and reveal its true content.
    There are several types of instructions, split by '|'
    • "Move {number of letters}":
        ◦ Moves the first n letters to the back of the string
    • "Insert {index} {value}":
        ◦ Inserts the given value before the given index in the string
    • "ChangeAll {substring} {replacement}":
        ◦ Changes all occurrences of the given substring with the replacement text
Input / Constraints:
    • On the first line, you will receive a string with a message.
    • On the following lines, you will be receiving commands, split by '|' .
Output:
    • After the "Decode" command is received, print this message:
"The decrypted message is: {message}"
Examples:
    zzHe
    ChangeAll|z|l
    Insert|2|o
    Move|3
    Decode
    ->
    The decrypted message is: Hello
        ChangeAll|z|l
        zzHe → llHe (We replace all occurrences of 'z' with 'l')
        Insert|2|o
        llHe → lloHe (We add an 'o' before the character on index 2)
        Move|3
        lloHe → Hello (We take the first three characters and move them to the end of the string)
        Finally, after receiving the "Decode" command, we print the resulting message.
    owyouh
    Move|2
    Move|3
    Insert|3|are
    Insert|9|?
    Decod->
    The decrypted message is: howareyou?
 */
package FundamentalsExams.FinalExamRetake01;

import java.util.Scanner;

import static java.lang.System.out;

public class TheImitationGame {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String encryptedMessage = scanner.nextLine();
        String input = scanner.nextLine();

        while (!input.equals("Decode")) {
            String[] instructions = input.split("\\|");
            String instruction = instructions[0];

            switch (instruction) {
                case "Move":
                    int numberOfLetters = Integer.parseInt(instructions[1]);

                    if (numberOfLetters >= 0 && numberOfLetters <= encryptedMessage.length()) {
                        String toBeMoved = encryptedMessage.substring(0, numberOfLetters);

                        encryptedMessage = encryptedMessage.substring(numberOfLetters) + toBeMoved;
                    }
                    break;
                case "Insert":
                    int index = Integer.parseInt(instructions[1]);
                    if (index >= 0 && index <= encryptedMessage.length()) {
                        String value = instructions[2];

                        encryptedMessage =
                                encryptedMessage.substring(0, index) + value + encryptedMessage.substring(index);
                    }
                    break;
                case "ChangeAll":
                    String toBeChanged = instructions[1];
                    String replacement = instructions[2];

//                    encryptedMessage = encryptedMessage.replaceAll(toBeChanged, replacement); // на judge не му хареса така
                    encryptedMessage = encryptedMessage.replace(toBeChanged.charAt(0), replacement.charAt(0));
                    break;
            }

            input = scanner.nextLine();
        }

        out.println("The decrypted message is: " + encryptedMessage);
    }
}
