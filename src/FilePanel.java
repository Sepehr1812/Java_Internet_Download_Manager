import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FilePanel{

    private static ArrayList<JPanel> downloadPanels = new ArrayList<>();
    private static boolean isFirstTime = true;

    public static void addToMainPanel(JFrame frame, MyFile file) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.DARK_GRAY);

        Container contain = frame.getContentPane();
        if (isFirstTime)
            isFirstTime = false;
        else
            contain.remove(mainPanel);

        downloadPanels.add(file.convertToJPanel());

//        mainPanel.repaint();

//        mainPanel.setLayout(null);
        mainPanel.setLayout(new GridLayout(downloadPanels.size(), 1, 1, 1));
//        System.out.println(downloadPanels.size());
        for (JPanel downloadPanel : downloadPanels)
            mainPanel.add(downloadPanel);

        frame.setContentPane(Main.basicPane);
//        contain.add(mainPanel);
//        frame.add(mainPanel, BorderLayout.CENTER);
//        frame.invalidate();
//        frame.repaint();

        Main.createDownloadPane(frame, mainPanel);
    }
}
