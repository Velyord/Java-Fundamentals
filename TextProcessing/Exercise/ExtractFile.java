/*
Task:
    Write a program that reads the path to a file and subtracts the file name and its extension.
Examples:
    C:\Internal\training-internal\Template.pptx
    ->
    File name: Template
    File extension: pptx

    C:\Projects\Data-Structures\LinkedList.cs
    ->
    File name: LinkedList
    File extension: cs
 */
package TextProcessing.Exercise;

import java.util.Scanner;

import static java.lang.System.out;

public class ExtractFile {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] input = scanner.nextLine().split("\\\\");
        String[] file = input[input.length - 1].split("\\.");
        String fileName = file[0];
        String fileExtension = file[1];

        out.println("File name: " + fileName);
        out.println("File extension: " + fileExtension);
    }
}
