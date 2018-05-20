import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewDownload {

    public static MyFile startNewDownload(JFrame frame) {
        MyFile file = new MyFile(SettingsFrame.getDirectory(), 500.00, "MB", 850.0,
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()), 0);

        String link = JOptionPane.showInputDialog(frame, "Enter the link: ", "New Download", JOptionPane.PLAIN_MESSAGE);
        String nameOfFile = JOptionPane.showInputDialog(frame, "Enter the name of file: ", "New Download", JOptionPane.PLAIN_MESSAGE);

        file.setLink(link);
        file.setName(nameOfFile);

        return file;
    }
}
