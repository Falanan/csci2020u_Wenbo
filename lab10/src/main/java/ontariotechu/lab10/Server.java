package ontariotechu.lab10;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Server implements Initializable {

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Thread severThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket sever = new ServerSocket(1234);

                    while (true){
                        Socket socket = sever.accept();
//                        InputStreamReader inR = new InputStreamReader(socket.getInputStream());
//                        BufferedReader bufferedReader = new BufferedReader(inR);
//                        String message = bufferedReader.readLine();
//                        while (message != null) {
//                            textArea.appendText(message+"\n");
//                            message = bufferedReader.readLine();
//                        }
                        new Thread(new ClientHandler(textArea, socket)).start();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        severThread.start();

    }






    @FXML
    public void exitButton(){
        System.exit(0);
    }

}


