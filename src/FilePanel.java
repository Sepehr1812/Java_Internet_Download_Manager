import javax.swing.*;
import java.awt.*;

public class FilePanel{

    private JPanel mainPanel = new JPanel(new GridLayout());

    FilePanel() {
        mainPanel.setSize(500, 400);
        mainPanel.setLocation(0, 0);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    public JPanel createDownloadPanel(String nameOfFile, double speedOfDownloading, int percent) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 2, 2));
//        panel.setSize(1000, 200);
//        panel.setLocation(0, 0);


        JLabel name = new JLabel(nameOfFile);
        name.setHorizontalAlignment(SwingConstants.LEFT);
//        int labelWidth = name.getPreferredSize().width;
//        int labelHeight = name.getPreferredSize().height + 10;
//        name.setPreferredSize(new Dimension(labelWidth, labelHeight));
//        name.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        panel.add(name);


        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        progressBar.setValue(percent);
//        UIManager.put("ProgressBar.background", Color.GREEN);
//        progressBar.setBackground(Color.GREEN);
//        progressBar.setForeground(Color.GRAY);

        panel.add(progressBar);


        JLabel speed = new JLabel(speedOfDownloading + "KB/s");
        panel.add(speed);
        speed.setHorizontalAlignment(SwingConstants.RIGHT);

        mainPanel.add(panel);

        return mainPanel;
    }
}
