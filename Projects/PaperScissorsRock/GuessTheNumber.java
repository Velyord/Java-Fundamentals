/*
Условие:
    Today we will make the console game "Guess A Number".
    "Guess A Number" is a game in which your opponent "the computer" chooses a random number between "1 and 100"
    and your task is to guess this number. After each number you enter,
    the computer will give you a hint of whether the number is greater or less than the number you selected
    until you guess the correct number
*/
package Projects.PaperScissorsRock;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class GuessTheNumber {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        boolean hasGuessedCorrectly = false;
        int guessCount = 0;
        int computerNumber = pickARandomNumberBetween(1, 100);

        while (!hasGuessedCorrectly) {
            int playerGuess = setValue(1, 100);
            guessCount++;

            if (playerGuess < computerNumber) {
                out.println("Too Low");
            } else if (playerGuess > computerNumber) {
                out.println("Too High");
            } else {
                out.println("You guessed it");
                out.println("Count of guesses: " + guessCount);
                hasGuessedCorrectly = true;
            }
        }
    }

    private static int pickARandomNumberBetween(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        String type = getType(max);
        Object value = setAndCheckInputFor(type);
        if (!isValueBetweenMinAndMax(value, min, max, type))
            return setValue(min, max);
        return (T) value;
    }

    private static <T> String getType(T max) {
        if (max instanceof Double)
            return "double";
        else if (max instanceof Float)
            return "float";
        else if (max instanceof Long)
            return "long";
        else
            return "int";
    }

    @SuppressWarnings("unchecked")
    private static <T> T setAndCheckInputFor(String type) {
        Object value;
        try {
            switch (type) {
                case "double": value = Double.parseDouble(scanner.nextLine()); break;
                case "float":  value = Float.parseFloat(scanner.nextLine());   break;
                case "long":   value = Long.parseLong(scanner.nextLine());     break;
                case "int":    value = Integer.parseInt(scanner.nextLine());   break;
                default:       value = null;                                   break;
            }
        } catch (Exception e) {
            out.println("Incorrect number. Try again!");
            return setAndCheckInputFor(type);
        }
        return (T) value;
    }

    private static <T> boolean isValueBetweenMinAndMax(T value, T min, T max, String type) {
        double minDouble;
        float minFloat;
        switch (type) {
            case "double":
                minDouble = (double) min == Double.MIN_VALUE ? -1 * Double.MAX_VALUE : (double) min;
                if ((double) value >= minDouble && (double) value <= (double) max)
                    return true;
                break;
            case "float":
                minFloat = (float) min == Float.MIN_VALUE ? -1 * Float.MAX_VALUE : (float) min;
                if ((float) value >= minFloat && (float) value <= (float) max)
                    return true;
                break;
            case "long":
                if ((long) value >= (long) min && (long) value <= (long) max)
                    return true;
                break;
            case "int":
                if ((int) value >= (int) min && (int) value <= (int) max)
                    return true;
                break;
        }
        out.printf("Please pick a number between %s and %s:\n", min, max);
        return false;
    }
}