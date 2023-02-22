/*
Условие:
    You are given a field size and the indexes of ladybugs inside the field.
    After that, on every new line, until the "end" command is given,
    a ladybug changes its position to its left or right by a given fly length.
    A command to a ladybug looks like this: "0 right 1".
    This means that the little insect placed on index 0 should fly one index to its right.
    If the ladybug lands on a fellow ladybug, it continues to fly in the same direction
    by the same fly length. If the ladybug flies out of the field, it is gone.
    For example, imagine you are given a field with size 3 and ladybugs on indexes 0 and 1.
    If the ladybug on index 0 needs to fly to its right by the length of 1 (0 right 1)
    it will attempt to land on index 1, but as there is another ladybug there,
    it will continue further to the right by an additional length of 1, landing on index 2.
    After that, if the same ladybug needs to fly to its right by the length of 1 (2 right 1),
    it will land somewhere outside the field, so it flies away:

    If you are given a ladybug index that does not have a ladybug there, nothing happens.
    If you are given a ladybug index that is outside the field, nothing happens.
    Your job is to create the program, simulating the ladybugs flying around doing nothing.
    In the end, print all cells in the field separated by blank spaces.
    For each cell that has a ladybug on it print '1' and for each empty cell print '0'.
    For the example above, the output should be '0 1 0'.
Input:
    • On the first line, you will receive an integer - the size of the field.
    • On the second line, you will receive the initial indexes of all ladybugs
    separated by a blank space. The given indexes may or may not be inside the field range.
    • On the next lines, until you get the "end" command you will receive commands in the format:
    "{ladybug index} {direction} {fly length}".
Output:
    • Print the all cells within the field in format: "{cell} {cell} … {cell}"
        ◦ If a cell has a ladybug in it, print '1'.
        ◦ If a cell is empty, print '0' .
Constraints:
    • The size of the field will be in the range [0 … 1000].
    • The ladybug indexes will be in the range [-2,147,483,647 … 2,147,483,647].
    • The number of commands will be in the range [0 … 100].
    • The fly length will be in the range [-2,147,483,647 … 2,147,483,647].
Examples
    3
    0 1
    0 right 1
    2 right 1
    end
    ->
    0 1 0
        1 1 0 - Initial field
        0 1 1 - field after "0 right 1"
        0 1 0 - field after "2 right 1"
    3
    0 1 2
    0 right 1
    1 right 1
    2 right 1
    end
    ->
    0 0 0

    5
    3
    3 left 2
    1 left -2
    end
    ->
    0 0 0 1 0
*/
package Arrays.MoreExercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class LadyBugs {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int fieldSize = setValue(0, 1000);

        List<Integer> indexesOfLadybugs = Arrays
                .stream(setValue().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        while (indexesOfLadybugs.size() < fieldSize) {
            indexesOfLadybugs.add(-1);
        }

        int[] ladyBugs = new int[fieldSize];

        for (int i = 0; i < fieldSize; i++) {
            if (indexesOfLadybugs.get(i) >= 0 && indexesOfLadybugs.get(i) < fieldSize) {
                ladyBugs[indexesOfLadybugs.get(i)] = 1;
            }
        }

        String command = setValue();

        while (!command.equals("end")) {
            String[] commandParts = command.split(" ");
            String direction = commandParts[1];
            int bugOnPosition = Integer.parseInt(commandParts[0]);
            int movement = Integer.parseInt(commandParts[2]);
            boolean hasMoved = false;

            if (ladyBugs[bugOnPosition] == 1) {
                switch (direction) {
                    case "left":
                        moveLeft(bugOnPosition, movement, ladyBugs, hasMoved);
                        break;
                    case "right":
                        moveRight(bugOnPosition, movement, ladyBugs, hasMoved);
                        break;
                }
            }

            command = setValue();
        }

        String output = "";

        for (int item : ladyBugs) {
            output += item + " ";
        }

        out.println(output);
    }

    private static int[] moveLeft(int bugOnPosition, int movement, int[] ladyBugs, boolean hasMoved) {
        if (bugOnPosition - movement >= 0) {
            if (ladyBugs[bugOnPosition - movement] == 0) {
                if (!hasMoved) {
                    ladyBugs[bugOnPosition] = 0;
                }
                ladyBugs[bugOnPosition - movement] = 1;
            } else {
                if (!hasMoved) {
                    ladyBugs[bugOnPosition] = 0;
                }
                hasMoved = true;
                moveLeft(bugOnPosition - movement, movement, ladyBugs, hasMoved);
            }
        } else {
            if (!hasMoved) {
                ladyBugs[bugOnPosition] = 0;
            }
        }

        return ladyBugs;
    }

    private static int[] moveRight(int bugOnPosition, int movement, int[] ladyBugs, boolean hasMoved) {
        if (bugOnPosition + movement < ladyBugs.length) {
            if (ladyBugs[bugOnPosition + movement] == 0) {
                if (!hasMoved) {
                    ladyBugs[bugOnPosition] = 0;
                }
                ladyBugs[bugOnPosition + movement] = 1;
            } else {
                if (!hasMoved) {
                    ladyBugs[bugOnPosition] = 0;
                }
                hasMoved = true;
                moveRight(bugOnPosition + movement, movement, ladyBugs, hasMoved);
            }
        } else {
            if (!hasMoved) {
                ladyBugs[bugOnPosition] = 0;
            }
        }

        return ladyBugs;
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
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}"; // може да се променят забранените символи
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
    private static <T> void printList(List<T> targets) {
        System.out.println(
                targets.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("|"))
        );
    }

    public static <T> void reverseList(List<T> list) {
        if (list.size() <= 1 || list == null)
            return;
        T value = list.remove(0);
        reverseList(list);
        list.add(value);
    }
}