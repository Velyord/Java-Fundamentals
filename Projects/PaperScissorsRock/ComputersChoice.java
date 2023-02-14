package Projects.PaperScissorsRock;

import static Projects.PaperScissorsRock.PersonalUtils.pickARandomNumberBetween;

public class ComputersChoice {
    // произволен избор на компютъра
    public static String randomComputerChoice() {
        return switch (pickARandomNumberBetween(1, 3)) {
            case 1 -> "Paper";
            case 2 -> "Scissors";
            case 3 -> "Rock";
            default -> "";
        };
    }
}
