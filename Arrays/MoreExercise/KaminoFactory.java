/*
Условие:
    The clone factory in Kamino got another order to clone troops.
    But this time, you are tasked to find the best DNA sequence to use in the production.
    You will receive the DNA length, and until you receive the command "Clone them!"
    you will be receiving DNA sequences of ones and zeroes, split by "!" (one or several).
    You should select the sequence with the longest subsequence of ones.
    If there are several sequences with the same length of a subsequence of ones,
    print the one with the leftmost starting index,
    if there are several sequences with the same length and starting index,
    select the sequence with the greater sum of its elements.
    After you receive the last command "Clone them!",
    you should print the collected information in the following format:
    "Best DNA sample {bestSequenceIndex} with sum: {bestSequenceSum}."
    "{DNA sequence, joined by space}"
Input / Constraints:
    • The first line holds the length of the sequences – integer in the range [1…100].
    • On the next lines, until you receive "Clone them!" you will be receiving sequences
    (at least one) of ones and zeroes, split by "!" (one or several).
Output:
    The output should be printed on the console and consists of two lines in the following format:
    "Best DNA sample {bestSequenceIndex} with sum: {bestSequenceSum}."
    "{DNA sequence, joined by space}"
Examples:
    5
    1!0!1!1!0
    0!1!1!0!0
    Clone them!
    ->
    Best DNA sample 2 with sum: 2.
    0 1 1 0 0
        We receive 2 sequences with the same length of a subsequence of ones,
        but the second is printed because its subsequence starts at index[1].
    4
    1!1!0!1
    1!0!0!1
    1!1!0!0
    Clone them!
    ->
    Best DNA sample 1 with sum: 3.
    1 1 0 1
        We receive 3 sequences. Both 1 and 3 have the same length of a subsequence of ones -> 2,
        and both start from the index[0], but the first is printed because its sum is greater.
*/
// https://github.com/Borovaneca/Fundamentals-Java-Jan-2023/blob/main/Arrays/Exercise/MoreExercise/KaminoFactory.java
package Arrays.MoreExercise;

import java.util.Arrays;
import java.util.Scanner;

public class KaminoFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dnaLength = Integer.parseInt(scanner.nextLine());
        int[] sample;
        String command = scanner.nextLine();
        int bestSumOfOnes = 1;
        int bestLength = 1;
        int bestLeftIndex = 1;
        int[] bestSample = new int[dnaLength];
        int sampleCounter = 0;
        int bestCounter = 0;

        while (!command.equals("Clone them!")) {
            sample = Arrays.stream(command.split("!+")).mapToInt(Integer::parseInt).toArray();
            sampleCounter++;
            int sumOfOnes = 1;
            int onesLength = 1;
            int biggerLength = 1;
            int startIndex = 0;

            for (int i = 0; i < sample.length - 1; i++) {
                int endIndex = 0;
                if (i == sample.length - 2 && sample[i + 1] == 0) {
                    sumOfOnes--;
                }
                if (sample[i] == 1) {
                    sumOfOnes++;
                    if (sample[i + 1] == 1) {
                        onesLength++;
                        endIndex = i + 1;
                        startIndex = endIndex - onesLength + 1;
                        if (onesLength > biggerLength) {
                            biggerLength = onesLength;
                        }
                    }
                } else {
                    onesLength = 1;
                }
            }
            if (biggerLength > bestLength) {
                bestLength = biggerLength;
                bestLeftIndex = startIndex;
                bestSumOfOnes = sumOfOnes;
                bestSample = sample;
                bestCounter = sampleCounter;
            } else if (biggerLength == bestLength) {
                if (startIndex < bestLeftIndex) {
                    bestLeftIndex = startIndex;
                    bestSumOfOnes = sumOfOnes;
                    bestSample = sample;
                    bestCounter = sampleCounter;
                } else if (startIndex == bestLeftIndex) {
                    if (sumOfOnes > bestSumOfOnes) {
                        bestSumOfOnes = sumOfOnes;
                        bestSample = sample;
                        bestCounter = sampleCounter;
                    }
                }
            }

            command = scanner.nextLine();
        }

        System.out.printf("Best DNA sample %d with sum: %d.%n", bestCounter, bestSumOfOnes);

        for (int index : bestSample) {
            System.out.print(index + " ");
        }
    }
}