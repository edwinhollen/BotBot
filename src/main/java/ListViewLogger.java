import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Created by fubar on 12/23/14.
 */
public class ListViewLogger {
    private ListView listView;
    private ObservableList<Label> messages = FXCollections.observableArrayList();


    public ListViewLogger(ListView lv){
        this.listView = lv;
    }

    public void log(String message){
        this.listView.getItems().add(new LoggerLabel("testing 123", "orange"));
    }

    private class LoggerLabel extends Label{
        private final static String FONT = "monospace";

        public LoggerLabel(String message, String color){
            super(message);
            this.setStyle(String.format("-fx-text-fill:%s;-fx-font-family:%s;-fx-font-weight:bold;", color, FONT));
        }
    }
}
