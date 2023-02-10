/*
Условие:
    Tony and Andi love playing in the snow and having snowball
    fights, but they always argue about which makes the best
    snowballs. They have decided to involve you in their fray
    by making you write a program that calculates snowball
    data and outputs the best snowball value.
    You will receive N – an integer, the number of snowballs
    being made by Tony and Andi.
    For each snowball, you will receive 3 input lines:
    •	On the first line, you will get the snowballSnow –
    an integer.
    •	On the second line, you will get the snowballTime –
    an integer.
    •	On the third line, you will get the snowballQuality –
    an integer.
    For each snowball, you must calculate its snowballValue by
    the following formula:
    (snowballSnow / snowballTime) ^ snowballQuality
    In the end, you must print the highest calculated
    snowballValue.
Input:
    •	On the first input line, you will receive N –
    the number of snowballs.
    •	On the next N * 3 input lines,
    you will receive data about snowballs.
Output:
    •	As output, you must print the highest calculated
    snowballValue, by the formula specified above.
    •	The output format is:
    "{snowballSnow} : {snowballTime} =
    {snowballValue} ({snowballQuality})"
Constraints:
    •	The number of snowballs (N) will be an integer
    in the range [0, 100].
    •	The snowballSnow is an integer in the range [0, 1000].
    •	The snowballTime is an integer in the range [1, 500].
    •	The snowballQuality is an integer in the range [0, 100].
    •	Allowed working time / memory: 100ms / 16MB.
Examples
    2
    10
    2
    3
    5
    5
    5
    ->
    10 : 2 = 125 (3)

    3
    10
    5
    7
    16
    4
    2
    20
    2
    2
    ->
    10 : 5 = 128 (7)
*/
package DataTypesAndVariables.Exercise;

import static java.lang.Math.pow;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Snowballs {
    static Scanner scanner = new Scanner(in);
    public static void main(String[] args) {
        int numberOfSnowBallsMade = setValue(0, 100);
        double highestCalculatedSnowballValue = 0;
        int snowballSnowWinner = 0;
        int snowballTimeWinner = 0;
        int snowballQualityWinner = 0;
        for (int i=1; i<=numberOfSnowBallsMade; i++) {
            int snowballSnow = setValue(0, 1000);
            int snowballTime = setValue(1, 500);
            int snowballQuality = setValue(0, 100);
            double snowballValue =
                pow(
                    ((double) snowballSnow / snowballTime),
                    snowballQuality
                );
            if (snowballValue > highestCalculatedSnowballValue) {
                highestCalculatedSnowballValue = snowballValue;
                snowballSnowWinner = snowballSnow;
                snowballTimeWinner = snowballTime;
                snowballQualityWinner = snowballQuality;
            }
        }
        out.printf(
            "%d : %d = %.0f (%d)",
            snowballSnowWinner,
            snowballTimeWinner,
            highestCalculatedSnowballValue,
            snowballQualityWinner
        );
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