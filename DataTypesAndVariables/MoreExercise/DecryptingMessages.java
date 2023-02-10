/*
Условие:
    You will receive a key (integer) and n characters afterward.
    Add the key to each character and append them to the message.
    In the end, print the message, which you decrypted.
Input
    • On the first line, you will receive the key.
    • On the second line, you will receive n – the number of lines that will follow.
    • On the next n lines – you will receive lower and uppercase characters from the Latin alphabet.
Output:
    Print the decrypted message.
Constraints:
    • The key will be in the interval [0…20].
    • n will be in the interval [1…20].
    • The characters will always be upper or lower-case letters from the English alphabet.
    • You will receive one letter per line.
Examples:
    3
    7
    P
    l
    c
    q
    R
    k
    f
    ->
    SoftUni

    1
    7
    C
    d
    b
    q
    x
    o
    s
    ->
    Decrypt
*/
package DataTypesAndVariables.MoreExercise;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class DecryptingMessages {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int key = setValue(0, 20);
        int charactersCount = setValue(1, 20);
        char decryptedCharacter;
        String decryptedText = "";

        for (int i=1; i<=charactersCount; i++) {
            char character = setValue().charAt(0);
            decryptedCharacter = (char) (character + key);
            decryptedText += decryptedCharacter;
        }

        out.println(decryptedText);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        String type = getType(max);
        Object value = setAndCheckInputFor(type);
        if (!isValueBetweenMinAndMax(value, min, max, type))
            return setValue(min, max);
        return (T) value;
    }
    private static <T> String getType(T max) {
        if (max instanceof Double)
            return "double";
        else if (max instanceof Float)
            return "float";
        else if (max instanceof Long)
            return "long";
        else
            return "int";
    }
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
    private static <T> boolean isValueBetweenMinAndMax(T value, T min, T max, String type) {
        double minDouble;
        float minFloat;
        switch (type) {
            case "double":
                minDouble = (double) min == Double.MIN_VALUE ? -1 * Double.MAX_VALUE : (double) min;
                if ((double) value >= minDouble && (double) value <= (double) max)
                    return true;
                break;
            case "float":
                minFloat = (float) min == Float.MIN_VALUE ? -1 * Float.MAX_VALUE : (float) min;
                if ((float) value >= minFloat && (float) value <= (float) max)
                    return true;
                break;
            case "long":
                if ((long) value >= (long) min && (long) value <= (long) max)
                    return true;
                break;
            case "int":
                if ((int) value >= (int) min && (int) value <= (int) max)
                    return true;
                break;
        }
        out.printf("Моля въведете число между %s и %s:\n", min, max);
        return false;
    }
    static int stringCount = 0;
    private static String setValue() {
        String value = scanner.nextLine();
        if (!hasValidChars(value) || !doesFollowTemplate(value))
            return setValue();
        return value;
    }
    private static <T> boolean hasValidChars(T value) {
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|} 0123456789";
        boolean isSpecialChar = false;
        char specialChar = ' ';
        for (int i = 0; i < ((String) value).length(); i++)
            if (specialChars.contains(Character.toString(value.toString().charAt(i)))) {
                isSpecialChar = true;
                specialChar = value.toString().charAt(i);
                break;
            }
        if (isSpecialChar) {
            if (specialChar == ' ')
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            else
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            return false;
        }
        return true;
    }
    private static <T> boolean doesFollowTemplate(T value) {
        stringCount++;
        String[] requiredStrings = {};
        if (stringCount == 1)
            requiredStrings = new String[]{}; // fill if needed
        if (stringCount == 2)
            requiredStrings = new String[]{}; // fill for second var
        if (stringCount > 2)
            requiredStrings = new String[]{}; // Keep empty
        if (requiredStrings.length != 0) {
            List<String> requiredList = List.of(requiredStrings);
            if (!requiredList.contains(value.toString())) {
                out.print("Моля въведете един от следните избори: \n| ");
                for (String requiredString : requiredStrings)
                    out.print(requiredString + " | ");
                out.println(); // new line
                stringCount--;
                return false;
            }
        }
        return true;
    }
    public static int[] addIntToArray(int toBeAdded, int[] array) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        newArray[array.length] = toBeAdded;
        return newArray;
    }
}