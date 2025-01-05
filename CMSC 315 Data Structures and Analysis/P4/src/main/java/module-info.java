module com.example.p4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.p4 to javafx.fxml;
    exports com.example.p4;
}