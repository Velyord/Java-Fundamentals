/*
Task:
    Using the Person class,
    write a program that reads from the console N lines of personal information
    and then prints all people whose age is more than 30 years.
Examples:
    3
    Peter 12
    Sam 31
    Itan 48
    Sam – 31
    Itan - 48

    5
    Niko 33
    Yana 88
    Todor 22
    Lisa 44
    Sam 11
    Niko - 33
    Yana - 88
    Lisa - 44
*/
package ObjectsAndClasses.Exercise.OpinionPoll;

import java.util.ArrayList;
import java.util.List;

import static ObjectsAndClasses.Exercise.OpinionPoll.NumberValidator.setNumber;
import static ObjectsAndClasses.Exercise.OpinionPoll.StringValidator.setText;
import static java.lang.System.out;

public class Main {
    private static final List<Person> peopleList = new ArrayList<>();

    public static void main(String[] args) {
        int people = setNumber();

        for (int i = 1; i <= people; i++) {
            String[] personalInformation = setText().split(" ");

            checkIfUnderage(personalInformation);
        }

        printPeople();
    }

    private static void checkIfUnderage(String[] personalInformation) {
        String personName = personalInformation[0];
        int personAge = Integer.parseInt(personalInformation[1]);

        if (personAge > 30) {
            Person person = new Person(personName, personAge);
            peopleList.add(person);
        }
    }

    private static void printPeople() {
        for (Person person : peopleList) {
            out.printf("%s - %d\n", person.getName(), person.getAge());
        }
    }
}