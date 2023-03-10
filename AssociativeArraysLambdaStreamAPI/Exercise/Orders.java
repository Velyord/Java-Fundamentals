package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class Orders {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Integer> productsQuantityMap = new LinkedHashMap<>();
    private static final Map<String, Double> productsPriceMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        fillMap();
        displayTotalPriceForProducts();
    }

    private static void displayTotalPriceForProducts() {
        for (Map.Entry<String, Integer> entryQuantity : productsQuantityMap.entrySet()) {
            String productName = entryQuantity.getKey();
            double productPrice = productsPriceMap.get(productName);
            int productQuantity = productsQuantityMap.get(productName);

            double totalPrice = productPrice * productQuantity;

            out.printf("%s -> %.2f\n", entryQuantity.getKey(), totalPrice);
        }
    }

    private static void fillMap() {
        String input = setText();

        while (!input.equals("buy")) {
            String[] productInfo = input.split(" ");
            String productName = productInfo[0];
            double newPrice = Double.parseDouble(productInfo[1]);
            int newQuantity = Integer.parseInt(productInfo[2]);

            if (productsQuantityMap.containsKey(productName)) {
                int currentQuantity = productsQuantityMap.get(productName);
                productsQuantityMap.put(productName, currentQuantity + newQuantity);
            } else {
                productsQuantityMap.put(productName, newQuantity);
            }
            productsPriceMap.put(productName, newPrice);

            input = setText();
        }
    }

    private static final String specialChars = "!#$%&'()*+/;<=>?@[]^_`{|}-"; // 0123456789 abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ -

    private static int stringCount = 0; // при въвеждане на низ, броячът нараства

    private static String stringValue;

    public static String setText() { // метод за откриване на грешни в низ от потребителя
        stringValue = scanner.nextLine();

        if (!hasValidChars() || !doesFollowTemplate()) { // ако има забранени символи или не следва задените шаблони, низът на потребителя не се приема и трябва да се въведе нов
            return setText();
        } else {
            return stringValue;
        }
    }

    private static boolean hasValidChars() { // хващане на специални/забранени символи
        boolean isSpecialChar = false;
        char specialChar = ' ';

        for (int i = 0; i < (stringValue).length(); i++) { // Търсене на забранен символ чрез сравняване на входните данни с низът със забранени символи
            if (specialChars.contains(Character.toString(stringValue.charAt(i)))) {
                isSpecialChar = true;
                specialChar = stringValue.charAt(i); // открит забранен символ
                break;
            }
        }

        if (isSpecialChar) { // При грешка се показва на потребителя, кой от въведените му символи е забранен
            if (specialChar == ' ') {
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            } else {
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            }

            return false;
        }

        return true;
    }

    private static boolean doesFollowTemplate() { // подтикване на потребителя да въвежда предварително зададени низове.
        stringCount++;
        String[] requiredStrings;

        // тук се нагласят шаблоните на низовете, ако имат такива.
        if (stringCount == 1) { // на първия низ. Празно ако няма такъв.
            requiredStrings = new String[]{};
        } else if (stringCount == 2) { // на втория низ. Празно ако няма такъв.
            requiredStrings = new String[]{};
        } else { // могат да се добавят и още шаблони преди else. Последния шаблон стои празен.
            requiredStrings = new String[]{};
        }

        if (requiredStrings.length != 0) { // ако е зададен шаблон се изпълнява следния код.
            List<String> requiredList = List.of(requiredStrings); // създава се списък със задължителни входни данни

            if (!requiredList.contains(stringValue)) { // ако се въведе нещо, различно от зададеното в шаблона
                out.println("Моля въведете един от следните избори:");
                out.println(String.join(" | ", requiredStrings)); // показване на потребителя, кои са възможните опции

                stringCount--; // шаблона се наглася отново

                return false;
            }
        }

        return true;
    }
}