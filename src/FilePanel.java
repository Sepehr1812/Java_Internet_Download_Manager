import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FilePanel{

    private JPanel mainPanel = new JPanel();
    private ArrayList<JPanel> downloadPanels = new ArrayList<>();

    FilePanel() {
        mainPanel.setSize(500, 400);
//        mainPanel.setLocation(0, 0);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    public JPanel createDownloadPanel(String nameOfFile, double speedOfDownloading, int percent, double sizeOfFile, String scale) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 2, 2));
//        panel.setSize(1000, 200);
//        panel.setLocation(0, 0);
        panel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));


        JLabel name = new JLabel(nameOfFile);
        name.setHorizontalAlignment(SwingConstants.LEFT);

        panel.add(name);


        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        progressBar.setValue(percent);
        progressBar.setString(percent / 100.0 * sizeOfFile + " " + scale + " / " + sizeOfFile + scale + "  (" + percent + "%)");
//        UIManager.put("ProgressBar.background", Color.GREEN);
//        progressBar.setBackground(Color.GREEN);
//        progressBar.setForeground(Color.GRAY);

        panel.add(progressBar);


        JLabel speed = new JLabel(speedOfDownloading + " KB/s");
        panel.add(speed);
        speed.setHorizontalAlignment(SwingConstants.RIGHT);

        downloadPanels.add(panel);

        addToMainPanel();

        return mainPanel;
    }

    private void addToMainPanel() {
        mainPanel.repaint();
//        mainPanel.setLayout(null);
        mainPanel.setLayout(new GridLayout(downloadPanels.size(), 1, 2, 2));
//        System.out.println(downloadPanels.size());
        for (JPanel downloadPanel : downloadPanels)
            mainPanel.add(downloadPanel);
    }
}
