/*
Task:
    Write a program to read a sequence of integers
    and find and print the top 5 numbers greater than the average value in the sequence,
    sorted in descending order.
Input:
    • Read from the console a single line holding space-separated integers.
Output:
    • Print the above-described numbers on a single line, space-separated.
    • If less than 5 numbers hold the property mentioned above, print less than 5 numbers.
    • Print "No" if no numbers hold the above property.
Constraints:
    • All input numbers are integers in the range [-1000000 … 1000000].
    • The count of numbers is in the range [1…10 000].
Examples:
    10 20 30 40 50
    ->
    50 40
        Average number = 30.
        Numbers greater than 30 are: {40, 50}.
        The top 5 numbers among them in descending order are: {50, 40}.
        Note that we have only 2 numbers, so all of them are included in the top 5.
    5 2 3 4 -10 30 40 50 20 50 60 60 51
    ->
    60 60 51 50 50
        Average number = 28.08.
        Numbers greater than 28.08 are:
        {30, 40, 50, 50, 60, 60, 51}.
        The top 5 numbers among them in descending order are: {60, 60, 51, 50, 50}.
    1
    ->
    No
        Average number = 1.
        There are no numbers greater than 1.
    -1 -2 -3 -4 -5 -6
    ->
    -1 -2 -3
        Average number = -3.5.
        Numbers greater than -3.5 are: {-1, -2, -3}.
        The top 5 numbers among them in descending order are: {-1, -2, -3}.
 */
package FundamentalsExams.MidExam02;

import java.util.Scanner;

public class Numbers {
    private static int[] numbers;
    private static int[] greaterThanAverage;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        populateNumbersArray();

        double average = getAverage();
        int countOfNumbersGreaterThanAvg = getCountGreaterThan(average);

        if (countOfNumbersGreaterThanAvg == 0) {
            System.out.println("No");
        } else {
            populateGreaterThanAverageArray(average, countOfNumbersGreaterThanAvg);
            sortGreaterThanAverageArray();
            printGreaterThanAverageArray();
        }
    }

    private static void printGreaterThanAverageArray() {
        if (greaterThanAverage.length < 5) {
            for (int i = 0; i < greaterThanAverage.length; i++) {
                if (i == greaterThanAverage.length - 1) {
                    System.out.print(greaterThanAverage[i]);
                } else {
                    System.out.print(greaterThanAverage[i] + " ");
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (i == 4) {
                    System.out.print(greaterThanAverage[i]);
                } else {
                    System.out.print(greaterThanAverage[i] + " ");
                }
            }
        }
    }

    private static void sortGreaterThanAverageArray() {
        for (int i = 0; i < greaterThanAverage.length; i++) {
            for (int j = i + 1; j < greaterThanAverage.length; j++) {
                if (greaterThanAverage[i] < greaterThanAverage[j]) {
                    int temp = greaterThanAverage[i];
                    greaterThanAverage[i] = greaterThanAverage[j];
                    greaterThanAverage[j] = temp;
                }
            }
        }
    }

    private static void populateGreaterThanAverageArray(double average, int countOfNumbersGreaterThanAvg) {
        greaterThanAverage = new int[countOfNumbersGreaterThanAvg];
        int index = 0;

        for (int number : numbers) {
            if (number > average) {
                greaterThanAverage[index] = number;
                index++;
            }
        }
    }

    private static int getCountGreaterThan(double average) {
        int countOfNumbersGreaterThanAvg = 0;

        for (int number : numbers) {
            if (number > average) {
                countOfNumbersGreaterThanAvg++;
            }
        }

        return countOfNumbersGreaterThanAvg;
    }

    private static double getAverage() {
        double average = 0;

        for (int number : numbers) {
            average += number;
        }

        average /= numbers.length;

        return average;
    }

    private static void populateNumbersArray() {
        String[] input = scanner.nextLine().split(" ");
        numbers = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }
    }
}