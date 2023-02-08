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

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class WaterOverflow {
    static int smallestByte = Byte.MIN_VALUE;
    static int biggestByte = Byte.MAX_VALUE;
    static short smallestShort = Short.MIN_VALUE;
    static short biggestShort = Short.MAX_VALUE;
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static long smallestLong = Long.MIN_VALUE;
    static long biggestLong = Long.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);
    enum Type {
        INT,
        DOUBLE,
        LONG
    }

    public static void main(String[] args) {
        short waterTankCapacity = 255;
        int n = setValue2(1, 20, Type.INT);
    }

    private static <T> T setValue2(T min, T max, Type type) {
        switch (type) {
            case DOUBLE:
                double value = Double.parseDouble(scanner.nextLine());
                double Min = (double) min;
                double Max = (double) max;
                if (value < Min || value > Max) {
                    if (Min == 0 && Max == Double.MAX_VALUE)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);
                    return setValue2(min, max, type);
                }
                Object finalValue = value;
                return (T) finalValue;
            case INT:
                int value = Double.parseDouble(scanner.nextLine());
                int Min = (int) min;
                int Max = (int) max;
                if (value < Min || value > Max) {
                    if (Min == 0 && Max == Integer.MAX_VALUE)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);
                    return setValue2(min, max, type);
                }
                Object finalValue = value;
                return (T) finalValue;
            default:
                out.println("Грешка в задаването на тип");
                int value = null;
                Object finalValue = value;
                return (T) finalValue;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|} 0123456789";
            boolean isSpecChar = false;
            value = scanner.nextLine();

            for (int i = 0; i < ((String) value).length(); i++)
                if (specialCharacters.contains(Character.toString(((String) value).charAt(i)))) {
                    isSpecChar = true;
                    break;
                }

            if (isSpecChar) {
                out.println("Моля въведете правилно наименование!");
                return setValue(null, null);
            }

            if (requiredString){
                stringCount++;
                String[] required = {};

                if (stringCount == 1)
                    required = new String[] {"Spring", "Summer", "Autumn", "Winter"};
                if (stringCount == 2)
                    required = new String[] {"Y", "N"};
                if (stringCount > 2) {
                    requiredString = false;
                    return (T) value;
                }

                List<String> requiredList = List.of(required);

                if (!requiredList.contains(value)){
                    out.print("Моля въведете един от следните избори: \n| ");
                    for (String thing : required)
                        out.print(thing + " | ");
                    out.println();

                    stringCount--;
                    return setValue(null, null);
                }
            }
        }
        else {
            try {
                if (max instanceof Long)
                    value = Long.parseLong(scanner.nextLine());
                else if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Short)
                    value = Short.parseShort(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка!");
                    value = null;
                    exit(1);
                }
            }
            catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }
            if (max instanceof Long) {
                if ((long) value < (long) min || (long) value > (long) max) {
                    if ((long) min == 0 && (long) max == biggestLong)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == biggestInt)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Short) {
                if ((short) value < (short) min || (short) value > (short) max) {
                    if ((long) min == 0 && (short) max == biggestShort)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == biggestDouble)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
        }

        return (T) value;
    }
}