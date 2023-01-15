/*
Условие:
    Write a program that prints all the numbers from 1 to 100,
    which are divisible by 3. You have to use a single for loop.
    The program should not receive input.
*/
package SoftUni.Fundamentals.Lab1;

import static java.lang.System.out;

public class DivisibleBy3 {
    public static void main(String[] args) {
        for (int i=3; i<=99; i+=3) {
            out.println(i);
        }
    }
}
