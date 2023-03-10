/*
Условие:
    Write a program that safely compares floating-point numbers (double)
    with precision eps = 0.000001.
    Note that we cannot directly compare two floating-point numbers a and b by a==b
    because of the nature of the floating-point arithmetic.
    Therefore, we assume two numbers are equal if they are closer to each other
    than some fixed constant eps.
    You will receive two lines, each containing a floating-point number.
    Your task is to compare the values of the two numbers.
Examples:
    5.3
    6.01
    ->
    False
        The difference of 0.71 is too big (> eps)

    5.00000001
    5.00000003
    ->
    True
        The difference 0.00000002 < eps

    5.00000005
    5.00000001
    ->
    True
        The difference 0.00000004 < eps

    -0.0000007
    0.00000007
    ->
    True
        The difference 0.00000077 < eps

    -4.999999
    -4.999998
    ->
    False
        Border case. The difference 0.0000001== eps. We consider the numbers are different.

    4.999999
    4.999998
    ->
    False
        Border case. The difference 0.0000001 == eps. We consider the numbers are different.
*/
package DataTypesAndVariables.MoreExercise;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class FloatingEquality {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double a = setValue(Double.MIN_VALUE, Double.MAX_VALUE);
        double b = setValue(Double.MIN_VALUE, Double.MAX_VALUE);
        double eps = 0.000001;

        checkIfEqualWithPrecision(a, b, eps);
    }

    private static void checkIfEqualWithPrecision(
            double a,
            double b,
            double eps
    ) {
        if (abs(a - b) < eps)
            out.println("True");
        else
            out.println("False");
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
    static int stringCount = 0;
    private static String setValue() {
        String value = scanner.nextLine();
        if (!hasValidChars(value) || !doesFollowTemplate(value))
            return setValue();
        return value;
    }
    private static <T> boolean hasValidChars(T value) {
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|} 0123456789";
        boolean isSpecialChar = false;
        char specialChar = ' ';
        for (int i = 0; i < ((String) value).length(); i++)
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i);
                break;
            }
        if (isSpecialChar) {
            if (specialChar == ' ')
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            else
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            return false;
        }
        return true;
    }
    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};
        if (stringCount == 1)
            requiredStrings = new String[]{}; // fill if needed
        if (stringCount == 2)
            requiredStrings = new String[]{}; // fill for second var
        if (stringCount > 2)
            requiredStrings = new String[]{}; // Keep empty
        if (requiredStrings.length != 0) {
            List<String> requiredList = List.of(requiredStrings);
            if (!requiredList.contains(value.toString())) {
                out.print("Моля въведете един от следните избори: \n| ");
                for (String requiredString : requiredStrings)
                    out.print(requiredString + " | ");
                out.println(); // new line
                stringCount--;
                return false;
            }
        }
        return true;
    }
    public static int[] addIntToArray(int toBeAdded, int[] array) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        newArray[array.length] = toBeAdded;
        return newArray;
    }
}