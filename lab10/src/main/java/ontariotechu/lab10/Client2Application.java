package ontariotechu.lab10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client2Application extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }

    @Override
    public void start(Stage stage) throws IOException {
        //Client 1
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("client.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Lab10 Client2");
        stage.setScene(scene2);
        stage.show();

    }



    public static void main(String[] args) {
        launch();
    }
}