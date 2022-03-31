module ca.ontariotechu.lab08 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.ontariotechu.lab08 to javafx.fxml;
    exports ca.ontariotechu.lab08;
}