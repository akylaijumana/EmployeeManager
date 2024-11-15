package org.example.employees;

public class Contractor extends Employee {

    private double hourlyRate;
    private double maxHours;

    public Contractor(String name, double hourlyRate, double maxHours) {
        super(name, "Contractor");
        this.hourlyRate = hourlyRate;
        this.maxHours = maxHours;
    }

    @Override
    public double getSalary() {
        return hourlyRate * maxHours;
    }

    @Override
    public void setSalary(double newSalary) {
        return;
    }
}
