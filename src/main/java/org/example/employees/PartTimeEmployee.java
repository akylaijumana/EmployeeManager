package org.example.employees;

public class PartTimeEmployee extends Employee {

    private double hourlyRate;
    private double hoursWorked;

    public PartTimeEmployee(String name, double hourlyRate, double hoursWorked) {
        super(name, "Part-time");
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double getSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public void setSalary(double newSalary) {
        return;
    }
}
