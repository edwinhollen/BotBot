import javafx.scene.control.TextArea;

import java.time.LocalDateTime;

/**
 * Created by fubar on 12/23/14.
 */
public class ListViewLogger {
    private TextArea logArea;

    public ListViewLogger(TextArea lv) {
        this.logArea = lv;
    }

    public void log(String message) {
        LocalDateTime now = LocalDateTime.now();
        this.logArea.appendText(String.format("%s %s \t%s \n", now.toLocalDate(), now.toLocalTime(), message));
    }
}
