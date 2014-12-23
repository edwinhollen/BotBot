package edwinhollen.botbot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    ListView listStatusLog;
    @FXML
    Button btnConnect;

    private ListViewLogger logger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger = new ListViewLogger(listStatusLog);

        logger.log("Hello world");
    }
}
