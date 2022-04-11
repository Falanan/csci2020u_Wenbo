package ontariotechu.lab10;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{
    @FXML
    private TextArea textArea;
    private Socket socket;

    public ClientHandler(TextArea textArea, Socket socket) {
        this.textArea = textArea;
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = bufferedReader.readLine();
            while (message != null) {
                textArea.appendText(message+"\n");
                message = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
