module org.example.employees {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.employees to javafx.fxml;
    exports org.example.employees;
}