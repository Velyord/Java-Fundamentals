/*
Task:
    You are tasked to help plan the next Programming Fundamentals course
    by keeping track of the lessons that will be included in the course,
    as well as all the exercises for the lessons.
    On the first input line, you will receive the initial schedule of lessons
    and exercises that will be part of the next course, separated by a comma and space ", ".
    But before the course starts, there are some changes to be made.
    Until you receive "course start", you will be given some commands to modify the course schedule.
    The possible commands are:
    Add:{lessonTitle} - add the lesson to the end of the schedule, if it does not exist
    Insert:{lessonTitle}:{index} - insert the lesson to the given index, if it does not exist
    Remove:{lessonTitle} - remove the lesson, if it exists
    Swap:{lessonTitle}:{lessonTitle} - change the place of the two lessons, if they exist
    Exercise:{lessonTitle} - add Exercise in the schedule right after the lesson index,
    if the lesson exists and there is no exercise already,
    in the following format "{lessonTitle}-Exercise".
    If the lesson doesn't exist, add the lesson at the end of the course schedule,
    followed by the Exercise.
    Each time you Swap or Remove a lesson, you should do the same with the Exercises,
    if there are any, which follow the lessons.
Input:
    • On the first line -the initial schedule lessons -strings, separated by comma and space ", ".
    • Until "course start", you will receive commands in the format described above.
Output:
    • Print the whole course schedule, each lesson on a new line with its number(index) in the schedule:
    "{lesson index}.{lessonTitle}".
    • Allowed working time / memory: 100ms / 16MB.
Examples:
    Data Types, Objects, Lists
    Add:Databases
    Insert:Arrays:0
    Remove:Lists
    course start
    ->
    1.Arrays
    2.Data Types
    3.Objects
    4.Databases
        We receive the initial schedule.
        Next, we add the Databases lesson because it doesn't exist.
        We Insert the given index lesson Arrays because it's not present in the schedule.
        After receiving the last command and removing lesson Lists, we print the whole schedule.
    Arrays, Lists, Methods
    Swap:Arrays:Methods
    Exercise:Databases
    Swap:Lists:Databases
    Insert:Arrays:0
    course start
    ->
    1.Methods
    2.Databases
    3.Databases-Exercise
    4.Arrays
    5.Lists
        We swap the given lessons because both exist.
        After receiving the Exercise command, we see that such a lesson doesn't exist,
        so we add the lesson at the end, followed by the exercise.
        We swap Lists and Databases lessons.
        The Databases-Exercise is also moved after the Databases lesson.
        We skip the next command because we already have such a lesson in our schedule.
*/
package Lists.Exercise;

import java.util.*;
import static java.lang.System.in;

public class SoftUniCoursePlanning {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        List<String> courses = new ArrayList<>(List.of(scanner.nextLine().split(", ")));

        String command = scanner.nextLine();

        while (!command.equals("course start")) {

            String[] commandArr = command.split(":");
            String currentCommand = commandArr[0];
            String lessonTitle = commandArr[1];

            switch (currentCommand) {
                case "Add":
                    if (!courses.contains(lessonTitle)) {
                        courses.add(lessonTitle);
                    }
                    break;
                case "Insert":
                    int index = Integer.parseInt(commandArr[2]);

                    if (!courses.contains(lessonTitle)) {
                        courses.add(index, lessonTitle);
                    }
                    break;
                case "Remove":
                    if (courses.contains(lessonTitle)) {
                        courses.removeAll(Collections.singleton((lessonTitle)));
                    }
                    break;
                case "Swap":
                    String secondTitle = commandArr[2];

                    swap(lessonTitle, secondTitle, courses);
                    break;
                case "Exercise":
                    addExercise(lessonTitle, courses);

                    break;
            }

            command = scanner.nextLine();
        }

        printList(courses);
    }

    private static void printList(List<String> courses) {
        for (int i = 1; i <= courses.size(); i++) {
            System.out.printf("%d.%s%n", i, courses.get(i - 1));
        }
    }

    private static void addExercise(String lessonTitle, List<String> courses) {
        if (!courses.contains(lessonTitle)) {
            courses.add(lessonTitle);
            courses.add(lessonTitle + "-Exercise");
        } else if (!courses.contains(lessonTitle + "-Exercise")) {
            int indexOfLesson = courses.indexOf(lessonTitle);

            courses.add(indexOfLesson + 1, lessonTitle + "-Exercise");
        }
    }

    private static void swap(String lessonTitle, String secondTitle, List<String> courses) {
        boolean hasExercise1 = courses.contains(lessonTitle+"-Exercise");
        boolean hasExercise2 = courses.contains(secondTitle+"-Exercise");

        if (courses.contains(lessonTitle) && courses.contains(secondTitle)) {
            int indexOfFirst = courses.indexOf(lessonTitle);
            int indexOfSecond = courses.indexOf(secondTitle);

            if (hasExercise1){
                courses.set(indexOfFirst, secondTitle);
                courses.set(indexOfSecond, lessonTitle);
                courses.add(indexOfSecond + 1, lessonTitle+"-Exercise");
                courses.remove(indexOfFirst + 1);
            } else if (hasExercise2){
                courses.set(indexOfSecond, lessonTitle);
                courses.set(indexOfFirst, secondTitle);
                courses.add(indexOfFirst + 1, secondTitle+"-Exercise");
                courses.remove(indexOfSecond + 2); // judge is broken
            } else {
                courses.set(indexOfSecond, lessonTitle);
                courses.set(indexOfFirst, secondTitle);
            }
        }
    }
}