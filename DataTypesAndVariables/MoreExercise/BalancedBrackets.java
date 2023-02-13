/*
Условие:
    You will receive n lines. On those lines, you will receive one of the following:
    • Opening bracket – "("
    • Closing bracket – ")"
    • Random string
    Your task is to find out if the brackets are balanced. That means after every closing bracket should follow an opening one. Nested parentheses are not valid, and if two consecutive opening brackets exist, the expression should be marked as unbalanced.
Input:
    • On the first line, you will receive n – the number of lines that will follow.
    • On the next n lines, you will receive "(", ")" or another string.
Output:
    You must print "BALANCED" if the parentheses are balanced and "UNBALANCED" otherwise.
Constraints:
    • n will be in the interval [1…20].
    • The length of the stings will be between [1…100] characters.
Examples:
    8
    (
    5 + 10
    )
    * 2 +
    (
    5
    )
    -12
    ->
    BALANCED

    6
    12 *
    )
    10 + 2 -
    (
    5 + 10
    )
    ->
    UNBALANCED
*/
package DataTypesAndVariables.MoreExercise;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class BalancedBrackets {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int linesCount = setValue(1, 20);
        boolean isBalanced = true;
        boolean openingBracket = false;
        boolean closingBracket = false;

        for (int i = 1; i <= linesCount; i++) {
            String input = scanner.nextLine();
            char currentChar = input.charAt(0);

            boolean consecutiveOpeningBrackets = (currentChar == '(' && openingBracket);
            boolean consecutiveClosingBrackets = (currentChar == ')' && closingBracket);

            if (consecutiveOpeningBrackets || consecutiveClosingBrackets)
                isBalanced = false;

            if (currentChar == '(') {
                openingBracket = true;
            } else if (currentChar == ')') {
                closingBracket = true;
            }

            boolean pairedBrackets = openingBracket && closingBracket;

            if (pairedBrackets) {
                openingBracket = false;
                closingBracket = false;
            }

            boolean startedWithClosingBracket = !openingBracket && closingBracket;

            if (startedWithClosingBracket)
                isBalanced = false;
        }
        if (openingBracket)
            isBalanced = false;

        displayResult(isBalanced);
    }

    private static void displayResult(boolean isBalanced) {
        if (isBalanced) {
            out.println("BALANCED");
        } else {
            out.println("UNBALANCED");
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        String type = getType(max);
        Object value = setAndCheckInputFor(type);
        if (!isValueBetweenMinAndMax(value, min, max, type))
            return setValue(min, max);
        return (T) value;
    }
    private static <T> String getType(T max) {
        if (max instanceof Double)
            return "double";
        else if (max instanceof Float)
            return "float";
        else if (max instanceof Long)
            return "long";
        else
            return "int";
    }
    @SuppressWarnings("unchecked")
    private static <T> T setAndCheckInputFor(String type) {
        Object value;
        try {
            switch (type) {
                case "double": value = Double.parseDouble(scanner.nextLine()); break;
                case "float":  value = Float.parseFloat(scanner.nextLine());   break;
                case "long":   value = Long.parseLong(scanner.nextLine());     break;
                case "int":    value = Integer.parseInt(scanner.nextLine());   break;
                default:       value = null;                                   break;
            }
        } catch (Exception e) {
            out.println("Невалидно число. Пробвайте пак!");
            return setAndCheckInputFor(type);
        }
        return (T) value;
    }
    private static <T> boolean isValueBetweenMinAndMax(T value, T min, T max, String type) {
        double minDouble;
        float minFloat;
        switch (type) {
            case "double":
                minDouble = (double) min == Double.MIN_VALUE ? -1 * Double.MAX_VALUE : (double) min;
                if ((double) value >= minDouble && (double) value <= (double) max)
                    return true;
                break;
            case "float":
                minFloat = (float) min == Float.MIN_VALUE ? -1 * Float.MAX_VALUE : (float) min;
                if ((float) value >= minFloat && (float) value <= (float) max)
                    return true;
                break;
            case "long":
                if ((long) value >= (long) min && (long) value <= (long) max)
                    return true;
                break;
            case "int":
                if ((int) value >= (int) min && (int) value <= (int) max)
                    return true;
                break;
        }
        out.printf("Моля въведете число между %s и %s:\n", min, max);
        return false;
    }
}