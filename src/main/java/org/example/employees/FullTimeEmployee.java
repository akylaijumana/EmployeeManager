package org.example.employees;

public class FullTimeEmployee extends Employee {

    private double annualSalary;

    public FullTimeEmployee(String name, double annualSalary) {
        super(name, "Full-time");
        this.annualSalary = annualSalary;
    }

    @Override
    public double getSalary() {
        return annualSalary;
    }

    @Override
    public void setSalary(double newSalary) {
        return;
    }
}
