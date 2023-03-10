package ObjectsAndClasses.Exercise.Articles;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class PersonalUtils {
    static Scanner scanner = new Scanner(in);
    public static <T> void printList(List<T> numberList, String delimiter) {
        out.println(String.join(delimiter, numberList.toString()));
    }
    public static String scan() {
        return scanner.nextLine();
    }
}
