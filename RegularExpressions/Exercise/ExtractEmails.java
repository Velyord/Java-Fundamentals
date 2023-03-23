/*
Task:
    Write a program to extract all email addresses from a given text.
    The text comes at the only input line. Print the emails on the console, each at a separate line.
    Emails are considered to be in format <user>@<host>, where:
    • <user> is a sequence of letters and digits, where '.', '-' and '_' can appear between them.
        ◦ Examples of valid users:
        "stephan", "mike03", "s.johnson", "st_steward", "softuni-bulgaria", "12345".
        ◦ Examples of invalid users: ''--123", ".....", "nakov_-", "_steve", ".info".
    • <host> is a sequence of at least two words, separated by dots '.'.
    Each word is a sequence of letters and can have hyphens '-' between the letters.
        ◦ Examples of hosts:
        "softuni.bg", "software-university.com", "intoprogramming.info", "mail.softuni.org".
        ◦ Examples of invalid hosts: "helloworld", ".unknown.soft.", "invalid-host-", "invalid-".
    • Examples of valid emails:
    "info@softuni-bulgaria.org", "kiki@hotmail.co.uk", "no-reply@github.com", "s.peterson@mail.uu.net",
    "info-bg@software-university.software.academy".
    • Examples of invalid emails:
    "--123@gmail.com", "…@mail.bg", ".info@info.info", "_steve@yahoo.cn", "mike@helloworld",
    "mike@.unknown.soft. ", "s.johnson@invalid-".
Examples:
    Please contact us at: support@github.com.
    ->
    support@github.com

    Just send email to s.miller@mit.edu and j.hopking@york.ac.uk for more information.
    ->
    s.miller@mit.edu
    j.hopking@york.ac.uk
 */
package RegularExpressions.Exercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.in;
import static java.lang.System.out;

public class ExtractEmails {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String text = scanner.nextLine();

        String regex = "(?<user>[A-Za-z0-9]+[\\-._]?[A-Za-z0-9]+)@(?<host>[A-Za-z]+-?[A-Za-z]+(\\.[A-Za-z]+-?[A-Za-z]+)+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            out.println(matcher.group());
        }
    }
}
