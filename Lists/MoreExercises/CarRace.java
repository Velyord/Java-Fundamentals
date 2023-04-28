/*
Task:
    Write a program to calculate the winner of a car race. You will receive an array of numbers.
    Each array element
    represents the time needed to pass through that step (the index). There are going to be two cars.
    One of them
    starts from the left side, and the other one starts from the right side.
    The middle index of the array is the finish
    line. (The number of elements of the array will always be odd).
    Calculate the total time for each racer to reach the
    finish (the middle of the array) and print the winner with his total time. (The racer with less time).
    If you have a zero in the array, you must reduce the racer's time that reached it by 20%
    (from the time so far).
    Print the result in the following format "The winner is {left/right} with total time: {total
    time}", formatted with one digit after the decimal point.
Examples:
    Input:
        29 13 9 0 13 0 21 0 14 82 12
    Output:
        The winner is left with total
        time: 53.8
    Comment:
        The time of the left racer is (29 + 13 + 9) * 0.8 (because of the zero) + 13 = 53.8
        The time of the right racer is (82 + 12 + 14) * 0.8 + 21 = 107.4
        The winner is the left racer, so we print it.
    Input:
        26 46 31 43 1 23 44
    Output:
        The winner is right with total
        time: 68.0
 */
package Lists.MoreExercises;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class CarRace {
    static Scanner scanner = new Scanner(System.in);
    private static double leftRacerTime;
    private static double rightRacerTime;

    public static void main(String[] args) {
        List<Integer> numbers = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        findWinner(numbers);
        printWinner();
    }

    private static void findWinner(List<Integer> numbers) {
        List<Integer> leftRacer = numbers.subList(0, numbers.size() / 2);
        List<Integer> rightRacer = numbers.subList(numbers.size() / 2 + 1, numbers.size());

        Collections.reverse(rightRacer);

        leftRacerTime = getRacerTime(leftRacer);
        rightRacerTime = getRacerTime(rightRacer);
    }

    private static void printWinner() {
        if (leftRacerTime < rightRacerTime) {
            out.printf("The winner is left with total time: %.1f", leftRacerTime);
        } else if (leftRacerTime > rightRacerTime) {
            out.printf("The winner is right with total time: %.1f", rightRacerTime);
        } else {
            throw new IllegalArgumentException("Both racers have the same time!");
        }
    }

    private static double getRacerTime(List<Integer> racer) {
        double racerTime = 0;

        for (int i = 0; i < racer.size(); i++) {
            if (racer.get(i) == 0) {
                racerTime *= 0.8;
            }
            racerTime += racer.get(i);
        }

        return racerTime;
    }
}