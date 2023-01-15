/*
Условие:
    A theatre is having a ticket sale, but they need a program
    to calculate the price of a single ticket. If the given age
    does not fit one of the categories, you should print "Error!".
    You can see the prices in the table below:
    Day / Age	0 <= age <= 18	18 < age <= 64	64 < age <= 122
    Weekday	         12$	         18$	         12$
    Weekend	         15$	         20$	         15$
    Holiday	         5$	             12$	         10$
Input:
    The input comes in two lines.
    On the first line, you will receive the type of day.
    On the second – is the age of the person.
Output:
    Print the ticket price according to the table, or "Error!"
    if the age is not in the table.
Constraints:
    •	The age will be in the interval [-1000…1000].
    •	The type of day will always be valid.
Examples
    Weekday
    42
    -> 18$
    Holiday
    -12
    -> Error!
    Holiday
    15
    -> 5$
    Weekend
    122
    -> 15$
*/
package SoftUni.Fundamentals.Lab1;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class TheatrePromotion {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String typeOfDay = setValue(null, null);
        int ageOfPerson = setValue(-1000, 1000);

        displayTicketCost(typeOfDay, ageOfPerson);
    }

    private static void displayTicketCost(String typeOfDay, int ageOfPerson) {
        switch (typeOfDay) {
            case "Weekday":
                if (ageOfPerson >= 0 && ageOfPerson <= 18 ||
                        ageOfPerson > 64 && ageOfPerson <= 122)
                    out.println("12$");
                else if (ageOfPerson > 18 && ageOfPerson <= 64)
                    out.println("18$");
                else
                    out.println("Error!");
                break;
            case "Weekend":
                if (ageOfPerson >= 0 && ageOfPerson <= 18 ||
                        ageOfPerson > 64 && ageOfPerson <= 122)
                    out.println("15$");
                else if (ageOfPerson > 18 && ageOfPerson <= 64)
                    out.println("20$");
                else
                    out.println("Error!");
                break;
            case "Holiday":
                if (ageOfPerson >= 0 && ageOfPerson <= 18)
                    out.println("5$");
                else if (ageOfPerson > 18 && ageOfPerson <= 64)
                    out.println("12$");
                else if (ageOfPerson > 64 && ageOfPerson <= 122)
                    out.println("10$");
                else
                    out.println("Error!");
                break;
        }
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
                    required = new String[] {"Weekday", "Weekend", "Holiday"};
                if (stringCount > 1) {
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
