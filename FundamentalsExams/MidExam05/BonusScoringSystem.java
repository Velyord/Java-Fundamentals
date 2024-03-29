/*
Task:
    Create a program that calculates bonus points for each student enrolled in a course.
    On the first line, you are going to receive the number of the students.
    On the second line, you will receive the total number of lectures in the course.
    The course has an additional bonus, which you will receive on the third line.
    On the following lines, you will be receiving the count of attendances for each student.
    The bonus is calculated with the following formula:
    {total bonus} = {student attendances} / {course lectures} * (5 + {additional bonus})
    Find the student with the maximum bonus and print them, along with his attendances,
    in the following format:
    "Max Bonus: {max bonus points}."
    "The student has attended {student attendances} lectures."
    Round the bonus points at the end to the nearest larger number.
Input / Constrains:
    • On the first line, you are going to receive the number of the students –
    an integer in the range [0…50]
    • On the second line, you will receive the number of the lectures –
    an integer number in the range [0...50].
    • On the third line, you will receive the additional bonus –
    an integer number in the range [0….100].
    • On the following lines, you will be receiving the attendance of each student.
    • There will never be students with equal bonuses.
Output:
    • Print the maximum bonus points and the attendances of the given student,
    rounded to the nearest larger number, scored by a student in this course in the format described above.
Examples:
    5
    25
    30
    12
    19
    24
    16
    20
    ->
    Max Bonus: 34.
    The student has attended 24 lectures.
        First, we receive the number of students enrolled in the course – 5.
        The total count of the lectures is 25, and the additional bonus is 30.
        Then we calculate the bonus of the student with 12 attendances, which is 16.8.
        We continue calculating each of the student's bonuses.
        The one with 24 attendances has the highest bonus – 33.6 (34 rounded),
        so we print the appropriate message on the console.
    10
    30
    14
    8
    23
    27
    28
    15
    17
    25
    26
    5
    18
    ->
    Max Bonus: 18.
    The student has attended 28 lectures.
 */
package FundamentalsExams.MidExam05;

import java.util.Scanner;

public class BonusScoringSystem {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int studentsCount = Integer.parseInt(scanner.nextLine());
        int lecturesCount = Integer.parseInt(scanner.nextLine());
        int additionalBonus = Integer.parseInt(scanner.nextLine());
        int maxBonus = 0;
        int maxAttendances = 0;

        for (int i = 0; i < studentsCount; i++) {
            int attendances = Integer.parseInt(scanner.nextLine());

            int bonus = (int) Math.ceil((double) attendances / lecturesCount * (5 + additionalBonus));

            if (bonus > maxBonus) {
                maxBonus = bonus;
                maxAttendances = attendances;
            }
        }

        System.out.printf("Max Bonus: %d.%n", maxBonus);
        System.out.printf("The student has attended %d lectures.", maxAttendances);
    }
}
