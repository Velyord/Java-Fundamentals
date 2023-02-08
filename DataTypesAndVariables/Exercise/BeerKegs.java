/*
Условие:
    Write a program that calculates the volume of n beer kegs.
    You will receive in total 3 * n lines. Every three lines will hold information for a single keg.
    First up is the model of the keg, after that is the radius of the keg,
    and lastly is the height of the keg.
    Calculate the volume using the following formula: π * r^2 * h.
    In the end, print the model of the biggest keg.
Input:
    You will receive 3 * n lines. Each group of lines will be on a new line:
        • First – model – string
        • Second –radius – floating-point number
        • Third – height – integer number
Output:
    Print the model of the biggest keg.
Constraints:
    • n will be in the interval [1…10].
    • The radius will be a floating-point number in the interval [1…3.402823E+38].
    • The height will be an integer in the interval [1…2147483647].
Examples:
    3
    Keg 1
    10
    10
    Keg 2
    20
    20
    Keg 3
    10
    30
    ->
    Keg 2

    2
    Smaller Keg
    2.41
    10
    Bigger Keg
    5.12
    20
    ->
    Bigger Keg
*/
package DataTypesAndVariables.Exercise;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class BeerKegs {
    static Scanner scanner = new Scanner(in);
    public static void main(String[] args) {
        int lines = setValue(1, 10);
        String biggestKeg = "";
        double biggestVolume = 0;
        for (int i=1; i<=lines; i++) {
            String model = setValue();
            float radius = setValue(1F, Float.MAX_VALUE);
            int height = setValue(1, Integer.MAX_VALUE);
            double volume = calculateVolume(radius, height);
            if (volume > biggestVolume) {
                biggestVolume = volume;
                biggestKeg = model;
            }
        }
        out.println(biggestKeg);
    }

    private static double calculateVolume(float radius, int height) {
        return PI * pow(radius, 2) * height;
    }
    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        String type = getType(max);
        Object value;
        value = setAndCheckInputFor(type);
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
        switch (type) {
            case "double":
                if ((double) value >= (double) min && (double) value <= (double) max)
                    return true;
                break;
            case "float":
                if ((float) value >= (float) min && (float) value <= (float) max)
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
        String value;
        value = scanner.nextLine();
        if (!hasValidChars(value) || !doesFollowTemplate(value))
            return setValue();
        return value;
    }
    private static <T> boolean hasValidChars(T value) {
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}";
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