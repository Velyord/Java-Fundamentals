/*
Условие:
    Read n numbers and print them in reverse order.
Examples:
    3
    10
    20
    30
    ->
    30 20 10

    3
    30
    20
    10
    ->
    10 20 30

    1
    10
    ->
    10
*/
package Arrays.Lab;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class PrintNumbersInReverseOrder {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int numberCount = setValue(0, Integer.MAX_VALUE);
        int[] numberArray = populateWith(numberCount);
        displayArrayInReverseOrder(numberArray);
    }

    private static void displayArrayInReverseOrder(int[] numberArray) {
        for (int i = numberArray.length - 1; i >= 0; i--) {
            out.print(numberArray[i] + " ");
        }
    }

    private static int[] populateWith(int numberCount) {
        int[] array = {};

        for (int i = 1; i <= numberCount; i++) {
            int input = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
            array = addIntToArray(input, array);
        }

        return array;
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
    private static <T> String getType(T max) {
        if (max instanceof Double) {
            return "double";
        } else if (max instanceof Float) {
            return "float";
        } else if (max instanceof Long) {
            return "long";
        } else {
            return "int";
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

    // добавяне на елемент към масив
    public static int[] addIntToArray(int toBeAdded, int[] array) {
        // създаване на нов масив с 1 индекс повече от дадения масив.
        int[] newArray = new int[array.length + 1];

        // копиране на данните от данения масив в новия масив
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        // добавяне на новия елемент на последната позиция на новия масив
        int lastPosition = newArray.length - 1;
        newArray[lastPosition] = toBeAdded;

        return newArray;
    }
}