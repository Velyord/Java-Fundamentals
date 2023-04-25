package ObjectsAndClasses.MoreExercises.CompanyRoster;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ObjectsAndClasses.MoreExercises.CompanyRoster.NumberValidator.setNumber;
import static ObjectsAndClasses.MoreExercises.CompanyRoster.PersonalUtils.*;
import static java.lang.System.out;

public class Main {
    private static final List<Company> data = new ArrayList<>();

    public static void main(String[] args) {
        populateData();
        printHighestDepartment();
    }

    private static void populateData() {
        int n = setNumber();

        for (int i = 1; i <= n; i++) {
            String[] input = userInput().split("\\s+");

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
            .filter(company -> company.getDepartment().equals(department))
            .sorted((company1, company2) -> Double.compare(company2.getSalary(), company1.getSalary()))
            .forEach(company ->
                out.printf(
                    "%s %.2f %s %d\n",
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