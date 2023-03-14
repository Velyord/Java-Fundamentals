/*
Task:
    The force users are struggling to remember which side is the different forceUsers from because they
    switch them too often. So you are tasked to create a web application to manage their profiles.
    You will receive several input lines in one of the following formats:
    "{force_side} | {force_user}"
    "{force_user} -> {force_side}"
    The "force_user" and "force_side" are strings containing any character.
    If you receive "force_side | force_user":
    • If there is no such force user and no such force side ->
    create a new force side and add the force user to the corresponding side.
    • Only if there is no such force user on any force side ->
    add the force user to the corresponding side.
    • If there is such force user already -> skip the command and continue to the next operation.
    If you receive a "force_user -> force_side":
    • If there is such force user already -> change their side.
    • If there is no such force user on any force side ->
    add the force user to the corresponding force side.
    • If there is no such force user and no such force side ->
    create a new force side and add the force user to the corresponding side.
    • Then you should print on the console: "{force_user} joins the {force_side} side!".
    You should end your program when you receive the command "Lumpawaroo".
    At that point, you should print each force side. For each side, print the force users.
    In case there are no force users on a side, you shouldn't print the side information.
Input / Constraints:
    • The input comes in the form of commands in one of the formats specified above.
    • The input ends when you receive the command "Lumpawaroo".
Output:
    • As output for each force side, you must print all the force users.
    • The output format is:
    "Side: {forceSide}, Members: {forceUsers.Count}
    ! {forceUser}
    ! {forceUser}
    ! {forceUser}"
    • In case there are NO forceUsers, don't print this side.
Examples:
    Light | Peter
    Dark | Kim
    Lumpawaroo
    ->
    Side: Light, Members: 1
    ! Peter
    Side: Dark, Members: 1
    ! Kim
        We register Peter on the Light side and Kim on the Dark side.
        After receiving "Lumpawaroo", we print both sides.

    Lighter | Royal
    Darker | DCay
    Ivan Ivanov -> Lighter
    DCay -> Lighter
    Lumpawaroo
    ->
    Ivan Ivanov joins the Lighter side!
    DCay joins the Lighter side!
    Side: Lighter, Members: 3
    ! Royal
    ! Ivan Ivanov
    ! DCay
        Although Ivan Ivanov doesn't have a profile,
        we registered him and added him to the Lighter side.
        We remove DCay from the Darker side and add him to the Lighter side.
        We print only the Lighter side because the Darker side has no members.
 */
package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.*;

public class ForceBook {
    static Scanner scanner = new Scanner(System.in);
    private static final Map<String, List<String>> sideUserMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        String input = scanner.nextLine();

        while (!input.equals("Lumpawaroo")) {
            boolean userExists;
            String[] data = input.split(" ");

            if (data[1].equals("|")) {
                String forceSide = data[0];
                String forceUser = data[2];

                userExists = doesUserExist(forceUser);

                if (!userExists) {
                    addUser(forceSide, forceUser);
                }
            } else {
                String forceUser = data[0];
                String forceSide = data[2];

                userExists = doesUserExist(forceUser);

                if (!userExists) {
                    addUser(forceSide, forceUser);
                } else {
                    removeUser(forceUser);
                    addUser(forceSide, forceUser);
                }
            }

            input = scanner.nextLine();
        }
    }

    private static void removeUser(String forceUser) {
        for (Map.Entry<String, List<String>> entry : sideUserMap.entrySet()) {
            for (String user : entry.getValue()) {
                if (user.equals(forceUser)) {
                    entry.getValue().remove(forceUser);
                    break;
                }
            }
        }
    }

    private static boolean doesUserExist(String forceUser) {
        for (Map.Entry<String, List<String>> entry : sideUserMap.entrySet()) {
            for (String user : entry.getValue()) {
                if (user.equals(forceUser)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void addUser(String forceSide, String forceUser) {
        if (sideUserMap.containsKey(forceSide)) {
            sideUserMap.get(forceSide).add(forceUser);
        } else {
            List<String> usersList = new ArrayList<>();
            usersList.add(forceUser);
            sideUserMap.put(forceSide, usersList);
        }
    }
}
