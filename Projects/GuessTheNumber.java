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

    // метод за въвеждане на произволно число в дадени граници
    private static int pickARandomNumberBetween(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }

    // метод за въвеждане на число в дадени граници
    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        String type = getType(max); // намиране на типа му според зададената му максимална граница
        Object value = setAndCheckInputFor(type); // задаване на числото според неговия тип и проверка за изключения

        // ако не е в дадената граница се повтаря метода
        if (!isValueBetweenMinAndMax(value, min, max, type)) {
            return setValue(min, max);
        } else {
            return (T) value;
        }
    }

    // метод за намиране на типа на число
    private static <T> String getType(T max) {
        if (max instanceof Double) {
            return "double";
        } else if (max instanceof Float) {
            return "float";
        } else if (max instanceof Long) {
            return "long";
        } else {
            return "int";
        }
    }

    // задава се число според прихванатия му тип, ако се хване изключение, трябва да се въведе ново число
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
            out.println("Invalid number. Try again!");
            return setAndCheckInputFor(type);
        }

        return (T) value;
    }

    // метод за проверка на входното число, дали е в зададените граници според неговия тип
    private static <T> boolean isValueBetweenMinAndMax(T value, T min, T max, String type) {
        // променливите са за постигане на максимално негативно число при числата с плаваща запетая.
        double minDouble;
        float minFloat;

        // проверка дали входното число е правилно избрано според зададените граници
        switch (type) {
            case "double":
                // ако е зададено най-малкото число, го замести с най-голямото умножено по -1
                minDouble = (double) min == Double.MIN_VALUE ? -1 * Double.MAX_VALUE : (double) min;
                if ((double) value >= minDouble && (double) value <= (double) max) {
                    return true;
                } break;
            case "float":
                // ако е зададено най-малкото число, го замести с най-голямото умножено по -1
                minFloat = (float) min == Float.MIN_VALUE ? -1 * Float.MAX_VALUE : (float) min;
                if ((float) value >= minFloat && (float) value <= (float) max) {
                    return true;
                } break;
            case "long":
                if ((long) value >= (long) min && (long) value <= (long) max) {
                    return true;
                } break;
            case "int":
                if ((int) value >= (int) min && (int) value <= (int) max) {
                    return true;
                } break;
        }

        // при грешка трябва да се въведе ново число
        out.printf("Please pick a number between %s and %s:\n", min, max);
        return false;
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

        if (stringCount > 1) { // при рестарт на играта
            stringCount = 1;
        }
        // тук се нагласят шаблоните на низовете, ако имат такива.
        if (stringCount == 1) {
            requiredStrings = new String[]{"y", "yes", "n", "no"}; // Възможните отговорите на въпроса за нова игра
        }

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