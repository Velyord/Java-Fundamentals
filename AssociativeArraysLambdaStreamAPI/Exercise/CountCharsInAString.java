/*
Task:
    Write a program that counts all characters in a string except space (' ').
    Print all occurrences in the following format: "{char} -> {occurrences}"
Examples:
    text
    t -> 2
    e -> 1
    x -> 1
    text text text

    t -> 6
    e -> 3
    x -> 3
 */
package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.*;
import static java.lang.System.out;

public class CountCharsInAString {
    static Scanner scanner = new Scanner(System.in);
    static Map<Character, Integer> textLinkedMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        String text = scanner.nextLine();
        char[] charArray = text.toCharArray();

        fillLinkedMap(charArray);
        textLinkedMap.forEach((key, value) -> out.println(key + " -> " + value));
    }

    private static void fillLinkedMap(char[] charArray) {
        for (char letter : charArray) {
            if (textLinkedMap.containsKey(letter)) {
                int timesFound = textLinkedMap.get(letter);
                textLinkedMap.put(letter, timesFound + 1);
            } else if (letter != ' ') {
                textLinkedMap.put(letter, 1);
            }
        }
    }
}