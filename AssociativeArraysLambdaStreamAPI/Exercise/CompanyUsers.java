/*
Task:
    Write a program which keeps the information about companies and their employees.
    You will receive company names and an employees' id until you receive the "End" command.
    Add each employee to the given company.
    Keep in mind that a company cannot have two employees with the same id.
    Print the company name and each employee's id in the following format:
    "{company_name}
    -- {id1}
    -- {id2}
    …
    -- {idN}"
Input / Constraints:
    • Until you receive "End", the input come in the format: "{companyName} -> {employeeId}".
    • The input always will be valid.
Examples:
    SoftUni -> AA12345
    SoftUni -> BB12345
    Microsoft -> CC12345
    HP -> BB12345
    End
    ->
    SoftUni
    -- AA12345
    -- BB12345
    Microsoft
    -- CC12345
    HP
    -- BB12345

    SoftUni -> AA12345
    SoftUni -> CC12344
    Lenovo -> XX23456
    SoftUni -> AA12345
    Movement -> DD11111
    End
    ->
    SoftUni
    -- AA12345
    -- CC12344
    Lenovo
    -- XX23456
    Movement
    -- DD11111
 */
package AssociativeArraysLambdaStreamAPI.Exercise;

import java.util.*;

import static java.lang.System.out;

public class CompanyUsers {
    static Scanner scanner = new Scanner(System.in);
    private static final Map<String, List<String>> data = new LinkedHashMap<>();

    public static void main(String[] args) {
        fillData();
        displayData();
    }

    private static void fillData() {
        String input = setText();

        while(!input.equals("End")) {
            boolean employeeExists = false;
            String[] inputInfo = input.split(" -> ");
            String company = inputInfo[0];
            String employeeID = inputInfo[1];

            if (data.containsKey(company)) {
                for (String employee : data.get(company)) {
                    if (employee.equals(employeeID)) {
                        employeeExists = true;
                        break;
                    }
                }

                if (!employeeExists) {
                    data.get(company).add(employeeID);
                }
            } else {
                List<String> employeeIDList = new ArrayList<>();
                employeeIDList.add(employeeID);
                data.put(company, employeeIDList);
            }

            input = setText();
        }
    }

    private static void displayData() {
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            out.printf("%s\n", entry.getKey());
            entry.getValue()
                    .stream()
                    .map(s -> "-- " + s)
                    .forEach(out::println);
        }
    }

    private static final String specialChars = "!#$%&'()*+./;<=?@[]^_`{|}:"; // 0123456789 abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ -

    private static int stringCount = 0; // при въвеждане на низ, броячът нараства

    private static String stringValue;

    public static String setText() { // метод за откриване на грешни в низ от потребителя
        stringValue = scanner.nextLine();

        if (!hasValidChars() || !doesFollowTemplate()) { // ако има забранени символи или не следва задените шаблони, низът на потребителя не се приема и трябва да се въведе нов
            return setText();
        } else {
            return stringValue;
        }
    }

    private static boolean hasValidChars() { // хващане на специални/забранени символи
        boolean isSpecialChar = false;
        char specialChar = ' ';

        for (int i = 0; i < (stringValue).length(); i++) { // Търсене на забранен символ чрез сравняване на входните данни с низът със забранени символи
            if (specialChars.contains(Character.toString(stringValue.charAt(i)))) {
                isSpecialChar = true;
                specialChar = stringValue.charAt(i); // открит забранен символ
                break;
            }
        }

        if (isSpecialChar) { // При грешка се показва на потребителя, кой от въведените му символи е забранен
            if (specialChar == ' ') {
                out.println("Разтоянията не са позволени. Пробвайте пак!");
            } else {
                out.printf("%c e неразрешен символ. Пробвайте пак!\n", specialChar);
            }

            return false;
        }

        return true;
    }

    private static boolean doesFollowTemplate() { // подтикване на потребителя да въвежда предварително зададени низове.
        stringCount++;
        String[] requiredStrings;

        // тук се нагласят шаблоните на низовете, ако имат такива.
        if (stringCount == 1) { // на първия низ. Празно ако няма такъв.
            requiredStrings = new String[]{};
        } else if (stringCount == 2) { // на втория низ. Празно ако няма такъв.
            requiredStrings = new String[]{};
        } else { // могат да се добавят и още шаблони преди else. Последния шаблон стои празен.
            requiredStrings = new String[]{};
        }

        if (requiredStrings.length != 0) { // ако е зададен шаблон се изпълнява следния код.
            List<String> requiredList = List.of(requiredStrings); // създава се списък със задължителни входни данни

            if (!requiredList.contains(stringValue)) { // ако се въведе нещо, различно от зададеното в шаблона
                out.println("Моля въведете един от следните избори:");
                out.println(String.join(" | ", requiredStrings)); // показване на потребителя, кои са възможните опции

                stringCount--; // шаблона се наглася отново

                return false;
            }
        }

        return true;
    }
}
