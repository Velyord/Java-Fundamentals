package TextProcessing.Exercise;

import java.util.Scanner;

import static java.lang.System.out;

public class MultiplyBigNumber {
    private static final Scanner scanner = new Scanner(System.in);
    private static int onMind = 0;

    public static void main(String[] args) {
        String num1 = scanner.nextLine();
        String num2 = scanner.nextLine();
        StringBuilder finalResult = new StringBuilder();

        for (int i = 0; i < num1.length(); i++) {
            int number1 = Integer.parseInt(String.valueOf(num1.charAt(num1.length() - i - 1)));
            int number2 = Integer.parseInt(num2);
            int result = number1 * number2 + onMind;
            onMind = 0;

            if (result >= 10) {
                finalResult.append(result % 10);
                onMind = result / 10;
            } else {
                finalResult.append(result);
            }
        }

        if(onMind > 0) {
            finalResult.append(onMind);
        }

        String finalResultString = finalResult.toString();
        String reversedFinalResult = reverse(finalResultString);

        out.println(reversedFinalResult);
    }

    private static String reverse(String input) {
        StringBuilder reversedInput = new StringBuilder();

        for (int i = input.length() - 1; i >= 0 ; i--) {
            reversedInput.append(input.charAt(i));
        }

        return reversedInput.toString();
    }
}
