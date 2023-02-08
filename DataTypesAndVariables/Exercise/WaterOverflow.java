/*
Условие:
    You have a water tank with a capacity of 255 liters.
    On the next n lines, you will receive liters of water, which you must pour into your tank.
    If the capacity is not enough, print "Insufficient capacity!" and continue reading the next line.
    On the last line, print the liters in the tank.
Input:
    The input will be on two lines:
        • On the first line, you will receive n – the number of lines, which will follow
        • On the next n lines – you receive quantities of water, which you have to pour into the tank
Output:
    Every time you do not have enough capacity in the tank to pour the given liters, print:
    "Insufficient capacity!".
    On the last line, print only the liters in the tank.
Constraints:
    • n will be in the interval [1…20].
    • liters will be in the interval [1…1000].
Examples:
    5
    20
    100
    100
    100
    20
    ->
    Insufficient capacity!
    240

    1
    1000
    ->
    Insufficient capacity!
    0

    7
    10
    20
    30
    10
    5
    10
    20
    ->
    105

    4
    250
    10
    20
    40
    ->
    Insufficient capacity!
    Insufficient capacity!
    Insufficient capacity!
    250
*/
package DataTypesAndVariables.Exercise;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class WaterOverflow {
    static Scanner scanner = new Scanner(in);
    public static void main(String[] args) {
        final int waterTankCapacity = 255;
        int waterTankFilled = 0;
        final int lines = setValue(1, 20);
        for (int i=1; i<=lines; i++) {
            int litersOfWater = Integer.parseInt(scanner.nextLine()); // for judge
            if (litersOfWater > waterTankCapacity - waterTankFilled)
                out.println("Insufficient capacity!");
            else
                waterTankFilled += litersOfWater;
        }
        out.println(waterTankFilled);
    }
    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        String type = getStringType(max);
        Object value;
        value = setAndCheckInputFor(type);
        if (!isValueBetweenMinAndMax(value, min, max, type))
            return setValue(min, max);
        return (T) value;
    }
    private static <T> String getStringType(T max) {
        if (max instanceof Double)
            return "double";
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
    private static String setValue() {
        String value;
        value = scanner.nextLine();
        if (!hasValidChars(value))
            return setValue();
        if (!doesFollowTemplate(value))
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
    static int stringCount = 0;
    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};
        if (stringCount == 1)
            requiredStrings = new String[]{"Summer", "Autumn", "Winter", "Spring"}; // fill if needed
        if (stringCount == 2)
            requiredStrings = new String[]{"yes", "no"}; // fill for second var
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