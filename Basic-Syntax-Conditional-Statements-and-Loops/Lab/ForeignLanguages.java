/*
Условие:
    Write a program that prints the language that a given
    country speaks. You can receive only the following
    combinations: English is spoken in England and USA; Spanish
    is spoken in Spain, Argentina, and Mexico; for the others,
    we should print "unknown".
Input
    You will receive a single country name on a single line.
Output
    Print the language, which the country speaks,
    or if it is unknown for your program, print "unknown".
Examples
    USA
    -> English
    Germany
    -> unknown
*/
package SoftUni.Fundamentals.Lab1;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class ForeignLanguages {

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String countryName = scanner.nextLine();
        displayLanguage(countryName);
    }

    private static void displayLanguage(String countryName) {
        switch (countryName) {
            case "England":
            case "USA":
                out.println("English");
                break;
            case "Spain":
            case "Argentina":
            case "Mexico":
                out.println("Spanish");
                break;
            default:
                out.println("unknown");
                break;
        }
    }
}
