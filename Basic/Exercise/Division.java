/*
Условие:
    You will be given an integer, 
    and you have to print on the console 
    whether that number is divisible by the following numbers: 
    2, 3, 6, 7, 10. 
    You should always take the bigger division. 
    If the number is divisible by both 2 and 3 
    it is also divisible by 6, 
    and you should print only the division by 6.
    If a number is divisible by 2 
    it is sometimes also divisible by 10, 
    and you should print the division by 10. 
    If the number is not divisible by any given number, 
    print "Not divisible". Otherwise, 
    print "The number is divisible by {number}".
Examples:
    30	
    -> The number is divisible by 10
    15	
    -> The number is divisible by 3
    12	
    -> The number is divisible by 6
    1643	
    -> Not divisible
*/
package Basic.Exercise;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Division {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int number = setValue(smallestInt, biggestInt);
        findTheCorrectDevisible(number);
    }

    private static void findTheCorrectDevisible(int number) {
        int divisible;
        if (number % 10 == 0)
            divisible = 10;
        else if (number % 7 == 0)
            divisible = 7;
        else if (number % 6 == 0)
            divisible = 6;
        else if (number % 3 == 0)
            divisible = 3;
        else if (number % 2 == 0)
            divisible = 2;
        else 
            divisible = 0;
        
        if (divisible != 0)
            out.printf("The number is divisible by %d", divisible);
        else 
            out.println("Not divisible");
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
