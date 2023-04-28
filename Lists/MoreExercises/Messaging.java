/*
Task:
    You will be given a list of numbers and a string.
    For each element of the list, you have to take the sum of its digits
    and take the element corresponding to that index from the text.
    If the index is greater than the length of the text,
    start counting from the beginning (so that you always have a valid index).
    After getting the element from the text,
    you must remove the character you have taken from it (so for the next index,
    the text will be with one
    characterless).
Examples:
    Input:
        9992 562 8933
        This is some message for you
    Output:
        hey
    Input:
        11 2 32 43 331 522 441 2241 711 1821
        69da343n44ge96rous311!
    Output:
        dangerous!
 */
package Lists.MoreExercises;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Messaging {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Integer> numbers = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        String text = scanner.nextLine();
        String result = generateMessage(numbers, text);

        out.println(result);
    }

    private static String generateMessage(List<Integer> numbers, String text) {
        StringBuilder result = new StringBuilder();

        for (int number : numbers) {
            int sum = 0;

            while (number > 0) {
                sum += number % 10;
                number /= 10;
            }

            if (sum >= text.length()) {
                sum -= text.length();
            }

            result.append(text.charAt(sum));
            text = text.substring(0, sum) + text.substring(sum + 1);
        }

        return result.toString();
    }
}
