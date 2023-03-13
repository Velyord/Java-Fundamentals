/*
Task:
    You will receive an unknown number of lines.
    On each line, you will receive an array with 3 elements.
    The first element will be a string and represents the name of the
    person. The second element will be a string and will represent the
    ID of the person. The last element will be an integer which
    represents the age of the person.
    When you receive the command "End", stop taking input and print
    all the people, ordered by age.
Examples:
    George 123456 20
    Peter 78911 15
    Stephan 524244 10
    End
    ->
    Stephan with ID: 524244 is 10 years old.
    Peter with ID: 78911 is 15 years old.
    George with ID: 123456 is 20 years old.

    Cindy 88611 2
    Kaloyan 13967 3
    Simona 53316 11
    Gil 31837 72
    End
    ->
    Cindy with ID: 88611 is 2 years old.
    Kaloyan with ID: 13967 is 3 years old.
    Simona with ID: 53316 is 11 years old.
    Gil with ID: 31837 is 72 years old.
*/
package ObjectsAndClasses.Exercise.OrderByAge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ObjectsAndClasses.Exercise.OrderByAge.StringValidator.setText;
import static java.lang.System.out;

public class Main {
    static List<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        addPeople();
        personList.sort(Comparator.comparingInt(Person::getAge));
        printPersonList();
    }

    private static void addPeople() {
        String input = setText();

        while (!input.equals("End")) {
            addPerson(input);

            input = setText();
        }
    }

    private static void addPerson(String input) {
        String[] personInfo = input.split(" ");
        String personName = personInfo[0];
        String personID = personInfo[1];
        int personAge = Integer.parseInt(personInfo[2]);
        Person person = new Person(personName, personID, personAge);
        personList.add(person);
    }

    private static void printPersonList() {
        for (Person person : personList) {
            out.printf(
                    "%s with ID: %s is %d years old.\n",
                    person.getName(), person.getID(), person.getAge()
            );
        }
    }
}