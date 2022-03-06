package com.example.lab05;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TableView<StudentRecord> tableView;
    @FXML
    private TableColumn<StudentRecord, String> sid;
    @FXML
    private TableColumn<StudentRecord, Float> assignments;
    @FXML
    private TableColumn<StudentRecord, Float> midterm;
    @FXML
    private TableColumn<StudentRecord, Float> finalExam;
    @FXML
    private TableColumn<StudentRecord, Float> finalMark;
    @FXML
    private TableColumn<StudentRecord, String> letterGrade;

    // add student components
    @FXML
    private TextField addSid;
    @FXML
    private TextField addAssignments;
    @FXML
    private TextField addMidterm;
    @FXML
    private TextField addFinalExam;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sid.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        assignments.setCellValueFactory(new PropertyValueFactory<>("Assignments"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("Midterm"));
        finalExam.setCellValueFactory(new PropertyValueFactory<>("FinalExam"));
        finalMark.setCellValueFactory(new PropertyValueFactory<>("FinalMark"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));

        tableView.setItems(DataSource.getAllMarks());
    }


    @FXML
    protected void addStudent(ActionEvent event){
        ObservableList<StudentRecord> dataSource = tableView.getItems();
        dataSource.add(new StudentRecord(addSid.getText(), Float.parseFloat(addAssignments.getText()),
                Float.parseFloat(addMidterm.getText()), Float.parseFloat(addFinalExam.getText())));

        addSid.setText("");
        addAssignments.setText("");
        addMidterm.setText("");
        addFinalExam.setText("");
    }

}