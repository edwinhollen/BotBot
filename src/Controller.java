import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    ListView listStatusLog;
    @FXML
    Button btnConnect;
    @FXML
    Label lblGetTwitchOAuthToken;


    private ListViewLogger logger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger = new ListViewLogger(listStatusLog);

        lblGetTwitchOAuthToken.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        Desktop.getDesktop().browse(new URL("http://www.twitchapps.com/tmi/").toURI());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
}
