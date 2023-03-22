/*
Task:
    Write a program to calculate the total cost of different types of furniture.
    You will be given some lines of input until you receive the line "Purchase".
    For the line to be valid, it should be in the following format:
    ">>{furniture name}<<{price}!{quantity}"
    The price can be a floating-point number or a whole number.
    Store the names of the furniture and the total price.
    In the end, print each bought furniture on a separate line in the format:
    "Bought furniture:
    {1st name}
    {2nd name}
    …"
    And on the last line, print the following: "Total money spend: {spend money}",
    formatted to the second decimal point.
Examples:
    >>Sofa<<312.23!3
    >>TV<<300!5
    >Invalid<<!5
    Purchase
    ->
    Bought furniture:
    Sofa
    TV
    Total money spend: 2436.69
        Only the Sofa and the TV are valid,
        for each of them we multiply the price by the quantity and print the result.
    >>TV<<312.23!3
    >>Monitor<<300!5
    >>Invalid<<<<!5
    >>Sink<<220!10
    >>>>>>Invalid<<!5
    Purchase
    ->
    Bought furniture:
    TV
    Monitor
    Sink
    Total money spend: 4636.69
 */
package RegularExpressions.Exercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.in;
import static java.lang.System.out;

public class Furniture {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String userInput = scanner.nextLine();
        out.println("Bought furniture:");
        double totalMoneySpend = 0;

        while (!userInput.equals("Purchase")) {
            Pattern pattern = Pattern.compile(">>(?<furniture>\\w+)<<(?<price>\\d+[.]*\\d+)!(?<quantity>\\d+)\\b");
            Matcher matcher = pattern.matcher(userInput);

            while (matcher.find()) {
                String furniture = matcher.group("furniture");
                double price = Double.parseDouble(matcher.group("price"));
                int quantity = Integer.parseInt(matcher.group("quantity"));
                totalMoneySpend += price * quantity;
                out.println(furniture);
            }

            userInput = scanner.nextLine();
        }

        out.printf("Total money spend: %.2f", totalMoneySpend);
    }
}
