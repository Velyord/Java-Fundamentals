/*
Условие:
    You will receive a number representing how many lines we will get as input.
    On the next N lines, you will receive a string with 2 numbers separated by a single space.
    You need to compare them. If the left number is greater than the right number,
    you need to print the sum of all digits in the left number, otherwise,
    print the sum of all digits in the right number.
Examples
    2
    1000 2000
    2000 1000
    ->
    2
    2

    4
    123456 2147483647
    5000000 -500000
    97766554 97766554
    9999999999 8888888888
    ->
    46
    5
    49
    90
*/
package DataTypesAndVariables.MoreExercise;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class FromLeftToTheRight {
    static Scanner scanner = new Scanner(in);
    public static void main(String[] args) {
        int linesCount = setValue(0, Integer.MAX_VALUE);

        for (int i=1; i<=linesCount; i++) {
            String input = setValue();
            String firstNumber = "";
            String secondNumber = "";
            boolean isSpace = false;

            for (int j=0; j<input.length(); j++) {
                if (input.charAt(j) == ' ') {
                    isSpace = true;
                    continue;
                }
                if (!isSpace)
                    firstNumber += input.charAt(j);
                else
                    secondNumber += input.charAt(j);
            }
            long firstNumberAsNumber = Long.parseLong(firstNumber);
            long secondNumberAsNumber = Long.parseLong(secondNumber);
            int sum;

            if (firstNumberAsNumber > secondNumberAsNumber)
                sum = sumDigitsOf(firstNumber);
            else
                sum = sumDigitsOf(secondNumber);

            out.println(sum);
        }
    }
    private static int sumDigitsOf(String number) {
        int sum = 0;
        for (int i=0; i<number.length(); i++) {
            sum += number.charAt(i) - 48; // ASCII table
        }
        return sum;
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
        if (!hasValidChars(value))
            return setValue();
        return value;
    }
    private static <T> boolean hasValidChars(T value) {
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
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