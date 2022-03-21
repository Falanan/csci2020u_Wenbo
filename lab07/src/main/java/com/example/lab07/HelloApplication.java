package com.example.lab07;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Map<String, Integer> record= readFile();
        String[] warnings = {"FLASH FLOOD", "TORNADO", "SEVERE THUNDERSTORM", "SPECIAL MARINE"};
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i=0; i<4; i++){
            pieChartData.add(new PieChart.Data(warnings[i], record.get(warnings[i])));
        }
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setLegendSide(Side.LEFT);
        FlowPane root = new FlowPane();
        root.getChildren().addAll(pieChart);
        Scene scene = new Scene(root, 500,500);
        stage.setTitle("Lab07");
        stage.setScene(scene);
        stage.show();


    }

    public Map<String, Integer> readFile() throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();
        System.out.println(csvFormat.toString());

        FileReader fileReader = new FileReader("src/main/resources/weatherwarnings-2015.csv");
        CSVParser csvParser = new CSVParser(fileReader, csvFormat);
        Map<String, Integer> count = new HashMap<>();
        for (CSVRecord csvRecord : csvParser){
            String L6 = csvRecord.get("L6");
//            System.out.println(L6);
            count.merge(L6, 1, Integer::sum);
//            if (count.get(L6) == null){
//                count.put(L6, 1);
//            }else {
//                count.put(L6, count.get(L6)+1);
//            }
        }
//        System.out.println(count.values());
        return count;
    }

    public static void main(String[] args) {
        launch();
    }
}