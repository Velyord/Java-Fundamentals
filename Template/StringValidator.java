package Template;

import java.util.List;
import static Template.PersonalUtils.*;
import static java.lang.System.out;

public class StringValidator {
    private static final String specialChars = "!#$%&'()*+./;<=>?@[]^_`{|}"; // 0123456789 abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ -

    static int stringCount = 0; // при въвеждане на низ, броячът нараства

    public static String setText() { // метод за откриване на грешни в низ от потребителя
        String value = scan();

        if (!hasValidChars(value) || !doesFollowTemplate(value)) { // ако има забранени символи или не следва задените шаблони, низът на потребителя не се приема и трябва да се въведе нов
            return setText();
        } else {
            return value;
        }
    }

    private static <T> boolean hasValidChars(T value) { // хващане на специални/забранени символи
        boolean isSpecialChar = false;
        char specialChar = ' ';

        for (int i = 0; i < ((String) value).length(); i++) { // Търсене на забранен символ чрез сравняване на входните данни с низът със забранени символи
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i); // открит забранен символ
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

    private static <T> boolean doesFollowTemplate(T value) { // подтикване на потребителя да въвежда предварително зададени низове.
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

            if (!requiredList.contains(value.toString())) { // ако се въведе нещо, различно от зададеното в шаблона
                out.println("Моля въведете един от следните избори:");
                out.println(String.join(" | ", requiredStrings)); // показване на потребителя, кои са възможните опции

                stringCount--; // шаблона се наглася отново

                return false;
            }
        }

        return true;
    }
}