/*
Task:
    You know the Judge system, right?! Your job is to create a program similar to the Judge system.
    You will receive several input lines in the following format:
    "{username} -> {contest} -> {points}"
    The constestName and username are strings. The given points will be an integer number.
    You need to keep track of every contest and individual statistics of every user.
    You should check if such a contest already exists, and if not, add it, otherwise,
    check if the current user is participating in the contest.
    If he is participating, take the higher score, otherwise, just add it.
    Also, you need to keep individual statistics for each user - the total points of all contests.
    You should end your program when you receive the command "no more time".
    At that point, you should print each contest in order of input.
    For each contest, print the participants ordered by points in descending order and then by name in ascending order.
    After that, you should print individual statistics for every participant
    ordered by total points in descending order and then by alphabetical order.
Input / Constraints
    • The input comes in the form of commands in the format specified above.
    • Username and contest name always will be one word.
    • Points will be an integer in the range [0, 1000].
    • There will be no invalid input lines.
    • If all sorting criteria fail, the order should be by order of input.
    • The input ends when you receive the command "no more time".
Output
    • The output format for the contests is:
    "{constestName}: {participants.Count} participants
    {position}. {username} <::> {points}"
    • After you print all contests, print the individual statistics for every participant.
    • The output format is:
    "Individual standings:
    {position}. {username} -> {totalPoints}"
Examples:
    Peter -> Algo -> 400
    George -> Algo -> 300
    Sam -> Algo -> 200
    Peter -> DS -> 150
    Maria -> DS -> 600
    no more time
    ->
    Algo: 3 participants
    1. Peter <::> 400
    2. George <::> 300
    3. Sam <::> 200
    DS: 2 participants
    1. Maria <::> 600
    2. Peter <::> 150
    Individual standings:
    1. Maria -> 600
    2. Peter -> 550
    3. George -> 300
    4. Sam -> 200

    Peter -> OOP -> 350
    George -> OOP -> 250
    Sam -> Advanced -> 600
    George -> OOP -> 300
    Pitter -> OOP -> 300
    Pitter -> Advanced -> 250
    Anna -> JSCore -> 400
    no more time
    ->
    OOP: 3 participants
    1. Peter <::> 350
    2. George <::> 300
    3. Pitter <::> 300
    Advanced: 2 participants
    1. Sam <::> 600
    2. Pitter <::> 250
    JSCore: 1 participants
    1. Anna <::> 400
    Individual standings:
    1. Sam -> 600
    2. Pitter -> 550
    3. Anna -> 400
    4. Peter -> 350
    5. George -> 300
 */
package AssociativeArraysLambdaStreamAPI.MoreExercises;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Judge {
    static Scanner scanner = new Scanner(System.in);
    private static final Map<String, Map<String, Integer>> storedValues = new LinkedHashMap<>();
    private static final AtomicInteger position = new AtomicInteger();

    public static void main(String[] args) {
        populateMap();
        printContestsResults();
        printIndividualStandings();
    }

    private static void printIndividualStandings() {
        System.out.println("Individual standings:");

        Map<String, Integer> finalResultsMap = new LinkedHashMap<>();

        for (Map.Entry<String, Map<String, Integer>> contest : storedValues.entrySet()) {
            for (Map.Entry<String, Integer> participantEntry : contest.getValue().entrySet()) {
                String username = participantEntry.getKey();
                int points = participantEntry.getValue();

                finalResultsMap.putIfAbsent(username, 0);

                int currentPoints = finalResultsMap.get(username);

                finalResultsMap.put(username, currentPoints + points);
            }
        }

        printDescMap(finalResultsMap, "->");
    }

    private static void printContestsResults() {
        storedValues.forEach((contest, participantEntryMap) -> {
            System.out.printf("%s: %d participants\n", contest, participantEntryMap.size());

            printDescMap(participantEntryMap, "<::>");
        });
    }

    private static void printDescMap(Map<String, Integer> map, String delimiter) {
        position.set(0);

        map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey())
                ).forEach(e ->
                        System.out.printf(
                                "%d. %s %s %d\n",
                                position.incrementAndGet(),
                                e.getKey(),
                                delimiter,
                                e.getValue()
                        )
                );
    }

    private static void populateMap() {
        String input = scanner.nextLine();

        while (!input.equals("no more time")) {
            String[] array = input.split(" -> ");
            String username = array[0];
            String contest = array[1];
            int points = Integer.parseInt(array[2]);

            if (!storedValues.containsKey(contest)) {
                storedValues.put(contest, new LinkedHashMap<>());
            }

            if (storedValues.get(contest).containsKey(username)) {
                if (points > storedValues.get(contest).get(username)) {
                    storedValues.get(contest).put(username, points);
                }
            } else {
                storedValues.get(contest).put(username, points);
            }

            input = scanner.nextLine();
        }
    }
}