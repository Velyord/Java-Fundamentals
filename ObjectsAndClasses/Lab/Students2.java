/*
Task:
    Use the class from the previous problem.
    If you receive a student who already exists (first name and last name should be unique),
    overwrite the information.
Examples:
    John Smith 15 Sofia
    John Smith 16 Sofia
    Linda Bridge 17 Sofia
    Simon Stone 12 Varna
    end
    Sofia
    ->
    John Smith is 16 years old
    Linda Bridge is 17 years old

    J S 3 S
    Peter Ivanov 14 P
    P J 104 S
    J P 61 S
    Simon Stone 12 Varna
    Simon Sone 12 Varna
    end
    Varna
    ->
    Simon Stone is 12 years old
    Simon Sone is 12 years old
*/
package ObjectsAndClasses.Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Students2 {
    static Scanner scanner = new Scanner(in);

    private static class Student {
        private String firstName;
        private String lastName;
        private short age;
        private String homeTown;

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

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public void setAge(short age) {
            this.age = age;
        }
        public void setHomeTown(String homeTown) {
            this.homeTown = homeTown;
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
            String homeTown = data[3];

            if (isStudentExisting(studentsList, firstName, lastName)) {
                Student student = getStudent(studentsList, firstName, lastName);

                student.setAge(age);
                student.setHomeTown(homeTown);
            } else {
                Student student = new Student(firstName, lastName, age, homeTown);

                studentsList.add(student);
            }

            input = setValue();
        }

        String city = setValue();

        for (Student student : studentsList) {
            if (student.getHomeTown().equals(city)) {
                out.printf(
                        "%s %s is %d years old\n",
                        student.getFirstName(), student.getLastName(), student.getAge()
                );
            }
        }
    }

    private static Student getStudent(List<Student> studentsList, String firstName, String lastName) {
        Student existingStudent = null;

        for (Student student : studentsList) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                existingStudent = student;
            }
        }

        return existingStudent;
    }

    private static boolean isStudentExisting(
            List<Student> studentsList,
            String firstName,
            String lastName
    ) {
        for (Student student : studentsList) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                return true;
            }
        }

        return false;
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
        String specialChars = "!#$%&'()*+,./:;<=>?@[]^_`{|}"; // може да се променят забранените символи
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
