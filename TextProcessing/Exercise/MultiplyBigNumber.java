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
import java.util.Scanner;

import static java.lang.System.out;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class MultiplyBigNumber {
    @Test
    public void testNegativeNumbers() {
        int result = multiply(-2, 3);
        assertEquals(-6, result);
    }
    @Test
    public void testMultiplicationWithZero() {
        int result = multiply(0, 5);
        assertEquals(0, result);
    }
    @Test
    public void testLargeNumbers() {
        int result = multiply(100000, 10000);
        assertEquals(1000000000, result);
    }
    @Test
    public void testMultiplicationWithOne() {
        int result = multiply(1, 7);
        assertEquals(7, result);
    }
    @Test
    public void testBothNumbersNegative() {
        int result = multiply(-4, -3);
        assertEquals(12, result);
    }
    @Test
    public void testMultiplyWithOnePositiveAndOneNegativeDoubleDigit() {
        int result = multiply(-56, 47);
        assertEquals(-2632, result);
    }
    @Test
    public void testMultiplyWithOnePositiveAndOneNegativeSingleDigit() {
        int result = multiply(8, -5);
        assertEquals(-40, result);
    }
    @Test
    public void testMultiplyWithTwoPositiveSingleDigit() {
        int result = multiply(3, 9);
        assertEquals(27, result);
    }
    @Test
    public void testMultiplyWithTwoNegativeDoubleDigit() {
        int result = multiply(-67, -25);
        assertEquals(1675, result);
    }
    @Test
    public void testMultiplyWithOneNegativeAndOneZero() {
        int result = multiply(-9, 0);
        assertEquals(0, result);
    }
    @Test
    public void testMultiplyWithOnePositiveAndOneZero() {
        int result = multiply(7, 0);
        assertEquals(0, result);
    }
    @Test
    public void testMultiplyWithTwoNegativeSingleDigit() {
        int result = multiply(-4, -6);
        assertEquals(24, result);
    }
    @Test
    public void testMultiplyWithOneNegativeAndOnePositiveDoubleDigit() {
        int result = multiply(-25, 63);
        assertEquals(-1575, result);
    }
    @Test
    public void testMultiplyWithOnePositiveAndOneNegativeZero() {
        int result = multiply(0, -5);
        assertEquals(0, result);
    }
    @Test
    public void testMultiplyWithTwoPositiveDoubleDigit() {
        int result = multiply(36, 98);
        assertEquals(3528, result);
    }
    @Test
    public void testMultiplyWithOnePositiveAndOneNegativeSmallerAbsValue() {
        int result = multiply(-10, 3);
        assertEquals(-30, result);
    }
    @Test
    public void testMultiplyWithOnePositiveAndOneNegativeLargerAbsValue() {
        int result = multiply(-2, 7);
        assertEquals(-14, result);
    }
    @Test
    public void testMultiplyWithTwoIdenticalNegative() {
        int result = multiply(-9, -9);
        assertEquals(81, result);
    }
    @Test
    public void testMultiplyWithTwoIdenticalPositive() {
        int result = multiply(6, 6);
        assertEquals(36, result);
    }
    @Test
    public void testMultiplyWithTwoDifferentPrimes() {
        int result = multiply(13, 17);
        assertEquals(221, result);
    }
    @Test
    public void testMultiplyWithTwoDifferentNonPrimes() {
        int result = multiply(14, 25);
        assertEquals(350, result);
    }
    @Test
    public void testMultiplyWithTwoDifferentOdds() {
        int result = multiply(3, 5);
        assertEquals(15, result);
    }
    @Test
    public void testMultiplyWithTwoDifferentEvens() {
        int result = multiply(4, 6);
        assertEquals(24, result);
    }
    @Test
    public void testMultiplyWithTwoLargeBigDecimalNumbers() {
        BigDecimal num1 = new BigDecimal("999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        BigDecimal num2 = new BigDecimal("888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
        BigDecimal expected = new BigDecimal("888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888887111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111112");
        assertEquals(expected, multiplyBigs(num1, num2));
    }
    @Test
    public void testMultiplyWithOneSmallAndOneLargeBigDecimalNumber() {
        BigDecimal num1 = new BigDecimal("123456789");
        BigDecimal num2 = new BigDecimal("987654321987654321");
        BigDecimal expected = new BigDecimal("121932631234567900112635269");
        assertEquals(expected, multiplyBigs(num1, num2));
    }
    @Test
    public void testMultiplyWithSamePrecision() {
        BigDecimal num1 = new BigDecimal("12345678901234567890");
        BigDecimal num2 = new BigDecimal("98765432109876543210");
        BigDecimal expected = new BigDecimal("1219326311370217952237463801111263526900");
        assertEquals(expected, multiplyBigs(num1, num2));
    }
    @Test
    public void testMultiplyWithLargeNumbers() {
        BigDecimal num1 = new BigDecimal("999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        BigDecimal num2 = new BigDecimal("999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        BigDecimal expected = new BigDecimal("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001");
        assertEquals(expected, multiplyBigs(num1, num2));
    }

    private int multiply(int a, int b) {
        return a * b;
    }
    public static BigDecimal multiplyBigs(BigDecimal num1, BigDecimal num2) {
        return num1.multiply(num2);
    }
    private static final Scanner scanner = new Scanner(System.in);
    private static int onMind = 0;

    public static void main(String[] args) {
        String num1 = scanner.nextLine();
        String num2 = scanner.nextLine();
        StringBuilder finalResult = new StringBuilder();
        boolean isNegative = false;

        if (num1.equals("0") || num2.equals("0")) {
            out.println(0);
            return;
        }

        if (num1.startsWith("-") && num2.startsWith("-")) {
            num1 = num1.substring(1);
            num2 = num2.substring(1);
        }

        if (num2.startsWith("-")) {
            num2 = num2.substring(1);
            isNegative = true;
        }

        if (num1.startsWith("-")) {
            num1 = num1.substring(1);
            isNegative = true;
        }

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

        if(onMind != 0) {
            finalResult.append(reverse(String.valueOf(onMind)));
        }

        String finalResultString = finalResult.toString();
        String reversedFinalResult = reverse(finalResultString);

        if (reversedFinalResult.startsWith("0")) {
            reversedFinalResult = reversedFinalResult.substring(1);
        }

        if (isNegative) {
            out.print("-");
        }

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
