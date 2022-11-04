module com.example.studentregapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.studentregapp to javafx.fxml;
    exports com.example.studentregapp;
}