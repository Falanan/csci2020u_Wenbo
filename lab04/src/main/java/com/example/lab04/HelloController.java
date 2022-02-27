package com.example.lab04;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label displayText;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private DatePicker datePicker;

    @FXML
    protected void handleRegister(){
        // display on the window
        displayText.setText("Username: " + usernameField.getText()
                + "\nPassword: " + passwordField.getText()
                + "\nFull name: " + fullNameField.getText()
                + "\n E-Mail address: " + emailField.getText()
                + "\nPhone number: " + phoneField.getText()
                + "\nDate of birth: " + datePicker.getValue()
        );
        // print values to the console
        System.out.println("Username: " + usernameField.getText()
                + "\nPassword: " + passwordField.getText()
                + "\nFull name: " + fullNameField.getText()
                + "\n E-Mail address: " + emailField.getText()
                + "\nPhone number: " + phoneField.getText()
                + "\nDate of birth: " + datePicker.getValue()
        );

    }
}