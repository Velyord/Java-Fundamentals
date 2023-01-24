/*
Условие:
    Write a program that helps you buy the games.
    The valid games are the following games in this table:
        Name
        Price
        OutFall 4
        $39.99
        CS: OG
        $15.99
        Zplinter Zell
        $19.99
        Honored 2
        $59.99
        RoverWatch
        $29.99
        RoverWatch Origins Edition
        $39.99
    On the first line, you will receive your current balance –
    a floating-point number in the range [0.00…5000.00].
    Until you receive the command "Game Time", you have to keep buying games.
    When a game is bought, the user's balance decreases by the price of the game.
    Additionally, the program should obey the following conditions:
        • If a game the user is trying to buy is not present in the table above, print "Not Found"
        and read the next line.
        • If at any point, the user has $0 left, print "Out of money!" and end the program.
        • Alternatively, if the user is trying to buy a game that they can't afford,
        print "Too Expensive" and read the next line.
    When you receive "Game Time", print the user's remaining money and total spent on games,
    rounded to the 2nd decimal place.
Examples:
    120
    RoverWatch
    Honored 2
    Game Time
    ->
    Bought RoverWatch
    Bought Honored 2
    Total spent: $89.98. Remaining: $30.02

    19.99
    Reimen origin
    RoverWatch
    Zplinter Zell
    Game Time
    ->
    Not Found
    Too Expensive
    Bought Zplinter Zell
    Out of mo-ney!

    79.99
    OutFall 4
    RoverWatch Origins Edition
    Game Time
    ->
    Bought OutFall 4
    Bought RoverWatch Origins Edition
    Total spent: $79.98. Remaining: $0.01
*/
package SoftUni.Fundamentals.ME1;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class GamingStore {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String [] nameOfGames = {"OutFall 4", "CS: OG", "Zplinter Zell", "Honored 2", "RoverWatch", "RoverWatch Origins Edition"};
        double [] priceOfGames = {39.99, 15.99, 19.99, 59.99, 29.99, 39.99};
        List<String> nameOfGamesList = List.of(nameOfGames);


        double originalBalance = setValue(0.00, 5000.00);
        double balance = originalBalance;
        String input = scanner.nextLine();
        boolean hasMoney = true;
        while(!input.equals("Game Time")) {
            String nameOfGame = input;

            if (!nameOfGamesList.contains(nameOfGame)){
                out.println("Not Found");
            } else {
                int indexOfGame = nameOfGamesList.indexOf(nameOfGame);
                double priceOfGame = priceOfGames[indexOfGame];
                if (balance < priceOfGame)
                    out.println("Too Expensive");
                else {
                    balance -= priceOfGame;
                    out.printf("Bought %s\n", nameOfGame);
                }
            }
            if (balance == 0) {
                out.println("Out of money!");
                hasMoney = false;
                break;
            }
            input = scanner.nextLine();
        }
        if (hasMoney) {
            double totalSpent = abs(balance - originalBalance);
            double remaining = originalBalance - totalSpent;
            out.printf("Total spent: $%.2f. Remaining: $%.2f", totalSpent, remaining);
        }        
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
            boolean isSpecChar = false;
            value = scanner.nextLine();

            for (int i = 0; i < ((String) value).length(); i++)
                if (specialCharacters.contains(Character.toString(((String) value).charAt(i)))) {
                    isSpecChar = true;
                    break;
                }

            if (isSpecChar) {
                out.println("Моля въведете правилно наименование!");
                return setValue(null, null);
            }

            if (requiredString){
                stringCount++;
                String[] required = {};

                if (stringCount == 1)
                    required = new String[] {"Spring", "Summer", "Autumn", "Winter"};
                if (stringCount == 2)
                    required = new String[] {"Y", "N"};
                if (stringCount > 2) {
                    requiredString = false;
                    return (T) value;
                }

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
        }
        else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
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

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == biggestInt)
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
