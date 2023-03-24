/*
Task:
    You are a world traveler, and your next goal is to make a world tour.
    To do that, you have to plan out everything first.
    To start with, you would like to plan out all of your stops where you will have a break.
    On the first line, you will be given a string containing all of your stops.
    Until you receive the command "Travel", you will be given some commands to manipulate that initial string.
    The commands can be:
    • "Add Stop:{index}:{string}":
        ◦ Insert the given string at that index only if the index is valid
    • "Remove Stop:{start_index}:{end_index}":
        ◦ Remove the elements of the string from the starting index to the end index (inclusive)
        if both indices are valid
    • "Switch:{old_string}:{new_string}":
        ◦ If the old string is in the initial string, replace it with the new one (all occurrences)
    Note: After each command, print the current state of the string!
    After the "Travel" command, print the following: "Ready for world tour! Planned stops: {string}"
Input / Constraints:
    • JavaScript: you will receive a list of strings
    • An index is valid if it is between the first and the last element index (inclusive) (0 ….. Nth)
    in the sequence.
Output:
    • Print the proper output messages in the proper cases as described in the problem description
Examples:
    Hawai::Cyprys-Greece
    Add Stop:7:Rome
    Remove Stop:11:16
    Switch:Hawai:Bulgaria
    Travel
    ->
    Hawai::RomeCyprys-Greece
    Hawai::Rome-Greece
    Bulgaria::Rome-Greece
    Ready for world tour! Planned stops: Bulgaria::Rome-Greece

    Albania:Bulgaria:Cyprus:Deuchland
    Add Stop:3:Nigeria
    Remove Stop:4:8
    Switch:Albania: Azərbaycan
    Travel
    ->
    AlbNigeriaania:Bulgaria:Cyprus:Deuchland
    AlbNaania:Bulgaria:Cyprus:Deuchland
    AlbNaania:Bulgaria:Cyprus:Deuchland
    Ready for world tour! Planned stops: AlbNaania:Bulgaria:Cyprus:Deuchland
 */
package FundamentalsExams.FinalExam02;

import java.util.Scanner;

import static java.lang.System.out;

public class WorldTour {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String allStops = scanner.nextLine();
        String commands = scanner.nextLine();

        while (!commands.equals("Travel")) {
            String[] commandParts = commands.split(":");
            String command = commandParts[0];

            switch (command) {
                case "Add Stop":
                    int index = Integer.parseInt(commandParts[1]);
                    String stop = commandParts[2];

                    allStops = addSubstringAt(index, stop, allStops);
                    break;
                case "Remove Stop":
                    int indexStart = Integer.parseInt(commandParts[1]);
                    int indexEnd = Integer.parseInt(commandParts[2]);

                    allStops = removeSubstringAt(indexStart, indexEnd, allStops);
                    break;
                case "Switch":
                    String oldStop = commandParts[1];
                    String newStop = commandParts[2];
                    allStops = allStops.replaceAll(oldStop, newStop);
                    break;
            }

            out.println(allStops);
            commands = scanner.nextLine();
        }

        out.println("Ready for world tour! Planned stops: " + allStops);
    }

    private static String addSubstringAt(int index, String substring, String text) {
        if (index >= 0 && index <= text.length()) {
            text = text.substring(0, index) + substring + text.substring(index);
        }

        return text;
    }

    private static String removeSubstringAt(int start, int end, String text) {
        if (start >= 0 && start <= text.length() - 1 &&
                end >= 0 && end <= text.length() - 1) {
            if (start <= end) {
                text = text.substring(0, start) + text.substring(end + 1);
            }
        }

        return text;
    }
}
