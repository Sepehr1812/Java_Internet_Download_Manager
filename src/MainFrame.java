import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JFrame mainFrame;

    MainFrame() {
        mainFrame = new JFrame("Tomato Downloader");

//        URL iconURL = getClass().getResource("getResource");
        ImageIcon icon = new ImageIcon("../Images/Icon.png");
        mainFrame.setIconImage(icon.getImage());

        mainFrame.setSize(700, 500);
        mainFrame.setLocation(100, 100);

        mainFrame.setLayout(new BorderLayout(5, 5));
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
