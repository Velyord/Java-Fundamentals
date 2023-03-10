package Template;

import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

public class PersonalUtils {
    public static Scanner scanner = new Scanner(System.in);

    public static <T> void printList(List<T> numberList, String delimiter) {
        out.println(String.join(delimiter, numberList.toString()));
    }

    public static String userInput() {
        return scanner.nextLine();
    }
}