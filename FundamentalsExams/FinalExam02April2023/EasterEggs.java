/*
Task:
    Easter is coming and your job is to find all the hidden eggs' colors and their amount.
    You will be given a text string.
    To find the hidden eggs and their amount, read them and mark the ones that are valid,
    considering the following rules:
    • Eggs' color:
        o Surrounded by one or more "@" or "#" (they don't have to be the same).
        o It is written with lower case alphabetical letters only.
        o Each color should be minimum 3 letters long.
    • Amount:
        o Always positioned after the color.
        o Between the color and the amount there could or could not be any other characters.
    If there are, they must NOT be alphabetical letters or digits. Otherwise, the color-amount combination is invalid.
        o Surrounded by one or more "/".
        o Contains only digits.
        o If the color or the amount is not valid, we consider that the color-amount combination is invalid.
    */// Examples of valid eggs: @red@*/54/, #green##//2//, @@@yellow#@*/%^&/36/, @#blue@*/1//
    // Examples of invalid eggs: ##@InvalidColor##/54/, @notc0l0r@*23*, @invalid_color@/notnumber/
    /* Next, you will have to print all the valid eggs like following:
    "You found {amount} {color} eggs!" for every color-amount combination.
Examples:
    • You will receive a string.
    • Print the proper output messages in the proper cases as described in the problem description.
Input:
    */// @@@@green@*/10/@yel0w@*26*#red#####//8//@limon*@*23*@@@red#*/%^&/6/@gree_een@/notnumber/###purple@@@@@*$%^&*/5/
/* Output:
    You found 10 green eggs!
    You found 8 red eggs!
    You found 6 red eggs!
    You found 5 purple eggs!
Input:
    */// #@##@red@#/8/@rEd@/2/#@purple@////10/
/* Output:
    You found 8 red eggs!
    You found 10 purple eggs!
*/
package FundamentalsExams.FinalExam02April2023;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasterEggs {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String text = scanner.nextLine();
        String regex = "[@#]+(?<color>[a-z]{3,})[@#]+[^A-Za-z0-9]*/+(?<count>\\d+)/+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String color = matcher.group("color");
            String count = matcher.group("count");
            System.out.printf("You found %s %s eggs!%n", count, color);
        }
    }
}
