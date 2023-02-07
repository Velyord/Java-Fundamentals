/*
Условие:
    We are placing N orders at a time. You need to calculate
    the price on the following formula:
    ((daysInMonth * capsulesCount) * pricePerCapsule)
Input / Constraints:
    •	On the first line, you will receive integer N – the
    count of orders the shop will receive.
    •	For each order, you will receive the following
    information:
    o	Price per capsule - floating-point number in the range
    [0.00…1000.00].
    o	Days – integer in the range [1…31].
    o	Capsules count - integer in the range [0…2000].
    The input will be in the described format, there is no need
    to check it explicitly.
Output:
    The output should consist of N + 1 line. For each order, you
    must print a single line in the following format:
    •	"The price for the coffee is: ${price}"
    On the last line, you need to print the total price in the
    following format:
    •	 "Total: ${totalPrice}"
    The price must be formatted to 2 decimal places.
Examples:
    1
    1.53
    30
    8
    -> The price for the coffee is: $367.20
       Total: $367.20
            We are given only 1 order. Then we  use the formulas:
            orderPrice = 30 * 8 * 1.53 = 367.20
    2
    4.99
    31
    3
    0.35
    31
    5
        The price for the coffee is: $464.07
        The price for the coffee is: $54.25
        Total: $518.32
*/
package Basic.Exercise;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Orders {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int countOfOrders = setValue(0, biggestInt);
        double totalPrice = 0;
        for (int i = 0; i< countOfOrders; i++) {
            double pricePerCapsule = Double.parseDouble(scanner.nextLine());
            int days = setValue(1, 31);
            int capsulesCount = Integer.parseInt(scanner.nextLine());
            double price =
                    days * capsulesCount * pricePerCapsule;
            out.printf(
                    "The price for the coffee is: $%.2f\n",
                    price
            );
            totalPrice += price;
        }
        out.printf("Total: $%.2f", totalPrice);
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
