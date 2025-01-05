module com.example.cmsc315p2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cmsc315p2 to javafx.fxml;
    exports com.example.cmsc315p2;
}