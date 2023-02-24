/*
Task:
    Read an integer n from the console.
    Find all top numbers in the range [1 … n] and print them.
    A top number holds the following properties:
        • Its sum of digits is divisible by 8, e.g. 8, 16, 88.
        • Holds at least one odd digit, e.g. 232, 707, 87578.
Examples:
    50
    ->
    17
    35

    100
    ->
    17
    35
    53
    71
    79
    97

*/
package Methods.Exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class TopNumber {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int number = setValue(1, Integer.MAX_VALUE);

        printTopNumbers(number);
    }

    private static void printTopNumbers(int number) {
        for (int i = 17; i <= number; i++) {
            boolean isSumOfDigitsDivisibleBy8 = isDivisibleBy8(getSumOfDigits(i));
            boolean hasAtLeastOneOddDigit = hasOddDigits(i);

            if (isSumOfDigitsDivisibleBy8 && hasAtLeastOneOddDigit) {
                out.println(i);
            }
        }
    }

    private static boolean hasOddDigits(int number) {
        List<Integer> digitList = Arrays
                .stream(String.valueOf(number).split(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        for (Integer digit : digitList) {
            if (digit % 2 != 0) {
                return true;
            }
        }

        return false;
    }

    private static boolean isDivisibleBy8(int number) {
        return number % 8 == 0;
    }

    private static int getSumOfDigits(int number) {
        int sum = 0;

        List<Integer> digitList = Arrays
                .stream(String.valueOf(number).split(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        for (Integer digit : digitList) {
            sum += digit;
        }

        return sum;
    }

    // метод за въвеждане на число в дадени граници
    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        String type = getType(max); // намиране на типа му според зададената му максимална граница
        Object value = setAndCheckInputFor(type); // задаване на числото според неговия тип и проверка за изключения

        // ако не е в дадената граница се повтаря метода
        if (!isValueBetweenMinAndMax(value, min, max, type)) {
            return setValue(min, max);
        } else {
            return (T) value;
        }
    }

    // метод за намиране на типа на число
    private static <T> String getType(T variable) {
        if (variable instanceof Double) {
            return "double";
        } else if (variable instanceof Float) {
            return "float";
        } else if (variable instanceof Long) {
            return "long";
        } else if (variable instanceof Integer) {
            return "int";
        } else {
            return "String";
        }
    }

    // задава се число според прихванатия му тип, ако се хване изключение, трябва да се въведе ново число
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

    // метод за проверка на входното число, дали е в зададените граници според неговия тип
    private static <T> boolean isValueBetweenMinAndMax(T value, T min, T max, String type) {
        // променливите са за постигане на максимално негативно число при числата с плаваща запетая.
        double minDouble;
        float minFloat;

        // проверка дали входното число е правилно избрано според зададените граници
        switch (type) {
            case "double":
                // ако е зададено най-малкото число, го замести с най-голямото умножено по -1
                minDouble = (double) min == Double.MIN_VALUE ? -1 * Double.MAX_VALUE : (double) min;
                if ((double) value >= minDouble && (double) value <= (double) max) {
                    return true;
                } break;
            case "float":
                // ако е зададено най-малкото число, го замести с най-голямото умножено по -1
                minFloat = (float) min == Float.MIN_VALUE ? -1 * Float.MAX_VALUE : (float) min;
                if ((float) value >= minFloat && (float) value <= (float) max) {
                    return true;
                } break;
            case "long":
                if ((long) value >= (long) min && (long) value <= (long) max) {
                    return true;
                } break;
            case "int":
                if ((int) value >= (int) min && (int) value <= (int) max) {
                    return true;
                } break;
        }

        // при грешка трябва да се въведе ново число
        out.printf("Моля въведете число между %s и %s:\n", min, max);
        return false;
    }
}