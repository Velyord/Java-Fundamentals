/*
Task:
    Write a program that mixes up two lists by some rules.
    You will receive two lines of input, each one being a list of
    numbers. The mixing rules are:
    • Start from the beginning of the first list and the ending of the second.
    • Add element from the first and element from the second.
    • In the end, there will always be a list in which there are 2 elements remaining.
    • These elements will be the range of the elements you need to print.
    • Loop through the result list and take only the elements that fulfill the condition.
    • Print the elements ordered in ascending order and separated by a space.
Examples:
    Input:
        1 5 23 64 2 3 34 54 12
        43 23 12 31 54 51 92
    Output:
        23 23 31 34 43 51
    Comment:
        After looping through the two of the arrays, we get: 1 92 5 51 23 54 64 31 2 12 3 23 34 43
        The constraints are 54 and 12 (so we take only the numbers between them): 51 23 31 23 34 43
        We print the result sorted
    Input:
        75 20 78 75 49
        47 91 32 45 55 62 20
    Output:
        49 55 62 75 75 78
 */
package Lists.MoreExercises;

import java.util.*;
import java.util.stream.Collectors;

public class MixedUpLists {
    static Scanner scanner = new Scanner(System.in);

    private static List<Integer> firstNumberList = new ArrayList<>();
    private static List<Integer> secondNumberList = new ArrayList<>();
    private static final List<Integer> mixedNumberList = new ArrayList<>();
    private static List<Integer> filteredMixedList = new ArrayList<>();

    private static int firstRangeNumber;
    private static int secondRangeNumber;

    public static void main(String[] args) {
        firstNumberList = populate();
        secondNumberList = populate();
        mixLists();
        filterMixedList();
        printFilteredList();
    }

    private static void printFilteredList() {
        for (Integer number : filteredMixedList) {
            System.out.print(number + " ");
        }
    }

    private static List<Integer> populate() {
        return Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void filterMixedList(
    ) {
        getRangeNumbers();
        orderRangeNumbers();

        filteredMixedList = mixedNumberList
                .stream()
                .filter(e -> e > firstRangeNumber && e < secondRangeNumber)
                .sorted()
                .collect(Collectors.toList());
    }

    private static void getRangeNumbers() {
        if (firstNumberList.size() > secondNumberList.size()) {
            firstRangeNumber = firstNumberList.get(firstNumberList.size() - 2);
            secondRangeNumber = firstNumberList.get(firstNumberList.size() - 1);
        } else {
            firstRangeNumber = secondNumberList.get(secondNumberList.size() - 2);
            secondRangeNumber = secondNumberList.get(secondNumberList.size() - 1);
        }
    }

    private static void mixLists(
    ) {
        Collections.reverse(secondNumberList);

        int smallerListSize = Math.min(firstNumberList.size(), secondNumberList.size());

        for (int i = 0; i < smallerListSize; i++) {
            mixedNumberList.add(firstNumberList.get(i));
            mixedNumberList.add(secondNumberList.get(i));
        }
    }

    private static void orderRangeNumbers() {
        if (firstRangeNumber > secondRangeNumber) {
            int temp = firstRangeNumber;
            firstRangeNumber = secondRangeNumber;
            secondRangeNumber = temp;
        }
    }
}
