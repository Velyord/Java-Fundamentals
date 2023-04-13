/*
Task:
    You are given two lines – the first one can be a really big number (0 to 1050).
    The second one will be a single-digit number (0 to 9).
    You must display the product of these numbers.
    Note: do not use the BigInteger class.
Examples:
    23
    2
    ->
    46

    9999
    9
    ->
    89991

    923847238931983192462832102
    4
    ->
    3695388955727932769851328408
 */
package TextProcessing.Exercise;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.RepetitionInfo;
//import org.junit.jupiter.api.RepeatedTest;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplyBigNumber {
//    @RepeatedTest(100000)
//    @DisplayName("Test multiply(%s, %s)")
//    public void unitTest() {
//        Random random = new Random();
//        int numDigits1 = random.nextInt(50) + 1;
//        BigInteger bigInteger1 = new BigInteger(numDigits1 * 4, random);
//        BigDecimal randomBigDecimal1 = new BigDecimal(bigInteger1, 0);
//
//        int randomNumber = random.nextInt(10);
//
//        String expected = multiplyBigs(randomBigDecimal1, randomNumber);
//        String actual = calculate(String.valueOf(randomBigDecimal1), String.valueOf(randomNumber));
//
//        assertEquals(expected, actual, () -> String.format("Failed test case: %s * %s", randomBigDecimal1, randomNumber));
//    }
//    public static String multiplyBigs(BigDecimal num1, int num2) {
//        return num1.multiply(BigDecimal.valueOf(num2)).toString();
//    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String num1 = scanner.nextLine();
        String num2 = scanner.nextLine();

//        BigDecimal bigDecimal1 = new BigDecimal(num1);
//        BigDecimal bigDecimal2 = new BigDecimal(num2);
//        out.println(bigDecimal1.multiply(bigDecimal2));

        String reversedFinalResult = calculate(num1, num2);
        out.println(reversedFinalResult);
    }

    private static String calculate(String num1, String num2) {
        int onMind = 0;
        StringBuilder finalResult = new StringBuilder();

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

//        for (int i = 0; i < num1.length(); i++) {
//            if (num1.charAt(i) == '0') {
//                num1 = num1.substring(1);
//            } else {
//                break;
//            }
//        }

//        int countZeros = 0;
//
//        for (int i = 0; i < num1.length(); i++) {
//            if (num1.charAt(i) == '0') {
//                countZeros++;
//            } else {
//                break;
//            }
//        }
//
//        if (countZeros == num1.length()) {
//            return "0";
//        }

        if (num2.equals("1")) {
            return num1;
        }

        for (int i = 0; i < num1.length(); i++) {
            char currentNumber = num1.charAt(num1.length() - i - 1);
            int number1 = Integer.parseInt(String.valueOf(currentNumber));
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

        if(onMind != 0) {
            finalResult.append(onMind);
        }

        return reverse(finalResult.toString());
    }

    private static String reverse(String input) {
        StringBuilder reversedInput = new StringBuilder();

        for (int i = input.length() - 1; i >= 0 ; i--) {
            reversedInput.append(input.charAt(i));
        }

        return reversedInput.toString();
    }
}