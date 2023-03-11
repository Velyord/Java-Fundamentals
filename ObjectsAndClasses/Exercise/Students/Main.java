/*
Task:
    Write a program that receives n count of students and orders
    them by grade (in descending). Each student should have a
    first name (string), last name (string), and grade (a
    floating-point number).
Input
    •	First-line will be a number n.
    •	Next n lines you will get a student info in the format
    "{first name} {second name} {grade}".
Output
    •	Print each student in the following format "{first name}
    {second name}: {grade}".
Example:
    4
    Lakia Eason 3.90
    Prince Messing 5.49
    Akiko Segers 4.85
    Rocco Erben 6.00
    ->
    Rocco Erben: 6.00
    Prince Messing: 5.49
    Akiko Segers: 4.85
    Lakia Eason: 3.90

    4
    Sydnie Britton 5.79
    Amias Mathews 2.30
    Mora Tod 2.78
    Pete Kendrick 2.61
    ->
    Sydnie Britton: 5.79
    Mora Tod: 2.78
    Pete Kendrick: 2.61
    Amias Mathews: 2.30
*/
package ObjectsAndClasses.Exercise.Students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ObjectsAndClasses.Exercise.Students.NumberValidator.setNumber;
import static ObjectsAndClasses.Exercise.Students.StringValidator.setText;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        int countOfStudents = setNumber();
        List<Student> studentList = new ArrayList<>();

        for (int i = 1; i <= countOfStudents; i++) {
            String[] input = setText().split(" ");
            String firstName = input[0];
            String lastName = input[1];
            double grade = Double.parseDouble(input[2]);
            Student student =
                    new Student(firstName, lastName, grade);

            studentList.add(student);
        }

        studentList.sort(Collections.reverseOrder());

        for (Student student: studentList) {
            out.printf(
                    "%s %s: %.2f\n",
                    student.getFirstName(),
                    student.getLastName(),
                    student.getGrade()
            );
        }
    }
}