/*
Task:
    The lottery is exciting.
    What is not is checking a million tickets for winnings only
    by hand. So, you are given the task of creating a program
    that automatically checks if a ticket is a winner.
    You are given a collection of tickets separated by commas and
    spaces. You need to check every one of them if it has a
    winning combination of symbols.
    A valid ticket should have exactly 20 characters. The winning
    symbols are '@', '#', '$' and '^'. But for a ticket to be a
    winner, the symbol should uninterruptedly repeat at least 6
    times in both the ticket left half and the ticket right half.
    For example, a valid winning ticket should be something like
    this:
    "Cash$$$$$$Ca$$$$$$sh"
    The left half "Cash$$$$$$" contains "$$$$$$", which is also
    contained in the ticket right half "Ca$$$$$$sh". A winning
    ticket should contain symbols repeating up to 10 times in both
    halves, which is considered a Jackpot
    (for example: "$$$$$$$$$$$$$$$$$$$$").
Input
    The input will be read from the console. The input consists of
    a single line containing all tickets separated by commas and
    one or more white spaces in the format:
    •	"{ticket}, {ticket}, … {ticket}"
Output
    Print the result for every ticket in the order of their
    appearance, each on a separate line in the format:
    •	"invalid ticket" - Invalid ticket
    •	"ticket "{ticket}" - no match" - no match
    •	"ticket "{ticket}" - {match length}{match symbol}" - Match
    with length 6 to 9
    •	"ticket "{ticket}" - {match length}{match symbol}
    Jackpot!" - Match with length 10
Constraints
    •	The number of tickets will be in the range [0 … 100].
Examples
    Cash$$$$$$Ca$$$$$$sh
    ->
    ticket "Cash$$$$$$Ca$$$$$$sh" - 6$

    $$$$$$$$$$$$$$$$$$$$, aabb  , th@@@@@@eemo@@@@@@ey
    ->
    ticket "$$$$$$$$$$$$$$$$$$$$" - 10$ Jackpot!
    invalid ticket
    ticket "th@@@@@@eemo@@@@@@ey" - 6@
    validticketnomatch:(
    ticket "validticketnomatch:(" - no match
 */
package RegularExpressions.MoreExercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningTicket { // solved by Borovaneca (https://github.com/Borovaneca/Fundamentals-Java-Jan-2023/blob/main/RegularExpression/Exercise/MoreExercise/WinningTicket.java)
    private static final String REGEX = "(?=.{20}).*?(?=(?<ch>[@#$^]))(?<match>\\k<ch>{6,}).*(?<=.{10})\\k<match>.*";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    private static final Pattern SEPARATOR = Pattern.compile("\\s*,\\s*");

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] tickets = SEPARATOR.split(scan.nextLine().trim());

        for (String ticket : tickets) {
            if (ticket.length() != 20) {
                System.out.println("invalid ticket");
            } else {
                Matcher matcher = PATTERN.matcher(ticket);
                if (matcher.matches()) {
                    String match = matcher.group("match");
                    System.out.printf("ticket \"%s\" - %d%s%s%n",
                            ticket, match.length(), match.charAt(0),
                            (match.length() == 10) ? " Jackpot!" : "");
                } else {
                    System.out.printf("ticket \"%s\" - no match%n", ticket);
                }
            }
        }
    }
}