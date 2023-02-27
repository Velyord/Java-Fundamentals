/*
Task:
    In the "Tribonacci" sequence, every number is formed by the sum of the previous 3.
    You are given a number num.
    Write a program that prints num numbers from the Tribonacci sequence, each on a new line,
    starting from 1. The input comes as a parameter named num.
    The value num will always be a positive integer.
Examples:
    4 -> 1 1 2 4
    8 -> 1 1 2 4 7 13 24 44
*/
package Methods.MoreExercise;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class TribonacciSequence {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int number = setValue(1, Integer.MAX_VALUE);
        int[] numberArray = new int[number];

        getTribonacci(numberArray);
        printArray(numberArray);
    }

    private static void printArray(int[] numberArray) {
        for (int j : numberArray) {
            out.print(j + " ");
        }
    }

    private static void getTribonacci(int[] numberArray) {
        for (int i = 0; i < numberArray.length; i++) {
            if (i == 0) {
                numberArray[i] = 1;
            } else if (i == 1) {
                numberArray[i] = 1;
            } else if (i == 2) {
                numberArray[i] = 2;
            } else {
                numberArray[i] =
                        numberArray[i-1] + numberArray[i-2] + numberArray[i-3];
            }
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
