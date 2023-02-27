/*
Task:
    You are given the coordinates of four points in the 2D plane.
    The first and the second pair of points form two different lines.
    Print the longer line in the format "(X1, Y1)(X2, Y2)"
    starting with the point that is closer to the center of the coordinate system (0, 0)
    (You can reuse the method that you wrote for the previous problem).
    If the lines are of equal length, print only the first one.
Examples:
    2
    4
    -1
    2
    -5
    -5
    4
    -3
    ->
    (4, -3)(-5, -5)

    4
    6
    -2
    -1
    2
    4
    7
    3
    ->
    (-2, -1)(4, 6)
*/
package Methods.MoreExercise;
// execution: Borovaneca (https://github.com/Borovaneca/Fundamentals-Java-Jan-2023/blob/main/Methods/Exercises/MoreExercise/LongerLine.java)

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class LongerLine {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int x1 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int y1 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int x2 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int y2 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);

        int x3 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int y3 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int x4 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int y4 = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);

        printLongestLine(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    static void printLongestLine(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        double result1 = Math.sqrt((Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
        double result2 = Math.sqrt((Math.pow((x3 - x4), 2) + Math.pow((y3 - y4), 2)));

        if (result1 >= result2) {
            closestToZero(x1, y1, x2, y2);
        } else {
            closestToZero(x3, y3, x4, y4);
        }
    }

    private static void closestToZero(int x1, int y1, int x2, int y2) {
        double result1 = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
        double result2 = Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2));

        if (result1 <= result2) {
            System.out.printf("(%d, %d)(%d, %d)", x1, y1, x2, y2);
        } else {
            System.out.printf("(%d, %d)(%d, %d)", x2, y2, x1, y1);
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
