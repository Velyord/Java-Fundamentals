package Projects.GuessTheNumber;

import java.util.Random;

public class PersonalUtils {
    // метод за въвеждане на произволно число в дадени граници
    public static int pickARandomNumberBetween(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }
}
