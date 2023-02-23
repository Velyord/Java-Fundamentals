/*
Task:
    Write a method that receives a single string and prints the count of the vowels.
    Use an appropriate name for the method.
Examples:
    SoftUni -> 3
    Cats -> 1
    JS -> 0
*/
package Methods.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class VowelsCount {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String input = scanner.nextLine();
        int vowelsCount = getVowelsCount(input);

        out.println(vowelsCount);
    }

    private static int getVowelsCount(String input) {
        int vowelsCount = 0;
        List<Character> vowels = new ArrayList<>(List.of('a', 'e', 'i', 'o', 'u'));

        for (int i = 0; i < input.length(); i++) {
            if (vowels.contains(input.toLowerCase().charAt(i))) {
                vowelsCount++;
            }
        }

        return vowelsCount;
    }
}