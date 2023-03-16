/*
Task:
    Judge statistics on the last Programming Fundamentals exam were not working correctly,
    so you have the task to take all the submissions and analyze them properly.
    You should collect all the submissions and print the final results and statistics about each
    language in which the participants submitted their solutions.
    You will be receiving lines in the following format: "{username}-{language}-{points}"
    until you receive "exam finished". You should store each username and their submissions and points.
    You can receive a command to ban a user for cheating in the following format: "{username}-banned".
    In that case, you should remove the user from the contest but preserve his submissions in the total
    count of submissions for each language.
    After receiving "exam finished", print each of the participants in the following format:
    "Results:
    {username} | {points}
    {username2} | {points}
    …
    {usernameN} | {points}"
    After that, print each language used in the exam in the following format:
    "Submissions:
    {language1} - {submissions_count}
    {language2} - {submissions_count}
    …
    {language3} - {submissions_count}"
Input / Constraints:
    Until you receive "exam finished", you will be receiving participant submissions
    in the following format: "{username}-{language}-{points}"
    You can receive a ban command -> "{username}-banned".
    The participant's points will always be a valid integer in the range [0-100].
Output:
    • Print the exam results for each participant.
    • After that, print each language in the format shown above.
    • Allowed working time / memory: 100ms / 16MB.
Examples:
    Peter-Java-84
    George-C#-84
    George-C#-70
    Katy-C#-94
    exam finished
    ->
    Results:
    Peter | 84
    George | 84
    Katy | 94
    Submissions:
    Java - 1
    C# - 3

    Peter-Java-91
    George-C#-84
    Katy-Java-90
    Katy-C#-50
    Katy-banned
    exam finished
    ->
    Results:
    Peter | 91
    George | 84
    Submissions:
    Java - 2
    C# - 2
 */
package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class SoftUniExamResults {
    static Scanner scanner = new Scanner(System.in);
    private static final Map<String, Integer> usernamePointsMap = new LinkedHashMap<>();
    private static final Map<String, Integer> languageSubmissionsMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        examResultsFiller();
        examResultsDisplayer();
    }

    private static void examResultsDisplayer() {
        printResults("Results", usernamePointsMap, "|");
        printResults("Submissions", languageSubmissionsMap, "-");
    }

    private static void printResults(String header, Map<String, Integer> map, String delimiter) {
        out.printf("%s:\n", header);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            out.printf("%s %s %d\n", entry.getKey(), delimiter, entry.getValue());
        }
    }

    private static void examResultsFiller() {
        String input = scanner.nextLine();

        while (!input.equals("exam finished")) {
            String[] inputInfo = input.split("-");
            String username = inputInfo[0];

            if (input.contains("banned")) {
                usernamePointsMap.remove(username);
            } else {
                String language = inputInfo[1];
                int points = Integer.parseInt(inputInfo[2]);

                addToUsers(username, points);
                addToSubmissions(language);
            }

            input = scanner.nextLine();
        }
    }

    private static void addToUsers(String username, int points) {
        if (usernamePointsMap.containsKey(username)) {
            int currentPoint = usernamePointsMap.get(username);

            if (points > currentPoint) {
                usernamePointsMap.put(username, points);
            }
        } else {
            usernamePointsMap.put(username, points);
        }
    }

    private static void addToSubmissions(String language) {
        if (languageSubmissionsMap.containsKey(language)) {
            int currentSubmissions = languageSubmissionsMap.get(language);

            languageSubmissionsMap.put(language, currentSubmissions + 1);
        } else {
            languageSubmissionsMap.put(language, 1);
        }
    }
}
