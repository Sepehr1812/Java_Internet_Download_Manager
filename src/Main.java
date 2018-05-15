import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

        Toolbar toolbar = new Toolbar();
        MenuBar menuBar = new MenuBar();

        FilePanel filePanel = new FilePanel();

        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        mainFrame.getMainFrame().setJMenuBar(menuBar.createMenu());

        mainFrame.getMainFrame().add(toolbar.createButtons(), BorderLayout.NORTH);

//        mainFrame.getMainFrame().add(filePanel.createDownloadPanel("GTA"));

        mainFrame.getMainFrame().add(filePanel.createDownloadPanel("GTA", 500.26, 50), BorderLayout.CENTER);

//        mainFrame.getMainFrame().setContentPane();

        mainFrame.getMainFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        mainFrame.getMainFrame().pack();
        mainFrame.getMainFrame().setVisible(true);
    }
}
