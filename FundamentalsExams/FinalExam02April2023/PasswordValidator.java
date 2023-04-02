/*
Task:
    Problem 1 - Password Validator
    Create a program that manipulates a string and makes it suitable for a password.
    Password rules are:
        • Must be at least 8 characters long
        • Consists only of letters, digits, and underscore - "_"
        • Must have at least one uppercase letter • Must have at least one lowercase letter
        • Must have at least one digit First, you are going to receive the password that the user wants to use.
    Next, you  will  be  receiving commands until you receive  the  "Complete"  command.
    There  are five possible commands:
        • "Make Upper {index}" o Replace the letter at the given index with upper case, then print the password.
        • "Make Lower {index}" o Replace the letter at the given index with lower case, then print the password.
        • "Insert {index} {char}" o Inserts the given char at the given index in the string, then print the password.
    o If the index is not valid, ignore the command.
        • "Replace {char} {value}" o Get the ASCII value of the given char.
        Sum its value with the given value and replace all occurrences of the char with the  new  symbol corresponding  to
        the sum  result in  the ASCII table. Print the password.
    o If the char is not in the password, ignore the command.
        • "Validation" o Check why the  password  is  not  valid.
        Each  of  the  checks  should  be performed  in  the order shown and are independent of each other:
        1) If  it  is  not at  least  8  characters,  print: "Password  must  be at  least  8 characters long!"
        2) If  it does  not  consist  only  of  letters,  digits  and underscore,
        print: "Password must consist only of letters, digits and _!"
        3) If  it does  not  have  at  least  one  uppercase  letter,
        print: "Password  must consist at least one uppercase letter!"
        4) If  it does  not  have  at least  one  lowercase  letter,
        print: "Password  must consist at least one lowercase letter!"
        5) If  it does  not  have  at  least  one  digit,
        print: "Password  must consist at least one digit!" If a given command is not valid, you should ignore it.
Input:
    • On the 1st line, you are going to receive the password in the form of a string.
    • On the next lines, until you receive the "Complete" command, you will be receiving commands.
Output:
    • Print the output of every command in the format described above.
Examples:
    invalidpassword*
    Add 2 p
    Replace i -50
    Replace * 10
    Make Upper 2
    Validation
    Complete
    ->
    7nval7dpassword*
    7nval7dpassword4
    7nVal7dpassword4

    123456789
    Insert 3 R
    Replace 5 15
    Validation
    Make Lower 3
    Complete
    ->
    123R456789
    123R4D6789
    Password must consist at least one lowercase letter!
    123r4D6789
 */
package FundamentalsExams.FinalExam02April2023;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String password = scanner.nextLine();
        executeCommands(password);
    }

    private static void executeCommands(String password) {
        String commands = scanner.nextLine();

        while(!commands.equals("Complete")){
            String[] commandParts = commands.split("\\s+");
            String command = commandParts[0];

            switch (command){
                case "Make":
                    String upperOrLower = commandParts[1];
                    int index = Integer.parseInt(commandParts[2]);

                    password = makeCommand(password, upperOrLower, index);
                    break;
                case "Insert":
                    int indexToInsert = Integer.parseInt(commandParts[1]);

                    password = insertCommand(password, indexToInsert, commandParts);
                    break;
                case "Replace":
                    char symbol = commandParts[1].charAt(0);
                    int value = Integer.parseInt(commandParts[2]);

                    password = replaceCommand(password, symbol, value);
                    break;
                case "Validation":
                    if(password.length() < 8){
                        System.out.println("Password must be at least 8 characters long!");
                    }

                    if(!password.matches("[a-zA-Z0-9_]+")){
                        System.out.println("Password must consist only of letters, digits and _!");
                    }

                    if(!password.matches(".*[A-Z].*")){
                        System.out.println("Password must consist at least one uppercase letter!");
                    }

                    if(!password.matches(".*[a-z].*")){
                        System.out.println("Password must consist at least one lowercase letter!");
                    }

                    if(!password.matches(".*[0-9].*")){
                        System.out.println("Password must consist at least one digit!");
                    }

                    break;
            }

            commands = scanner.nextLine();
        }
    }

    private static String replaceCommand(
            String password,
            char symbol,
            int value
    ) {
        if (password.contains(String.valueOf(symbol))) {
            password = password.replaceAll(
                    Pattern.quote(String.valueOf(symbol)),
                    String.valueOf((char) (symbol + value))
            );

            System.out.println(password);
        }

        return password;
    }

    private static String insertCommand(
            String password,
            int indexToInsert,
            String[] commandParts
    ) {
        if(indexToInsert >= 0 && indexToInsert < password.length()){
            String firstPart = password.substring(0, indexToInsert);
            String secondPart = password.substring(indexToInsert);

            password = firstPart + commandParts[2] + secondPart;

            System.out.println(password);
        }

        return password;
    }

    private static String makeCommand(
            String password,
            String upperOrLower,
            int index
    ) {
        if(upperOrLower.equals("Upper")) {
            String firstPart = password.substring(0, index);
            String secondPart = password.substring(index + 1);
            char charToBeReplaced = Character.toUpperCase(password.charAt(index));

            password = firstPart + charToBeReplaced + secondPart;

            System.out.println(password);
        } else if(upperOrLower.equals("Lower")){
            String firstPart = password.substring(0, index);
            String secondPart = password.substring(index + 1);
            char charToBeReplaced = Character.toLowerCase(password.charAt(index));

            password = firstPart + charToBeReplaced + secondPart;

            System.out.println(password);
        }
        return password;
    }
}
