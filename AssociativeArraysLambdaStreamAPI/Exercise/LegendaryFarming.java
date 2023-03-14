/*
Task:
    You are playing a game, and your goal is to win a legendary item - any legendary item will be good enough.
    However, it's a tedious process and requires quite a bit of farming. The possible items are:
    • "Shadowmourne" - requires 250 Shards
    • "Valanyr" - requires 250 Fragments
    • "Dragonwrath" - requires 250 Motes
    "Shards", "Fragments", and "Motes" are the key materials 	(case-insensitive), and everything else is junk.
    You will be given lines of input in the format:
    "{quantity1} {material1} {quantity2} {material2} … {quantityN} {materialN}"
    Keep track of the key materials - the first one that reaches 250, wins the race.
    At that point, you have to print that the corresponding legendary item is obtained.
    In the end, print the remaining shards, fragments, and motes in the format:
    "shards: {numberOfShards}
    fragments: {numberOfFragments}
    motes: {numberOfMotes}"
    Finally, print the collected junk items in the order of appearance.
Input:
    • Each line comes in the following format:
    "{quantity1} {material1} {quantity2} {material2} … {quantityN} {materialN}"
Output:
    • On the first line, print the obtained item in the format: "{Legendary item} obtained!" .
    • On the next three lines, print the remaining key materials.
    • On the several final lines, print the junk items.
    • All materials should be printed in the format: "{material}: {quantity}".
    • The output should be lowercase, except for the first letter of the legendary.
Examples:
    3 Motes 5 stones 5 Shards
    6 leathers 255 fragments 7 Shards
    ->
    Valanyr obtained!
    shards: 5
    fragments: 5
    motes: 3
    stones: 5
    leathers: 6

    123 silver 6 shards 8 shards 5 motes
    9 fangs 75 motes 103 MOTES 8 Shards
    86 Motes 7 stones 19 silver
    ->
    Dragonwrath obtained!
    shards: 22
    fragments: 0
    motes: 19
    silver: 123
    fangs: 9
 */
package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

public class LegendaryFarming {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Integer> junkInventory = new LinkedHashMap<>();
    private static final Map<String, Integer> legendaryInventory = new LinkedHashMap<>();
    private static boolean win = false;

    public static void main(String[] args) {
        legendaryInventory.put("shards", 0);
        legendaryInventory.put("fragments", 0);
        legendaryInventory.put("motes", 0);

        farmUntilLegendaryWeaponIsObtained();
        showContentsOfBackpack(legendaryInventory);
        showContentsOfBackpack(junkInventory);
    }

    private static void farmUntilLegendaryWeaponIsObtained() {
        while(!win) {
            String[] input = scanner.nextLine().split("\\s+");

            for (int i = 0; i < input.length - 1; i+=2) {
                int quantity = Integer.parseInt(input[i]);
                String material = input[i + 1].toLowerCase();

                if (material.equals("shards") || material.equals("fragments") || material.equals("motes")) {
                    putInLegendaryBackpack(material, quantity);

                    if (legendaryInventory.get(material) >= 250) {
                        obtainALegendaryWeapon(material);
                        win = true;
                        break;
                    }
                } else {
                    putInJunkBackpack(material, quantity);
                }
            }
        }
    }

    private static void showContentsOfBackpack(Map<String, Integer> inventory) {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }

    private static void putInJunkBackpack(String material, int quantity) {
        if (junkInventory.containsKey(material)) {
            int currentQuantity = junkInventory.get(material);
            junkInventory.put(material, currentQuantity + quantity);
        } else {
            junkInventory.put(material, quantity);
        }
    }

    private static void obtainALegendaryWeapon(String material) {
        if (material.equals("shards")) {
            out.println("Shadowmourne obtained!");
        } else if (material.equals("fragments")) {
            out.println("Valanyr obtained!");
        } else {
            out.println("Dragonwrath obtained!");
        }

        int currentQuantity = legendaryInventory.get(material);
        legendaryInventory.put(material, currentQuantity - 250);
    }

    private static void putInLegendaryBackpack(String material, int quantity) {
        if (legendaryInventory.containsKey(material)) {
            int currentQuantity = legendaryInventory.get(material);
            legendaryInventory.put(material, currentQuantity + quantity);
        }
    }
}
