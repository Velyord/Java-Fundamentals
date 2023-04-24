/*
Task:
    Define a class Employee with the following information:
    name, salary, position, department, email, and age. The
    name, salary, position, and department are mandatory, while the rest are optional.
    Your task is to write a program that takes N lines of employees
    from the console and calculates the department with
    the highest average salary, and prints for each employee in that department
    his name, salary, email, and age –
    sorted by salary in descending order. If an employee doesn't have an email –
    in place of that field, you should print
    "n/a" instead, if he doesn't have an age – print "-1" instead.
    The salary should be printed in two decimal places
    after the separator.
Hint:
    you can define a Department class with a list of employees.
Examples:
    4
    Peter 120.00 Dev Development peter@abv.bg 28
    Todor 333.33 Manager Marketing 33
    Itan 840.20 ProjectLeader Development itan@itan.com
    George 0.20 Freeloader Nowhere 18
    ->
    Highest Average Salary: Development
    Itan 840.20 itan@itan.com -1
    Peter 120.00 peter@abv.bg 28
    6
    Stan 496.37 Temp Coding stan@yahoo.com
    Yana 610.13 Manager Sales
    Todor 609.99 Manager Sales todor@abv.bg 44
    Venecia 0.02 Director BeerDrinking beer@beer.br 23
    Andrey 700.00 Director Coding
    Popeye 13.3333 Sailor SpinachGroup popeye@pop.ey
    ->
    Highest Average Salary: Sales
    Yana 610.13 n/a -1
    Todor 609.99 todor@abv.bg 44
 */
package ObjectsAndClasses.MoreExercises;

import java.util.*;

import static java.lang.System.out;

public class CompanyRoster {
    private static class Company {
        private final String name;
        private final String position;
        private final String department;
        private final double salary;
        private final String email;
        private final int age;

        public Company(
                String name,
                String position,
                String department,
                double salary,
                String email,
                int age
        ) {
            this.name = name;
            this.position = position;
            this.department = department;
            this.salary = salary;
            this.email = email;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        public String getEmail() {
            return email;
        }

        public int getAge() {
            return age;
        }
    }

    static Scanner scanner = new Scanner(System.in);

    private static final List<Company> data = new ArrayList<>();

    public static void main(String[] args) {
        populateData();
        printHighestDepartment();
    }

    private static void populateData() {
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            String[] input = scanner.nextLine().split("\\s+");

            String name = input[0];
            String position = input[2];
            double salary = Double.parseDouble(input[1]);
            String department = input[3];
            String email = "n/a";
            int age = -1;

            if (input.length > 4) {
                if (input[4].contains("@")) {
                    email = input[4];
                } else {
                    age = Integer.parseInt(input[4]);
                }
            }

            if (input.length > 5) {
                age = Integer.parseInt(input[5]);
            }

            Company company = new Company(name, position, department, salary, email, age);
            data.add(company);
        }
    }

    private static void printHighestDepartment() {
        String department = getHighestAverageSalaryDepartment();

        out.printf("Highest Average Salary: %s%n", department);

        data.stream()
            .filter(company ->
                company.getDepartment().equals(department)
            ).sorted((company1, company2) ->
                Double.compare(company2.getSalary(), company1.getSalary())
            ).forEach(company ->
                out.printf(
                    "%s %.2f %s %d%n",
                    company.getName(),
                    company.getSalary(),
                    company.getEmail(),
                    company.getAge()
                )
            );
    }

    private static String getHighestAverageSalaryDepartment() {
        String highestAvgSalaryForDepartment = "";
        double highestAvgSalary = 0;

        Map<String, Integer> departmentsMap = new LinkedHashMap<>();

        for (Company company : data) {
            if (!departmentsMap.containsKey(company.getDepartment())) {
                departmentsMap.put(company.getDepartment(), 1);
            } else {
                int currentCount = departmentsMap.get(company.getDepartment());
                departmentsMap.put(company.getDepartment(), currentCount + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : departmentsMap.entrySet()) {
            String department = entry.getKey();
            int count = entry.getValue();
            double sumOfSalaries = 0;
            double avgSalary;

            for (Company company : data) {
                if (company.getDepartment().equals(department)) {
                    sumOfSalaries += company.getSalary();
                }
            }

            avgSalary = sumOfSalaries / count;

            if (avgSalary > highestAvgSalary) {
                highestAvgSalary = avgSalary;
                highestAvgSalaryForDepartment = department;
            }
        }

        return  highestAvgSalaryForDepartment;
    }
}