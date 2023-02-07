/*
Условие:
    As a MOBA challenger player, Peter has the bad habit of
    trashing his PC when he loses a game and rage quits.
    His gaming setup consists of a headset, mouse, keyboard,
    and display. You will receive Peter's lost games count.
    Every second lost game, Peter trashes his headset.
    Every third lost game, Peter trashes his mouse.
    When Peter trashes both his mouse and headset in the same
    lost game, he also trashes his keyboard.
    Every second time when he trashes his keyboard, he also
    trashes his display.
    You will receive the price of each item in his gaming setup.
    Calculate his rage expenses for renewing his gaming
    equipment.
Input / Constraints:
    •	On the first input line - lost games count –
    integer in the range [0, 1000].
    •	On the second line – headset price -
    the floating-point number in the range [0, 1000].
    •	On the third line – mouse price -
    the floating-point number in the range [0, 1000].
    •	On the fourth line – keyboard price -
    the floating-point number in the range [0, 1000].
    •	On the fifth line – display price -
    the floating-point number in the range [0, 1000].
Output:
    •	As output you must print Peter's total expenses:
    "Rage expenses: {expenses} lv."
    •	Allowed working time / memory: 100ms / 16MB.
Examples:
    7
    2
    3
    4
    5
    -> Rage expenses: 16.00 lv.
        Trashed headset -> 3 times
        Trashed mouse -> 2 times
        Trashed keyboard -> 1 time
        Total: 6 + 6 + 4 = 16.00 lv;
    23
    12.50
    21.50
    40
    200
    -> Rage expenses: 608.00 lv.
*/
package Basic.Exercise;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class RageExpenses {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int lostGamesCount = setValue(0, 1000);
        double headsetPrice = setValue(0.0, 1000.0);
        double mousePrice = setValue(0.0, 1000.0);
        double keyboardPrice = setValue(0.0, 1000.0);
        double displayPrice = setValue(0.0, 1000.0);
        int trashingHeadsetCounter = 0;
        int trashingMouseCounter  = 0;
        int trashingKeyboardCounter  = 0;
        int trashedHeadsets = 0;
        int trashedMouses = 0;
        int trashedKeyboards = 0;
        int trashedDisplays = 0;
        for (int i=1; i <= lostGamesCount; i++) {
            boolean hasTrashedHeadset = false;
            boolean hasTrashedMouse = false;
            trashingHeadsetCounter++;
            if (trashingHeadsetCounter == 2) {
                trashingHeadsetCounter = 0;
                trashedHeadsets++;
                hasTrashedHeadset = true;
            }
            trashingMouseCounter++;
            if (trashingMouseCounter == 3) {
                trashingMouseCounter = 0;
                trashedMouses++;
                hasTrashedMouse = true;
            }
            if (hasTrashedHeadset && hasTrashedMouse) {
                trashedKeyboards++;
                trashingKeyboardCounter++;
            }
            if (trashingKeyboardCounter == 2) {
                trashingKeyboardCounter = 0;
                trashedDisplays++;
            }
        }
        double rageExpenses =
                trashedHeadsets * headsetPrice +
                trashedMouses * mousePrice +
                trashedKeyboards * keyboardPrice +
                trashedDisplays * displayPrice;
        out.printf("Rage expenses: %.2f lv.", rageExpenses);
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
