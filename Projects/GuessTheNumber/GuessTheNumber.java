/*
Условие:
    Today we will make the console game "Guess A Number".
    "Guess A Number" is a game in which your opponent "the computer" chooses a random number between "1 and 100"
    and your task is to guess this number. After each number you enter,
    the computer will give you a hint of whether the number is greater or less than the number you selected
    until you guess the correct number
*/
package Projects.GuessTheNumber;

import static Projects.GuessTheNumber.PersonalUtils.pickARandomNumberBetween;
import static Projects.GuessTheNumber.StringValidator.setValue;
import static Projects.GuessTheNumber.NumberValidator.setValue;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class GuessTheNumber {
    static Scanner scanner = new Scanner(in);
    static int correctGuesses = 0; // общо правилни отгатвания от началото на програмата
    static int allGuesses = 0; // общо опити за познаване от началото на програмата

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        boolean hasGuessedCorrectly = false;
        int guessCount = 0;
        int computerNumber = pickARandomNumberBetween(1, 100); // произволно число между 1 и 100

        while (!hasGuessedCorrectly) { // цикъл до познаване на правилното число
            out.print("\nGuess a number (1-100): ");
            int playerGuess = setValue(1, 100); // потребителя трябва да въведе число между 1 и 100
            guessCount++;

            if (playerGuess < computerNumber) { // подсказка
                out.println("Too Low");
            } else if (playerGuess > computerNumber) { // подсказка
                out.println("Too High");
            } else {
                correctGuesses++;
                allGuesses += guessCount;
                out.println("You guessed it");
                out.println("Count of guesses: " + guessCount);
                hasGuessedCorrectly = true; // край на цикъла
            }
        }
        playAgain(); // покана за нова игра
    }

    // метод за рестартиране на играта
    private static void playAgain() {
        out.println("\nDo you want to play again? [y]es/[n]o?");
        String userInput = setValue();
        if (userInput.equals("yes") || userInput.equals("y")) { // при ДА се пуска отново играта
            playGame();
        } else { // при НЕ се изчисляват резултатите от рпедходните игри и се визуализират
            double averageGuessCount = (double) allGuesses / correctGuesses;
            out.printf(
                    "\nGame Over!\nGuessed numbers: %d | Guesses made: %d | Average guesses: %.1f\n",
                    correctGuesses, allGuesses, averageGuessCount
            );
        }
    }






}