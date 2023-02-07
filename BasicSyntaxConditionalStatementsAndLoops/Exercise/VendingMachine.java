/*
Условие:
    Your task is to calculate the total purchase price from a
    vending machine. Until you receive "Start" you will be
    given different coins that are being inserted into the
    machine. You have to sum them to have the total money
    inserted. There is a problem though. Your vending machine
    only works with 0.1, 0.2, 0.5, 1, and 2 coins. If someone
    tries to insert some other coins, you have to display
    "Cannot accept {money}", where the value is formatted to
    the second digit after the decimal point and not add it to
    the total money. On the next few lines until you receive
    "End" you will be given products to purchase. Your machine
    has, however, only "Nuts", "Water", "Crisps", "Soda",
    "Coke". The prices are: 2.0, 0.7, 1.5, 0.8, 1.0
    respectively. If the person tries to purchase a not
    existing product, print "Invalid product". Be careful that
    the person may try to purchase a product for which he
    doesn't have money. In that case, print "Sorry, not enough
    money". If the person purchases a product successfully
    print "Purchased {product name}". After the "End" command,
    print the money that is left formatted to the second
    decimal point in the format "Change: {money left}".
Examples:
    1
    1
    0.5
    0.6
    Start
    Coke
    Soda
    Crisps
    End
    ->
    Cannot accept 0.60
    Purchased Coke
    Purchased Soda
    Sorry, not enough money
    Change: 0.70

    1
    Start
    Nuts
    Coke
    End
    ->
    Sorry, not enough money
    Purchased Coke
    Change: 0.00
*/
package BasicSyntaxConditionalStatementsAndLoops.Exercise;

import java.util.Scanner;
import java.util.List;

import static java.lang.System.*;

public class VendingMachine {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double collectedCoins = 0;
        String input = scanner.nextLine();
        while (!input.equals("Start")) {
            double coin = Double.parseDouble(input);
            if (coin == 0.1 || coin == 0.2 || coin == 0.5 ||
                    coin == 1 || coin == 2)
                collectedCoins += coin;
            else
                out.printf("Cannot accept %.2f\n", coin);
            input = scanner.nextLine();
        }
        input = scanner.nextLine();
        while (!input.equals("End")) {
            boolean isValid = true;
            double productPrice = 0;
            String[] required = new String[] {
                    "Nuts", "Water", "Crisps", "Soda", "Coke"
            };
            List<String> requiredList = List.of(required);
            if (!requiredList.contains(input)) {
                out.println("Invalid product");
                isValid = false;
            }
            else {
                switch (input) {
                    case "Nuts":   productPrice = 2.0; break;
                    case "Water":  productPrice = 0.7; break;
                    case "Crisps": productPrice = 1.5; break;
                    case "Soda":   productPrice = 0.8; break;
                    case "Coke":   productPrice = 1.0; break;
                }
            }
            if (collectedCoins - productPrice < 0)
                out.println("Sorry, not enough money");
            else {
                if (isValid) {
                    collectedCoins -= productPrice;
                    out.printf("Purchased %s\n", input);
                }
            }
            input = scanner.nextLine();
        }
        out.printf("Change: %.2f", collectedCoins);
    }
}
