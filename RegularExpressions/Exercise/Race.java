/*
Task:
    Write a program that processes information about a race.
    On the first line, you will be given a list of participants separated by ", ".
    On the next few lines, until you receive the line "end of race"
    you will be given some info which will be some alphanumeric characters.
    In between them, you could have some extra characters which you should ignore.
    For example: "G!32e%o7r#32g$235@!2e". The letters are the name of the person,
    and the sum of the digits is the distance he ran. So here we have George, who ran 29 km.
    Store the information about the person only if the list of racers contains the name of the person.
    If you receive the same person more than once add the distance to his old distance.
    Finally, print the racers in the order they are given, in the format:
    "1st place: {first racer}
    2nd place: {second racer}
    3rd place: {third racer}"
Examples:
    George, Peter, Bill, Tom
    G4e@55or%6g6!68e!!@
    R1@!3a$y4456@
    B5@i@#123ll
    G@e54o$r6ge#
    7P%et^#e5346r
    T$o553m&6
    end of race
    ->
    1st place: George
    2nd place: Peter
    3rd place: Bill
        On the 3rd input line, we have Ray. He is not on the list, so we do not count his result.
        The other ones are valid. We need to arrange them in the way they are given by the input.
    Joro, George, Georgi, Stamat
    ^&^%^232St#$ama&&^^t
    ^&^%^232St#$ama&&^^t
    G7667eorge&^^&^
    &^&&&J99999oro&^^57
    end of race
    ->
    1st place: Joro
    2nd place: George
    3rd place: Georgi
 */
package RegularExpressions.Exercise;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Race {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        List<String> listOfParticipants = List.of(scanner.nextLine().split(", "));
        String raceData = scanner.nextLine();

        while(!raceData.equals("end of race")) {
            raceData = scanner.nextLine();
        }

        out.printf("1st place: %s\n", listOfParticipants.get(0));
        out.printf("2nd place: %s\n", listOfParticipants.get(1));
        out.printf("3rd place: %s\n", listOfParticipants.get(2));
    }
}
