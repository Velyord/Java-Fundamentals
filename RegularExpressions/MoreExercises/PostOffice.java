/*
Task:
    You read a single line of ASCII symbols, and the message is somewhere inside it. You must find it.
     The input consists of three parts separated with "|" like this:
    "{firstPart}|{secondPart}|{thirdPart}"
    Each word starts with a capital letter and has a fixed length;
    you can find those in each different input part.
    The first part carries the capital letters for each word inside the message.
    You must find those capital letters 1 or more from A to Z.
    The capital letters should be surrounded from both sides with any of the following symbols –
    "#, $, %, *, &". And those symbols should match on both sides.
    This means that $AOTP$ - is a valid pattern for the capital letters.
    $AKTP% - is invalid since the symbols do not match.
    The second part of the data contains the starting letter ASCII code and words length /between
    1 – 20 characters/, in the following format: "{asciiCode}:{length}".
    For example, "67:05" – means that '67' - ASCII code equal to the capital letter "C",
    represents a word starting with "C" with the following 5 characters: like "Carrot".
    The ASCII code should be a capital letter equal to a letter from the first part.
    Word's length should be exactly 2 digits. Length less than 10 will always have a padding zero.
    You don't need to check that.
    The third part of the message are words separated by spaces.
    Those words have to start with the Capital letter [A…Z] equal to the ASCII code
    and have exactly the length for each capital letter you have found in the second part.
    Those words can contain any ASCII symbol without spaces.
    When you find the valid word, you must print it on a new line.
Input / Constraints:
    • In the first line – the text is in the form of three different parts separated by "|".
    Any ASCII character can be inside the input, except '|'.
    • Input will always be valid - you don’t need to check it.
    • The input will always have three different parts that will always be separated by "|".
Output:
    • Print all extracted words, each on a new line.
    • Allowed working time / memory: 100ms / 16MB.
Examples:
    sdsGGasAOTPWEEEdas$AOTP$|a65:1.2s65:03d79:01ds84:02! -80:07++ABs90:1.1|adsaArmyd Gara So La Arm Armyw21 Argo O daOfa Or Ti Sar saTheww The Parahaos
    ->
    Argo
    Or
    The
    Parahaos
        The capital letters are "AOTP"
        Then we look for the additional length of the words for each capital letter.
        For A(65) -> it's 4. For O(79) -> it's 2 For T(84) -> it's 3 For P(80) -> it's 8.
        Then we search in the last part for the words.
        First, start with the letter 'A' and we find "Argo". With the letter 'O' we find  "Or".
        With the letter 'T' we find "The" and with the letter 'P' we find "Parahaos".
    Urgent"Message.TO$#POAML#|readData79:05:79:0!2reme80:03--23:11{79:05}tak{65:11ar}!77:!23--)77:05ACCSS76:05ad|Remedy Por Ostream :Istream Post sOffices Office Of Ankh-Morpork MR.LIPWIG Mister Lipwig
    Post
    Office
    Ankh-Morpork
    Mister
    Lipwig
        The first capital letters are "POAML"
        Then we look for the additional length of the words for each capital letter.
        P(80) -> it's 4.
        O(79) -> it's 6
        A(65) -> it's 12
        M(77) -> it's 6
        L(76) -> it's 6.
        Then we search the last part for the words. First, start with the letter 'P' and we find "Post".
        With the letter 'O' we find "Office". With the letter 'A' we find "Ankh-Morpork".
        With the letter 'M' we find "Mister" and with the letter 'L' we find "Lipwig".
 */
package RegularExpressions.MoreExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class PostOffice {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> words = new ArrayList<>();

    public static void main(String[] args) {
        String[] userInput = scanner.nextLine().split("\\|");
        String firstPart = userInput[0];
        String secondPart = userInput[1];
        String thirdPart = userInput[2];

        String regex1 = "([#$%*&])(?<capitalLetters>[A-Z]+)\\1";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(firstPart);
        String capitalLetters = "";

        if (matcher1.find()) {
            capitalLetters = matcher1.group("capitalLetters");
        }

        for (int i = 0; i < capitalLetters.length(); i++) {
            char currentCapitalLetter = capitalLetters.charAt(i);
            String regex2 = "(?<asciiCode>\\d{2}):(?<length>\\d{2})";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(secondPart);

            while (matcher2.find()) {
                int asciiCode = Integer.parseInt(matcher2.group("asciiCode"));
                int lengthOfWord = Integer.parseInt(matcher2.group("length"));

                if ((int) currentCapitalLetter == asciiCode) {
                    String regex3 = "(?<word>\\b[A-Z][^ ]+\\b)";
                    Pattern pattern3 = Pattern.compile(regex3);
                    Matcher matcher3 = pattern3.matcher(thirdPart);

                    while (matcher3.find()) {
                        String word = matcher3.group("word");

                        if (word.length() == lengthOfWord + 1 && word.charAt(0) == currentCapitalLetter) {
                            if (!words.contains(word)) {
                                words.add(word);
                            }
                            break;
                        }
                    }
                }
            }
        }

        words.forEach(out::println);
    }
}