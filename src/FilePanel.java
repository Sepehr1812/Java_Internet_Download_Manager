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
        file.setProcessing(true);

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

        if (downloadPanels.size() == 0) {
            mainPanel.setLayout(new GridLayout(1, 0, 1, 1));
            mainPanel.add(MainFrame.setDefaultPanel());
        } else
            mainPanel.setLayout(new GridLayout(downloadPanels.size(), 0, 1, 1));

        for (JPanel downloadPanel : downloadPanels) {
            mainPanel.add(downloadPanel);
        }

        Main.createDownloadPane(frame, mainPanel);
    }

    public static void updateProcessingMenu(JFrame frame) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        Container contain = frame.getContentPane();
        contain.removeAll();

        int numberOfProcessingFiles = 0;
        for (MyFile downloadFile : downloadFiles) {
            if (downloadFile.isProcessing())
                numberOfProcessingFiles++;
        }

        if (numberOfProcessingFiles == 0) {
            mainPanel.setLayout(new GridLayout(1, 0, 1, 1));
            mainPanel.add(MainFrame.setDefaultPanel());
        } else
            mainPanel.setLayout(new GridLayout(numberOfProcessingFiles, 0, 1, 1));

        for (int i = 0; i < downloadPanels.size(); i ++) {
            if (downloadFiles.get(i).isProcessing())
                mainPanel.add(downloadPanels.get(i));
        }

        Main.createDownloadPane(frame, mainPanel);
    }

    public static void updateCompletedMenu(JFrame frame) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        Container contain = frame.getContentPane();
        contain.removeAll();

        int numberOfCompletedFiles = 0;
        for (MyFile downloadFile : downloadFiles) {
            if (downloadFile.isCompleted())
                numberOfCompletedFiles++;
        }

        if (numberOfCompletedFiles == 0) {
            mainPanel.setLayout(new GridLayout(1, 0, 1, 1));
            mainPanel.add(MainFrame.setDefaultPanel());
        } else
            mainPanel.setLayout(new GridLayout(numberOfCompletedFiles, 0, 1, 1));

        for (int i = 0; i < downloadPanels.size(); i ++) {
            if (downloadFiles.get(i).isCompleted())
                mainPanel.add(downloadPanels.get(i));
        }

        Main.createDownloadPane(frame, mainPanel);
    }

    public static void updateQueueMenu(JFrame frame) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        Container contain = frame.getContentPane();
        contain.removeAll();

        int numberOfInQueueFiles = 0;
        for (MyFile downloadFile : downloadFiles) {
            if (downloadFile.isInQueue())
                numberOfInQueueFiles++;
        }

        if (numberOfInQueueFiles == 0) {
            mainPanel.setLayout(new GridLayout(1, 0, 1, 1));
            mainPanel.add(MainFrame.setDefaultPanel());
        } else
            mainPanel.setLayout(new GridLayout(numberOfInQueueFiles, 0, 1, 1));

        for (int i = 0; i < downloadPanels.size(); i ++) {
            if (downloadFiles.get(i).isInQueue())
                mainPanel.add(downloadPanels.get(i));
        }

        Main.createDownloadPane(frame, mainPanel);
    }
}
