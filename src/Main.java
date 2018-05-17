import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        mainFrame.getMainFrame().setJMenuBar(menuBar.createMenu(mainFrame.getMainFrame()));

        mainFrame.getMainFrame().add(toolbar.createButtons(), BorderLayout.NORTH);

//        mainFrame.getMainFrame().add(filePanel.createDownloadPanel("GTA"));

        createDownloadPane(mainFrame.getMainFrame(), filePanel.createDownloadPanel("GTA", 500.55, 50, 500.26, "MB"));
//        mainFrame.getMainFrame().setVisible(true);
        createDownloadPane(mainFrame.getMainFrame(), filePanel.createDownloadPanel("GTA IV", 5110.55, 60, 78.4, "KB"));


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

//        mainFrame.getMainFrame().pack();
        mainFrame.getMainFrame().setVisible(true);
    }

    private static void createDownloadPane(JFrame frame, JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(scrollPane);
    }

    public static void openSettings(JFrame frame) {
        JDesktopPane desktop = new JDesktopPane();

        JInternalFrame internalFrame = new JInternalFrame("Setting", false, true, false, false);
        internalFrame.setSize(100, 100);
        internalFrame.setLocation(20, 20);
        internalFrame.setVisible(true);

        desktop.add(internalFrame);

//                        JInternalFrame if1 = new JInternalFrame("Settings", false, true, false, false);
//                        if1.setSize(500,300);
//                        desktop.add(if1);
//
//                        if1.setLocation(20,20);
//                        if1.setVisible(true);
//                        try {
//                            internalFrame.setSelected(true);
//                        } catch (PropertyVetoException e1) {
//                            e1.printStackTrace();
//                        }

        frame.add(desktop);
    }
}
