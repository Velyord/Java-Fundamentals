/*
Task:
    You are given a list of words in one line. Randomize their order and print each word on a separate line.
Examples:
    Welcome to SoftUni and have fun learning programming
    ->
    learning
    Welcome
    SoftUni
    and
    fun
    programming
    have
    to
        The order of the words in the output will be different after each program execution.
    Java is the best programming language
    ->
    the
    programming
    best
    language
    is
    Java
*/
package ObjectsAndClasses.Lab;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class RandomizeWords {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String[] sentenceArray = scanner.nextLine().split(" ");
        Collections.shuffle(Arrays.asList(sentenceArray));

        for (String item : sentenceArray) {
            out.println(item);
        }
    }
}
