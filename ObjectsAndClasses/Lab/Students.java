/*
Task:
    Define a class Student, which holds the following information about students:
    first name, last name, age, and hometown.
    Read the list of students until you receive the "end" command.
    After that, you will receive a city name.
    Print only students which are from the given city, in the following format:
    "{firstName} {lastName} is {age} years old".
Examples:
    John Smith 15 Sofia
    Peter Ivanov 14 Plovdiv
    Linda Bridge 16 Sofia
    Simon Stone 12 Varna
    end
    Sofia
    ->
    John Smith is 15 years old
    Linda Bridge is 16 years old
    Anthony Taylor 15 Chicago
    David Anderson 16 Washington
    Jack Lewis 14 Chicago
    David Lee 14 Chicago
    end
    Chicago
    ->
    Anthony Taylor is 15 years old
    Jack Lewis is 14 years old
    David Lee is 14 years old
*/
package ObjectsAndClasses.Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Students {
    static Scanner scanner = new Scanner(in);

    static class Student {
        private final String firstName;
        private final String lastName;
        private final short age;
        private final String homeTown;

        public Student (String firstName, String lastName, short age, String homeTown) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.homeTown = homeTown;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public short getAge() {
            return age;
        }

        public String getHomeTown() {
            return homeTown;
        }
    }

    public static void main(String[] args) {
        String input = setValue();
        List<Student> studentsList = new ArrayList<>();

        while (!input.equals("end")) {
            String[] data = input.split(" ");
            String firstName = data[0];
            String lastName = data[1];
            short age = Short.parseShort(data[2]);
            String hometown = data[3];
            Student student = new Student(firstName, lastName, age, hometown);

            studentsList.add(student);
            input = setValue();
        }

        String city = setValue();

        for (Student student : studentsList) {
            if (student.getHomeTown().equals(city)) {
                out.printf("%s %s is %d years old\n", student.getFirstName(), student.getLastName(), student.getAge());
            }
        }
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
}
