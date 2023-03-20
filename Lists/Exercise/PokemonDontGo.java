/*
Task:
    Ely likes to play Pokémon Go a lot. But Pokémon Go went bankrupt…
    So, the developers made Pokémon Don't Go out of depression. And so, Ely now plays Pokémon Don't Go.
    In Pokémon Don't Go, when you walk to a certain Pokémon, those closest to you naturally get further,
    and those further from you get closer.
    You will receive a sequence of integers, separated by spaces - the distances to the Pokémons.
    Then you will begin receiving integers corresponding to indexes in that sequence.
    When you receive an index, you must remove the element at that index from the sequence
    (as if you've captured the Pokémon).
    • You must INCREASE the value of all elements in the sequence which are LESS or EQUAL to
    the removed element with the value of the removed element.
    • You must DECREASE the value of all elements in the sequence which are GREATER than
    the removed element with the value of the removed element.
    If the given index is LESS than 0, remove the first element of the sequence,
    and COPY the last element to its place.
    If the given index is GREATER than the last index of the sequence,
    remove the last element from the sequence, and COPY the first element to its place.
    The increasing and decreasing of elements should be done in these cases, also.
    The element whose value you should use is the REMOVED element.
    The program ends when the sequence has no elements (there are no Pokémons left for Ely to catch).
Input:
    • On the first line of input, you will receive a sequence of integers, separated by spaces.
    • On the next several lines, you will receive integers – the indexes.
Output:
    • When the program ends, you must print the summed up value of all REMOVED elements on the console.
Constraints:
    • The input data will consist ONLY of valid integers in the range [-2.147.483.648, 2.147.483.647].
Examples:
    4 5 3
    1
    1
    0
    ->
    14
        The array is {4, 5, 3}. The index is 1.
        We remove 5, and we increase all lower than it and decrease all higher than it.
        In this case, there are no higher than 5.
        The result is {9, 8}.
        The index is 1. So we remove 8 and decrease all higher than it.
        The result is {1}.
        The index is 0. So we remove 1.
        No elements are left, so we print the sum of all removed elements.
        5 + 8 + 1 = 14.
    5 10 6 3 5
    2
    4
    1
    1
    3
    0
    0
    ->
    51
        Step 1: {11, 4, 9, 11}
        Step 2: {22, 15, 20, 22}
        Step 3: {7, 5, 7}
        Step 4: {2, 2}
        Step 5: {4, 4}
        Step 6: {8}
        Step 7: {} (empty).
        Result = 6 + 11 + 15 + 5 + 2 + 4 + 8 = 51.
*/
package Lists.Exercise;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class PokemonDontGo {
    static Scanner scanner = new Scanner(in);
    private static int removedElementsSum = 0;

    public static void main(String[] args) {
        List<Integer> distancesToPokemonsList = Arrays
                .stream(setValue().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        while (!distancesToPokemonsList.isEmpty()) {
            int index = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (index >= 0 && index <= distancesToPokemonsList.size() - 1) {
                capturePokemon(index, distancesToPokemonsList);
            } else if (index < 0) {
                distancesToPokemonsList.add(
                        1,
                        distancesToPokemonsList.get(distancesToPokemonsList.size() - 1)
                );

                capturePokemon(0, distancesToPokemonsList);
            } else {
                distancesToPokemonsList.add(
                        distancesToPokemonsList.size() - 1,
                        distancesToPokemonsList.get(0)
                );

                capturePokemon(
                        distancesToPokemonsList.size() - 1,
                        distancesToPokemonsList
                );
            }
        }

        out.println(removedElementsSum);
    }

    private static void capturePokemon(int indexOfCapturedPokemon, List<Integer> list) {
        int valueOfCapturedPokemon = list.get(indexOfCapturedPokemon);

        list.remove(indexOfCapturedPokemon);
        removedElementsSum += valueOfCapturedPokemon;
        changeDistances(valueOfCapturedPokemon, list);
    }

    private static void changeDistances(int valueOfCapturedPokemon, List<Integer> list) {
        for (int i = 0; i < list.size(); i ++) {
            int currentPokemon = list.get(i);

            if (currentPokemon <= valueOfCapturedPokemon) {
                list.set(i, currentPokemon + valueOfCapturedPokemon);
            } else {
                list.set(i, currentPokemon - valueOfCapturedPokemon);
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
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; // може да се променят забранените символи
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