/*
Условие:
    You are given a group of people, type of the group,
    on which day of the week they will stay.
    Based on that information,
    calculate how much they must pay
    and print that price on the console. Use the table below.
    In each cell is the price for a single person.
    The output should look like that: "Total price: {price}".
    The price should be formatted to the second decimal point.
                    Friday	Saturday    Sunday
        Students	8.45	9.80	    10.46
        Business	10.90	15.60	    16
        Regular	    15	    20	        22.50
There are also discounts based on some conditions:
    •	Students – if the group is bigger than or equal to 30
    people, you should reduce the total price by 15%
    •	Business – if the group is bigger than or equal to 100
    people 10 of them can stay for free.
    •	Regular – if the group is bigger than or equal to 10
    and less than or equal to 20 reduce the total price by 5%
    You should reduce the prices in that EXACT order.
Examples:
    30
    Students
    Sunday
    -> Total price: 266.73
    40
    Regular
    Saturday
    -> Total price: 800.00
    50
    Business
    Friday
    -> Total price: 545.00
*/
package Basic.Exercise;

import java.util.Scanner;
import java.util.List;

import static java.lang.System.*;

public class Vacation {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int peopleCount = setValue(0, biggestInt);
        String groupType = setValue(null, null);
        String day = setValue(null, null);

        double totalPrice =
                calcTotalPrice(peopleCount, groupType, day);
        out.printf("Total price: %.2f", totalPrice);
    }

    private static double calcTotalPrice(
            int peopleCount,
            String groupType,
            String day
    ) {
        double priceForPerson = 0;
        int discountPercent = 0;
        switch (day) {
            case "Friday":
                switch (groupType) {
                    case "Students":
                        priceForPerson = 8.45;
                        if (peopleCount >= 30)
                            discountPercent = 15;
                        break;
                    case "Business":
                        priceForPerson = 10.90;
                        if (peopleCount >= 100)
                            peopleCount -= 10;
                        break;
                    case "Regular":
                        priceForPerson = 15;
                        if (peopleCount >= 10 && peopleCount <= 20)
                            discountPercent = 5;
                        break;
                }
                break;
            case "Saturday":
                switch (groupType) {
                    case "Students":
                        priceForPerson = 9.8;
                        if (peopleCount >= 30)
                            discountPercent = 15;
                        break;
                    case "Business":
                        priceForPerson = 15.6;
                        if (peopleCount >= 100)
                            peopleCount -= 10;
                        break;
                    case "Regular":
                        priceForPerson = 20;
                        if (peopleCount >= 10 && peopleCount <= 20)
                            discountPercent = 5;
                        break;
                }
                break;
            case "Sunday":
                switch (groupType) {
                    case "Students":
                        priceForPerson = 10.46;
                        if (peopleCount >= 30)
                            discountPercent = 15;
                        break;
                    case "Business":
                        priceForPerson = 16;
                        if (peopleCount >= 100)
                            peopleCount -= 10;
                        break;
                    case "Regular":
                        priceForPerson = 22.50;
                        if (peopleCount >= 10 && peopleCount <= 20)
                            discountPercent = 5;
                        break;
                }
                break;
        }
        double totalPriceBeforeDiscount = 
                priceForPerson * peopleCount;
        double discount = 
                totalPriceBeforeDiscount * discountPercent / 100;
        return totalPriceBeforeDiscount - discount;
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
                    required = new String[] {"Students", "Business", "Regular"};
                if (stringCount == 2)
                    required = new String[] {"Friday", "Saturday", "Sunday"};
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
