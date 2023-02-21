/*
Условие:
    The Fibonacci sequence is quite a famous sequence of numbers.
    Each sequence member is calculated from the sum of the two previous elements.
    The first two elements are 1, 1. Therefore the sequence goes like 1, 1, 2, 3, 5, 8, 13, 21, 34…
    The following sequence can be generated with an array, but that's easy,
    so your task is to implement it recursively.
    So if the function GetFibonacci(n) returns the n’th Fibonacci number
    we can express it using GetFibonacci(n) = GetFibonacci(n-1) + GetFibonacci(n-2).
    However, this will never end and in a few seconds, a StackOverflow Exception is thrown.
    For the recursion to stop, it has to have a "bottom".
    At the bottom of the recursion is GetFibonacci(2) should return 1,
    and GetFibonacci(1) should return 1.
Input:
    • The user should enter the wanted Fibonacci number on the only line in the input.
Output:
    • The output should be the n'th Fibonacci number counting from 1.
Constraints:
    • 1 ≤ N ≤ 50
Examples:
    5 -> 5
    10 -> 55
    21 -> 10946
    For the Nth Fibonacci number, we calculate the N-1th and the N-2th number,
    but for the calculation of the N-1th number, we calculate the N-1-1th(N-2th)
    and the N-1-2th number, so we have a lot of repeated calculations.
*/
package Arrays.MoreExercise;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class RecursiveFibonacci {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int n = setValue(1, Integer.MAX_VALUE);

        int result = getFibonacci(n);
        out.println(result);
    }

    private static int getFibonacci(int n) {
        if (n == 2) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else {
            return getFibonacci(n - 1) + getFibonacci(n - 2);
        }
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