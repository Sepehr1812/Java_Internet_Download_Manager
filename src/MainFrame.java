import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JFrame mainFrame;

    MainFrame() {
        mainFrame = new JFrame("Wavy Downloader");

//        URL iconURL = getClass().getResource("getResource");
        ImageIcon icon = new ImageIcon("../Images/Icon.png");
        mainFrame.setIconImage(icon.getImage());

        mainFrame.setSize(720, 500);
        mainFrame.setLocation(100, 100);

        mainFrame.setBackground(Color.BLACK);

        mainFrame.setLayout(new BorderLayout(1, 1));
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
