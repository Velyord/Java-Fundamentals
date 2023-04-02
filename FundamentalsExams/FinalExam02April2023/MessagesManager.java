/*
Task:
    Create a program that manages messages sent and received by users.
    You need to keep information about username, their sent and received messages.
    On the first line, you will receive the capacity of possible messages
    (total of send and received) kept at once per user.
    Next, you will be receiving lines with commands until you receive the "Statistics" command.
    There  are three possible commands:
    • "Add={username}={sent}={received}":
    o Add the username and the number of already sent and received messages to your records.
    o If the person with the given username already exists, ignore the line.
    • "Message={sender}={receiver}":
    o If  both  usernames exist, increase the sender's sent  messages by 1 and  the receiver's  received messages by 1.
    o If anyone reaches the capacity (first check the sender),
    he/she should be removed from the record, and you should print the following message:
    ▪ "{username} reached the capacity!"
    • "Empty={username}":
    o Delete all records of the given user if he exists.
    o If "All" is given as username - delete all records you have.
    Finally, print  the  total  number  of  users.
    On  the following  lines,  print  each  person with the sum of  their sent and received messages.
Input:
    • On the first line, you will receive the capacity - an integer number in the range [1-10000].
    • You will be receiving lines until you receive the "Statistics" command.
    • The initial messages (sent and received) will always be below the capacity.
    • The input will always be valid.
Output:
    • Print the appropriate message after the "Message" command if someone reaches the capacity.
    • Print the users with their messages in the following format:
    "Users count: {count} {username1} - {number of messages} {username2} - {number of messages} ...
    {usernameN} - {number of messages}"
Examples:
    10
    Add=Berg=9=0
    Add=Kevin=0=0
    Message=Berg=Kevin
    Add=Mark=5=4
    Statistics
    ->
    Berg reached the capacity!
    Users count: 2
    Kevin - 1
    Mark - 9

    20
    Add=Mark=3=9
    Add=Berry=5=5
    Add=Clark=4=0
    Empty=Berry
    Add=Blake=9=3
    Add=Michael=3=9
    Add=Amy=9=9
    Message=Blake=Amy
    Message=Michael=Amy
    Statistics
    ->
    Amy reached the capacity!
    Users count: 4
    Mark - 12
    Clark - 4
    Blake - 13
    Michael - 13

    12
    Add=Bonnie=3=5
    Add=Johny=4=4
    Empty=All
    Add=Bonnie=3=3
    Statistics
    ->
    Users count: 1
    Bonnie - 6
 */
package FundamentalsExams.FinalExam02April2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessagesManager {
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<User> users = new ArrayList<>();

    private static class User {
        private final String username;
        private int sent;
        private int received;

        public User(String username, int sent, int received) {
            this.username = username;
            this.sent = sent;
            this.received = received;
        }

        public String getUsername() {
            return username;
        }

        public int getSent() {
            return sent;
        }

        public int getReceived() {
            return received;
        }

        public void setSent(int sent) {
            this.sent = sent;
        }

        public void setReceived(int received) {
            this.received = received;
        }
    }

    public static void main(String[] args) {
        executeCommands();
        printResult();
    }

    private static void printResult() {
        System.out.printf("Users count: %d%n", users.size());

        for (User user : users) {
            System.out.printf("%s - %d%n", user.getUsername(), user.getSent() + user.getReceived());
        }
    }

    private static void executeCommands() {
        int capacity = Integer.parseInt(scanner.nextLine());
        String commands = scanner.nextLine();

        while (!commands.equals("Statistics")) {
            String[] commandParts = commands.split("=");
            String command = commandParts[0];

            switch (command) {
                case "Add":
                    String username = commandParts[1];
                    int sent = Integer.parseInt(commandParts[2]);
                    int received = Integer.parseInt(commandParts[3]);
                    addCommand(username, sent, received);
                    break;
                case "Message":
                    String sender = commandParts[1];
                    String receiver = commandParts[2];
                    messageCommand(sender, receiver, capacity);
                    break;
                case "Empty":
                    String user = commandParts[1];
                    emptyCommand(user);
                    break;
            }

            commands = scanner.nextLine();
        }
    }

    private static void emptyCommand(String user) {
        if (user.equals("All")) {
            users.clear();
        } else {
            if (doesUserExist(user)) {
                User currentUser = getUser(user);
                users.remove(currentUser);
            }
        }
    }

    private static void messageCommand(String sender, String receiver, int capacity) {
        if (doesUserExist(sender) && doesUserExist(receiver)) {
            User senderUser = getUser(sender);
            User receiverUser = getUser(receiver);

            senderUser.setSent(senderUser.getSent() + 1);
            receiverUser.setReceived(receiverUser.getReceived() + 1);

            if (senderUser.getSent() + senderUser.getReceived() >= capacity) {
                System.out.printf("%s reached the capacity!%n", senderUser.getUsername());

                users.remove(senderUser);
            }

            if (receiverUser.getSent() + receiverUser.getReceived() >= capacity) {
                System.out.printf("%s reached the capacity!%n", receiverUser.getUsername());

                users.remove(receiverUser);
            }
        }
    }

    private static void addCommand(String username, int sent, int received) {
        if (!doesUserExist(username)) {
            User user = new User(username, sent, received);
            users.add(user);
        }
    }

    private static User getUser(String user) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user)) {
                return currentUser;
            }
        }

        return null;
    }

    private static boolean doesUserExist(String user) {
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user)) {
                return true;
            }
        }

        return false;
    }
}