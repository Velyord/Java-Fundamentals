package ObjectsAndClasses.Exercise.Students;

import static ObjectsAndClasses.Exercise.Students.PersonalUtils.userInput;
import static java.lang.System.out;

public class NumberValidator {
    private static Object value;
    private static Object minValue = 0;
    private static Object maxValue = Integer.MAX_VALUE;
    private static final String valueType = getType();

    public static void setMinValue(Object minValue) {
        NumberValidator.minValue = minValue;
    }

    public static void setMaxValue(Object maxValue) {
        NumberValidator.maxValue = maxValue;
    }

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
                case "double": value = Double.parseDouble(userInput()); break;
                case "float":  value = Float.parseFloat(userInput());   break;
                case "long":   value = Long.parseLong(userInput());     break;
                case "int":    value = Integer.parseInt(userInput());   break;
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
}