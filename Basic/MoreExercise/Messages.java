/*
Условие:
    Write a program that emulates typing an SMS, following this guide:
    1
    2
    abc
    3
    def
    4
    ghi
    5
    jkl
    6
    mno
    7
    pqrs
    8
    tuv
    9
    wxyz
    0
    space
    Following the guide, 2 becomes "a", 22 becomes "b" and so on.
Examples:
    5
    44
    33
    555
    555
    666
    ->
    hello

    9
    44
    33
    999
    0
    8
    44
    33
    777
    33
    ->
    hey there

    7
    6
    33
    33
    8
    0
    6
    33
    ->
    meet me
*/
package Basic.MoreExercise;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Messages {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int messageLength = setValue(0, biggestInt);
        String message = "";
        for (int i=0; i<messageLength; i++) {
            int input = setValue(0, 9999);
            switch (input) {
                case 0:    message += " "; break;
                case 2:    message += "a"; break;
                case 22:   message += "b"; break;
                case 222:  message += "c"; break;
                case 3:    message += "d"; break;
                case 33:   message += "e"; break;
                case 333:  message += "f"; break;
                case 4:    message += "g"; break;
                case 44:   message += "h"; break;
                case 444:  message += "i"; break;
                case 5:    message += "j"; break;
                case 55:   message += "k"; break;
                case 555:  message += "l"; break;
                case 6:    message += "m"; break;
                case 66:   message += "n"; break;
                case 666:  message += "o"; break;
                case 7:    message += "p"; break;
                case 77:   message += "q"; break;
                case 777:  message += "r"; break;
                case 7777: message += "s"; break;
                case 8:    message += "t"; break;
                case 88:   message += "u"; break;
                case 888:  message += "v"; break;
                case 9:    message += "w"; break;
                case 99:   message += "x"; break;
                case 999:  message += "y"; break;
                case 9999: message += "z"; break;
            }
        }
        out.println(message);
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