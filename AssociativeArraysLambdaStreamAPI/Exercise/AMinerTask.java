/*
Task:
    Until you receive the "stop" command, you will be given a sequence of strings, each on a new line.
    Every odd line on the console represents a resource (e.g., Gold, Silver, Copper, etc.)
    and every even - quantity. Your task is to collect the resources and print them on a new line.
    Print the resources and their quantities in format: "{resource} –> {quantity}".
    The quantities inputs will be in the range [1 … 2 000 000 000].
Examples:
    Gold
    155
    Silver
    10
    Copper
    17
    stop
    ->
    Gold -> 155
    Silver -> 10
    Copper -> 17

    gold
    155
    silver
    10
    copper
    17
    gold
    15
    stop
    ->
    gold -> 170
    silver -> 10
    copper -> 17
 */
package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class AMinerTask {
    static Scanner scanner = new Scanner(System.in);
    private static final Map<String, Long> resourcesMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        fillMap();
        resourcesMap.forEach((key, value) -> out.println(key + " -> " + value));
    }

    private static void fillMap() {
        String resource = setText();

        while (!resource.equals("stop")) {
            long addQuantity = setNumber();

            if (resourcesMap.containsKey(resource)) {
                long quantity = resourcesMap.get(resource);

                resourcesMap.put(resource, quantity + addQuantity);
            } else {
                resourcesMap.put(resource, addQuantity);
            }

            resource = setText();
        }
    }

    private static Object value;
    private static final Object minValue = 1L;
    private static final Object maxValue = 2000000000L;
    private static final String valueType = getType();

    @SuppressWarnings("unchecked")
    public static <T> T setNumber() { // метод за въвеждане на правилно число в дадени граници
        value = init();

        if (!isInScope()) { // ако не е в дадената граница се повтаря метода
            return setNumber();
        } else {
            return (T) value;
        }
    }

    private static <T> String getType() { // метод за намиране на типа на число
        if (maxValue instanceof Double) {
            return "double";
        } else if (maxValue instanceof Float) {
            return "float";
        } else if (maxValue instanceof Long) {
            return "long";
        } else if (maxValue instanceof Integer) {
            return "int";
        } else {
            return "String";
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T init() { // задава се число според прихванатия му тип, ако се хване изключение, трябва да се въведе ново число
        Object value;

        try {
            switch (valueType) {
                case "double": value = Double.parseDouble(scanner.nextLine()); break;
                case "float":  value = Float.parseFloat(scanner.nextLine());   break;
                case "long":   value = Long.parseLong(scanner.nextLine());     break;
                case "int":    value = Integer.parseInt(scanner.nextLine());   break;
                default:       value = null;                       break;
            }
        } catch (Exception e) {
            out.println("Невалидно число. Пробвайте пак!");

            return init();
        }

        return (T) value;
    }

    private static boolean isInScope() { // метод за проверка на входното число, дали е в зададените граници според неговия тип
        switch (valueType) { // проверка дали входното число е правилно избрано според зададените граници
            case "double": return isInDoubleScope();
            case "float":  return isInFloatScope();
            case "long":   return isInLongScope();
            case "int":    return isInIntScope();
            default:       return false;
        }
    }

    private static boolean isInDoubleScope() {
        double input = (double) value;
        double min = (double) minValue == Double.MIN_VALUE ? -Double.MAX_VALUE : (double) minValue; // постигане на максимално негативно число
        double max = (double) maxValue;

        if (input >= min && input <= max) {
            return true;
        } else { // при грешка трябва да се въведе ново число
            if (min == 0 && max == Double.MAX_VALUE) {
                out.println("Моля въведете положително число:");
            } else {
                out.printf("Моля въведете число между %s и %s:\n", min, max);
            }

            return false;
        }
    }

    private static boolean isInFloatScope() {
        float input = (float) value;
        float min = (float) minValue == Float.MIN_VALUE ? -Float.MAX_VALUE : (float) minValue; // постигане на максимално негативно число
        float max = (float) maxValue;

        if (input >= min && input <= max) {
            return true;
        } else { // при грешка трябва да се въведе ново число
            if (min == 0 && max == Float.MAX_VALUE) {
                out.println("Моля въведете положително число:");
            } else {
                out.printf("Моля въведете число между %s и %s:\n", min, max);
            }

            return false;
        }
    }

    private static boolean isInLongScope() {
        long input = (long) value;
        long min = (long) minValue;
        long max = (long) maxValue;

        if (input >= min && input <= max) {
            return true;
        } else { // при грешка трябва да се въведе ново число
            if (min == 0 && max == Long.MAX_VALUE) {
                out.println("Моля въведете положително число:");
            } else {
                out.printf("Моля въведете число между %s и %s:\n", min, max);
            }

            return false;
        }
    }

    private static boolean isInIntScope() {
        int input = (int) value;
        int min = (int) minValue;
        int max = (int) maxValue;

        if (input >= min && input <= max) {
            return true;
        } else { // при грешка трябва да се въведе ново число
            if (min == 0 && max == Integer.MAX_VALUE) {
                out.println("Моля въведете положително число:");
            } else {
                out.printf("Моля въведете число между %s и %s:\n", min, max);
            }

            return false;
        }
    }

    private static final String specialChars = "!#$%&'()*+./;<=>?@[]^_`{|}-"; // 0123456789 abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ -

    private static int stringCount = 0; // при въвеждане на низ, броячът нараства

    private static String stringValue;

    public static String setText() { // метод за откриване на грешни в низ от потребителя
        stringValue = scanner.nextLine();

        if (!hasValidChars() || !doesFollowTemplate()) { // ако има забранени символи или не следва задените шаблони, низът на потребителя не се приема и трябва да се въведе нов
            return setText();
        } else {
            return stringValue;
        }
    }

    private static boolean hasValidChars() { // хващане на специални/забранени символи
        boolean isSpecialChar = false;
        char specialChar = ' ';

        for (int i = 0; i < (stringValue).length(); i++) { // Търсене на забранен символ чрез сравняване на входните данни с низът със забранени символи
            if (specialChars.contains(Character.toString(stringValue.charAt(i)))) {
                isSpecialChar = true;
                specialChar = stringValue.charAt(i); // открит забранен символ
                break;
            }
        }

        if (isSpecialChar) { // При грешка се показва на потребителя, кой от въведените му символи е забранен
            if (specialChar == ' ') {
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            } else {
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            }

            return false;
        }

        return true;
    }

    private static boolean doesFollowTemplate() { // подтикване на потребителя да въвежда предварително зададени низове.
        stringCount++;
        String[] requiredStrings;

        // тук се нагласят шаблоните на низовете, ако имат такива.
        if (stringCount == 1) { // на първия низ. Празно ако няма такъв.
            requiredStrings = new String[]{};
        } else if (stringCount == 2) { // на втория низ. Празно ако няма такъв.
            requiredStrings = new String[]{};
        } else { // могат да се добавят и още шаблони преди else. Последния шаблон стои празен.
            requiredStrings = new String[]{};
        }

        if (requiredStrings.length != 0) { // ако е зададен шаблон се изпълнява следния код.
            List<String> requiredList = List.of(requiredStrings); // създава се списък със задължителни входни данни

            if (!requiredList.contains(stringValue)) { // ако се въведе нещо, различно от зададеното в шаблона
                out.println("Моля въведете един от следните избори:");
                out.println(String.join(" | ", requiredStrings)); // показване на потребителя, кои са възможните опции

                stringCount--; // шаблона се наглася отново

                return false;
            }
        }

        return true;
    }
}
