/*
Task:
    Read an array of strings, and take only words whose length is even. Print each word on a new line.
Examples:
    kiwi orange banana apple
    ->
    kiwi
    orange
    banana

    pizza cake pasta chips
    ->
    cake
*/
package AssociativeArraysLambdaStreamAPI.Lab;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class WordFilter {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String[] words = Arrays
                .stream(scanner.nextLine()
                        .split(" "))
                .filter(w -> w.length() % 2 == 0)
                .toArray(String[]::new);

        for (String word : words) {
            out.println(word);
        }
    }
}
