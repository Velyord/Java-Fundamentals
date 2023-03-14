/*
Task:
    Write a program which keeps the information about courses.
    Each course has a name and registered students.
    You will receive the course name and student name until you receive the command "end".
    Check if such a course already exists and if not - add the course.
    Register the user into the course.
    When you do receive the command "end",
    print the courses with their names and total registered users.
    For each contest, print the registered users.
Input:
    • Until you receive "end", the input come in the format: "{courseName} : {studentName}".
    • The product data is always delimited by " : ".
Output:
    • Print information about each course, following the format:
    "{courseName}: {registeredStudents}"
    • Print information about each student, following the format:
    "-- {studentName}"
Examples:
    Programming Fundamentals : John Smith
    Programming Fundamentals : Linda Johnson
    JS Core : Will Wilson
    Java Advanced : Harrison White
    end
    Programming Fundamentals: 2
    -- John Smith
    -- Linda Johnson
    JS Core: 1
    -- Will Wilson
    Java Advanced: 1
    -- Harrison White
    Algorithms : Jay Moore
    Programming Basics : Martin Taylor
    Python Fundamentals : John Anderson
    Python Fundamentals : Andrew Robinson
    Algorithms : Bob Jackson
    Python Fundamentals : Clark Lewis
    end
    Algorithms: 2
    -- Jay Moore
    -- Bob Jackson
    Programming Basics: 1
    -- Martin Taylor
    Python Fundamentals: 3
    -- John Anderson
    -- Andrew Robinson
    -- Clark Lewis
 */
package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.*;
import static java.lang.System.out;

public class Courses {
    static Scanner scanner = new Scanner(System.in);
    private static final Map<String, List<String>> courseData = new LinkedHashMap<>();

    public static void main(String[] args) {
        fillCourses();
        displayCourses();
    }

    private static void fillCourses() {
        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String[] inputInfo = input.split(" : ");
            String course = inputInfo[0];
            String student = inputInfo[1];

            if (courseData.containsKey(course)) {
                courseData.get(course).add(student);
            } else {
                List<String> studentList = new ArrayList<>();
                studentList.add(student);
                courseData.put(course, studentList);
            }

            input = scanner.nextLine();
        }
    }

    private static void displayCourses() {
        for (Map.Entry<String, List<String>> entry : courseData.entrySet()) {
            out.printf("%s: %d\n", entry.getKey(), entry.getValue().size());
            entry.getValue()
                    .stream()
                    .map(s -> "-- " + s)
                    .forEach(out::println);
        }
    }
}
