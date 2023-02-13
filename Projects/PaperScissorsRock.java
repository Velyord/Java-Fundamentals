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
    static int playerScore = 0; // общ резултат на потребителя от началото на програмата
    static int computerScore = 0; // общ резултат на компютъра от началото на програмата

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        out.print("\nChoose [p]aper, [s]cissors, [r]ock: ");
        String userInput = setValue();
        String computersChoice = randomComputerChoice(); // произволен избор
        out.printf("The computer chose %s.\n", computersChoice);
        displayGameWonOrLost(userInput, computersChoice); // резулат на последната игра
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

    // произволен избор на компютъра
    private static String randomComputerChoice() {
        Random rand = new Random();
        return switch (pickARandomNumberBetween(1, 3)) {
            case 1 -> "Paper";
            case 2 -> "Scissors";
            case 3 -> "Rock";
            default -> "";
        };
    }

    // метод за избиране на произволно число между дадени граници
    private static int pickARandomNumberBetween(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }

    static int stringCount = 0; // при въвеждане на низ, броячът нараства

    // метод за откриване на грешни в низ от потребителя
    private static String setValue() {
        String value = scanner.nextLine();

        /* ако има забранени символи или не следва задените шаблони,
        низът на потребителя не се приема и трябва да се въведе нов */
        if (!hasValidChars(value) || !doesFollowTemplate(value)) {
            return setValue();
        } else {
            return value;
        }
    }

    // хващане на специални/забранени символи
    private static <T> boolean hasValidChars(T value) {
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789"; // може да се променят забранените символи
        boolean isSpecialChar = false;
        char specialChar = ' ';

        // Търсене на забранен символ чрез сравняване на входните данни с низът със забранени символи
        for (int i = 0; i < ((String) value).length(); i++) {
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i); // открит забранен символ
                break;
            }
        }

        // При грешка се показва на потребителя, кой от въведените му символи е забранен
        if (isSpecialChar) {
            if (specialChar == ' ') {
                out.println("Spaces are not allowed. Try again!");
            } else {
                out.printf("%c is not allowed. Try again!\n", specialChar);
            }

            return false;
        }

        return true;
    }

    // подтикване на потребителя да въвежда предварително зададени низове.
    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};

        if (stringCount > 2) { // при рестарт на играта
            stringCount = 1;
        }
        // тук се нагласят шаблоните на низовете, ако имат такива.
        if (stringCount == 1) { // Избор на играча.
            requiredStrings = new String[]{"p", "paper", "s", "scissors", "r", "rock"};
        }
        if (stringCount == 2) { // Въпрос за рестартиране на играта.
            requiredStrings = new String[]{"y", "yes", "n", "no"};
        }
        // могат да се добавят и още шаблони.

        // ако е зададен шаблон се изпълнява следния код.
        if (requiredStrings.length != 0) {
            List<String> requiredList = List.of(requiredStrings); // създава се списък със задължителни входни данни

            if (!requiredList.contains(value.toString())) { // ако се въведе нещо, различно от зададеното в шаблона
                out.print("Please enter one of the following choices: \n| ");

                // завърта се цикъл, който да покаже на потребителя, кои са възможните опции
                for (String requiredString : requiredStrings) {
                    out.print(requiredString + " | "); // разделител
                } out.println(); // нов ред

                stringCount--; // не се брои сгрешения низ.

                return false;
            }
        }

        return true;
    }
}