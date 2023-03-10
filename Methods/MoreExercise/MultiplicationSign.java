/*
Task:
    You are given a number num1, num2, and num3.
    Write a program that finds if num1 * num2 * num3 (the product) is negative, positive, or zero.
    Try to do this WITHOUT multiplying the 3 numbers.
Examples:
    2
    3
    -1
    ->
    negative

    2
    3
    1
    ->
    positive
*/
package Methods.MoreExercise;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class MultiplicationSign {
    static Scanner scanner = new Scanner(in);
    static boolean isPositive = true;
    static boolean isZero = false;

    public static void main(String[] args) {
        int num1 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int num2 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int num3 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);

        checkIsPositive(num1);
        checkIsPositive(num2);
        checkIsPositive(num3);
        checkHasZero(num1, num2, num3);

        printIsPositive(isPositive);
    }

    private static void printIsPositive(boolean isPositive) {
        if (isZero) {
            out.println("zero");
        } else if (isPositive) {
            out.println("positive");
        } else {
            out.println("negative");
        }
    }

    private static void checkHasZero(int num1, int num2, int num3) {
        if (num1 == 0 || num2 == 0 || num3 == 0) {
            isZero = true;
        }
    }

    private static void checkIsPositive(int num) {
        if (num < 0) {
            isPositive = !isPositive;
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
