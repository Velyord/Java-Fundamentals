/*
Условие:
    Write a program that prints common elements in two arrays.
    You have to compare the elements of the second array to the elements of the first.
Examples:
    Hey hello 2 4
    10 hey 4 hello
    ->
    4 hello

    S of t un i
    of i 10 un
    ->
    of i un

    i love to code
    code i love to
    ->
    code i love to
*/
package Arrays.Exercise;

import java.util.Scanner;
import java.util.List;

import static java.lang.System.*;

public class CommonElements {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String[] firstArray = scanner.nextLine().split(" ");
        String[] secondArray = scanner.nextLine().split(" ");
        Object[] commonArray = {};

        commonArray = addCommonItemsFromArrays(firstArray, secondArray, commonArray);
        printArray(commonArray);
    }

    private static Object[] addCommonItemsFromArrays(
            String[] firstArray,
            String[] secondArray,
            Object[] commonArray
    ) {
        for (String secondArrayItem : secondArray) {
            for (String firstArrayItem : firstArray) {
                if (secondArrayItem.equals(firstArrayItem)) {
                    commonArray = addToArray(secondArrayItem, commonArray);
                }
            }
        }

        return commonArray;
    }

    private static void printArray(Object[] array) {
        for (Object item : array) {
            out.print(item + " ");
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] addToArray(T toBeAdded, T[] array) {
        T[] newArray = (T[]) new Object[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[newArray.length - 1] = toBeAdded;
        return newArray;
    }
}