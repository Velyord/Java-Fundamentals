package Projects.PaperScissorsRock;

import java.util.Random;

public class PersonalUtils {
    // метод за избиране на произволно число между дадени граници
    public static int pickARandomNumberBetween(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }
}
