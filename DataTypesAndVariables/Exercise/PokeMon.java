/*
Условие:
    A Poke Mon is a special type of pokemon which likes to Poke others.
    But at the end of the day, the Poke Mon wants to keep statistics about how many pokes
    it has managed to make.
    The Poke Mon pokes his target and then proceeds to poke another target.
    The distance between his targets reduces his poke power.
    You will be given the poke power the Poke Mon has, N – an integer.
    Then you will be given the distance between the poke targets, M – an integer.
    Then you will be given the exhaustionFactor Y – an integer.
    Your task is to start subtracting M from N until N becomes less than M, i.e.
    the Poke Mon does not have enough power to reach the next target.
    Every time you subtract M from N, that means you've reached a target and poked it successfully.
    COUNT how many targets you've poked – you'll need that count.
    The Poke Mon becomes gradually more exhausted.
    IF N becomes equal to EXACTLY 50 % of its original value, you must divide N by Y,
    if it is POSSIBLE. This DIVISION is between integers.
    If a division is not possible, you should NOT do it.
    Instead, you should continue subtracting.
    After dividing, you should continue subtracting from N, until it becomes less than M.
    When N becomes less than M,
    you must take what has remained of N and the count of targets you've poked and print them as output.

NOTE:
    When you are calculating percentages, you should be PRECISE at maximum.
Example:
    505 is NOT EXACTLY 50 % from 1000, its 50.5 %.
Input:
    • The input consists of 3 lines.
    • On the first line, you will receive N – an integer.
    • On the second line, you will receive M – an integer.
    • On the third line, you will receive Y – an integer.
Output:
    • The output consists of 2 lines.
    • On the first line, print what has remained of N, after subtracting from it.
    • On the second line, print the count of targets, you've managed to poke.
Constraints:
    • The integer N will be in the range [1, 2.000.000.000].
    • The integer M will be in the range [1, 1.000.000].
    • The integer Y will be in the range [0, 9].
    • Allowed time / memory: 16 MB / 100ms.
Examples:
    5
    2
    3
    ->
    1
    2
        N = 5, M = 2, Y = 3.
        We start subtracting M from N.
        N – M = 3. 1 target poked.
        N – M = 1. 2 targets poked.
        N < M.
        We print what has remained of N, which is 1.
        We print the count of targets, which is 2.
    10
    5
    2
    ->
    2
    1
        N = 10, M = 5, Y = 2.
        We start subtracting M from N.
        N – M = 5. (N is still not less than M, they are equal).
        N became EXACTLY 50 % of its original value.
        5 is 50 % from 10. So we divide N by Y.
        N / Y = 5 / 2 = 2. (INTEGER DIVISION).
*/
package DataTypesAndVariables.Exercise;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class PokeMon {
    static Scanner scanner = new Scanner(in);
    public static void main(String[] args) {
        int N = setValue(1, 2000000000);
        int originalN = N;
        int M = setValue(1, 1000000);
        int Y = setValue(0, 9);
        int targetsPoked = 0;
        while (N >= M) {
            N -= M;
            targetsPoked++;
            if (N == originalN / 2)
                if (Y != 0)
                    N /= Y;
        }
        out.println(N);
        out.println(targetsPoked);
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