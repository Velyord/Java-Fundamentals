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
package Projects.PaperScissorsRock;

import java.util.Scanner;

import static java.lang.System.*;

import static Projects.PaperScissorsRock.ComputersChoice.randomComputerChoice;
import static Projects.PaperScissorsRock.StringValidator.setValue;

public class PaperScissorsRock {
    static Scanner scanner = new Scanner(in);
    static int playerScore = 0; // общ резултат на потребителя от началото на програмата
    static int computerScore = 0; // общ резултат на компютъра от началото на програмата

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        out.print("\nChoose [p]aper, [s]cissors, [r]ock: ");
        String userInput = setValue();
        String computersChoice = randomComputerChoice(); // произволен избор на компютъра
        out.printf("The computer chose %s.\n", computersChoice);
        displayGameStatus(userInput, computersChoice); // резулат на последната игра
        out.printf("Your score: %d | Computer's score: %d\n", playerScore, computerScore);
        playAgain(); // покана за нова игра
    }

    // метод за стартиране на нова игра
    private static void playAgain() {
        out.println("\nDo you want to play again? [y]es/[n]o?");
        String userInput = setValue();
        if (userInput.equals("yes") || userInput.equals("y")) { // при ДА се започва нова игра
            playGame();
        } else { // при НЕ се показва общия резултат от всички игри
            out.printf("\nGame Over!\nYour score: %d | Computer's score: %d\n", playerScore, computerScore);
        }
    }

    // метод за определяне на победителя според правилата на играта
    private static void displayGameStatus(String userInput, String computersChoice) {
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

        if (isDraw) {
            out.println("This game was a draw.");
        } else if (isWon) {
            playerScore++;
            out.println("You win.");
        } else if (isLost) {
            computerScore++;
            out.println("You lose.");
        }
    }
}