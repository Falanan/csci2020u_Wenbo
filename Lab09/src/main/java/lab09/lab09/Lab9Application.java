package lab09.lab09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lab9Application extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Lab9Application.class.getResource("Lab9-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Lab09");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}