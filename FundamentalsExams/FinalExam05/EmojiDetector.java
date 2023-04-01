/*
Task:
    Your task is to write a program that extracts emojis from a text and find the threshold based on the input.
    You have to get your cool threshold. It is obtained by multiplying all the digits found in the input.
    The cool threshold could be a huge number, so be mindful.
    An emoji is valid when:
    •	It is surrounded by 2 characters, either "::" or "**"
    •	It is at least 3 characters long (without the surrounding symbols)
    •	It starts with a capital letter
    •	Continues with lowercase letters only
    Examples of valid emojis: ::Joy::, **Banana**, ::Wink::
    Examples of invalid emojis: ::Joy**, ::fox:es:, **Monk3ys**, :Snak::Es::
    You need to count all valid emojis in the text and calculate their coolness.
    The coolness of the emoji is determined by summing all the ASCII values of all letters in the emoji.
    Examples: ::Joy:: - 306, **Banana** - 577, ::Wink:: - 409
    You need to print the result of the cool threshold and, after that to take all emojis out of the text,
    count them and print only the cool ones on the console.
Input:
    •	On the single input, you will receive a piece of string.
Output:
    •	On the first line of the output, print the obtained Cool threshold in the format:
    "Cool threshold: {coolThresholdSum}"
    •	On the following line, print the count of all emojis found in the text in format:
    "{countOfAllEmojis} emojis found in the text. The cool ones are:
    {cool emoji 1}
    {cool emoji 2}
    …
    {cool emoji N}"
Constraints:
    There will always be at least one digit in the text!
Examples:
    In the Sofia Zoo there are 311 animals in total! ::Smiley:: This includes 3 **Tigers**, 1 ::Elephant:,
    12 **Monk3ys**, a **Gorilla::, 5 ::fox:es: and 21 different types of :Snak::Es::. ::Mooning:: **Shy**
    ->
    Cool threshold: 540
    4 emojis found in the text. The cool ones are:
    ::Smiley::
    **Tigers**
    ::Mooning::
        You can see all the valid emojis in green. There are various reasons why the rest are not valid, examine them carefully. The "cool threshold" is 3*1*1*3*1*1*2*3*5*2*1 = 540.
        ::Smiley:: -> 83 + 109 + 105 + 108 + 101 + 121 = 627 > 540 -> cool
        **Tigers** -> 84 + 105 + 103 + 101 + 114 + 115 = 622 > 540 -> cool
        ::Mooning:: -> 77 + 111 + 111 + 110 + 105 + 110 + 103 = 727 > 540 -> cool
        **Shy** -> 83 + 104 + 121 = 308 < 540 -> not cool
        In the end, we print the count of all valid emojis found and each of the cool ones on a new line.
    5, 4, 3, 2, 1, go! The 1-th consecutive banana-eating contest has begun! ::Joy:: **Banana** ::Wink:: **Vali**
    ::valid_emoji::
    ->
    Cool threshold: 120
    4 emojis found in the text. The cool ones are:
    ::Joy::
    **Banana**
    ::Wink::
    **Vali**

    It is a long-established fact that 1 a reader will be distracted by 9 the readable content of a page when looking at
    its layout. The point of using ::LoremIpsum:: is that it has a more-or-less normal 3 distribution of 8 letters,
    as opposed to using 'Content here, content 99 here', making it look like readable **English**.
    ->
    Cool threshold: 17496
    1 emojis found in the text. The cool ones are:
        You can see **English** is a valid emoji, but the sum of ASCII is not bigger than the cool threshold.
        That's why we don't print anything in the end.
 */
package FundamentalsExams.FinalExam05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    static List<String> emojis = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String text = scanner.nextLine();
        String regex =
                "([:]{2}|[*]{2})[A-Z][a-z]{2,}\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int coolThreshold = 1;

        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                coolThreshold *= Integer.parseInt(
                        String.valueOf(text.charAt(i))
                );
            }
        }

        System.out.printf(
                "Cool threshold: %d%n",
                coolThreshold
        );

        int fountEmojis = 0;

        while (matcher.find()) {
            int coolMeter = 0;
            String emoji = matcher.group();

            for (int i = 0; i < emoji.length(); i++) {
                if (Character.isLetter(emoji.charAt(i))) {
                    coolMeter += emoji.charAt(i);
                }
            }

            fountEmojis++;

            if (coolMeter > coolThreshold) {
                emojis.add(emoji);
            }
        }

        System.out.printf(
            "%d emojis found in the text. The cool ones are:%n",
            fountEmojis
        );

        emojis.forEach(System.out::println);
    }
}
