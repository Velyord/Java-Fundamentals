/*
Условие:
    Rock - Paper - Scissors is a simple two player game where you and your opponent (the computer)
    simultaneously choose one of the following three options: "rock", "paper" or "scissors".
    The rules are as follows:
    · Rock beats scissors (the scissors get broken by the rock)
    · Scissors beats paper (the paper gets cut by the scissors)
    · Paper beats rock (the paper covers the rock)
    The winner is the player whose choice beats the choice of his opponent.
    If both players choose the same option (e.g. "paper"), the game outcome is "draw":
*/
package Projects;

import java.util.Random;
import java.util.Scanner;
import java.util.List;

import static java.lang.System.*;

public class PaperScissorsRock {
    static Scanner scanner = new Scanner(in);
    static int playerScore = 0;
    static int computerScore = 0;

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        out.print("\nChoose [p]aper, [s]cissors, [r]ock: ");
        String userInput = setValue();
        String computersChoice = randomComputerChoice();
        out.printf("The computer chose %s.\n", computersChoice);
        displayGameWonOrLost(userInput, computersChoice);
        out.printf("Your score: %d | Computer's score: %d\n", playerScore, computerScore);
        playAgain();
    }

    private static void playAgain() {
        out.println("\nDo you want to play again? [y]es/[n]o?");
        String userInput = setValue();
        if (userInput.equals("yes") || userInput.equals("y"))
            playGame();
        else
            out.printf("\nGame Over!\nYour score: %d | Computer's score: %d\n", playerScore, computerScore);
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
            out.println("This game was a draw.");

        else if (isWon) {
            playerScore++;
            out.println("You win.");
        }

        else if (isLost) {
            computerScore++;
            out.println("You lose.");
        }
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
                out.println("Spaces are not allowed. Try again!");
            else
                out.printf("%c is not allowed. Try again!\n", specialChar);
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
                out.print("Please pick one of the following choices: \n| ");
                for (String requiredString : requiredStrings)
                    out.print(requiredString + " | ");
                out.println(); // new line
                stringCount--;
                return false;
            }
        }
        return true;
    }
}