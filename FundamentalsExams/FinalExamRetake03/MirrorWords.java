/*
Task:
    The SoftUni Spelling Bee competition is here.
    But it`s not like any other Spelling Bee competition out there.
    It`s different and a lot more fun! You, of course, are a participant,
    and you are eager to show the competition that you are the best, so go ahead,
    learn the rules and win!
    On the first line of the input, you will be given a text string. To win the competition,
    you have to find all hidden word pairs, read them,
    and mark the ones that are mirror images of each other.
    First of all, you have to extract the hidden word pairs. Hidden word pairs are:
    • Surrounded by "@" or "#" (only one of the two)
    in the following pattern #wordOne##wordTwo# or @wordOne@@wordTwo@
    • At least 3 characters long each (without the surrounding symbols)
    • Made up of letters only
    If the second word, spelled backward, is the same as the first word and vice versa (casing matters!),
    they are a match, and you have to store them somewhere. Examples of mirror words:
    #Part##traP# @leveL@@Level@ #sAw##wAs#
    • If you don`t find any valid pairs, print: "No word pairs found!"
    • If you find valid pairs print their count: "{valid pairs count} word pairs found!"
    • If there are no mirror words, print: "No mirror words!"
    • If there are mirror words print:
    "The mirror words are:
    {wordOne} <=> {wordtwo}, {wordOne} <=> {wordtwo}, … {wordOne} <=> {wordtwo}"
Input / Constraints:
    • You will recive a string.
Output:
    • Print the proper output messages in the proper cases as described in the problem description.
    • If there are pairs of mirror words, print them in the end, each pair separated by ", ".
    • Each pair of mirror word must be printed with " <=> " between the words.
Examples:
    @mix#tix3dj#poOl##loOp#wl@@bong&song%4very$long@thong#Part##traP##@@leveL@@Level@##car#rac##tu@pack@@ckap@#rr#sAw##wAs#r#@w1r
    ->
    5 word pairs found!
    The mirror words are:
    Part <=> traP, leveL <=> Level, sAw <=> wAs
        There are 5 green and yellow pairs that meet all requirements and thus are valid.
        #poOl##loOp# is valid and looks very much like a mirror words pair,
        but it isn`t because the casings don`t match.
        #car#rac# "rac" spelled backward is "car",
        but this is not a valid pair because there is only one "#" between the words.
        @pack@@ckap@ is also valid, but "ckap" backward is "pakc" which is not the same as "pack",
        so they are not mirror words.
    #po0l##l0op# @bAc##cAB@ @LM@ML@ #xxxXxx##xxxXxx# @aba@@ababa@
    ->
    2 word pairs found!
    No mirror words!
        "xxxXxx" backward is not the same as "xxxXxx"
        @aba@@ababa@ is a valid pair, but the word lengths are different -
        these are definitely not mirror words
    #lol#lol# @#God@@doG@# #abC@@Cba# @Xyu@#uyX#
    ->
    No word pairs found!
    No mirror words!
 */
package FundamentalsExams.FinalExamRetake03;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class MirrorWords {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String text = scanner.nextLine();
        String regex = "([@#])(?<firstWord>[A-Za-z]{3,})\\1\\1(?<secondWord>[A-Za-z]{3,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int wordPairs = 0;
        int mirrorWords = 0;
        Map<String, String> mirrorWordsMap = new LinkedHashMap<>();

        while(matcher.find()) {
            wordPairs++;
            String firstWord = matcher.group("firstWord");
            String secondWord = matcher.group("secondWord");

            if (firstWord.equals(reverseString(secondWord))) {
                mirrorWords++;
                mirrorWordsMap.put(firstWord, secondWord);
            }
        }

        if (wordPairs == 0) {
            out.println("No word pairs found!");
        } else {
            out.printf("%d word pairs found!\n", wordPairs);
        }

        if (mirrorWords == 0) {
            out.println("No mirror words!");
        } else {
            out.println("The mirror words are:");

            int index = 0;
            int lastIndex = mirrorWordsMap.size() - 1;

            for (Map.Entry<String, String> mirrorPair : mirrorWordsMap.entrySet()) {
                out.printf("%s <=> %s", mirrorPair.getKey(), mirrorPair.getValue());

                if (index != lastIndex) {
                    out.print(", ");
                }

                index++;
            }
        }
    }

    private static String reverseString(String text) {
        StringBuilder reversedString = new StringBuilder();

        for (int i = text.length() - 1; i >= 0; i --) {
            reversedString.append(text.charAt(i));
        }

        return reversedString.toString();
    }
}
