/*
Task:
    Your first task is to determine if the given sequence of characters is a valid barcode or not.
Each line must not contain anything else but a valid barcode. A barcode is valid when:
    • It is surrounded by a "@" followed by one or more "#"
    • It is at least 6 characters long (without the surrounding "@" or "#")
    • It starts with a capital letter
    • It contains only letters (lower and upper case) and digits
    • It ends with a capital letter
Examples of valid barcodes: @###Che46sE@##, @#FreshFisH@#, @###Brea0D@###, @##Che46sE@##
Examples of invalid barcodes: ##InvaliDiteM##, @InvalidIteM@, @#Invalid_IteM@#
Next, you have to determine the product group of the item from the barcode. The product group is obtained by concatenating all the digits found in the barcode. If there are no digits present in the barcode, the default product group is "00".
Examples:
@#FreshFisH@# -> product group: 00
@###Brea0D@### -> product group: 0
@##Che4s6E@## -> product group: 46
Input
On the first line, you will be given an integer n – the count of barcodes that you will be receiving next.
On the following n lines, you will receive different strings.
Output
For each barcode that you process, you need to print a message.
If the barcode is invalid:
    • "Invalid barcode"
If the barcode is valid:
    • "Product group: {product group}"
Examples
Input
Output
3
@#FreshFisH@#
@###Brea0D@###
@##Che4s6E@##
Product group: 00
Product group: 0
Product group: 46
Input
Output
6
@###Val1d1teM@###
@#ValidIteM@#
##InvaliDiteM##
@InvalidIteM@
@#Invalid_IteM@#
@#ValiditeM@#
Product group: 11
Product group: 00
Invalid barcode
Invalid barcode
Invalid barcode
Product group: 00

 */
package FundamentalsExams.FinalExam04;

import java.util.Scanner;

public class FancyBarcodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String regex = "@#+[A-Z][A-Za-z0-9]{4,}[A-Z]@#+";
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            if (input.matches(regex)) {
                String productGroup = "";
                for (int j = 0; j < input.length(); j++) {
                    char currentChar = input.charAt(j);
                    if (Character.isDigit(currentChar)) {
                        productGroup += currentChar;
                    }
                }
                if (productGroup.isEmpty()) {
                    productGroup = "00";
                }
                System.out.printf("Product group: %s%n", productGroup);
            } else {
                System.out.println("Invalid barcode");
            }
        }
    }
}
