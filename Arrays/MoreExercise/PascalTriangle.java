/*
Условие:
    The triangle may be constructed in the following manner:
    In row 0 (the topmost row), there is a unique nonzero entry 1.
    Each entry of each subsequent row is constructed by adding the number above
    and to the left with the number above and to the right, treating blank entries as 0.
    For example, the initial number in the first (or any other) row is 1 (the sum of 0 and 1),
    whereas the numbers 1 and 3 in the third row are added to produce the number 4 in the fourth row.
    If you want more info about it: https://en.wikipedia.org/wiki/Pascal's_triangle
    Print each row element, separated with whitespace.
    Examples:
    4
    ->
    1
    1 1
    1 2 1
    1 3 3 1

    13
    ->
    1
    1 1
    1 2 1
    1 3 3 1
    1 4 6 4 1
    1 5 10 10 5 1
    1 6 15 20 15 6 1
    1 7 21 35 35 21 7 1
    1 8 28 56 70 56 28 8 1
    1 9 36 84 126 126 84 36 9 1
    1 10 45 120 210 252 210 120 45 10 1
    1 11 55 165 330 462 462 330 165 55 11 1
    1 12 66 220 495 792 924 792 495 220 66 12 1
*/
package Arrays.MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class PascalTriangle {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int numberOfRows = setValue(0, Integer.MAX_VALUE);
        List<Integer> addedNumbers = new ArrayList<>();
        List<Integer> addedNumbersOuter = new ArrayList<>();

        for (int i = 1; i <= numberOfRows; i++) {
            for (int j = 1; j <= i; j++) {
                int number;

                if (j == 1 || j == i) {
                    number = 1;
                    out.print(number + " ");
                } else {
                    number = addedNumbersOuter.get(j - 1) + addedNumbersOuter.get(j - 2);
                    out.print(number + " ");
                }

                addedNumbers.add(number);
            }

            addedNumbersOuter.removeAll(addedNumbersOuter);
            addedNumbersOuter.addAll(addedNumbers);
            addedNumbers.removeAll(addedNumbers);

            out.println();
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