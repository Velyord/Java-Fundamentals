/*
Examples:
    1 3 5 7 9
    exchange 1
    max odd
    min even
    first 2 odd
    last 2 even
    exchange 3
    end
    ->
    2
    No matches
    [5, 7]
    []
    [3, 5, 7, 9, 1]

    1 10 100 1000
    max even
    first 5 even
    exchange 10
    min odd
    exchange 0
    max even
    min even
    end
    ->
    3
    Invalid count
    Invalid index
    0
    2
    0
    [10, 100, 1000, 1]

    1 10 100 1000
    exchange 3
    first 2 odd
    last 4 odd
    end
    ->
    [1]
    [1]
    [1, 10, 100, 1000]
*/
package Methods.MoreExercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class ArrayManipulator {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int[] initialArray = Arrays
                .stream(setValue().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        String input = setValue();

        while (!input.equals("end")) {
            String[] commandArray = input.split(" ");
            String firstCommand = commandArray[0];
            String evenOrOdd;

            switch (firstCommand) {
                case "exchange":
                    int index = Integer.parseInt(commandArray[1]);

                    if (index >= initialArray.length || index < 0) {
                        out.println("Invalid index");
                    } else {
                        exchange(index, initialArray);
                    }

                    break;
                case "max":
                case "min":
                    evenOrOdd = commandArray[1];
                    minOrMaxOnEvenOrOdd(firstCommand, evenOrOdd, initialArray);
                    break;
                case "first":
                case "last":
                    int count = Integer.parseInt(commandArray[1]);

                    if (count > initialArray.length) {
                        out.println("Invalid count");
                    } else {
                        evenOrOdd = commandArray[2];
                        firstOrLastCountOnEvenOrOdd(firstCommand, count, evenOrOdd, initialArray);
                    }

                    break;
            }

            input = setValue();
        }

        out.println(Arrays.toString(initialArray));
    }

    private static void firstOrLastCountOnEvenOrOdd(
            String firstOrLast,
            int count,
            String evenOrOdd,
            int[] initialArray
    ) {
        boolean isFirst = firstOrLast.equals("first");
        boolean isPickedEven = evenOrOdd.equals("even");
        int[] newArray = {};

        if (isFirst) {
            for (int element : initialArray) {
                if (newArray.length == count) {
                    break;
                }

                boolean isEven = isPickedEven && element % 2 == 0;
                boolean isOdd = !isPickedEven && element % 2 != 0;

                if (isEven) {
                    newArray = addToArray(element, newArray);
                } else if (isOdd) {
                    newArray = addToArray(element, newArray);
                }
            }
        } else {
            for (int i = initialArray.length - 1; i >= 0; i--) {
                if (newArray.length == count) {
                    break;
                }

                int element = initialArray[i];
                boolean isEven = isPickedEven && element % 2 == 0;
                boolean isOdd = !isPickedEven && element % 2 != 0;

                if (isEven) {
                    newArray = addToArray(element, newArray);
                } else if (isOdd) {
                    newArray = addToArray(element, newArray);
                }
            }
        }

        if (isFirst) {
            out.println(Arrays.toString(newArray));
        } else {
            StringBuilder result = new StringBuilder("[");

            for (int i = newArray.length - 1; i >= 0; i--) {
                result.append(newArray[i]);

                if (i != 0) {
                    result.append(", ");
                }
            }

            result.append("]");

            System.out.println(result);
        }

    }

    private static void minOrMaxOnEvenOrOdd(String minOrMax, String evenOrOdd, int[] initialArray) {
        boolean isPickedEven = evenOrOdd.equals("even");
        boolean isPickedMax = minOrMax.equals("max");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int maxIndex = -1;
        int minIndex = -1;

        for (int i = 0; i < initialArray.length; i++) {
            boolean isEven = isPickedEven && initialArray[i] % 2 == 0;
            boolean isOdd = !isPickedEven && initialArray[i] % 2 != 0;
            boolean isMax = isPickedMax && initialArray[i] >= max;
            boolean isMin = !isPickedMax && initialArray[i] <= min;

            if (isEven) {
                if (isMax) {
                    max = initialArray[i];
                    maxIndex = i;
                } else if (isMin) {
                    min = initialArray[i];
                    minIndex = i;
                }
            } else if (isOdd) {
                if (isMax) {
                    max = initialArray[i];
                    maxIndex = i;
                } else if (isMin) {
                    min = initialArray[i];
                    minIndex = i;
                }
            }
        }

        if (isPickedMax) {
            printIndex(maxIndex);
        } else {
            printIndex(minIndex);
        }
    }

    private static void printIndex(int index) {
        if (index == -1) {
            out.println("No matches");
        } else {
            out.println(index);
        }
    }

    private static void exchange(int index, int[] initialArray) {
        int[] arrayFirstPart = new int[index + 1];
        int[] arraySecondPart = new int[initialArray.length - (index + 1)];

        for (int i = index + 1; i < initialArray.length; i++) {
            arraySecondPart[i - (index + 1)] = initialArray[i];
        }

        for (int i = 0; i <= index; i++) {
            arrayFirstPart[i] = initialArray[i];
        }

        for (int i = 0; i < arraySecondPart.length; i++) {
            initialArray[i] = arraySecondPart[i];
        }

        for (int i = arraySecondPart.length; i < initialArray.length; i++) {
            initialArray[i] = arrayFirstPart[i - arraySecondPart.length];
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

    // добавяне на елемент към масив
    private static int[] addToArray(int toBeAdded, int[] array) {
        int[] newArray = new int[array.length + 1]; // създаване на нов масив с 1 индекс повече от дадения масив.
        System.arraycopy(array, 0, newArray, 0, array.length); // копиране на данните от данения масив в новия масив
        newArray[newArray.length - 1] = toBeAdded; // добавяне на новия елемент на последната позиция на новия масив
        return newArray;
    }
}
