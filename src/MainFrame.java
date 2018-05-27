import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JFrame mainFrame;

    MainFrame() {
        mainFrame = new JFrame("Wavy Downloader");

        ImageIcon icon = new ImageIcon("../Images/Icon.png");
        mainFrame.setIconImage(icon.getImage());

        mainFrame.setSize(750, 500);
        mainFrame.setLocation(100, 100);

        mainFrame.setBackground(Color.BLACK);

        mainFrame.setLayout(new BorderLayout(1, 1));

        mainFrame.getContentPane().add(setDefaultPanel(), BorderLayout.CENTER);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public static JPanel setDefaultPanel() {
        JLabel initialLabel = new JLabel("There is no download here yet!", SwingConstants.CENTER);
        initialLabel.setBackground(Color.BLACK);
        initialLabel.setForeground(Color.LIGHT_GRAY);
        JPanel initialPanel = new JPanel(new BorderLayout());
        initialPanel.add(initialLabel, BorderLayout.CENTER);
        initialPanel.setBackground(Color.DARK_GRAY);

        return initialPanel;
    }
}
