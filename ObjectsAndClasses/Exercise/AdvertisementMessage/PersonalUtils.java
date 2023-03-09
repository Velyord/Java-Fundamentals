package ObjectsAndClasses.Exercise.AdvertisementMessage;

import java.util.List;

import static java.lang.System.out;

public class PersonalUtils {
    public static <T> void printList(List<T> numberList, String delimiter) {
        out.println(String.join(delimiter, numberList.toString()));
    }
}
