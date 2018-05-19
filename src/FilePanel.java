import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FilePanel{

    private static ArrayList<JPanel> downloadPanels = new ArrayList<>();

    public static void addToMainPanel(JFrame frame, MyFile file) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        Container contain = frame.getContentPane();
        contain.removeAll();

//        JTable table = new JTable(downloadPanels.size(), 0);
//        JScrollPane scrollPane = new JScrollPane(table);
//        table.setFillsViewportHeight(true);
//
//        JLabel lblHeading = new JLabel("Stock Quotes");
//        lblHeading.setFont(new Font("Arial", Font.PLAIN,24));
//
//        frame.getContentPane().setLayout(new BorderLayout());
//
//        frame.getContentPane().add(lblHeading,BorderLayout.PAGE_START);
//        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);

        downloadPanels.add(file.convertToJPanel());

//        mainPanel.repaint();

//        mainPanel.setLayout(null);
        mainPanel.setLayout(new GridLayout(downloadPanels.size(), 0, 1, 1));
//        System.out.println(downloadPanels.size());
        for (JPanel downloadPanel : downloadPanels) {
            mainPanel.add(downloadPanel);
        }

        Main.createDownloadPane(frame, mainPanel);
    }
}
