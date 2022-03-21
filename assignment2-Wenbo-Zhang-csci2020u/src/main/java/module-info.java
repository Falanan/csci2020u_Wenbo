module ca.ontariotechu.assignment2wenbozhangcsci2020u {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;
    requires java.xml;


    opens ca.ontariotechu.assignment2wenbozhangcsci2020u to javafx.fxml;
    exports ca.ontariotechu.assignment2wenbozhangcsci2020u;
}