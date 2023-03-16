package TextProcessing.Lab;

import java.util.Scanner;

import static java.lang.System.out;

public class Substring {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String toBeRemoved = scanner.nextLine();
        String string = scanner.nextLine();
        String filteredString = removeFrom(string, toBeRemoved);

        out.println(filteredString);
    }

    private static String removeFrom(String string, String toBeRemoved) {
        while(true) {
            int index = string.indexOf(toBeRemoved);

            if (index != -1) {
                string = string.replace(string.substring(index, index + toBeRemoved.length()), "");
            } else {
                break;
            }
        }

        return string;
    }
}
