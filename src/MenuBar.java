import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar {

    public JMenuBar createMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("Download");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        String[] menuItems = {"New Download", "Resume", "Pause", "Cancel", "Remove", "Search", "Settings", "Exit"};

        for (int i = 0; i < menuItems.length; i ++) {
            menuItem = new JMenuItem(menuItems[i]);

            switch (i) {
                case 0:
                    menuItem.setMnemonic(KeyEvent.VK_N);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
                    menuItem.addActionListener(e -> NewDownload.startNewDownload(frame));

                    break;
                case 1:
                    menuItem.setMnemonic(KeyEvent.VK_R);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK));
                    menuItem.addActionListener(e -> SelectActions.resume());

                    break;
                case 2:
                    menuItem.setMnemonic(KeyEvent.VK_P);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_MASK));
                    menuItem.addActionListener(e -> SelectActions.pause());

                    break;
                case 3:
                    menuItem.setMnemonic(KeyEvent.VK_C);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_MASK));
                    menuItem.addActionListener(e -> SelectActions.cancel());

                    break;
                case 4:
                    menuItem.setMnemonic(KeyEvent.VK_E);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
                    menuItem.addActionListener(e -> SelectActions.remove(frame));

                    break;
                case 5:
                    menu.addSeparator();
                    menuItem.setMnemonic(KeyEvent.VK_A);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke("control shift S"));

                    menuItem.addActionListener(e -> SearchAndSort.searchFrame(frame));

                    break;
                case 6:
                    menuItem.setMnemonic(KeyEvent.VK_S);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

                    menuItem.addActionListener(e -> SettingsFrame.createSettings(frame));

                    break;
                case 7:
                    menu.addSeparator();
                    menuItem.setMnemonic(KeyEvent.VK_X);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke("control alt X"));

                    menuItem.addActionListener(e -> {
                        FileManager.writeFiles(FilePanel.downloadFiles);
                        FileManager.writeSettingsFile();
                        FileManager.writeBannedLinksFile();
                        System.exit(0);
                    });

                    break;
            }

            menu.add(menuItem);
        }

        menuBar.add(menu);

        menu = new JMenu("Queue");
        menu.setMnemonic(KeyEvent.VK_Q);

        menuItem = new JMenuItem("Add to Queue");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> SelectActions.addToQueue());

        menu.add(menuItem);

        menuItem = new JMenuItem("Remove from Queue");
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> SelectActions.removeFromQueue());

        menu.add(menuItem);

        menuItem = new JMenuItem("Swap");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> SelectActions.swapInQueue());

        menu.add(menuItem);

        menuBar.add(menu);


        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);

        menuItem = new JMenuItem("Check for Updates...");
        menuItem.setMnemonic(KeyEvent.VK_U);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "There is not any newer version :(",
                "Check For Updates...", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/Update.gif")));

        menu.add(menuItem);

        menuItem = new JMenuItem("Register...");
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "You are REGISTERED! :)",
                "Register...", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/Thank you.gif")));

        menu.add(menuItem);

        menuItem = new JMenuItem("About");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "\nWavy Downloader!\n\nDeveloper: Sepehr Akhoundi" +
                        "\nID Number: 9631002\n\nDate of Start: 2018/12/05\nDate of End: \n\nYou can use Wavy Downloader EASY & FREE!",
                "About", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/About.gif")));

        menu.add(menuItem);

        menuBar.add(menu);


        return menuBar;
    }
}
