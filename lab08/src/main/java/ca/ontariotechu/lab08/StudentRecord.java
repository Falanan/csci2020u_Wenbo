package ca.ontariotechu.lab08;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentRecord {
//    String studentID;
//    float midterm;
//    float assignments;
//    float finalExam;
//    float finalMark;
//    char letterGrade;
    SimpleStringProperty studentID;
    SimpleFloatProperty midterm;
    SimpleFloatProperty assignments;
    SimpleFloatProperty finalExam;
    SimpleFloatProperty finalMark;
    SimpleStringProperty letterGrade;


    StudentRecord(String studentID, float assignments, float midterm, float finalExam){
        this.studentID = new SimpleStringProperty(studentID);
        this.midterm = new SimpleFloatProperty(midterm);
        this.assignments = new SimpleFloatProperty(assignments);
        this.finalExam = new SimpleFloatProperty(finalExam);

        this.finalMark = new SimpleFloatProperty ((float) (assignments * 0.2 + midterm * 0.3 + finalExam * 0.5));

        if(this.finalExam.get() <= 100 && this.finalExam.get() >= 80){
            this.letterGrade = new SimpleStringProperty("A");
        }else if (this.finalExam.get() <= 79 && this.finalExam.get() >= 70){
            this.letterGrade = new SimpleStringProperty("B");
        }else if (this.finalExam.get() <= 69 && this.finalExam.get() >= 60){
            this.letterGrade = new SimpleStringProperty("C");
        }else if (this.finalExam.get() <= 59 && this.finalExam.get() >= 50){
            this.letterGrade = new SimpleStringProperty("D");
        }else {
            this.letterGrade = new SimpleStringProperty("F");
        }
    }


    public String getStudentID() {
        return studentID.get();
    }

    public SimpleStringProperty studentIDProperty() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID.set(studentID);
    }

    public float getMidterm() {
        return midterm.get();
    }

    public SimpleFloatProperty midtermProperty() {
        return midterm;
    }

    public void setMidterm(float midterm) {
        this.midterm.set(midterm);
    }

    public float getAssignments() {
        return assignments.get();
    }

    public SimpleFloatProperty assignmentsProperty() {
        return assignments;
    }

    public void setAssignments(float assignments) {
        this.assignments.set(assignments);
    }

    public float getFinalExam() {
        return finalExam.get();
    }

    public SimpleFloatProperty finalExamProperty() {
        return finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam.set(finalExam);
    }

    public float getFinalMark() {
        return finalMark.get();
    }

    public SimpleFloatProperty finalMarkProperty() {
        return finalMark;
    }

    public void setFinalMark(float finalMark) {
        this.finalMark.set(finalMark);
    }

    public String getLetterGrade() {
        return letterGrade.get();
    }

    public SimpleStringProperty letterGradeProperty() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade.set(letterGrade);
    }
}
