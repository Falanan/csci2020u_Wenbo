package lab09.lab09;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controller {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
    @FXML
    private Canvas canvas;

    private ArrayList<Double> googlePrice = null;
    private ArrayList<Double> applePrice = null;
    private double max = -999999999;


    @FXML
    public void initialize() throws Exception {
        WebSocketSever google = new WebSocketSever("GOOG");
        google.downloadStockPrices();
        googlePrice = google.getPrices();

        WebSocketSever apple = new WebSocketSever("AAPL");
        apple.downloadStockPrices();
        applePrice = apple.getPrices();

//        double max = -999999999;
        for (Double d : googlePrice){
            if (d > max){
                max = d;
            }
        }

        for (Double d : applePrice){
            if (d > max){
                max = d;
            }
        }


        GraphicsContext context = canvas.getGraphicsContext2D();
        drawPlot(context);
    }

    private void drawPlot(GraphicsContext context){


        context.strokeLine(50, 50, 50, 700-50);
        context.strokeLine(50, 700-50, 800-50, 700-50);
        plotLine(context, applePrice, "Apple");
        plotLine(context, googlePrice, "Google");
    }

    private void plotLine(GraphicsContext context, ArrayList<Double> price, String name){

        double widthPerData = 700.0 / (price.size() + 1);
        double height = 600 / max;

        if (name.equals("Apple"))
            context.setStroke(Color.BLUE);
        else
            context.setStroke(Color.RED);


        double initPos = 50;
        for (int i=0; i<price.size()-1; i++){
            context.strokeLine(initPos, 700-50-(price.get(i))*height, initPos+widthPerData, 700-50-(price.get(i+1))*height);
            initPos += widthPerData;
        }


    }



}