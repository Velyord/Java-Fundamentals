/*
Условие:

*/
package Projects.PaperScissorsRock;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class PaperScissorsRock {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        out.print("Choose [p]aper, [s]cissors, [r]ock: ");
        String userInput = setValue();
        String computersChoice = randomComputerChoice();
        out.printf("The computer chose %s.\n", computersChoice);
        displayGameWonOrLost(userInput, computersChoice);
        playAgain();
    }

    private static void playAgain() {
        out.println("Do you want to play again? [y]es/[n]o?");
        String userInput = setValue();
        if (userInput.equals("yes"))
            playGame();
        else
            out.println("Have a good day!");
    }

    private static void displayGameWonOrLost(String userInput, String computersChoice) {
        boolean isWon = false;
        boolean isLost = false;
        boolean isDraw = false;

        switch (userInput) {
            case "paper", "p":
                switch (computersChoice) {
                    case "Paper"    -> isDraw = true;
                    case "Scissors" -> isLost = true;
                    case "Rock"     -> isWon  = true;
                }
                break;
            case "scissors", "s":
                switch (computersChoice) {
                    case "Paper"    -> isWon  = true;
                    case "Scissors" -> isDraw = true;
                    case "Rock"     -> isLost = true;
                }
                break;
            case "rock", "r":
                switch (computersChoice) {
                    case "Paper"    -> isLost = true;
                    case "Scissors" -> isWon  = true;
                    case "Rock"     -> isDraw = true;
                }
                break;
        }
        if (isDraw)
            out.println("This game was a draw");
        else if (isWon)
            out.println("You win.");
        else if (isLost)
            out.println("You lose.");
    }

    private static String randomComputerChoice() {
        Random rand = new Random();
        return switch (rand.nextInt(3)) {
            case 0 -> "Paper";
            case 1 -> "Scissors";
            case 2 -> "Rock";
            default -> "";
        };
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
            out.println("Невалидно число. Пробвайте пак!");
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
        out.printf("Моля въведете число между %s и %s:\n", min, max);
        return false;
    }
    static int stringCount = 0;
    private static String setValue() {
        String value = scanner.nextLine();
        if (!hasValidChars(value) || !doesFollowTemplate(value))
            return setValue();
        return value;
    }
    private static <T> boolean hasValidChars(T value) {
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|} 0123456789";
        boolean isSpecialChar = false;
        char specialChar = ' ';
        for (int i = 0; i < ((String) value).length(); i++)
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i);
                break;
            }
        if (isSpecialChar) {
            if (specialChar == ' ')
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            else
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            return false;
        }
        return true;
    }
    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};
        if (stringCount > 2)
            stringCount = 1;
        if (stringCount == 1)
            requiredStrings = new String[]{"p", "paper", "s", "scissors", "r", "rock"}; // fill if needed
        if (stringCount == 2)
            requiredStrings = new String[]{"y", "yes", "n", "no"}; // fill for second var
//        if (stringCount > 2)
//            requiredStrings = new String[]{}; // Keep empty
        if (requiredStrings.length != 0) {
            List<String> requiredList = List.of(requiredStrings);
            if (!requiredList.contains(value.toString())) {
                out.print("Моля въведете един от следните избори: \n| ");
                for (String requiredString : requiredStrings)
                    out.print(requiredString + " | ");
                out.println(); // new line
                stringCount--;
                return false;
            }
        }
        return true;
    }
    public static int[] addIntToArray(int toBeAdded, int[] array) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        newArray[array.length] = toBeAdded;
        return newArray;
    }
}