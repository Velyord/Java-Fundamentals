/*
Task:
    Gabsy is Orgolt's Final Revenge charming drummer.
    She has a drum set, but the different drums have different origins – some she bought, some are gifts,
    so they are all of different quality.
    Every day she practices on each of them, so she does damage and reduces the drum's quality.
    Sometimes a drum breaks, so she needs to buy a new one. Help her keep her drum set organized.
    You will receive Gabsy's savings, the money she can spend on new drums.
    Next, you receive a sequence of integers
    which represent the initial quality of each drum in Gabsy's drum set.
    Until you receive the command "Hit it again, Gabsy!",
    you will be receiving an integer: the hit power Gabsy applies on each drum while practicing.
    When the power is applied, you should decrease the value of the drum's quality with the current power.
    When a certain drum reaches 0 quality, it breaks. Then Gabsy should buy a replacement.
    She needs to buy the same model.
    Therefore, its quality will be the same as the initial quality of the broken drum.
    The price is calculated by the formula: {initialQuality} * 3.
    Gabsy will always replace her broken drums until the moment she can no longer afford them.
    If she doesn't have enough money for a replacement, the broken drum is removed from the drum set.
    When you receive the command "Hit it again, Gabsy!", the program ends,
    and you should print the current state of the drum set. On the second line,
    you should print the remaining money in Gabsy's savings account.
Input
    • On the first line, you receive the savings – a floating-point number.
    • On the second line, you receive the drum set: a sequence of integers separated by spaces.
    • Until you receive the command "Hit it again, Gabsy!" you will be receiving integers – the hit power
    Gabsy applies on each drum.
Output
    • On the first line, you should print each drum in the drum set, separated by space.
    • Then you must print the money that is left on the second line in the format "Gabsy has {money
    left}lv.", formatted with two digits after the decimal point.
Constraints
    • The savings – the floating-point number in the range [0.00, 10000.00].
    • The quality of each drum in the drum set – is an integer in the range [1, 1000].
    • The hit power will be in the range [0, 1000].
    • Allowed working time / memory: 100ms / 16MB.
Examples:
    Input:
        1000.00
        58 65 33
        11
        12
        18
        10
        Hit it again, Gabsy!
    Output:
        7 14 23
        Gabsy has 901.00lv.
    Comment:
        DrumSet – 58 65 33.
        Day 1: hit power applied = 11 => 47 54 22;
        Day 2: hit power applied = 12 => 35 42 10;
        Day 3: hit power applied = 18 => 17 24 -8;
        The third drum breaks. But Gabsy has enough
        savings, so she replaces it => 17 24 33;
        Day 4: hit power applied = 10 => 7 14 23;
        We print the current state of the drum set and
        what's left in Gabsy's bank account.
    Input:
        154.00
        55 111 3 5 8 50
        2
        50
        8
        23
        1
        Hit it again, Gabsy!
    Output:
        27 2 4 7
        Gabsy has 10.00lv.
 */
package Lists.MoreExercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DrumSet {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double savings = Double.parseDouble(scanner.nextLine());
        List<Integer> initialDrumQuality = populate();
        List<Integer> drumSet = new ArrayList<>(initialDrumQuality);

        practice(savings, initialDrumQuality, drumSet);
        printDrumHealth(drumSet);
        printSavings(savings);
    }

    private static List<Integer> populate() {
        return Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void printSavings(double savings) {
        System.out.printf("%nGabsy has %.2flv.", savings);
    }

    private static void printDrumHealth(List<Integer> drumSet) {
        for (int drum : drumSet) {
            System.out.print(drum + " ");
        }
    }

    private static void practice(double savings, List<Integer> initialDrumQuality, List<Integer> drumSet) {
        String userInput = scanner.nextLine();

        while (!userInput.equals("Hit it again, Gabsy!")) {
            int hitPower = Integer.parseInt(userInput);

            for (int drum : drumSet) { // TODO: fix ConcurrentModificationException
                int drumIndex = drumSet.indexOf(drum);
                drum -= hitPower;

                if (drum <= 0) {
                    int drumInitialQuality = initialDrumQuality.get(drumIndex);
                    int drumPrice = initialDrumQuality.get(drumIndex) * 3;

                    if (savings >= drumPrice) {
                        savings -= drumPrice;
                        drumSet.set(drumIndex, drumInitialQuality);
                    } else {
                        drumSet.remove(drumIndex);
                        initialDrumQuality.remove(drumIndex);
                    }

                    continue;
                }

                drumSet.set(drumIndex, drum);
            }

            userInput = scanner.nextLine();
        }
    }
}
