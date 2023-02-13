/*
Условие:
    Today we will make the console game "Guess A Number".
    "Guess A Number" is a game in which your opponent "the computer" chooses a random number between "1 and 100"
    and your task is to guess this number. After each number you enter,
    the computer will give you a hint of whether the number is greater or less than the number you selected
    until you guess the correct number
*/
package Projects;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class GuessTheNumber {
    static Scanner scanner = new Scanner(in);
    static int correctGuesses = 0;
    static int allGuesses = 0;

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        boolean hasGuessedCorrectly = false;
        int guessCount = 0;
        int computerNumber = pickARandomNumberBetween(1, 100);

        while (!hasGuessedCorrectly) {
            out.print("\nGuess a number (1-100): ");
            int playerGuess = setValue(1, 100);
            guessCount++;

            if (playerGuess < computerNumber) {
                out.println("Too Low");
            } else if (playerGuess > computerNumber) {
                out.println("Too High");
            } else {
                correctGuesses++;
                allGuesses += guessCount;
                out.println("You guessed it");
                out.println("Count of guesses: " + guessCount);
                hasGuessedCorrectly = true;
            }
        }
        playAgain();
    }

    private static void playAgain() {
        out.println("\nDo you want to play again? [y]es/[n]o?");
        String userInput = setValue();
        if (userInput.equals("yes") || userInput.equals("y")) {
            playGame();
        } else {
            double averageGuessCount = (double) allGuesses / correctGuesses;
            out.printf(
                "\nGame Over!\nGuessed numbers: %d | Guesses made: %d | Average guesses: %.1f\n",
                correctGuesses, allGuesses, averageGuessCount
            );
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

    static int stringCount = 0;

    private static String setValue() {
        String value = scanner.nextLine();

        if (!hasValidChars(value) || !doesFollowTemplate(value)) {
            return setValue();
        } else {
            return value;
        }
    }

    private static <T> boolean hasValidChars(T value) {
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|} 0123456789";
        boolean isSpecialChar = false;
        char specialChar = ' ';

        for (int i = 0; i < ((String) value).length(); i++) {
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i);
                break;
            }
        }

        if (isSpecialChar) {
            if (specialChar == ' ') {
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            } else {
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            }

            return false;
        }

        return true;
    }

    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};

        if (stringCount > 1)
            stringCount = 1;
        else {
            requiredStrings = new String[]{"y", "yes", "n", "no"}; // fill if needed
        }

        if (requiredStrings.length != 0) {
            List<String> requiredList = List.of(requiredStrings);

            if (!requiredList.contains(value.toString())) {
                out.print("Please pick one of the following choices: \n| ");

                for (String requiredString : requiredStrings) {
                    out.print(requiredString + " | ");
                } out.println(); // new line

                stringCount--;

                return false;
            }
        }

        return true;
    }
}