package TextProcessing.MoreExercises;

import java.util.Arrays;
import java.util.Scanner;

public class TreasureFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] key = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        String input = scanner.nextLine();

        while (!input.equals("find")) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < input.length(); i++) {
                sb.append((char) (input.charAt(i) - key[i % key.length]));
            }

            String[] tokens = sb.toString().split("&");

            System.out.printf(
                "Found %s at %s%n",
                tokens[1],
                tokens[2].substring(
                    tokens[2].indexOf("<") + 1,
                    tokens[2].indexOf(">")
                )
            );

            input = scanner.nextLine();
        }
    }
}
