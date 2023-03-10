package FundamentalsExams.MidExam19February2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class CoffeeLover {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        ArrayList<String> coffees = new ArrayList<>(List.of(setValue().split(" ")));
        int commandCount = setValue(0, Integer.MAX_VALUE);

        for (int i = 1; i <= commandCount; i++) {
            String[] commandParts = setValue().split(" ");

            switch (commandParts[0]) {
                case "Include":
                    String newCoffee = commandParts[1];

                    coffees.add(newCoffee);
                    break;
                case "Remove":
                    String firstOrLast = commandParts[1];
                    int countOfCoffees = Integer.parseInt(commandParts[2]);

                    removeCoffees(firstOrLast, countOfCoffees, coffees);
                    break;
                case "Prefer":
                    int coffeeFirstIndex = Integer.parseInt(commandParts[1]);
                    int coffeeSecondIndex = Integer.parseInt(commandParts[2]);

                    changePlacesOf(coffeeFirstIndex, coffeeSecondIndex, coffees);
                    break;
                case "Reverse":
                    reverseList(coffees);
                    break;
                default:
                    out.println("Error! Wrong command. Try again!");
                    break;
            }
        }

        out.println("Coffees:");
        printList(coffees);
    }

    private static void removeCoffees(String firstOrLast, int countOfCoffees, ArrayList<String> coffees) {
        if (countOfCoffees <= coffees.size()) {
            for (int j = 1; j <= countOfCoffees; j++) {
                if (firstOrLast.equals("first")) {
                    coffees.remove(0);
                }

                if (firstOrLast.equals("last")) {
                    coffees.remove(coffees.size() - 1);
                }
            }
        }
    }

    private static void changePlacesOf(int coffeeFirstIndex, int coffeeSecondIndex, ArrayList<String> coffees) {
        boolean coffeeFirstIndexDoesExist = coffeeFirstIndex >= 0 && coffeeFirstIndex < coffees.size();
        boolean coffeeSecondIndexDoesExist = coffeeSecondIndex >= 0 && coffeeSecondIndex < coffees.size();

        if (coffeeFirstIndexDoesExist && coffeeSecondIndexDoesExist) {
            String temp = coffees.get(coffeeFirstIndex);
            coffees.set(coffeeFirstIndex, coffees.get(coffeeSecondIndex));
            coffees.set(coffeeSecondIndex, temp);
        }
    }

    // ?????????? ???? ?????????????????? ???? ?????????? ?? ???????????? ??????????????
    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        String type = getType(max); // ???????????????? ???? ???????? ???? ???????????? ???????????????????? ???? ???????????????????? ??????????????
        Object value = setAndCheckInputFor(type); // ???????????????? ???? ?????????????? ???????????? ?????????????? ?????? ?? ???????????????? ???? ????????????????????

        // ?????? ???? ?? ?? ???????????????? ?????????????? ???? ?????????????? ????????????
        if (!isValueBetweenMinAndMax(value, min, max, type)) {
            return setValue(min, max);
        } else {
            return (T) value;
        }
    }

    // ?????????? ???? ???????????????? ???? ???????? ???? ??????????
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

    // ???????????? ???? ?????????? ???????????? ?????????????????????? ???? ??????, ?????? ???? ?????????? ????????????????????, ???????????? ???? ???? ???????????? ???????? ??????????
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
            out.println("?????????????????? ??????????. ?????????????????? ??????!");
            return setAndCheckInputFor(type);
        }

        return (T) value;
    }

    // ?????????? ???? ???????????????? ???? ???????????????? ??????????, ???????? ?? ?? ???????????????????? ?????????????? ???????????? ?????????????? ??????
    private static <T> boolean isValueBetweenMinAndMax(T value, T min, T max, String type) {
        // ???????????????????????? ???? ???? ?????????????????? ???? ???????????????????? ?????????????????? ?????????? ?????? ?????????????? ?? ?????????????? ??????????????.
        double minDouble;
        float minFloat;

        // ???????????????? ???????? ???????????????? ?????????? ?? ???????????????? ?????????????? ???????????? ???????????????????? ??????????????
        switch (type) {
            case "double":
                // ?????? ?? ???????????????? ??????-?????????????? ??????????, ???? ?????????????? ?? ??????-???????????????? ???????????????? ???? -1
                minDouble = (double) min == Double.MIN_VALUE ? -1 * Double.MAX_VALUE : (double) min;
                if ((double) value >= minDouble && (double) value <= (double) max) {
                    return true;
                } break;
            case "float":
                // ?????? ?? ???????????????? ??????-?????????????? ??????????, ???? ?????????????? ?? ??????-???????????????? ???????????????? ???? -1
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

        // ?????? ???????????? ???????????? ???? ???? ???????????? ???????? ??????????
        out.printf("???????? ???????????????? ?????????? ?????????? %s ?? %s:\n", min, max);
        return false;
    }

    static int stringCount = 0; // ?????? ?????????????????? ???? ??????, ?????????????? ????????????????

    // ?????????? ???? ?????????????????? ???? ???????????? ?? ?????? ???? ??????????????????????
    private static String setValue() {
        String value = scanner.nextLine();

        /* ?????? ?????? ?????????????????? ?????????????? ?????? ???? ???????????? ???????????????? ??????????????,
        ?????????? ???? ?????????????????????? ???? ???? ???????????? ?? ???????????? ???? ???? ???????????? ?????? */
        if (!hasValidChars(value) || !doesFollowTemplate(value)) {
            return setValue();
        } else {
            return value;
        }
    }

    // ?????????????? ???? ??????????????????/?????????????????? ??????????????
    private static <T> boolean hasValidChars(T value) {
        // !#$%&'()*+,./:;<=>?@[]^_`{|}
        // 0123456789
        // abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}"; // ???????? ???? ???? ???????????????? ?????????????????????? ??????????????
        boolean isSpecialChar = false;
        char specialChar = ' ';

        // ?????????????? ???? ???????????????? ???????????? ???????? ???????????????????? ???? ???????????????? ?????????? ?? ?????????? ?????? ?????????????????? ??????????????
        for (int i = 0; i < ((String) value).length(); i++) {
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i); // ???????????? ???????????????? ????????????
                break;
            }
        }

        // ?????? ???????????? ???? ?????????????? ???? ??????????????????????, ?????? ???? ???????????????????? ???? ?????????????? ?? ????????????????
        if (isSpecialChar) {
            if (specialChar == ' ') {
                out.println("?????????????????????? ???? ???? ??????????????????. ?????????????????? ??????!");
            } else {
                out.printf("%c e ???????????????????? ????????????. ?????????????????? ??????!\n", specialChar);
            }

            return false;
        }

        return true;
    }

    // ???????????????????? ???? ?????????????????????? ???? ?????????????? ?????????????????????????? ???????????????? ????????????.
    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};

        // ?????? ???? ???????????????? ?????????????????? ???? ????????????????, ?????? ???????? ????????????.
        if (stringCount == 1) {
            requiredStrings = new String[]{}; // ???? ???????????? ??????. ???????????? ?????? ???????? ??????????.
        } else if (stringCount == 2) {
            requiredStrings = new String[]{}; // ???? ???????????? ??????. ???????????? ?????? ???????? ??????????.
        } else { // ?????????? ???? ???? ?????????????? ?? ?????? ?????????????? ?????????? else. ?????????????????? ???????????? ???????? ????????????.
            requiredStrings = new String[]{};
        }

        // ?????? ?? ?????????????? ???????????? ???? ?????????????????? ?????????????? ??????.
        if (requiredStrings.length != 0) {
            List<String> requiredList = List.of(requiredStrings); // ?????????????? ???? ???????????? ?????? ???????????????????????? ???????????? ??????????

            if (!requiredList.contains(value.toString())) { // ?????? ???? ???????????? ????????, ???????????????? ???? ???????????????????? ?? ??????????????
                out.println("???????? ???????????????? ???????? ???? ???????????????? ????????????:");

                // ?????????????? ???? ??????????, ?????????? ???? ???????????? ???? ??????????????????????, ?????? ???? ???????????????????? ??????????
                out.println(String.join(" | ", requiredStrings)); // ????????????????????

                stringCount--; // ???? ???? ???????? ?????????????????? ??????.

                return false;
            }
        }

        return true;
    }

    // ???????????????? ???????????????????????? ???? ????????
    private static <T> void printList(List<T> targets) {
        System.out.println(
                targets.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
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