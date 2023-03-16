package TextProcessing.Lab;

import java.util.Scanner;
import static java.lang.System.out;

public class ReverseStrings {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String reversedInput = reverse(input);

            out.printf("%s = %s\n", input, reversedInput);

            input = scanner.nextLine();
        }
    }

    private static String reverse(String input) {
        StringBuilder reversedInput = new StringBuilder();

        for (int i = input.length() - 1; i >= 0 ; i--) {
            reversedInput.append(input.charAt(i));
        }

        return reversedInput.toString();
    }
}