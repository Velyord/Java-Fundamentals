/*
Условие:
    Read a list of integers and find the longest increasing subsequence (LIS).
    If several such exist, print the leftmost.
Examples:
    1 -> 1
    7 3 5 8 -1 0 6 7 -> 3 5 6 7
    1 2 5 3 5 2 4 1 -> 1 2 3 5
    0 10 20 30 30 40 1 50 2 3 4 5 6 -> 0 1 2 3 4 5 6
    11 12 13 3 14 4 15 5 6 7 8 7 16 9 8 -> 3 4 5 6 7 8 16
    3 14 5 12 15 7 8 9 11 10 1 -> 3 5 7 8 9 11
*/
// https://github.com/Borovaneca/Fundamentals-Java-Jan-2023/blob/main/Arrays/Exercise/MoreExercise/LongestIncreasingSubsequences.java
package Arrays.MoreExercise;

import java.util.Scanner;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array1 = scanner.nextLine().split(" ");
        int[] numbers = new int[array1.length];

        for (int i = 0; i < array1.length; i++) {
            numbers[i] = Integer.parseInt(array1[i]);
        }

        int maxLength = 0;
        int lastIndex = -1;
        int[] len = new int[numbers.length];
        int[] previous = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            len[i] = 1;
            previous[i] = -1;

            for (int k = 0; k < i; k++) {
                if (numbers[k] < numbers[i] && len[k] + 1 > len[i]) {
                    len[i] = len[k] + 1;
                    previous[i] = k;
                }
            }

            if (len[i] > maxLength) {
                maxLength = len[i];
                lastIndex = i;
            }
        }

        int[] lis = new int[maxLength];
        int currentIndex = maxLength - 1;

        while (lastIndex != -1) {
            lis[currentIndex] = numbers[lastIndex];
            currentIndex--;
            lastIndex = previous[lastIndex];
        }

        for (int item : lis) {
            System.out.print(item + " ");
        }
    }
}