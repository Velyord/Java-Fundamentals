package TextProcessing.Lab;

import java.util.Scanner;
import static java.lang.System.out;

public class DigitsLettersAndOther {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StringBuilder digits = new StringBuilder();
    private static final StringBuilder letters = new StringBuilder();
    private static final StringBuilder symbols = new StringBuilder();

    public static void main(String[] args) {
        String input = scanner.nextLine();
        splitDifferentChars(input);
        printDifferentChars();
    }

    private static void splitDifferentChars(String input) {
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isDigit(currentChar)) {
                digits.append(currentChar);
            } else if (Character.isLetter(currentChar)) {
                letters.append(currentChar);
            } else {
                symbols.append(currentChar);
            }
        }
    }

    private static void printDifferentChars() {
        out.println(digits);
        out.println(letters);
        out.println(symbols);
    }
}
