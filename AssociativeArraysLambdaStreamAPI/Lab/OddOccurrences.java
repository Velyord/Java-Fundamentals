/*
Task:
    Write a program that extracts from a given sequence of words
    all elements that are present in it an odd number of times (case-insensitive).
    • Words are given in a single line, space-separated.
    • Print the result elements in lowercase in their order of appearance.
Examples:
    Java C# PHP PHP JAVA C java -> java, c#, c
    3 5 5 hi pi HO Hi 5 ho 3 hi pi -> 5, hi
    a a A SQL xx a xx a A a XX c -> a, sql, xx, c
*/
package AssociativeArraysLambdaStreamAPI.Lab;

import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class OddOccurrences {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String[] wordsArray = scanner.nextLine().split(" ");
        Map<String, Integer> wordsMap = new LinkedHashMap<>();
        List<String> resultList = new ArrayList<>();

        fillMap(wordsMap, wordsArray);
        addOddOccurrences(wordsMap, resultList);
        printList(resultList, ", ");
    }

    private static void fillMap(Map<String, Integer> wordsMap, String[] wordsArray) {
        for (String word : wordsArray) {
            word = word.toLowerCase();

            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }
    }

    private static void addOddOccurrences(Map<String, Integer> wordsMap, List<String> resultList) {
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                resultList.add(entry.getKey());
            }
        }
    }

    private static <T> void printList(List<T> numberList, String delimiter) {
        out.println(
                numberList
                        .toString()
                        .replace("[", "")
                        .replace("]", "")
                        .replace(",", "")
                        .replace(" ", delimiter)
        );
    }
}
