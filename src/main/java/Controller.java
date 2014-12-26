import javafx.collections.ListChangeListener;
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
import java.util.prefs.Preferences;

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
    TextField txtNickname, txtServer, txtPort, txtAddChannel;
    @FXML
    CheckBox chkSSL, chkSavePassword;
    @FXML
    TextArea txtaStatusLog;
    @FXML
    PasswordField passPassword;


    private Logger logger;
    private BotBot botbot;
    private Preferences prefs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prefs = Preferences.userRoot();
        logger = new Logger(txtaStatusLog);
        botbot = new BotBot();

        // load previous settings
        txtNickname.setText(prefs.get("nickname", null));
        txtServer.setText(prefs.get("server", null));
        txtPort.setText(prefs.get("port", null));
        passPassword.setText(prefs.get("password", null));
        chkSavePassword.setSelected(prefs.getBoolean("savePassword", false));
        // load previous channel list
        for(String channel : prefs.get("channels", "").split(",")){
            listChannels.getItems().add(new Label(channel));
        }


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

        txtNickname.textProperty().addListener(listener -> {
            prefs.put("nickname", txtNickname.getText().trim());
        });

        txtNickname.focusedProperty().addListener((listener, oldVal, newVal) ->{
            if(oldVal){
                txtNickname.setText(txtNickname.getText().trim());
            }
        });

        passPassword.textProperty().addListener(listener ->{
            if(prefs.getBoolean("savePassword", false)){
                prefs.put("password", passPassword.getText());
            }
        });

        txtServer.textProperty().addListener(listener ->{
            prefs.put("server", txtServer.getText());
        });
        txtPort.textProperty().addListener(listener ->{
            prefs.put("port", txtPort.getText());
        });

        txtAddChannel.setOnAction(event -> btnAddChannel.fire());

        chkSavePassword.setOnAction(event ->{
            prefs.putBoolean("savePassword", chkSavePassword.isSelected());
        });

        chkSSL.setOnAction(event ->{
            prefs.putBoolean("SSL", chkSSL.isSelected());
        });

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

        listChannels.getItems().addListener((ListChangeListener<? super Label>) listener ->{
            String csv = "";
            for(Label l : listChannels.getItems()){
                csv = csv.concat(l.getText() + ",");
            }
            csv = csv.substring(0, csv.length()-1);
            prefs.put("channels", csv);
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
