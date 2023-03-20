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
import static java.lang.System.out;

public class SoftUniCoursePlanning {
    static Scanner scanner = new Scanner(in);
    private static final List<String> schedule = new ArrayList<>(List.of(setValue().split(", ")));

    public static void main(String[] args) {
        String commands = setValue();

        while (!commands.contains("course start")) {
            String[] commandParts = commands.split(":");
            String action = commandParts[0];
            String lessonTitle = commandParts[1];

            switch (action) {
                case "Add":
                    if (!schedule.contains(lessonTitle)) {
                        schedule.add(lessonTitle);
                    }
                    break;
                case "Insert":
                    int position = Integer.parseInt(commandParts[2]);

                    if (!schedule.contains(lessonTitle)) {
                        schedule.add(position, lessonTitle);
                    }
                    break;
                case "Remove":
                    schedule.removeIf(lesson -> lesson.equals(lessonTitle));
                    schedule.removeIf(lesson -> lesson.equals(lessonTitle + "-Exercise"));
                    break;
                case "Swap":
                    String otherLessonTitle = commandParts[2];
                    swapPlaces(lessonTitle, otherLessonTitle);
                    break;
                case "Exercise":
                    addExercise(lessonTitle);
                    break;
                default:
                    out.println("Такава команда несъщестува. Пробвайте пак!");
            }

            commands = setValue();
        }

        for (int i = 0; i < schedule.size(); i++) {
            out.printf("%d.%s\n", i + 1, schedule.get(i));
        }
    }

    private static void addExercise(String lessonTitle) {
        String lessonExercise = lessonTitle + "-Exercise";

        if (schedule.contains(lessonTitle)) {
            int indexOfLesson = schedule.indexOf(lessonTitle);

            if (indexOfLesson + 1 < schedule.size()) {
                if (!schedule.get(indexOfLesson + 1).equals(lessonExercise)) {
                    schedule.add(indexOfLesson + 1, lessonExercise);
                }
            } else {
                schedule.add(lessonExercise);
            }
        } else {
            schedule.add(lessonTitle);
            schedule.add(lessonExercise);
        }
    }

    private static void swapPlaces(String lessonTitle, String otherLessonTitle) {
        String lessonExercise = lessonTitle + "-Exercise";
        String otherLessonExercise = otherLessonTitle + "-Exercise";
        boolean lessonHasExercise = false;
        boolean otherLessonHasExercise = false;

        if (schedule.contains(lessonTitle) && schedule.contains(otherLessonTitle)) {
            if (schedule.contains(lessonExercise)) {
                lessonHasExercise = true;
            }

            if (schedule.contains(otherLessonExercise)) {
                otherLessonHasExercise = true;
            }

            schedule.set(schedule.indexOf(lessonTitle), otherLessonTitle);
            schedule.set(schedule.lastIndexOf(otherLessonTitle), lessonTitle);

            if (lessonHasExercise) {
                addExerciseNextToLesson(lessonExercise, lessonTitle);
            }

            if (otherLessonHasExercise) {
                addExerciseNextToLesson(otherLessonExercise, otherLessonTitle);
            }
        }
    }

    private static void addExerciseNextToLesson(String exercise, String lesson) {
        schedule.remove(exercise);
        schedule.add(schedule.indexOf(lesson) + 1, exercise);
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
        String specialChars = "!#$%&'()*+./;<=>?@[]^_`{|}"; // може да се променят забранените символи
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
}
