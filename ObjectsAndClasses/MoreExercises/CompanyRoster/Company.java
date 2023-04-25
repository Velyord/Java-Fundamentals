package ObjectsAndClasses.MoreExercises.CompanyRoster;

public class Company {
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
