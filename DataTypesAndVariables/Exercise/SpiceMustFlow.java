/*
Условие:
    Spice is Love, Spice is Life. And most importantly, Spice must flow.
    It must be extracted from the scorching sands of Arrakis,
    under the constant threat of giant sandworms.
    The Duke has tasked you with creating management software to make the work as efficient as possible.
    Write a program that calculates the total amount of spice extracted from a source.
    The source has a starting yield, which indicates how much spice can be mined on the first day.
    After it has been mined for a day, the yield drops by 10, meaning on the second day,
    it'll produce 10 less spice than on the first, on the third day 10 less than on the second,
    and so on (see examples).
    A source is considered profitable only while its yield is at least 100 –
    when less than 100 spices are expected in a day, abandon the source.
    The mining crew consumes 26 spices every day at the end of their shift
    and an additional 26 after the mine has been exhausted.
    Note that the workers cannot consume more spice than there is in storage.
    When the operation is complete,
    print on the console on two separate lines how many days the mine has operated
    and the total amount of spice extracted.
Input:
    You will receive a number representing the starting yield of the source.
Output:
    Print on the console on two separate lines how many days the mine has operated
    and the total amount of spice extracted.
Constraints:
    • The starting yield will be a positive integer within the range [0 … 2 147 483 647].
Examples:
    111
    ->
    2
    134
        Day 1, we extract 111 spices, and at the end of the shift, the workers consume 26, leaving 85.
        The yield drops by 10 to 101.
        On day 2, we extract 101 spices, the workers consume 26, leaving 75.
        The total is 160, and the yield has dropped to 91.
        Since the expected yield is less than 100, we abandon the source.
        The workers take another 26, leaving 134. The mine has operated for 2 days.
    200
    ->
    11
    1338
*/
package DataTypesAndVariables.Exercise;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class SpiceMustFlow {
    static Scanner scanner = new Scanner(in);
    public static void main(String[] args) {
        int startingYield = setValue(0, Integer.MAX_VALUE);
        extractSpicesAndCountDays(startingYield);
    }
    private static void extractSpicesAndCountDays(int startingYield) {
        int spices = 0;
        int daysCount = 0;
        while (startingYield >= 100) {
            spices += startingYield - 26;
            startingYield -= 10;
            daysCount++;
        }
        spices -= 26;
        if (spices < 0)
            spices = 0;
        out.println(daysCount + "\n" + spices);
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