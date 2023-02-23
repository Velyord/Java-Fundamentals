/*
Task:
    Create a program that reads an integer number
    and multiplies the sum of all its even digits by the sum of all its odd digits:
Examples:
    12345
    ->
    54
        12345 has 2 even digits - 2 and 4. Even digits have a sum of 6.
        Also, it has 3 odd digits - 1, 3, and 5. Odd digits have a sum of 9.
        Multiply 6 by 9, and you get 54.
    -12345
    ->
    54
*/
package Methods.Lab;

import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.System.in;
import static java.lang.System.out;

public class MultiplyEvensByOdds {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int integer = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);

        if (integer < 0) {
            integer = abs(integer);
        }

        int multipleOfEvensAndOdds = getMultipleOfEvensAndOdds(integer);

        out.println(multipleOfEvensAndOdds);
    }

    private static int getMultipleOfEvensAndOdds(int integer) {
        int evenSum = getSumOfDigits(integer, "even");
        int oddSum = getSumOfDigits(integer, "odd");

        return oddSum * evenSum;
    }

    private static int getSumOfDigits(int integer, String evenOrOdd) {
        int sum = 0;

        while (integer > 0) {
            int currentNumber = integer % 10;

            if (evenOrOdd.equals("even") && currentNumber % 2 == 0) {
                sum += currentNumber % 10;
            } else if (evenOrOdd.equals("odd") && currentNumber % 2 != 0) {
                sum += currentNumber % 10;
            }

            integer /= 10;
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