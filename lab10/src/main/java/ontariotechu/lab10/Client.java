package ontariotechu.lab10;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Client implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private TextField message;

    private Socket socket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("127.0.0.1", 1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendButton() throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        String content = userName.getText() + " : " + message.getText() + "\n";
        writer.println(content);
        writer.flush();
        message.clear();
    }

    @FXML
    public void exitButton() throws IOException {
        socket.close();
        System.exit(0);
    }



}
