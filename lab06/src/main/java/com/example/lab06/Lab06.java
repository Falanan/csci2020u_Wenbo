package com.example.lab06;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Lab06 extends Application {

    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };


    @Override
    public void start(Stage stage) {
        BarChart<String, Number> barChart = genBarChart();
        PieChart pieChart = genPieChart();

        FlowPane root = new FlowPane();
        root.getChildren().addAll(barChart, pieChart);
        Scene scene = new Scene(root, 1000,600);
        stage.setTitle("Lab06");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


    private PieChart genPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i=0; i<ageGroups.length; i++){
            pieChartData.add(new PieChart.Data(ageGroups[i], purchasesByAgeGroup[i]));
        }

        final PieChart chart = new PieChart(pieChartData);
        int i = 0;
        for (PieChart.Data data : pieChartData){
            data.getNode().setStyle("-fx-pie-color: " + pieColours[i % pieColours.length].toString().replace("0x", "#") + ";");
            i++;
        }

        chart.setLegendVisible(false);
        return chart;
    }

    private BarChart<String, Number> genBarChart(){
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        xAxis.setLabel("Years");
        yAxis.setLabel("Values");

        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("average commercial price");
        for (int i=0; i<avgCommercialPricesByYear.length; i++){
            series1.getData().add(new XYChart.Data(""+i, avgCommercialPricesByYear[i]));
        }

        XYChart.Series series2 = new XYChart.Series<>();
        series2.setName("average housing price");
        for (int i=0; i<avgHousingPricesByYear.length; i++){
            series2.getData().add(new XYChart.Data(""+i, avgHousingPricesByYear[i]));
        }
        barChart.getData().addAll(series2, series1);

        return barChart;
    }


}
