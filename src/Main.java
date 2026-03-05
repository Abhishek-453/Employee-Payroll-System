import java.util.ArrayList;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public int getId() { return id; }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [Name=" + name + ", ID=" + id + ", Salary=" + calculateSalary() + "]";
    }
}



    class FullTimeEmployee extends Employee {
        private double monthlySalary;

        public FullTimeEmployee(String name, int id, double monthlySalary) {
            super(name, id);
            this.monthlySalary = monthlySalary;
        }

        @Override
        public double calculateSalary() {
            return monthlySalary;
        }
    }

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}




class PayrollSystem {
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        employeeList.removeIf(emp -> emp.getId() == id);
    }

    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();

        // Adding Employees
        payrollSystem.addEmployee(new FullTimeEmployee("Abhishek", 1, 75000.0));
        payrollSystem.addEmployee(new PartTimeEmployee("Rahul", 2, 40, 500.0));

        System.out.println("--- Current Payroll System Details ---");
        payrollSystem.displayEmployees();

        System.out.println("\nRemoving Employee with ID 1...");
        payrollSystem.removeEmployee(101);

        System.out.println("\n--- Updated Payroll System Details ---");
        payrollSystem.displayEmployees();
    }
}