/*
Условие:
    Every time John tries to pay his bills,
    he sees on the cash desk the sign:
    "I will be back in 30 minutes".
    One day John was sick of waiting and decided he needed a
    program that prints the time after 30 minutes.
    That way he won't have to wait at the desk and come at the
    appropriate time. He gave the assignment to you,
    so you have to do it.
Input:
    The input will be on two lines. On the first line,
    you will receive the hours, and on the second,
    you will receive the minutes.
Output:
    Print on the console the time after 30 minutes.
    The result should be in the format "hh:mm".
    The hours have one or two numbers,
    and the minutes always have two numbers (with leading zero).
Constraints:
    •	The hours will be between 0 and 23.
    •	The minutes will be between 0 and 59.
Examples:
    1
    46
    -> 2:16
    0
    01
    -> 0:31
    23
    59
    -> 0:29
    11
    08
    -> 11:38
    11
    32
    -> 12:02
*/
package Basic.Lab;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class BackIn30Minutes {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int hours = setValue(0, 23);
        int minutes = setValue(0, 59);
        
        calcTimeAfter30Min(hours, minutes);
    }

    private static void calcTimeAfter30Min(int hours, int minutes) {
        minutes += 30;
        if (minutes > 59) {
            minutes -= 60;
            hours += 1;
        }
        if (hours > 23)
            hours -= 24;

        out.printf("%d:%02d", hours, minutes);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
            boolean isSpecChar = false;
            value = scanner.nextLine();

            for (int i = 0; i < ((String) value).length(); i++)
                if (specialCharacters.contains(Character.toString(((String) value).charAt(i)))) {
                    isSpecChar = true;
                    break;
                }

            if (isSpecChar) {
                out.println("Моля въведете правилно наименование!");
                return setValue(null, null);
            }

            if (requiredString){
                stringCount++;
                String[] required = {};

                if (stringCount == 1)
                    required = new String[] {"Spring", "Summer", "Autumn", "Winter"};
                if (stringCount == 2)
                    required = new String[] {"Y", "N"};
                if (stringCount > 2) {
                    requiredString = false;
                    return (T) value;
                }

                List<String> requiredList = List.of(required);

                if (!requiredList.contains(value)){
                    out.print("Моля въведете един от следните избори: \n| ");
                    for (String thing : required)
                        out.print(thing + " | ");
                    out.println();

                    stringCount--;
                    return setValue(null, null);
                }
            }
        }
        else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка!");
                    value = null;
                    exit(1);
                }
            }
            catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == biggestInt)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == biggestDouble)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
        }

        return (T) value;
    }
}
