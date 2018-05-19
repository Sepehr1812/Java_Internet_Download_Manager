import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static Container basicPane;

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

        Toolbar toolbar = new Toolbar();
        MenuBar menuBar = new MenuBar();
        MainMenu mainMenu = new MainMenu();

        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        mainFrame.getMainFrame().setJMenuBar(menuBar.createMenu(mainFrame.getMainFrame()));

        mainFrame.getMainFrame().add(toolbar.createButtons(mainFrame.getMainFrame()), BorderLayout.NORTH);
        mainFrame.getMainFrame().add(mainMenu.createMainMenu(), BorderLayout.WEST);

//        mainFrame.getMainFrame().add(filePanel.createDownloadPanel("GTA"));


//        mainFrame.getMainFrame().add(filePanel.createDownloadPanel("GTA", 500.26, 50), BorderLayout.CENTER);

//        mainFrame.getMainFrame().setContentPane();

        mainFrame.getMainFrame().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainFrame.getMainFrame().addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e Clicking on the closing operation.
             */
            @Override
            public void windowClosing(WindowEvent e) {
                SystemTray.goToTray(mainFrame.getMainFrame());
            }
        });

        basicPane = mainFrame.getMainFrame().getContentPane();
//        mainFrame.getMainFrame().pack();
        mainFrame.getMainFrame().setVisible(true);
    }

    public static void createDownloadPane(JFrame frame, JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.invalidate();
        frame.repaint();
    }
}
