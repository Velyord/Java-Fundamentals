/*
Task:
    The war is at its peak, but you, young Padawan, can turn the tides with your programming skills.
    You are tasked to create a program to decrypt the messages of The Order and prevent the death of hundreds
    of lives.
    You will receive several messages, which are encrypted using the legendary star enigma.
    You should decrypt the messages following these rules:
    To properly decrypt a message, you should count all the letters [s, t, a, r] –
    case insensitive and remove the count from the current ASCII value of each symbol of the encrypted
    message.
    After decryption:
    Each message should have a planet name, population, attack type
    ('A', as an attack or 'D', as destruction), and soldier count.
    The planet's name starts after '@' and contains only letters from the Latin alphabet.
    The planet population starts after ':' and is an Integer;
    The attack type may be "A"(attack) or "D"(destruction) and must be surrounded by "!" (Exclamation mark).
    The soldier count starts after "->" and should be an Integer.
    The order in the message should be: planet name -> planet population -> attack type -> soldier count.
    Each part can be separated from the others by any character except: '@', '-', '!', ':' and '>'.
Input / Constraints:
    • The first line holds n – the number of messages – integer in the range [1…100].
    • On the next n lines, you will be receiving encrypted messages.
Output:
    After decrypting all messages, you should print the decrypted information in the following format:
    First print the attacked planets, then the destroyed planets.
    "Attacked planets: {attackedPlanetsCount}"
    "-> {planetName}"
    "Destroyed planets: {destroyedPlanetsCount}"
    "-> {planetName}"
    The planets should be ordered by name alphabetically.
Examples:
    2
    STCDoghudd4=63333$D$0A53333
    EHfsytsnhf?8555&I&2C9555SR
    ->
    Attacked planets: 1
    -> Alderaa
    Destroyed planets: 1
    -> Cantonica
        We receive two messages, and to decrypt them, we calculate the key:
        The first message has decryption key 3. So we subtract from each character's code 3.
        PQ@Alderaa1:30000!A!->20000
        The second message has key 5.
        @Cantonica:3000!D!->4000NM
        Both messages are valid and contain planet, population, attack type, and soldier count.
        After decrypting all messages, we print each planet according to the format given.
    3
    tt(''DGsvywgerx>6444444444%H%1B9444
    GQhrr|A977777(H(TTTT
    EHfsytsnhf?8555&I&2C9555SR
    ->
    Attacked planets: 0
    Destroyed planets: 2
    -> Cantonica
    -> Coruscant
        We receive three messages.
        Message one is decrypted with key 4:
        pp$##@Coruscant:2000000000!D!->5000
        Message two is decrypted with key 7:
        @Jakku:200000!A!MMMM
        This is the invalid message, missing soldier count, so we continue.
        The third message has key 5.
        @Cantonica:3000!D!->4000NM
 */
package RegularExpressions.Exercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.in;
import static java.lang.System.out;

public class StarEnigma {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int messagesCount = Integer.parseInt(scanner.nextLine());

        for(int i = 1; i <= messagesCount; i++) {
            String input = scanner.nextLine();
            String regex = "[s t a r S T A R]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            int decryptionKey = 0;

            while (matcher.find()) {
                decryptionKey++;
            }

            StringBuilder decryptedMessage = new StringBuilder();

            for (int characterIndex = 0; characterIndex < input.length(); characterIndex++) {
                decryptedMessage.append((char) (input.charAt(characterIndex) - decryptionKey));
            }

            out.println(decryptedMessage); // @(?<planet>[A-Z][a-z]+)[^A-Za-z]*:
        }
    }
}
