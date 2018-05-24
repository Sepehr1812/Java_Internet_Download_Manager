import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    private static JMenuBar basicMenuBar;
    private static JPanel basicToolbarPanel, basicMainMenuPanel;

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

        Toolbar toolbar = new Toolbar();
        MenuBar menuBar = new MenuBar();
        MainMenu mainMenu = new MainMenu();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        basicMenuBar = menuBar.createMenu(mainFrame.getMainFrame());
        mainFrame.getMainFrame().setJMenuBar(basicMenuBar);

        basicToolbarPanel = toolbar.createButtons(mainFrame.getMainFrame());
        mainFrame.getMainFrame().add(basicToolbarPanel, BorderLayout.NORTH);

        basicMainMenuPanel = mainMenu.createMainMenu(mainFrame.getMainFrame());
        mainFrame.getMainFrame().add(basicMainMenuPanel, BorderLayout.WEST);


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

        mainFrame.getMainFrame().setVisible(true);
    }

    public static void createDownloadPane(JFrame frame, JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.setJMenuBar(basicMenuBar);

        frame.add(basicToolbarPanel, BorderLayout.NORTH);

        frame.add(basicMainMenuPanel, BorderLayout.WEST);

        frame.add(scrollPane, BorderLayout.CENTER);

        frame.invalidate();
        frame.repaint();
        SwingUtilities.updateComponentTreeUI(frame);
    }
}
