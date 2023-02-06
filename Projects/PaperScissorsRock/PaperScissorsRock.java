/*
Условие:

*/
package Projects.PaperScissorsRock;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class PaperScissorsRock {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static long smallestLong = Long.MIN_VALUE;
    static long biggestLong = Long.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static short smallestShort = Short.MIN_VALUE;
    static short biggestShort = Short.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        out.print("Choose [p]aper, [s]cissors, [r]ock: ");
        String userInput = setValue(null, null);
        String computersChoice = randomComputerChoice();
        out.printf("The computer chose %s.\n", computersChoice);
        displayGameWonOrLost(userInput, computersChoice);
        playAgain();
    }

    private static void playAgain() {
        out.println("Do you want to play again? [y]es/[n]o?");
        String userInput = setValue(null, null);
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
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            value = scanner.nextLine();

            if (requiredString) {
                stringCount++;
                String[] required = {};

                if (stringCount > 2)
                    stringCount = 1;
                if (stringCount == 1)
                    required = new String[] {"p", "paper", "s", "scissors", "r", "rock"};
                if (stringCount == 2)
                    required = new String[] {"y", "yes", "n", "no"};

                List<String> requiredList = List.of(required);

                if (!requiredList.contains(value)){
                    out.print("Моля въведете един от следните избори: \n| ");
                    for (String thing : required)
                        out.print(thing + " | ");
                    out.println();

                    stringCount--;
                    return setValue(null, null);
                }
            }

            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|} 0123456789";
            boolean isSpecChar = false;

            for (int i = 0; i < ((String) value).length(); i++)
                if (specialCharacters.contains(Character.toString(((String) value).charAt(i)))) {
                    isSpecChar = true;
                    break;
                }

            if (isSpecChar) {
                out.println("Моля въведете правилно наименование!");
                return setValue(null, null);
            }
        }
        else {
            try {
                if (max instanceof Long)
                    value = Long.parseLong(scanner.nextLine());
                else if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Short)
                    value = Short.parseShort(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка!");
                    value = null;
                    exit(1);
                }
            }
            catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }
            if (max instanceof Long) {
                if ((long) value < (long) min || (long) value > (long) max) {
                    if ((long) min == 0 && (long) max == biggestLong)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == biggestInt)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Short) {
                if ((short) value < (short) min || (short) value > (short) max) {
                    if ((long) min == 0 && (short) max == biggestShort)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == biggestDouble)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
        }

        return (T) value;
    }
}