/*
Task:
    After the successful second Christmas,
    Santa needs to gather information about children's behavior to plan the presents for next Christmas.
    He has a secret helper who is sending him encrypted information.
    Your task is to decrypt it and create a list of the good children.
    You will receive an integer, which represents a key, and afterward some messages,
    which you must decode by subtracting the key from the value of each character.
    After the decryption, to be considered a valid match, a message should:
    • Have a name that starts after '@' and contains only letters from the Latin alphabet
    • Have a behavior type - "G" (good) or "N" (naughty) and must be surrounded by "!"
    (exclamation mark).
    The order in the message should be the child’s name -> child’s behavior.
    They can be separated from the others by any character except: '@', '-', '!', ':' and '>'.
    You will be receiving messages until you are given the "end" command.
    Afterward, print the names of the children who will receive a present on a new line.
Input / Constraints:
    • The first line holds n – the number you must subtract from the characters –
    integer in the range [1…100].
    • On the next lines, you will be receiving encrypted messages.
Output:
    Print the names of the children, each on a new line.
Examples:
    3
    CNdwhamigyenumje$J$
    CEreelh-nmguuejn$J$
    CVwdq&gnmjkvng$Q$
    end
    ->
    Kate
    Bobbie
        We receive three messages, and to decrypt them, we use the key:
        The first message has a decryption key 3. So we subtract from each characters code 3,
        and we receive:
        @Kate^jfdvbkrjgb!G!
        @Bobbie*kjdrrbgk!G!
        @Stan#dkjghskd!N!
        They are all valid, and contain a child’s name and behavior – G for good and N for naughty.
    3
    N}eideidmk$'(mnyenmCNlpamnin$J$
    ddddkkkkmvkvmCFrqqru-nvevek$J$nmgievnge
    ppqmkkkmnolmnnCEhq/vkievk$Q$
    yyegiivoguCYdohqwlqh/kguimhk$J$
    end
    ->
    Kim
    Connor
    Valentine
        We receive four messages.
        They are with key 3:
        Kzbfabfajh!$%jkvbkj@Kim^jkfk!G!
        aaaahhhhjshsj@Connor*ksbsbh!G!kjdfbskdb
        mmnjhhhjklijkk@Ben,shfbsh!N!
        vvbdffsldr@Valentine,hdrfjeh!G!
 */
package RegularExpressions.MoreExercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SantasSecretHelper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int key = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();

        while (!input.equals("end")) {
            StringBuilder decryptedMessage = new StringBuilder();

            for (int i = 0; i < input.length(); i++) {
                decryptedMessage.append((char) (input.charAt(i) - key));
            }

            String pattern = "@(?<name>[A-Za-z]+)[^@\\-!:>]*!(?<behavior>[GN])!";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(decryptedMessage);

            if (matcher.find()) {
                String name = matcher.group("name");
                String behavior = matcher.group("behavior");

                if (behavior.equals("G")) {
                    System.out.println(name);
                }
            }

            input = scanner.nextLine();
        }
    }
}
