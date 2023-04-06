/*
Task:
    Pirates are invading the sea, and you're tasked to help them plunder
    Create a program that checks if target plunder is reached.
    First, you will receive how many days the pirating lasts.
    Then you will receive how much the pirates plunder for a day.
    Last you will receive the expected plunder at the end.
    Calculate how much plunder the pirates manage to gather.
    Each day they gather the plunder.
    Keep in mind that they attack more ships every third day and add additional plunder to their total gain,
    which is 50% of the daily plunder. Every fifth day the pirates encounter a warship, and after the battle,
    they lose 30% of their total plunder.
    If the gained plunder is more or equal to the target, print the following:
    "Ahoy! {totalPlunder} plunder gained."
    If the gained plunder is less than the target. Calculate the percentage left and print the following:
    "Collected only {percentage}% of the plunder."
    Both numbers should be formatted to the 2nd decimal place.
Input:
    • On the 1st line, you will receive the days of the plunder – an integer number in the range [0…100000]
    • On the 2nd line, you will receive the daily plunder – an integer number in the range [0…50]
    • On the 3rd line, you will receive the expected plunder – a real number in the range [0.0…10000.0]
Output:
    •  In the end, print whether the plunder was successful or not, following the format described above.
Examples:
    5
    40
    100
    ->
    Ahoy! 154.00 plunder gained.
        The days are 5, and the daily plunder is 40.
        On the third day, the total plunder is 120, and since it is a third day,
        they gain an additional 50% from the daily plunder, which adds up to 140.
        On the fifth day, the plunder is 220,
        but they battle with a warship and lose 30% of the collected cargo, and the total becomes 154.
        That is more than expected.
    10
    20
    380
    ->
    Collected only 36.29% of the plunder.
 */
package FundamentalsExams.MidExamRetake06;

import java.util.Scanner;

public class BlackFlag {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int days = Integer.parseInt(scanner.nextLine());
        int dailyPlunder = Integer.parseInt(scanner.nextLine());
        double expectedPlunder = Double.parseDouble(scanner.nextLine());

        double totalPlunder = plunder(days, dailyPlunder);

        printResult(totalPlunder, expectedPlunder);
    }

    private static double plunder(int days, int dailyPlunder) {
        double totalPlunder = 0;

        for (int i = 1; i <= days; i++) {
            totalPlunder += dailyPlunder;

            if (i % 3 == 0) {
                totalPlunder += dailyPlunder * 0.5;
            }

            if (i % 5 == 0) {
                totalPlunder -= totalPlunder * 0.3;
            }
        }

        return totalPlunder;
    }

    private static void printResult(double totalPlunder, double expectedPlunder) {
        if (totalPlunder >= expectedPlunder) {
            System.out.printf("Ahoy! %.2f plunder gained.", totalPlunder);
        } else {
            double percentage = totalPlunder / expectedPlunder * 100;
            System.out.printf("Collected only %.2f%% of the plunder.", percentage);
        }
    }
}
