/*
Условие:
    Write a program to check whether or not a given number is
    strong. A number is strong if the sum of the Factorial of
    each digit is equal to the number. For example 145 is a
    strong number, because 1! + 4! + 5! = 145. Print "yes" if
    the number is strong and "no" if the number is not strong.
Examples:
    2 -> yes
    3451 -> no
    40585 -> yes
*/
package BasicSyntaxConditionalStatementsAndLoops.Exercise;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class StrongNumber {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int originalNumber = setValue(smallestInt, biggestInt);
        int number = originalNumber;
        int[] arr = new int [0];
        while (number != 0) {
            arr = addX(arr.length, arr, number % 10);
            number /= 10;
        }
        int sum = 0;
        int factorialOfNumber = 0;
        for (int i=0; i<arr.length; i++) {
            factorialOfNumber = factorialOf(arr[i]);
            sum += factorialOfNumber;
        }
        if (sum == originalNumber)
            out.println("yes");
        else
            out.println("no");
    }

    private static int factorialOf(int i) {
        int factorial = i;
        while (i>1) {
            factorial *= (i - 1);
            i--;
        }
        return factorial;
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

            if (requiredString) {
                stringCount++;
                String[] required = {};

                if (stringCount == 1)
                    required = new String[]{"Spring", "Summer", "Autumn", "Winter"};
                if (stringCount == 2)
                    required = new String[]{"Y", "N"};
                if (stringCount > 2) {
                    requiredString = false;
                    return (T) value;
                }

                List<String> requiredList = List.of(required);

                if (!requiredList.contains(value)) {
                    out.print("Моля въведете един от следните избори: \n| ");
                    for (String thing : required)
                        out.print(thing + " | ");
                    out.println();

                    stringCount--;
                    return setValue(null, null);
                }
            }
        } else {
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
            } catch (Exception e) {
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

    //    add x to array
    public static int[] addX(int size, int[] arr, int x) {
        int i;

        // create a new array of size n+1
        int[] newArr = new int[size + 1];

        // insert the elements from the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < size; i++)
            newArr[i] = arr[i];

        newArr[size] = x;

        return newArr;
    }
}
