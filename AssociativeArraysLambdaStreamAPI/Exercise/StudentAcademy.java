/*
Task:
    Write a program that keeps the information about students and their grades.
    On the first line, you will receive number n. After that, you will receive n pair of rows. First, you will receive the student's name, after that, you will receive his grade. Check if the student already exists and if not - add him. Keep track of all grades for each student.
    When you finish reading data, keep students with an average grade higher or equal to 4.50.
    Print the students and their average grade in the format:
    "{name} –> {averageGrade}"
    Format the average grade to the 2nd decimal place.
Examples:
    5
    John
    5.5
    John
    4.5
    Alice
    6
    Alice
    3
    George
    5
    ->
    John -> 5.00
    Alice -> 4.50
    George -> 5.00

    5
    Amanda
    3.5
    Amanda
    4
    Rob
    5.5
    Christian
    5
    Robert
    6
    ->
    Rob -> 5.50
    Christian -> 5.00
    Robert -> 6.00
 */
package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.*;

import static java.lang.System.out;

public class StudentAcademy {
    static Scanner scanner = new Scanner(System.in);
    private static final Map<String, List<Double>> studentInfo = new LinkedHashMap<>();

    public static void main(String[] args) {
        int entries = setNumber();

        fillStudentInfo(entries);
        displayStudentGrades();
    }

    private static void fillStudentInfo(int entries) {
        for (int i = 1; i <= entries; i++) {
            String student = setText();
            minValue =  2.00;
            maxValue = 6.00;
            valueType = "double";
            double grade = setNumber();

            if (studentInfo.containsKey(student)) {
                studentInfo.get(student).add(grade);
            } else {
                List<Double> grades = new ArrayList<>();
                grades.add(grade);
                studentInfo.put(student, grades);
            }
        }
    }

    private static void displayStudentGrades() {
        for (Map.Entry<String, List<Double>> student : studentInfo.entrySet()) {
            double sumGrades = 0;

            List<Double> studentGradeList = student.getValue();

            for (Double grade : studentGradeList) {
                sumGrades += grade;
            }

            double avgGrades = sumGrades / studentGradeList.size();

            if (avgGrades >= 4.50) {
                out.printf("%s -> %.2f\n", student.getKey(), avgGrades);
            }
        }
    }

    private static Object value;
    private static Object minValue = 0;
    private static Object maxValue = Integer.MAX_VALUE;
    private static String valueType = getType();

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

    private static final String specialChars = "!#$%&'()*+./;<=>?@[]^_`{|}-:"; // 0123456789 abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ -

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