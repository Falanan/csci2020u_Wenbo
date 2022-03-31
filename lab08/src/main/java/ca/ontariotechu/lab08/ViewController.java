package ca.ontariotechu.lab08;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
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
    @FXML
    private MenuItem newFile;
    @FXML
    private MenuItem openFile;
    @FXML
    private MenuItem saveFile;
    @FXML
    private MenuItem saveAs;
    @FXML
    private MenuItem exit;

    private String fileName = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sid.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        assignments.setCellValueFactory(new PropertyValueFactory<>("Assignments"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("Midterm"));
        finalExam.setCellValueFactory(new PropertyValueFactory<>("FinalExam"));
        finalMark.setCellValueFactory(new PropertyValueFactory<>("FinalMark"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));

//        tableView.setItems(DataSource.getAllMarks());
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

    @FXML
    protected void setNewFile(){
        ObservableList<StudentRecord> dataSource = tableView.getItems();
//        for (int i=0; i< dataSource.size(); i++){
//            dataSource.remove(0);
//        }
        if (dataSource.size() > 0) {
            dataSource.subList(0, dataSource.size()).clear();
        }
    }

    private void saveFile(String fileName) throws Exception {
        File filename = new File(fileName);
        FileWriter fw = new FileWriter(filename);
        BufferedWriter writer = new BufferedWriter(fw);
        String header = "SID,Assignments,Midterm,Final Exam";
        writer.write(header);
        writer.newLine();

        ObservableList<StudentRecord> dataSource = tableView.getItems();
//        for (int i=0; i<dataSource.size(); i++){
//            StringBuilder data = new StringBuilder();
//            data.append(dataSource.get(i).getStudentID()).append(",");
//            data.append(dataSource.get(i).getAssignments()).append(",");
//            data.append(dataSource.get(i).getMidterm()).append(",");
//            data.append(dataSource.get(i).getFinalExam()).append(",");
//            writer.write(String.valueOf(data));
//        }

        for (StudentRecord studentRecord : dataSource) {
            String data = studentRecord.getStudentID() + "," +
                    studentRecord.getAssignments() + "," +
                    studentRecord.getMidterm() + "," +
                    studentRecord.getFinalExam();
            writer.write(data);
            writer.newLine();
        }
        writer.close();
    }

    @FXML
    protected void setSaveFile() throws Exception {
        if (this.fileName == null){
            this.setSaveAs();
        }
        else {
            saveFile(this.fileName);
        }
    }

    @FXML
    protected void setOpenFile() throws Exception {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File f = fc.showOpenDialog(null);
        if (f != null){
            this.fileName = f.getAbsolutePath();
            this.setNewFile();
//            File readFile = new File(f.getAbsolutePath());
            FileReader fr = new FileReader(f);
            BufferedReader reader = new BufferedReader(fr);
            String header = reader.readLine();
            ObservableList<StudentRecord> dataSource = tableView.getItems();
            while (true){
                String nextLine = reader.readLine();
                if (nextLine == null)
                    break;
                String[] data = nextLine.split(",");
                StudentRecord studentRecord = new StudentRecord(data[0],
                        Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[1]));
                dataSource.add(studentRecord);
            }
            fr.close();
        }
    }

    @FXML
    protected void setExit(){
        System.exit(0);
    }

    @FXML
    protected void setSaveAs() throws Exception {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File f = fc.showOpenDialog(null);
        if (f!=null){
            this.fileName = f.getAbsolutePath();
            this.saveFile(f.getAbsolutePath());
        }
    }


}