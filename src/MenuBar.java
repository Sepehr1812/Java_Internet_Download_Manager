import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar {

    public JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("Download");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        String[] menuItems = {"New Download", "Resume", "Pause", "Cancel", "Remove", "Setting", "Exit"};

        for (int i = 0; i < menuItems.length; i ++) {
            menuItem = new JMenuItem(menuItems[i]);

            switch (i) {
                case 0:
                    menuItem.setMnemonic(KeyEvent.VK_N);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
//
                    break;
                case 1:
                    menuItem.setMnemonic(KeyEvent.VK_R);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK));

                    break;
                case 2:
                    menuItem.setMnemonic(KeyEvent.VK_P);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_MASK));

                    break;
                case 3:
                    menuItem.setMnemonic(KeyEvent.VK_C);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_MASK));

                    break;
                case 4:
                    menuItem.setMnemonic(KeyEvent.VK_E);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

                    break;
                case 5:
                    menuItem.setMnemonic(KeyEvent.VK_S);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

                    break;
                case 6:
                    menu.addSeparator();
                    menuItem.setMnemonic(KeyEvent.VK_X);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));

                    break;
            }

            menu.add(menuItem);
        }

        menuBar.add(menu);


        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);

        menuItem = new JMenuItem("Check for Updates...");
        menuItem.setMnemonic(KeyEvent.VK_U);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.SHIFT_MASK));

        menu.add(menuItem);

        menuItem = new JMenuItem("About");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_MASK));

        menu.add(menuItem);

        menuBar.add(menu);


        return menuBar;
    }
}