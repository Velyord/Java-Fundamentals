/*
Task:
    Write a Java Program to match full names from a list of names and print them on the console.
    Writing the Regular Expression
    First, write a regular expression to match a valid full name, according to these conditions:
    • A valid full name has the following characteristics:
        ◦ It consists of two words.
        ◦ Each word starts with a capital letter.
        ◦ After the first letter, it only contains lowercase letters afterward.
        ◦ Each of the two words should be at least two letters long.
        ◦ The two words are separated by a single space.
    To help you out, we've outlined several steps:
    1. Use an online regex tester like https://regex101.com/
    2. Check out how to use character sets (denoted with square brackets - '[]')
    3. Specify that you want two words with a space between them (the space character ' ',
    and not any whitespace symbol)
    4. For each word, specify that it should begin with an uppercase letter using a character set.
    The desired characters are in a range – from 'A' to 'Z'.
    5. For each word, specify that what follows the first letter are only lowercase letters,
    one or more – use another character set and the correct quantifier.
    6. To prevent letters' capture across new lines, put "\b" at the beginning and the end of your regex.
    This will ensure that what precedes and what follows the match is a word boundary (like a new line).
    To check your RegEx, use these values for reference (paste all of them in the Test String field):
    Match ALL of these
    Match NONE of these
    Ivan Ivanov
    Ivan Ivanov, Ivan ivanov, ivan Ivanov, IVan Ivanov, Georgi Georgiev, Ivan	Ivanov
    Peter Georgiev
    peter georgiev, peter GeOrgiev, Peter   GeORgiev, PEter GEorgiev, Peter Georgiev, Anna Petrova
    By the end, the matches should look something like this:
    After you've constructed your regular expression, it's time to write the solution in Java.
    Implementing the Solution in Java
    Create a new Java project and copy your regular expression into a String variable:
    Now, it's time to read the input and create two classes to help us work with regular expressions:
    • Pattern Class − A Pattern object is a compiled representation of a regular expression.
    • Matcher Class − A Matcher object is the engine that interprets the pattern and performs match
    operations against an input string.
    Now, it's time to extract all the matches from our input and print them. We use the matcher method
    find(), which attempts to find the next subsequence of the input sequence that matches the pattern.
    To get our matches, we need to use method group().
Examples:
    Ivan Ivanov, Ivan ivanov, ivan Ivanov, IVan Ivanov, Georgi Georgiev, Ivan	Ivanov
    ->
    Ivan Ivanov Georgi Georgiev

    peter georgiev, peter Georgiev, Peter GeoRgiev, PEter GEorgiev, Peter Georgiev, Anna Petrova
    ->
    Peter Georgiev Anna Petrova
 */
package RegularExpressions.Lab;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class MatchFullName {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\b[A-Z][a-z]+ [A-Z][a-z]+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String matchedText = matcher.group();
            out.print(matchedText + " ");
        }
    }
}
