/*
Условие:
    Yoda is starting his newly created Jedi academy.
    So, he asked Master George Lucas to buy the needed
    equipment. The number of items depends on how many students
    will sign up. The equipment for the Padawan contains
    lightsabers, belts, and robes.
    You will be given the amount of money George Lucas has,
    the number of students, and the prices of each item.
    You have to help George Lucas calculate if the money he has
    is enough to buy all of the equipment or how much more
    money he needs.
    Because the lightsabers sometimes break, George Lucas
    should buy 10% more, rounded up to the next integer. Also,
    every sixth belt is free.
Input / Constraints:
    The input data should be read from the console. It will
    consist of exactly 5 lines:
    •	The amount of money George Lucas has – the
    floating-point number in the range [0.00…1,000.00].
    •	The count of students – integer in the range [0…100].
    •	The price of lightsabers for a single saber – the
    floating-point number in the range [0.00…100.00].
    •	The price of robes for a single robe – the
    floating-point number in the range [0.00…100.00].
    •	The price of belts for a single belt – the
    floating-point number in the range [0.00…100.00].
    The input data will always be valid. There is no need to
    check it explicitly.
Output:
    The output should be printed on the console.
    •	If the calculated price of the equipment is less or
    equal to the money George Lucas has:
    "The money is enough - it would cost {the cost of the
    equipment}lv."
    •	If the calculated price of the equipment is more than
    the money George Lucas has:
     "George Lucas will need {neededMoney}lv more."
    •	All prices must be rounded to two digits after the
    decimal point.
Examples:
    100
    2
    1.0
    2.0
    3.0
    -> The money is enough - it would cost 13.00lv.
        Needed equipment for 2 padawans:
        sabresPrice * (studentsCount + 10%) + robesPrice *
        (studentsCount) + beltsPrice * (studentsCount - freeBelts)
        1*(3) + 2*(2) + 3*(2) = 13.00
        13.00 <= 100 – the money will be enough.
    100
    42
    12.0
    4.0
    3.0
    -> George Lucas will need 737.00lv more.
        Needed equipment for 42 padawans:
        12*47 + 4*42 + 3*35 = 837.00
        837 > 100 – need 737.00 lv. more.
*/
package SoftUni.Fundamentals.Exer1;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class PadawanEquipment {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double amountOfMoney = setValue(0.00, 1000.00);
        int countOfStudents = setValue(0, 100);
        double priceOfLightsaber = setValue(0.00, 100.00);
        double priceOfRobe = setValue(0.00, 100.00);
        double priceOfBelt = setValue(0.00, 100.00);

        int freeBelts = countOfStudents / 6;
        double totalPrice =
            priceOfLightsaber * (ceil(countOfStudents * 1.1)) +
            priceOfRobe * countOfStudents +
            priceOfBelt * (countOfStudents - freeBelts);
        double difference = amountOfMoney - totalPrice;
        if (difference >= 0)
            out.printf(
                "The money is enough - it would cost %.2flv.",
                totalPrice
            );
        else
            out.printf(
                "George Lucas will need %.2flv more.",
                abs(difference)
            );
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
