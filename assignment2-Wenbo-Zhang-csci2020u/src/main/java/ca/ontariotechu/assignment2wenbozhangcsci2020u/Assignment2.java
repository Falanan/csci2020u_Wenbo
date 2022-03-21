package ca.ontariotechu.assignment2wenbozhangcsci2020u;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Assignment2 extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        String originalCSVFileName = "src/main/resources/airline_safety.csv";
        String convertedXMLFileName = "src/main/resources/converted_airline_safety.xml";
        String summaryXMLFileName = "src/main/resources/airline_summary_statistic.xml";

        calIncident(readFromCSV(originalCSVFileName), originalCSVFileName);
        convertXML(readFromCSV(originalCSVFileName), convertedXMLFileName);
        calWriteSummary(readFromCSV(originalCSVFileName), summaryXMLFileName);
        BarChart<String, Number> barChart = genBarChart(readFromCSV(originalCSVFileName));
        barChart.setPrefWidth(1000);
        barChart.setPrefHeight(500);
        FlowPane root = new FlowPane();
        root.getChildren().addAll(barChart);
        Scene scene = new Scene(root, 1000,500);
        stage.setTitle("Assignment 2");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This function is used to read the CSV file.
     * In this function, I used BufferedReader to read file,
     *
     * 1. Read the first line as "header", readLine() function should give me a string,
     * split the string with "," and store it in an ArrayList<String>
     *
     * 2. Declare an ArrayList<ArrayList<String>> type variable called "records", add "header" in it,
     * continuing using readLine() function to read from file, also split the string with ","
     * store it into an ArrayList<String> variable, then add it into "records"
     *
     * The way to arrange data (example):
     * ArrayList<ArrayList<String>>[ArrayList<String>["header1","header2"], ArrayList<String>["data1","data2"]]
     *
     * @return ArrayList<ArrayList<String>>
     * @throws IOException
     */
    public ArrayList<ArrayList<String>> readFromCSV(String fileName) throws IOException {
        File file = new File(fileName);
//        FileReader in = new FileReader("src/main/resources/airline_safety.csv");
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);
        // read the first line as the header
        String firstLine = br.readLine();
        ArrayList<String> header = null;
        if (firstLine != null){
            header = new ArrayList<>(List.of(firstLine.split(",")));
        }else {
            return null;
        }

        // read all records
        ArrayList<ArrayList<String>> records = new ArrayList<>();
        records.add(header);
        while (true){
            String s = br.readLine();
            if (s == null){
                break;
            }
            ArrayList<String> record = new ArrayList<>(List.of(s.split(",")));
            records.add(record);
        }
        br.close();
        return records;
    }


    /**
     * This function is used to add a column represent the total number of incidents between 1985 and 2014
     * and calculate the total number.
     *
     * 1. Extract the first element in records and store it into variable "header",
     * cause it represent the header of the csv file. After that, delete the first element in records.
     *
     * 2. Add "total_number_incidents_85_14" to header, this going to represent the total number of incidents between 1985 and 2014
     *
     * 3. calculate the total number and add to every element in records
     *
     * 4. write them to the file using BufferedWriter
     *
     * @param records - data returned by readFromCSV() function
     * @throws Exception
     */
    public void calIncident(ArrayList<ArrayList<String>> records, String fileName) throws Exception {
        if (records == null){
            return;
        }
        // calculate total incidents between 1985-2014 for every record
        ArrayList<String> header = records.get(0);
        records.remove(0);
        header.add("total_number_incidents_85_14");
        for (ArrayList<String> record : records) {
            int total = Integer.parseInt(record.get(header.indexOf("incidents_85_99"))) +
                    Integer.parseInt(record.get(header.indexOf("incidents_00_14")));
            record.add(String.valueOf(total));
        }


        //write to file
//        File test = new File("src/main/resources/test.csv");
        File test = new File(fileName);
        FileWriter fw = new FileWriter(test);
        BufferedWriter writer = new BufferedWriter(fw);
        //write header
        StringBuilder string_header = new StringBuilder();
        for (int i=0; i<header.size();i++){
            if (i == header.size()-1)
                string_header.append(header.get(i));
            else
                string_header.append(header.get(i)).append(",");
        }
        writer.write(string_header.toString());
        writer.newLine();

        // write contents
        for(ArrayList<String> record : records){
            StringBuilder string_record = new StringBuilder();
            for (int i=0; i<record.size();i++){
                if (i == record.size()-1)
                    string_record.append(record.get(i));
                else
                    string_record.append(record.get(i)).append(",");
            }
            writer.write(string_record.toString());
            writer.newLine();
        }
        writer.close();
    }

    /**
     * This function convert the csv file to XML file
     * In this function I used an external tool org.w3c.dom
     * Example of how do I organize the data
     *     <airline>
     *         Aer Lingus
     *         <avail_seat_km_per_week>320906734</avail_seat_km_per_week>
     *         <incidents_85_99>2</incidents_85_99>
     *         <fatal_accidents_85_99>0</fatal_accidents_85_99>
     *         <fatalities_85_99>0</fatalities_85_99>
     *         <incidents_00_14>0</incidents_00_14>
     *         <fatal_accidents_00_14>0</fatal_accidents_00_14>
     *         <fatalities_00_14>0</fatalities_00_14>
     *     </airline>
     *
     * @param records
     * @throws Exception
     */
    public void convertXML(ArrayList<ArrayList<String>> records, String fileName) throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        ArrayList<String> header = records.get(0);
        records.remove(0);

        Element rootElement = doc.createElement("Airline_Safety");
//        Element rootElement = doc.createElement("Summary");
        doc.appendChild(rootElement);

        for (ArrayList<String> record : records){
            Element airline = doc.createElement(header.get(0));
            airline.setTextContent(record.get(0));
            for (int i=1; i<header.size(); i++){
                Element e = doc.createElement(header.get(i));
                e.setTextContent(record.get(i));
                airline.appendChild(e);
            }
            rootElement.appendChild(airline);
        }
        // write dom document to a file
//        File file = new File("src/main/resources/converted_airline_safety.xml");
        File file = new File(fileName);
        FileOutputStream output = new FileOutputStream(file);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);
    }


    /**
     * This function is used to calculate data required in task2, and write to the xml file
     * cal the number required first, then use the same way to write into file as convertXML
     * @param records - result from the readCSV() function to read airline_safety.csv file.
     * @throws Exception
     */
    public void calWriteSummary(ArrayList<ArrayList<String>> records, String fileName) throws Exception {
        ArrayList<String> header = records.get(0);
        records.remove(0);
        ArrayList<double[]> result = new ArrayList<>();
        // calculate min, max, avg for every column
        for (int i=1; i<header.size(); i++){
            double avg = 0;
            double min = 999999999;
            double max = -999999999;
            for (ArrayList<String> rec : records){
                double current_number = Double.parseDouble(rec.get(i));
                if (min > current_number)
                    min = current_number;
                if (max < current_number)
                    max = current_number;
                avg += current_number;
            }
            avg = avg / records.size();
            double[] temp = {min, max, avg};
            result.add(temp);
        }


        // write summary to file
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element rootElement = doc.createElement("Summary");
        doc.appendChild(rootElement);

        /* cause the first column represent the airline name, so it doesn't have min, max, avg
        * so these lines is used to add the summary using the template
        */
        Element first_col_stat = doc.createElement("stat");
        Element first_name = doc.createElement("Name");
        first_name.setTextContent(header.get(0));
        first_col_stat.appendChild(first_name);
        Element first_col_min = doc.createElement("Min");
        first_col_stat.appendChild(first_col_min);
        // max_val
        Element first_col_max = doc.createElement("Max");
        first_col_stat.appendChild(first_col_max);
        // avg
        Element first_col_avg = doc.createElement("Avg");
        first_col_stat.appendChild(first_col_avg);
        rootElement.appendChild(first_col_stat);
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        // add summary for the following column
        for (int i=1; i<header.size(); i++){

            Element stat = doc.createElement("stat");
            // column name
            Element name = doc.createElement("Name");
            name.setTextContent(header.get(i));
            stat.appendChild(name);
            // min_val
            Element min = doc.createElement("Min");
//            min.setTextContent(String.valueOf(result.get(i-1)[0]));
            min.setTextContent(nf.format(result.get(i-1)[0]));
            stat.appendChild(min);
            // max_val
            Element max = doc.createElement("Max");
//            max.setTextContent(String.valueOf(result.get(i-1)[1]));
            max.setTextContent(nf.format(result.get(i-1)[1]));
            stat.appendChild(max);
            // avg
            Element avg = doc.createElement("Avg");
//            avg.setTextContent(String.valueOf(result.get(i-1)[2]));
            avg.setTextContent(nf.format(result.get(i-1)[2]));
            stat.appendChild(avg);
            rootElement.appendChild(stat);
        }

        // separate avg_85_99 column, avg of indecents_85_99
        Element avg_85_99_stat = doc.createElement("stat");
        Element avg_85_99_name = doc.createElement("Name");
        avg_85_99_name.setTextContent("avg_85_99");
        avg_85_99_stat.appendChild(avg_85_99_name);
        Element avg_85_99_min = doc.createElement("Min");
        avg_85_99_stat.appendChild(avg_85_99_min);
        // max_val
        Element avg_85_99_max = doc.createElement("Max");
        avg_85_99_stat.appendChild(avg_85_99_max);
        // avg
        Element avg_85_99_avg = doc.createElement("Avg");
        avg_85_99_avg.setTextContent(nf.format(result.get(1)[2]));
        avg_85_99_stat.appendChild(avg_85_99_avg);
        rootElement.appendChild(avg_85_99_stat);

        // separate avg_85_99 column, avg of indecents_00_14

        Element avg_00_14_stat = doc.createElement("stat");
        Element avg_00_14_name = doc.createElement("Name");
        avg_00_14_name.setTextContent("avg_00_14");
        avg_00_14_stat.appendChild(avg_00_14_name);
        Element avg_00_14_min = doc.createElement("Min");
        avg_00_14_stat.appendChild(avg_00_14_min);
        // max_val
        Element avg_00_14_max = doc.createElement("Max");
        avg_00_14_stat.appendChild(avg_00_14_max);
        // avg
        Element avg_00_14_avg = doc.createElement("Avg");
        avg_00_14_avg.setTextContent(nf.format(result.get(4)[2]));
        avg_00_14_stat.appendChild(avg_00_14_avg);
        rootElement.appendChild(avg_00_14_stat);


        // write dom document to a file
//        File file = new File("src/main/resources/airline_summary_statistic.xml");
        File file = new File(fileName);
        FileOutputStream output = new FileOutputStream(file);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult res = new StreamResult(output);
        transformer.transform(source, res);

    }

    public BarChart<String, Number> genBarChart(ArrayList<ArrayList<String>> records){

        ArrayList<String> header = records.get(0);
        records.remove(0);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Airlines");
        yAxis.setLabel("Numbers");

        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("fatal_accidents_85_99");
        for (int i=0; i<records.size(); i++){
            series1.getData().add(new XYChart.Data<>(records.get(i).get(0), Integer.parseInt(records.get(i).get(header.indexOf("fatal_accidents_85_99")))));
        }

        XYChart.Series series2 = new XYChart.Series<>();
        series2.setName("fatal_accidents_00_14");
        for (int i=0; i<records.size(); i++){
            series2.getData().add(new XYChart.Data<>(records.get(i).get(0), Integer.parseInt(records.get(i).get(header.indexOf("fatal_accidents_00_14")))));
        }

        barChart.getData().addAll(series1, series2);

        return barChart;
    }




    public static void main(String[] args) {
        launch();
    }
}