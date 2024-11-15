package org.example.employees;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    @FXML private TextField nameField;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextField hourlyRateField;
    @FXML private TextField hoursWorkedField;
    @FXML private TextField annualSalaryField;
    @FXML private TextField maxHoursField;
    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, String> nameColumn;
    @FXML private TableColumn<Employee, String> typeColumn;
    @FXML private TableColumn<Employee, Double> salaryColumn;

    private List<Employee> employees = new ArrayList<>();

    @FXML
    public void initialize() {
        // Set up the table columns
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        salaryColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSalary()).asObject());

        // Initially hide all fields
        hourlyRateField.setVisible(false);
        hoursWorkedField.setVisible(false);
        annualSalaryField.setVisible(false);
        maxHoursField.setVisible(false);

        // Populate ComboBox with employee types
        typeComboBox.getItems().setAll("Full-time", "Part-time", "Contractor");
    }

    @FXML
    public void handleTypeSelection() {
        String selectedType = typeComboBox.getSelectionModel().getSelectedItem();

        // Reset visibility of all fields before adjusting based on type
        hourlyRateField.setVisible(false);
        hoursWorkedField.setVisible(false);
        annualSalaryField.setVisible(false);
        maxHoursField.setVisible(false);

        // Show/hide fields based on the employee type selected
        if ("Contractor".equals(selectedType)) {
            hourlyRateField.setVisible(true);  // Hourly rate is always visible for contractors
            maxHoursField.setVisible(true);    // Max hours should be shown for contractors
        } else if ("Full-time".equals(selectedType)) {
            annualSalaryField.setVisible(true); // Annual salary should be visible for full-time employees
        } else if ("Part-time".equals(selectedType)) {
            hourlyRateField.setVisible(true);   // Hourly rate is visible for part-time employees
            hoursWorkedField.setVisible(true);  // Hours worked field should also be visible for part-time employees
        }
    }

    @FXML
    public void addEmployee() {
        String name = nameField.getText();
        String type = typeComboBox.getSelectionModel().getSelectedItem();

        if (type == null || name.isEmpty()) {
            showAlert("Please enter a name and select a type.");
            return;
        }

        try {
            Employee employee = null;

            // Create employee based on type
            if ("Full-time".equals(type)) {
                double annualSalary = Double.parseDouble(annualSalaryField.getText());
                employee = new FullTimeEmployee(name, annualSalary);
            } else if ("Part-time".equals(type)) {
                double hourlyRate = Double.parseDouble(hourlyRateField.getText());
                double hoursWorked = Double.parseDouble(hoursWorkedField.getText());
                employee = new PartTimeEmployee(name, hourlyRate, hoursWorked);
            } else if ("Contractor".equals(type)) {
                double hourlyRate = Double.parseDouble(hourlyRateField.getText());
                double maxHours = Double.parseDouble(maxHoursField.getText());
                employee = new Contractor(name, hourlyRate, maxHours);
            }

            // Add the employee to the list and update the table
            if (employee != null) {
                employees.add(employee);
                employeeTable.getItems().setAll(employees);
            }

            // Clear the input fields after adding employee
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Please enter valid numbers for salary, hourly rate, or hours.");
        }
    }

    @FXML
    public void updateSalaries() {
        // Assume you have a list of employees
        for (Employee employee : employees) {
            // Example logic: Increase each employee's salary by 10%
            double newSalary = employee.getSalary() * 1.10;
            employee.setSalary(newSalary);
        }
        System.out.println("Salaries updated!");
        // Update the TableView
        employeeTable.getItems().setAll(employees);
    }

    @FXML
    public void removeEmployee() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employees.remove(selectedEmployee);
            employeeTable.getItems().remove(selectedEmployee);
        }
    }

    private void clearFields() {
        nameField.clear();
        typeComboBox.setValue(null);
        hourlyRateField.clear();
        hoursWorkedField.clear();
        annualSalaryField.clear();
        maxHoursField.clear();

        // Reset visibility for the next employee input
        handleTypeSelection();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

