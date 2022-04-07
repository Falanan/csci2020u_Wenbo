module lab09.lab09 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab09.lab09 to javafx.fxml;
    exports lab09.lab09;
}