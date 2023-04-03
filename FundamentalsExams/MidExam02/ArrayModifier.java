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
