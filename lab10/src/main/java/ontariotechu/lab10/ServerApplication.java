

package ontariotechu.lab10;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

        import java.io.IOException;

public class ServerApplication extends Application {
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

        //Server
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("server.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        stage.setTitle("Lab10 Server");
        stage.setScene(scene);
        stage.show();

    }



    public static void main(String[] args) {
        launch();
    }

}