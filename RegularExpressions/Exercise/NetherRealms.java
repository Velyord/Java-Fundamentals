/*
Task:
    A mighty battle is coming. In the stormy nether realms,
    demons fight against each other for supremacy in a duel from which only one will survive.
    Your job, however, is not so exciting.
    You must sign in all the participants in the nether realm's mighty battle's demon book.
    A demon's name contains his health and his damage.
    The sum of the asci codes of all characters
    (excluding numbers (0-9), arithmetic symbols ('+', '-', '*', '/') and delimiter dot ('.'))
    gives a demon's total health.
    The sum of all numbers in his name forms his base damage.
    Note that you should consider the plus '+' and minus '-' signs (e.g., +10 is 10 and -10 is -10).
    However, there are some symbols ('*' and '/')
    that can further alter the base damage by multiplying or dividing it by 2
    (e.g. in the name "m15* /c-5.0", the base damage is 15 + (-5.0) = 10
    and then you need to multiply it by 2 (e.g. 10 * 2 = 20) and then divide it by 2 (e.g. 20 / 2 = 10)).
    So, multiplication and division are applied only after all numbers are included in the calculation
    and the order they appear in the name.
Input:
    The input will be read from the console.
    The input consists of a single line containing all demon names separated by commas and zero
    or more spaces in the format: "{demon name}, {demon name}, … {demon name}"
Output:
    Print all demons, each on a separate line in the format:
    • "{demon name} - {health points} health, {damage points} damage"
Constraints:
    • A demon's name will contain at least one character.
    • A demon's name cannot contain blank spaces ' ' or commas ','.
    • A floating-point number will always have digits before and after its decimal separator.
    • The number in a demon's name is considered everything that is a valid integer or floating point number
    (with a dot '.' used as separator).
    For example, all these are valid numbers: '4', '+4', '-4', '3.5', '+3.5', '-3.5'.
Examples:
    M3ph-0.5s-0.5t0.0**
    ->
    M3ph-0.5s-0.5t0.0** - 524 health, 8.00 damage
        M3ph-0.5s-0.5t0.0**:
        Health = 'M' + 'p' + 'h' + 's' + 't' = 524 health.
        Damage = (3 + (-0.5) + (-0.5) + 0.0) * 2 * 2 = 8 damage.
    M3ph1st0**, Azazel
    ->
    M3ph1st0** - 524 health, 16.00 damage
    Azazel - 615 health, 0.00 damage
        M3ph1st0**:
        Health - 'M' + 'p' + 'h' + 's' + 't' = 524 health.
        Damage - (3 + 1 + 0) * 2 * 2 = 16 damage.
        Azazel:
        Health - 'A' + 'z' + 'a' + 'z' + 'e' + 'l' = 615 health. Damage - no digits = 0 damage.
    Gos/ho
    ->
    Gos/ho - 512 health, 0.00 damage
*/
package RegularExpressions.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.in;
import static java.lang.System.out;

public class NetherRealms {
    static Scanner scanner = new Scanner(in);
    private static Pattern pattern;
    private static Matcher matcher;

    private static class Participant {
        private String name;
        private int health;
        private double damage;

        public Participant(String name, int health, double damage) {
            this.name = name;
            this.health = health;
            this.damage = damage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public double getDamage() {
            return damage;
        }

        public void setDamage(double damage) {
            this.damage = damage;
        }
    }

    public static void main(String[] args) {
        String[] participants = scanner.nextLine().split(",\\s*");
        List<Participant> participantList = new ArrayList<>();

        for (int i = 0; i < participants.length; i++) {
            String participantName = participants[i].trim();
            int participantHealth = calculateHealth(participantName);
            double participantDamage = calculateDamage(participantName);
            Participant participant = new Participant(participantName, participantHealth, participantDamage);
            participantList.add(participant);
        }

        printList(participantList);
    }

    private static double calculateDamage(String participantName) {
        double participantDamage = 0;

        String damageRegex = "[-]?\\d+[.]?\\d*";
        pattern = Pattern.compile(damageRegex);
        matcher = pattern.matcher(participantName);

        while (matcher.find()) {
            participantDamage += Double.parseDouble(matcher.group());
        }

        String baseMultiplierRegex = "[/*]";
        pattern = Pattern.compile(baseMultiplierRegex);
        matcher = pattern.matcher(participantName);

        while (matcher.find()) {
            if (matcher.group().equals("*")) {
                participantDamage *= 2;
            } else if (matcher.group().equals("/")) {
                participantDamage /= 2;
            }
        }

        return participantDamage;
    }

    private static int calculateHealth(String participantName) {
        int participantHealth = 0;
        String healthRegex = "[^0-9*/.+-]";
        pattern = Pattern.compile(healthRegex);
        matcher = pattern.matcher(participantName);

        while (matcher.find()) {
            char letter = matcher.group().charAt(0);
            participantHealth += letter;
        }

        return participantHealth;
    }

    private static void printList(List<Participant> participantList) {
        participantList.forEach(participant ->
                out.printf(
                        "%s - %d health, %.2f damage\n",
                        participant.getName(), participant.getHealth(), participant.getDamage()
                )
        );
    }
}
