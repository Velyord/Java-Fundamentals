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

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class DataTypeFinder {
    static Scanner scanner = new Scanner(in);
    public static void main(String[] args) {
        while (true) {
            String variable = scanner.nextLine();
            try {
                int testVariable = Integer.parseInt(variable);
            } catch (Exception e) {
                try {
                    float testVariable = Float.parseFloat(variable);
                } catch (Exception e2) {
                    String testVariable = variable;
                    if (testVariable.equals("END"))
                        break;
                    if (testVariable.equals("true") || testVariable.equals("false")) {
                        out.println(variable + " is boolean type");
                        continue;
                    }
                    if (testVariable.length() == 1) {
                        out.println(variable + " is character type");
                        continue;
                    }
                    out.println(variable + " is string type");
                    continue;
                }
                out.println(variable + " is floating point type");
                continue;
            }
            out.println(variable + " is integer type");
        }
    }
}