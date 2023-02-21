/*
Условие:
    Write a program that reads a sequence of strings from the console. Encrypt every string by summing:
    •	The code of each vowel multiplied by the string length.
    •	The code of each consonant is divided by the string length.
    Sort the number sequence in ascending order and print it on the console.
    On the first line, you will always receive the number of strings you must read.
Examples:
    4
    Peter
    Maria
    Katya
    Todor
    ->
    1032
    1071
    1168
    1532
        Peter = 1071
        Maria = 1532
        Katya = 1032
        Todor = 1168
    3
    Sofia
    London
    Washington
    ->
    1396
    1601
    3202
        Sofia = 1601
        London = 1396
        Washington = 3202
*/
package Arrays.MoreExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class EncryptSortAndPrintArray {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int countOfStrings = setValue(0, Integer.MAX_VALUE);
        int wordValue = 0;
        List<Integer> valueList = new ArrayList<>();

        for (int word = 1; word <= countOfStrings; word++) {
            String input = scanner.nextLine();

            for (int character = 0; character < input.length(); character++) {
                wordValue += calculateValueFor(character, input);
            }

            valueList.add(wordValue);
            wordValue = 0;
        }

        List<Integer> sortedValueList = valueList.stream().sorted().collect(Collectors.toList());
        sortedValueList.forEach(System.out::println);
    }

    private static int calculateValueFor(int character, String input) {
        int charValue;
        List<Character> vowelsList = List.of('a', 'e', 'o', 'u', 'i', 'A', 'E', 'O', 'U', 'I');
        char currentChar = input.charAt(character);

        if (vowelsList.contains(currentChar)) {
            charValue = (int) currentChar * input.length();
        } else {
            charValue = (int) currentChar / input.length();
        }

        return charValue;
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