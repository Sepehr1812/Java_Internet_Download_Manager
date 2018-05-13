import javax.swing.*;

public class MainFrame extends JFrame {

    JFrame mainFrame;

    MainFrame() {
        new JFrame("Tomato Downloader");

//        URL iconURL = getClass().getResource("");
        ImageIcon icon = new ImageIcon("../Icon.png");
        mainFrame.setIconImage(icon.getImage());
        mainFrame.setIconImage(icon.getImage());

        mainFrame.setSize(800, 500);
        mainFrame.setLocation(100, 100);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
