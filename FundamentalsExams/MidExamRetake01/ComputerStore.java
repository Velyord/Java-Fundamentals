/*
Условие:
    Write a program that prints you a receipt for your new computer.
    You will receive the parts' prices (without tax) until you receive what type of customer this is -
    special or regular. Once you receive the type of customer you should print the receipt.
    The taxes are 20% of each part's price you receive.
    If the customer is special, he has a 10% discount on the total price with taxes.
    If a given price is not a positive number, you should print "Invalid price!" on the console and
    continue with the next price.
    If the total price is equal to zero, you should print "Invalid order!" on the console.
Input:
    • You will receive numbers representing prices (without tax) until command "special" or "regular":
Output:
    • The receipt should be in the following format:
    "Congratulations you've just bought a new computer!
    Price without taxes: {total price without taxes}$
    Taxes: {total amount of taxes}$
    -----------
    Total price: {total price with taxes}$"
Note:
    All prices should be displayed to the second digit after the decimal point!
    The discount is applied only on the total price. Discount is only applicable to the final price!
Examples:
    1050
    200
    450
    2
    18.50
    16.86
    special
    ->
    Congratulations you've just bought a new computer!
    Price without taxes: 1737.36$
    Taxes: 347.47$
    -----------
    Total price: 1876.35$
        1050 – valid price, total 1050
        200 – valid price, total 1250
        …
        16.86 – valid price, total 1737.36
        We receive special
        Price is positive number, so it is valid order
        Price without taxes is 1737.36
        Taxes: 20% from 1737.36 = 347.47
        Final price = 1737.36 + 347.47 = 2084.83
        Additional 10% discount for special customers
        2084.83 – 10% = 1876.35
    1023
    15
    -20
    -5.50
    450
    20
    17.66
    19.30
    regular
    ->
    Invalid price!
    Invalid price!
    Congratulations you've just bought a new computer!
    Price without taxes: 1544.96$
    Taxes: 308.99$
    -----------
    Total price: 1853.95$

    regular
    ->
    Invalid order!
*/
package FundamentalsExams.MidExamRetake01;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class ComputerStore {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int taxPercent = 20;
        int discountPercent = 0;
        double sum = 0;

        String input = setValue();

        while (!input.equals("special") && !input.equals("regular")) {
            double price = Double.parseDouble(input);
            if (price < 0) {
                out.println("Invalid price!");
            } else {
                sum += price;
            }

            input = setValue();
        }

        if (sum <= 0) {
            out.println("Invalid order!");
        } else {
            if (input.equals("special")) {
                discountPercent = 10;
            }

            double tax = sum * taxPercent / 100.0;
            double priceAfterTax = sum + tax;
            double discount = priceAfterTax * discountPercent / 100.0;
            double priceAfterDiscount = priceAfterTax - discount;

            out.printf(
                "Congratulations you've just bought a new computer!\n" +
                "Price without taxes: %.2f$\n" +
                "Taxes: %.2f$\n" +
                "-----------\n" +
                "Total price: %.2f$",
                sum, tax, priceAfterDiscount
            );
        }
    }

    static int stringCount = 0; // при въвеждане на низ, броячът нараства

    // метод за откриване на грешни в низ от потребителя
    private static String setValue() {
        String value = scanner.nextLine();

        /* ако има забранени символи или не следва задените шаблони,
        низът на потребителя не се приема и трябва да се въведе нов */
        if (!hasValidChars(value) || !doesFollowTemplate(value)) {
            return setValue();
        } else {
            return value;
        }
    }

    // хващане на специални/забранени символи
    private static <T> boolean hasValidChars(T value) {
        // !#$%&'()*+,./:;<=>?@[]^_`{|}
        // 0123456789
        // abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
        String specialChars = "!#$%&'()*+,/:;<=>?@[]^_`{|}"; // може да се променят забранените символи
        boolean isSpecialChar = false;
        char specialChar = ' ';

        // Търсене на забранен символ чрез сравняване на входните данни с низът със забранени символи
        for (int i = 0; i < ((String) value).length(); i++) {
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i); // открит забранен символ
                break;
            }
        }

        // При грешка се показва на потребителя, кой от въведените му символи е забранен
        if (isSpecialChar) {
            if (specialChar == ' ') {
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            } else {
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            }

            return false;
        }

        return true;
    }

    // подтикване на потребителя да въвежда предварително зададени низове.
    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};

        // тук се нагласят шаблоните на низовете, ако имат такива.
        if (stringCount == 1) {
            requiredStrings = new String[]{}; // на първия низ. Празно ако няма такъв.
        } else if (stringCount == 2) {
            requiredStrings = new String[]{}; // на втория низ. Празно ако няма такъв.
        } else { // могат да се добавят и още шаблони преди else. Последния шаблон стои празен.
            requiredStrings = new String[]{};
        }

        // ако е зададен шаблон се изпълнява следния код.
        if (requiredStrings.length != 0) {
            List<String> requiredList = List.of(requiredStrings); // създава се списък със задължителни входни данни

            if (!requiredList.contains(value.toString())) { // ако се въведе нещо, различно от зададеното в шаблона
                out.println("Моля въведете един от следните избори:");

                // завърта се цикъл, който да покаже на потребителя, кои са възможните опции
                out.println(String.join(" | ", requiredStrings)); // разделител

                stringCount--; // не се брои сгрешения низ.

                return false;
            }
        }

        return true;
    }
}