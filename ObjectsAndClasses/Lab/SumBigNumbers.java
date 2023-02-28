/*
Task:
    You will receive two numbers (0 to 10^50), and print their sum.
Examples:
    923847238931983192462832102
    934572893617836459843471846187346
    ->
    934573817465075391826664309019448

    4
    100
    ->
    104
*/
package ObjectsAndClasses.Lab;

import java.math.BigInteger;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class SumBigNumbers {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        BigInteger firstNum = new BigInteger(scanner.nextLine());
        BigInteger secondNum = new BigInteger(scanner.nextLine());
        BigInteger sum = firstNum.add(secondNum);

        out.println(sum);
    }
}