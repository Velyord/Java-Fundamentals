/*
Task:
    You are given an array with integers.
    Write a program to modify the elements after receiving the following commands:
    • "swap {index1} {index2}" takes two elements and swap their places.
    • "multiply {index1} {index2}" takes element at the 1st index
    and multiply it with the element at 2nd index. Save the product at the 1st index.
    • "decrease" decreases all elements in the array with 1.
Input
On the first input line, you will be given the initial array values separated by a single space.
On the next lines you will receive commands until you receive the command "end".
The commands are as follow:
    • "swap {index1} {index2}"
    • "multiply {index1} {index2}"
    • "decrease"
Output
    The output should be printed on the console and consist of elements of the modified array –
    separated by a comma and a single space ", ".
Constraints
    • Elements of the array will be integer numbers in the range [-231...231]
    • Count of the array elements will be in the range [2...100]
    • Indexes will be always in the range of the array
Examples
    23 -2 321 87 42 90 -123
    swap 1 3
    swap 3 6
    swap 1 0
    multiply 1 2
    multiply 2 1
    decrease
    end
    ->
    86, 7382, 2369942, -124, 41, 89, -3
        23 -2 321 87 42 90 -123 – initial values
        swap 1(-2) and 3(87) ▼
        23 87 321 -2 42 90 -123
        swap 3(-2) and 6(-123) ▼
        23 87 321 -123 42 90 -2
        swap 1(87) and 0(23) ▼
        87 23 321 -123 42 90 -2
        multiply 1(23) 2(321) = 7383 ▼
        87 7383 321 -123 42 290 -2
        multiply 2(321) 1(7383) = 2369943 ▼
        87 7383 2369943 -123 42 90 -2
        decrease – all - 1 ▼
        86 7383 2369942 -124 41 89 -3
    1 2 3 4
    swap 0 1
    swap 1 2
    swap 2 3
    multiply 1 2
    decrease
    end
    ->
    1, 11, 3, 0
 */
package FundamentalsExams.MidExam02;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayModifier {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] numbers = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String[] tokens = input.split(" ");
            String command = tokens[0];
            switch (command) {
                case "swap":
                    int index1 = Integer.parseInt(tokens[1]);
                    int index2 = Integer.parseInt(tokens[2]);
                    int temp = numbers[index1];
                    numbers[index1] = numbers[index2];
                    numbers[index2] = temp;
                    break;
                case "multiply":
                    int index3 = Integer.parseInt(tokens[1]);
                    int index4 = Integer.parseInt(tokens[2]);
                    numbers[index3] *= numbers[index4];
                    break;
                case "decrease":
                    for (int i = 0; i < numbers.length; i++) {
                        numbers[i] -= 1;
                    }
                    break;
            }

            input = scanner.nextLine();
        }

        for (int i = 0; i < numbers.length; i++) {
            if (i == numbers.length - 1) {
                System.out.print(numbers[i]);
            } else {
                System.out.print(numbers[i] + ", ");
            }
        }
    }
}
