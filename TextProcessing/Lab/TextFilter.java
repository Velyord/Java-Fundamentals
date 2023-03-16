package TextProcessing.Lab;

import java.text.spi.BreakIteratorProvider;
import java.util.Scanner;
import static java.lang.System.out;

public class TextFilter {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] bannedWords = scanner.nextLine().split(", ");
        String text = scanner.nextLine();
        text = filterBanWords(text, bannedWords);

        out.println(text);
    }

    private static String filterBanWords(String text, String[] bannedWords) {
        for (String banWord : bannedWords) {
            StringBuilder replacement = new StringBuilder();
            replacement.append("*".repeat(banWord.length()));
            text = text.replace(banWord, replacement);
        }

        return text;
    }
}
