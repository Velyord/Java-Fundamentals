/*
Task:
    Anonymous has created a cyber-hyper virus that steals data from the CIA.
    As the lead security developer in the CIA,
    you have been tasked to analyze the software of the virus and observe its actions on the data.
    The virus is known for its innovative and unbelievably clever technique
    of merging and dividing data into partitions.
    You will receive a single input line containing STRINGS separated by spaces.
    The strings may contain any ASCII character except whitespace.
    You will then begin receiving commands in one of the following formats:
    • merge {startIndex} {endIndex}
    • divide {index} {partitions}
    Whenever you receive the merge command,
    you must merge all elements from the startIndex to the endIndex.
    In other words, you should concatenate them.
    Example: {abc, def, ghi} -> merge 0 1 -> {abcdef, ghi}
    If any of the given indexes is out of the array,
    you must take ONLY the range that is INSIDE the array and merge it.
    Every time you receive the divide command,
    you must DIVIDE the element at the given index into several small substrings with equal length.
    The count of the substrings should be equal to the given partitions.
    Example: {abcdef, ghi, jkl} -> divide 0 3 -> {ab, cd, ef, ghi, jkl}
    If the string CANNOT be exactly divided into the given partitions,
    make all partitions except the LAST with EQUAL LENGTHS, and make the LAST one – the LONGEST.
    Example: {abcd, efgh, ijkl} -> divide 0 3 -> {a, b, cd, efgh, ijkl}
    The input ends when you receive the command "3:1".
    At that point, you must print the resulting elements, joined by a space.
Input:
    • The first input line will contain the array of data.
    • On the next several input lines, you will receive commands in the format specified above.
    • The input ends when you receive the command "3:1".
Output:
    • As output, you must print a single line containing the array elements, joined by a space.
Constrains:
    • The strings in the array may contain any ASCII character except whitespace.
    • The startIndex and the endIndex will be in the range [-1000, 1000].
    • The endIndex will ALWAYS be GREATER than the startIndex.
    • The index in the divide command will ALWAYS be INSIDE the array.
    • The partitions will be in the range [0, 100].
    • Allowed working time/memory: 100ms / 16MB.
Examples:
    Ivo Johny Tony Bony Mony
    merge 0 3
    merge 3 4
    merge 0 3
    3:1
    ->
    IvoJohnyTonyBonyMony

    abcd efgh ijkl mnop qrst uvwx yz
    merge 4 10
    divide 4 5
    3:1
    ->
    abcd efgh ijkl mnop qr st uv wx yz
*/
package Lists.Exercise;

import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class AnonymousThreat {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String userInput = scanner.nextLine();
        List<String> inputParts = new ArrayList<>(List.of(userInput.split("\\s+")));

        executeCommands(inputParts);
        printList(inputParts);
    }

    private static void printList(List<String> inputParts) {
        inputParts.forEach(element -> out.printf("%s%s", element, " "));
    }

    private static void executeCommands(List<String> inputParts) {
        String commands = scanner.nextLine();

        while (!commands.equals("3:1")) {
            String[] commandParts = commands.split(" ");
            String command = commandParts[0];

            switch (command) {
                case "merge":  merge(commandParts, inputParts);  break;
                case "divide": divide(commandParts, inputParts); break;
            }

            commands = scanner.nextLine();
        }
    }

    private static void divide(String[] commandParts, List<String> inputParts) {
        int index = Integer.parseInt(commandParts[1]);
        int partitions = Integer.parseInt(commandParts[2]);

        String toBeDivided = inputParts.get(index);
        int substringLength = toBeDivided.length() / partitions;

        inputParts.remove(index);

        for (int i = 1; i <= partitions; i++) {
            inputParts.add(index + i - 1, toBeDivided.substring(0, substringLength)); // i partition
            toBeDivided = toBeDivided.substring(substringLength); // after i partition
        }

        inputParts.set(index + partitions - 1, inputParts.get(index + partitions - 1) + toBeDivided); // add the leftover to the last partition
    }

    private static void merge(String[] commandParts, List<String> inputParts) {
        int startIndex = Integer.parseInt(commandParts[1]);
        int endIndex = Integer.parseInt(commandParts[2]);

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (endIndex > inputParts.size() - 1) {
            endIndex = inputParts.size() - 1;
        }

        int countOfMerges = endIndex - startIndex;

        for (int i = 1; i <= countOfMerges; i++) {
            inputParts.set(
                    startIndex,
                    inputParts.get(startIndex) + inputParts.get(startIndex + 1)
            );

            inputParts.remove(startIndex + 1);
        }
    }
}