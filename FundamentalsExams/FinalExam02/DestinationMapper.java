/*
Task:
    Now that you have planned out your tour,
    you are ready to go! Your next task is to mark all the points on the map that you are going to visit.
    You will be given a string representing some places on the map. You have to filter only the valid ones.
    A valid location is:
    • Surrounded by "=" or "/" on both sides (the first and the last symbols must match)
    • After the first "=" or "/" there should be only letters (the first must be upper-case,
    other letters could be upper or lower-case)
    • The letters must be at least 3
    Example: In the string "=Hawai=/Cyprus/=Invalid/invalid==i5valid=/I5valid/=i="
    only the first two locations are valid.
    After you have matched all the valid locations, you have to calculate travel points.
    They are calculated by summing the lengths of all the valid destinations that you have found on the map.
    In the end, on the first line, print: "Destinations: {destinations joined by ', '}".
    On the second line, print "Travel Points: {travel_points}".
Input / Constraints:
    • You will receive a string representing the locations on the map
    • JavaScript: you will receive a single parameter: string
Output:
    • Print the messages described above
Examples:
    =Hawai=/Cyprus/=Invalid/invalid==i5valid=/I5valid/=i=
    ->
    Destinations: Hawai, Cyprus
    Travel Points: 11

    ThisIs some InvalidInput
    ->
    Destinations:
    Travel Points: 0
 */
package FundamentalsExams.FinalExam02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class DestinationMapper {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String locations = scanner.nextLine();
        String regex = "(=|/)(?<destination>[A-Z][A-Za-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(locations);
        List<String> destinationList = new ArrayList<>();
        int travelPoints = 0;

        while (matcher.find()) {
            String destination = matcher.group("destination");
            destinationList.add(destination);
            travelPoints += destination.length();
        }

        printOutput(destinationList, travelPoints);
    }

    private static void printOutput(List<String> destinationList, int travelPoints) {
        out.print("Destinations: ");
        printDestinations(destinationList);
        out.print("\nTravel Points: " + travelPoints);
    }

    private static void printDestinations(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                out.printf("%s, ", list.get(i));
            } else {
                out.printf("%s", list.get(i));
            }
        }
    }
}
