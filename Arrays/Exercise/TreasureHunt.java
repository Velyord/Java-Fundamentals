/*
Условие:
    The pirates must safely carry a treasure chest back to the ship, looting along the way.
    Create a program that manages the state of the treasure chest along the way.
    On the first line, you will receive the initial loot of the treasure chest,
    a string of items separated by a "|".
    "{loot1}|{loot2}|{loot3} … {lootn}"
    The following lines represent commands until "Yohoho!" which ends the treasure hunt:
    • "Loot {item1} {item2}…{itemn}":
        ◦ Pick up treasure loot along the way. Insert the items at the beginning of the chest.
        ◦ If an item is already contained, don't insert it.
    • "Drop {index}":
        ◦ Remove the loot at the given position and add it to the end of the treasure chest.
        ◦ If the index is invalid, skip the command.
    • "Steal {count}":
        ◦ Someone steals the last count loot items.
        If there are fewer items than the given count, remove as many as there are.
        ◦ Print the stolen items separated by ", ":
    "{item1}, {item2}, {item3} … {itemn}"
    In the end, output the average treasure gain,
    which is the sum of all treasure items length divided by the count of all items
    inside the chest formatted to the second decimal point:
    "Average treasure gain: {averageGain} pirate credits."
    If the chest is empty, print the following message:
    "Failed treasure hunt."
Input:
    • On the 1st line, you will receive the initial treasure chest (loot separated by "|").
    • On the following lines, you will receive commands until "Yohoho!".
Output:
    • Print the output in the format described above.
Constraints:
    • The loot items will be strings containing any ASCII code.
    • The indexes will be integers in the range [-200…200].
    • The count will be an integer in the range [1….100].
Examples:
    Gold|Silver|Bronze|Medallion|Cup
    Loot Wood Gold Coins
    Loot Silver Pistol
    Drop 3
    Steal 3
    Yohoho!
    ->
    Medallion, Cup, Gold
    Average treasure gain: 5.40 pirate credits.
        The first command, "Loot Wood Gold Coins" adds Wood and Coins to the chest
        but omits Gold since it is already contained. The chest now has the following items:
        Coins Wood Gold Silver Bronze Medallion Cup
        The second command adds only Pistol to the chest
        The third command, "Drop 3" removes the Gold from the chest but immediately adds it at the end:
        Pistol Coins Wood Silver Bronze Medallion Cup Gold
        The fourth command, "Steal 3" removes the last 3 items Medallion, Cup, Gold,
        from the chest and prints them.
        In the end calculate the average treasure gain
        which is the sum of all items length
        Pistol(6) + Coins(5) + Wood(4)  + Silver(6) + Bronze(6) = 27
        and divide it by the count 27 / 5 = 5.4 and format it to the second decimal point.
    Diamonds|Silver|Shotgun|Gold
    Loot Silver Medals Coal
    Drop -1
    Drop 1
    Steal 6
    Yohoho!
    ->
    Coal, Diamonds, Silver, Shotgun, Gold, Medals
    Failed treasure hunt.
*/
package Arrays.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class TreasureHunt {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String[] initialLoot = setValue().split("\\|");
        List<String> chestItems = new ArrayList<>(List.of(initialLoot));

        String input = setValue();

        while (!input.equals("Yohoho!")) {
            if (input.startsWith("Loot")) {
                loot(input, chestItems);
            } else if (input.startsWith("Drop")) {
                drop(input, chestItems);
            } else if (input.startsWith("Steal")) {
                steal(input, chestItems);
            } else {
                out.println("ERROR!");
            }

            input = setValue();
        }

        out.println(chestItems);
    }

    private static void steal(String input, List<String> chestItems) {
        int count = Integer.parseInt(input.substring(6));
        List<String> stolenItems = new ArrayList<>();

        if (chestItems.size() <= count) {
            stolenItems = chestItems;
            chestItems.removeAll(chestItems);
        } else {
            for (int i=1; i <= count; i++) {
                stolenItems.add(chestItems.get(chestItems.size() - 1));
                chestItems.remove(chestItems.size() - 1);
            }
        }

        String output = "";

        for (int i = stolenItems.size() - 1; i >= 0; i--) {
            output += stolenItems.get(i) + " | ";
        }
        out.println(output.substring(0, output.length() - 3));
    }

    private static void drop(String input, List<String> chestItems) {
        int index = Integer.parseInt(input.substring(5));
        String toBeAdded = chestItems.get(index);
        chestItems.remove(index);
        chestItems.add(toBeAdded);
    }

    private static void loot(String input, List<String> chestItems) {
        String[] loot = input.split(" ");

        for (String item : loot) {
            if (!item.equals("Loot") && !chestItems.contains(item)) {
                chestItems.add(0, item);
            }
        }
    }

    // метод за въвеждане на число в дадени граници
    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        String type = getType(max); // намиране на типа му според зададената му максимална граница
        Object value = setAndCheckInputFor(type); // задаване на числото според неговия тип и проверка за изключения

        // ако не е в дадената граница се повтаря метода
        if (!isValueBetweenMinAndMax(value, min, max, type)) {
            return setValue(min, max);
        } else {
            return (T) value;
        }
    }

    // метод за намиране на типа на число
    private static <T> String getType(T variable) {
        if (variable instanceof Double) {
            return "double";
        } else if (variable instanceof Float) {
            return "float";
        } else if (variable instanceof Long) {
            return "long";
        } else if (variable instanceof Integer) {
            return "int";
        } else {
            return "String";
        }
    }

    // задава се число според прихванатия му тип, ако се хване изключение, трябва да се въведе ново число
    @SuppressWarnings("unchecked")
    private static <T> T setAndCheckInputFor(String type) {
        Object value;

        try {
            switch (type) {
                case "double": value = Double.parseDouble(scanner.nextLine()); break;
                case "float":  value = Float.parseFloat(scanner.nextLine());   break;
                case "long":   value = Long.parseLong(scanner.nextLine());     break;
                case "int":    value = Integer.parseInt(scanner.nextLine());   break;
                default:       value = null;                                   break;
            }
        } catch (Exception e) {
            out.println("Невалидно число. Пробвайте пак!");
            return setAndCheckInputFor(type);
        }

        return (T) value;
    }

    // метод за проверка на входното число, дали е в зададените граници според неговия тип
    private static <T> boolean isValueBetweenMinAndMax(T value, T min, T max, String type) {
        // променливите са за постигане на максимално негативно число при числата с плаваща запетая.
        double minDouble;
        float minFloat;

        // проверка дали входното число е правилно избрано според зададените граници
        switch (type) {
            case "double":
                // ако е зададено най-малкото число, го замести с най-голямото умножено по -1
                minDouble = (double) min == Double.MIN_VALUE ? -1 * Double.MAX_VALUE : (double) min;
                if ((double) value >= minDouble && (double) value <= (double) max) {
                    return true;
                } break;
            case "float":
                // ако е зададено най-малкото число, го замести с най-голямото умножено по -1
                minFloat = (float) min == Float.MIN_VALUE ? -1 * Float.MAX_VALUE : (float) min;
                if ((float) value >= minFloat && (float) value <= (float) max) {
                    return true;
                } break;
            case "long":
                if ((long) value >= (long) min && (long) value <= (long) max) {
                    return true;
                } break;
            case "int":
                if ((int) value >= (int) min && (int) value <= (int) max) {
                    return true;
                } break;
        }

        // при грешка трябва да се въведе ново число
        out.printf("Моля въведете число между %s и %s:\n", min, max);
        return false;
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
        String specialChars = "#$%&'()*+,./:;<=>?@[]^_`{}"; // може да се променят забранените символи
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

    // принтира съдържанието на лист
    private static void printList(List<Integer> list) {
        for (int item : list) {
            out.print(item + " ");
        }
    }
}