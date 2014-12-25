import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    ListView<Label> listChannels;
    @FXML
    Button btnConnect, btnAddChannel, btnRemoveChannel;
    @FXML
    Label lblGetTwitchOAuthToken;
    @FXML
    Tab tabConnectionSettings;
    @FXML
    TextField txtNickname, txtServer, txtPort, txtPassword, txtAddChannel;
    @FXML
    CheckBox chkSSL;
    @FXML
    TextArea txtaStatusLog;


    private ListViewLogger logger;
    private BotBot botbot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger = new ListViewLogger(txtaStatusLog);
        botbot = new BotBot();

        // load previous settings

        // check channel list
        checkChannelList();

        lblGetTwitchOAuthToken.setOnMouseClicked(event -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    Desktop.getDesktop().browse(new URL("http://www.twitchapps.com/tmi/").toURI());
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            });
        });

        txtAddChannel.setOnAction(event -> btnAddChannel.fire());

        btnAddChannel.setOnAction(event -> {
            String newChannel = txtAddChannel.getText().trim();
            newChannel = newChannel.replace("#", "");

            if (newChannel.isEmpty() || newChannel.contains(" ")) return;

            listChannels.getItems().add(new Label(newChannel));
            txtAddChannel.clear();
            txtAddChannel.requestFocus();

            checkChannelList();
        });

        btnRemoveChannel.setOnAction(event -> {
            listChannels.getItems().remove(listChannels.getFocusModel().getFocusedIndex());
            checkChannelList();
        });

        btnConnect.setOnAction(event -> {

            //botbot.connect(txtNickname.getText(), txtServer.getText(), txtPassword.getText(), );
        });

        logger.log("GUI initialization finished");
    }

    private void checkChannelList() {
        btnRemoveChannel.setDisable(listChannels.getItems().size() < 1);

    }
}
