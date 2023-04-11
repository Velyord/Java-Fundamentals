/*
Task:
    Your first task is to determine if the given sequence of characters is a valid online banking password.
    Each line must not contain anything else but a valid password. A password is valid when:
    •	It is surrounded by a "_" followed by one or more "."
    •	It is at least 6 characters long (without the surrounding "_" or ".")
    •	It starts with a capital letter
    •	It contains only letters (lower and upper case) and digits
    •	It ends with a capital letter
    Examples of valid passwords: _...ChelseA_., _..Online1BankinG_., _.Valid1PasS_., _.A123f23A_.
    Examples of invalid passwords: __InvalidPass.., _Invalid_, _.Invalid.IteM_., _.pass1InvaliD_.
    Next, you have to determine which group the password is from.
    The group is obtained by concatenating all the digits found in the password, if any.
    If there are no digits present in the password, the default group is "default".
Examples:
    _...ChelseA_. -> group: default
    _..Online1BankinG_. -> group: 1
    _.A123f23A_.-> group: 12323
Input:
    On the first line, you will be given an integer n – the count of passwords that you will be receiving next.
    On the following n lines, you will receive different strings.
Output:
    For each password that you process, you need to print a message.
    If the password is invalid:
    •	"Invalid pass!"
    If the password is valid:
    •	"Group: {group}"
Examples:
    3
    _...ChelseA_.
    _..Online1BankinG_.
    _.A1f23A_.	Group: default
    ->
    Group: 1
    Group: 123

    5
    _.Online2BanK_.
    __.Pass2Da_.
    _.InvaliDitem_.
    _.My2Pass47S_...
    _...QAPassV_..
    ->
    Group: 2
    Invalid pass!
    Invalid pass!
    Group: 247
    Group: default
 */
package RegularExpressions.Exercise;

import java.util.Scanner;

public class ValidatePassword { // ValidPassword
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            String password = scanner.nextLine();

            if (password.matches("_\\.+[A-Z][A-Za-z0-9]{4,}[A-Z]_\\.+")) {
                String group = password.replaceAll("\\D+", "");

                if (group.isEmpty()) {
                    group = "default";
                }

                System.out.printf("Group: %s%n", group);
            } else {
                System.out.println("Invalid pass!");
            }
        }
    }
}
