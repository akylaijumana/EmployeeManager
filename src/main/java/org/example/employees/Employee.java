package org.example.employees;

public abstract class Employee {

    protected String name;
    protected String type;

    public Employee(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // Abstract method to be implemented in concrete subclasses
    public abstract double getSalary();

    public abstract void setSalary(double newSalary);
}
