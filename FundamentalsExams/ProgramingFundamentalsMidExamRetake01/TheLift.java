/*
Условие:
    Write a program that finds a place for the tourist on a lift.
    Every wagon should have a maximum of 4 people on it.
    If a wagon is full, you should direct the people to the next one with space available.
Input:
    • On the first line, you will receive how many people are waiting to get on the lift
    • On the second line,
    you will receive the current state of the lift separated by a single space: " ".
Output:
    When there is no more available space left on the lift, or there are no more people in the queue,
    you should print on the console the final state of the lift's wagons separated by " "
    and one of the following messages:
    • If there are no more people and the lift have empty spots, you should print:
    "The lift has empty spots!
    {wagons separated by ' '}"
    • If there are still people in the queue and no more available space, you should print:
    "There isn't enough space! {people} people in a queue!
    {wagons separated by ' '}"
    • If the lift is full and there are no more people in the queue,
     you should print only the wagons separated by " "
Examples:
    15
    0 0 0 0
    ->
    The lift has empty spots!
    4 4 4 3
        First state - 4 0 0 0 -> 11 people left
        Second state – 4 4 0 0 -> 7 people left
        Third state – 4 4 4 0 -> 3 people left
    20
    0 2 0
    ->
    There isn't enough space! 10 people in a queue!
    4 4 4
        First state - 4 2 0  -> 16 people left
        Second state – 4 4 0  -> 14 people left
        Third state – 4 4 4 -> 10 people left, but there are no more wagons.
*/
package FundamentalsExams.ProgramingFundamentalsMidExamRetake01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class TheLift {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int peopleInQ = setValue(0, Integer.MAX_VALUE);
        int peopleOnLift = 0;

        int[] wagonsState = Arrays
                .stream(setValue().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 1; i <= peopleInQ; i++) {
            for (int j = 0; j < wagonsState.length; j++) {
                if (wagonsState[j] < 4) {
                    wagonsState[j] += 1;
                    peopleOnLift++;
                    break;
                }
            }
        }

        boolean wagonsAreFull = checkIfWagonsAreFull(wagonsState);

        int peopleLeft = peopleInQ - peopleOnLift;
        displayResult(peopleLeft, wagonsState, wagonsAreFull);
    }

    private static boolean checkIfWagonsAreFull(int[] wagonsState) {
        int allWagons = 0;
        boolean wagonsAreFull = false;

        for (int wagon : wagonsState) {
            allWagons += wagon;
        }

        if (allWagons == wagonsState.length * 4) {
            wagonsAreFull = true;
        }

        return wagonsAreFull;
    }

    private static void displayResult(int peopleLeft, int[] wagonsState, boolean wagonsAreFull) {
        if (peopleLeft > 0) {
            out.printf("There isn't enough space! %d people in a queue!\n", peopleLeft);
            for (int wagon : wagonsState) {
                out.print(wagon + " ");
            }
        } else {
            if (!wagonsAreFull) {
                out.println("The lift has empty spots!");
            }

            for (int wagon : wagonsState) {
                out.print(wagon + " ");
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

    static int stringCount = 0; // при въвеждане на низ, броячът нараства

    // метод за откриване на грешни в низ от потребителя
    private static String setValue() {
        String value = scanner.nextLine();

        /* ако има забранени символи или не следва задените шаблони,
        низът на потребителя не се приема и трябва да се въведе нов */
        if (!hasValidChars(value) || !doesFollowTemplate(value)) {
            return setValue();
        } else {
            return value;
        }
    }

    // хващане на специални/забранени символи
    private static <T> boolean hasValidChars(T value) {
        // !#$%&'()*+,./:;<=>?@[]^_`{|}
        // 0123456789
        // abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}"; // може да се променят забранените символи
        boolean isSpecialChar = false;
        char specialChar = ' ';

        // Търсене на забранен символ чрез сравняване на входните данни с низът със забранени символи
        for (int i = 0; i < ((String) value).length(); i++) {
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i); // открит забранен символ
                break;
            }
        }

        // При грешка се показва на потребителя, кой от въведените му символи е забранен
        if (isSpecialChar) {
            if (specialChar == ' ') {
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            } else {
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            }

            return false;
        }

        return true;
    }

    // подтикване на потребителя да въвежда предварително зададени низове.
    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};

        // тук се нагласят шаблоните на низовете, ако имат такива.
        if (stringCount == 1) {
            requiredStrings = new String[]{}; // на първия низ. Празно ако няма такъв.
        } else if (stringCount == 2) {
            requiredStrings = new String[]{}; // на втория низ. Празно ако няма такъв.
        } else { // могат да се добавят и още шаблони преди else. Последния шаблон стои празен.
            requiredStrings = new String[]{};
        }

        // ако е зададен шаблон се изпълнява следния код.
        if (requiredStrings.length != 0) {
            List<String> requiredList = List.of(requiredStrings); // създава се списък със задължителни входни данни

            if (!requiredList.contains(value.toString())) { // ако се въведе нещо, различно от зададеното в шаблона
                out.println("Моля въведете един от следните избори:");

                // завърта се цикъл, който да покаже на потребителя, кои са възможните опции
                out.println(String.join(" | ", requiredStrings)); // разделител

                stringCount--; // не се брои сгрешения низ.

                return false;
            }
        }

        return true;
    }
}