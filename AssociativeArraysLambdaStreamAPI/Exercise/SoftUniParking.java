/*
Task:
    SoftUni just got a new parking lot. It's so fancy, it even has online parking validation.
    Except, the online service doesn't work. It can only receive users' data but doesn't know what to do with it.
    Good thing you're on the dev team and know how to fix it, right?
    Write a program that validates parking for an online service. Users can register to park and unregister to leave.
    The program receives 2 commands:
    •	"register {username} {licensePlateNumber}":
    o	The system only supports one car per user at the moment,
    so if a user tries to register another license plate using the same username, the system should print:
    "ERROR: already registered with plate number {licensePlateNumber}"
    o	If the aforementioned checks pass successfully, the plate can be registered, so the system should print:
    "{username} registered {licensePlateNumber} successfully"
    •	"unregister {username}":
    o	If the user is not present in the database, the system should print:
    "ERROR: user {username} not found"
    o	If the aforementioned check passes successfully, the system should print:
    "{username} unregistered successfully"
    After you execute all of the commands,
    print all the currently registered users and their license plates in the format:
    •	"{username} => {licensePlateNumber}"
Input:
    •	First line: n - number of commands – integer.
    •	Next n lines: commands in one of two possible formats:
    o	Register: "register {username} {licensePlateNumber}"
    o	Unregister: "unregister {username}"
    The input will always be valid, and you do not need to check it explicitly.
Examples:
    5
    register John CS1234JS
    register George JAVA123S
    register Andy AB4142CD
    register Jesica VR1223EE
    unregister Andy
    ->
    John registered CS1234JS successfully
    George registered JAVA123S successfully
    Andy registered AB4142CD successfully
    Jesica registered VR1223EE successfully
    Andy unregistered successfully
    John => CS1234JS
    George => JAVA123S
    Jesica => VR1223EE

    4
    register Jony AA4132BB
    register Jony AA4132BB
    register Linda AA9999BB
    unregister Jony
    ->
    Jony registered AA4132BB successfully
    ERROR: already registered with plate number AA4132BB
    Linda registered AA9999BB successfully
    Jony unregistered successfully
    Linda => AA9999BB

    6
    register Jacob MM1111XX
    register Anthony AB1111XX
    unregister Jacob
    register Joshua DD1111XX
    unregister Lily
    register Samantha AA9999BB
    ->
    Jacob registered MM1111XX successfully
    Anthony registered AB1111XX successfully
    Jacob unregistered successfully
    Joshua registered DD1111XX successfully
    ERROR: user Lily not found
    Samantha registered AA9999BB successfully
    Anthony => AB1111XX
    Joshua => DD1111XX
    Samantha => AA9999BB
 */
package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.*;

import static java.lang.System.out;

public class SoftUniParking {
    static Scanner scanner = new Scanner(System.in);
    private static final Map<String, String> registration = new LinkedHashMap<>();

    public static void main(String[] args) {
        int inputCount = setNumber();
        executeCommands(inputCount);

        registration.forEach((user, licensePlate) -> out.printf("%s => %s\n", user, licensePlate));
    }

    private static void executeCommands(int inputCount) {
        for (int i = 1; i <= inputCount; i++) {
            String[] input = setText().split(" ");
            String command = input[0];
            String username = input[1];

            switch (command) {
                case "register":
                    String licensePlateNumber = input[2];

                    register(username, licensePlateNumber);
                    break;
                case "unregister":
                    unregister(username);
                    break;
            }
        }
    }

    private static void register(String username, String licensePlateNumber) {
        if (registration.containsKey(username)) {
            out.printf("ERROR: already registered with plate number %s\n", licensePlateNumber);
        } else {
            registration.put(username, licensePlateNumber);
            out.printf("%s registered %s successfully\n", username, licensePlateNumber);
        }
    }

    private static void unregister(String username) {
        if (registration.containsKey(username)) {
            registration.remove(username);
            out.printf("%s unregistered successfully\n", username);
        } else {
            out.printf("ERROR: user %s not found\n", username);
        }
    }

    private static final String specialChars = "!#$%&'()*+./;<=>?@[]^_`{|}-"; // 0123456789 abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ -
    private static Object value;
    private static final Object minValue = 0;
    private static final Object maxValue = Integer.MAX_VALUE;
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