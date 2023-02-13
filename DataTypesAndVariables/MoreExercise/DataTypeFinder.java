/*
Условие:
    ou will receive input until you receive "END".
    Find what data type is the input. Possible data types are:
    • Integer
    • Floating point
    • Characters
    • Boolean
    • Strings
    Print the result in the following format: "{input} is {data type} type".
Examples:
    5
    2.5
    true
    END
    ->
    5 is integer type
    2.5 is floating point type
    true is boolean type

    a
    asd
    -5
    END
    ->
    a is character type
    asd is string type
    -5 is integer type
*/
package DataTypesAndVariables.MoreExercise;

import static java.lang.System.in;

import java.util.Scanner;

public class DataTypeFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        String input = scanner.nextLine();

        while (!input.equals("END")){
            Scanner type = new Scanner(input);

            if (type.hasNextBoolean()) {
                System.out.println(input + " is boolean type");
            } else if (type.hasNextInt()) {
                System.out.println(input + " is integer type");
            } else if (type.hasNextDouble()) {
                System.out.println(input + " is floating point type");
            } else if (input.length() == 1) {
                System.out.println(input + " is character type");
            } else if (type.hasNextLine()) {
                System.out.println(input + " is string type");
            }

            input = scanner.nextLine();
        }
    }
}