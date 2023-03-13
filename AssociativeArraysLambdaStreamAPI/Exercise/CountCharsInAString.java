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
    static Map<String, Integer> textLinkedMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        String[] textArray = scanner.nextLine().split("");

        fillLinkedMap(textArray);
        printLinkedMap();
    }

    private static void fillLinkedMap(String[] textArray) {
        for (String letter : textArray) {
            if (textLinkedMap.containsKey(letter)) {
                int value = textLinkedMap.get(letter);
                textLinkedMap.put(letter, value + 1);
            } else if (!letter.equals(" ")){
                textLinkedMap.put(letter, 1);
            }
        }
    }

    private static void printLinkedMap() {
        for (Map.Entry<String, Integer> letter : textLinkedMap.entrySet()) {
            out.printf("%s -> %s%n", letter.getKey(), letter.getValue());
        }
    }
}