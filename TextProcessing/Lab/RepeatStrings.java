package TextProcessing.Lab;

import java.util.Scanner;

import static java.lang.System.out;

public class RepeatStrings {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] input = scanner.nextLine().split(" ");

        for (String word : input) {
            repeatLengthTimes(word);
        }
    }

    private static void repeatLengthTimes(String word) {
        for (int i = 1; i <= word.length(); i++) {
            out.print(word);
        }
    }
}
