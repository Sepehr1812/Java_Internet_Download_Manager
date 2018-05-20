import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FilePanel{

    public static ArrayList<JPanel> downloadPanels = new ArrayList<>();
    public static ArrayList<MyFile> downloadFiles = new ArrayList<>();

    public static void addToMainPanel(JFrame frame, MyFile file) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        Container contain = frame.getContentPane();
        contain.removeAll();

        downloadFiles.add(file);
        downloadPanels.add(file.convertToJPanel());

        mainPanel.setLayout(new GridLayout(downloadPanels.size(), 0, 1, 1));

        for (JPanel downloadPanel : downloadPanels) {
            mainPanel.add(downloadPanel);
        }

        Main.createDownloadPane(frame, mainPanel);
    }

    public static void updateMainPanel(JFrame frame) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        Container contain = frame.getContentPane();
        contain.removeAll();

        if (downloadPanels.size() == 0)
            frame.getContentPane().add(MainFrame.setDefaultPanel(), BorderLayout.CENTER);
        else
            mainPanel.setLayout(new GridLayout(downloadPanels.size(), 0, 1, 1));

        for (JPanel downloadPanel : downloadPanels) {
            mainPanel.add(downloadPanel);
        }

        Main.createDownloadPane(frame, mainPanel);
    }
}
