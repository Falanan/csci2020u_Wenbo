module ontariotechu.lab10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ontariotechu.lab10 to javafx.fxml;
    exports ontariotechu.lab10;
}