/*
Task:
    Write a program to append several arrays of numbers.
    	Arrays are separated by "|".
    	Values are separated by spaces (" ", one or several).
    	Order the arrays from the last to the first and their
    values from left to right.
Examples:
    1 2 3 |4 5 6 |  7  8 -> 7 8 4 5 6 1 2 3
    7 | 4  5|1 0| 2 5 |3 -> 3 2 5 1 0 4 5 7
    1| 4 5 6 7  |  8 9   -> 8 9 4 5 6 7 1
*/
package Lists.Exercise;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class AppendArrays {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        List<Integer> resultList = new ArrayList<>();
        String userInput = scanner.nextLine(); // get user input
        String[] arrays = userInput.split("\\|"); // separate user input into arrays

        for (int i = arrays.length - 1; i >= 0; i--) {
            String array = arrays[i]; // get first array
            boolean containsNumbers = array.matches(".*\\d.*");

            if (containsNumbers) {
                array = stripFromStartingSpaces(array);

                List<Integer> numbersInArray = Arrays
                        .stream(array.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                resultList.addAll(numbersInArray);
            }
        }

        resultList.forEach(number -> out.printf("%d ", number));
    }

    private static String stripFromStartingSpaces(String array) {
        for (int ch = 0; ch < array.length(); ch++) {
            if (array.charAt(ch) != ' ') {
                return array.substring(ch);
            }
        }

        return "";
    }
}