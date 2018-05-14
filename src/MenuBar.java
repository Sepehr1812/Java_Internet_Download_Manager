import javax.swing.*;

public class MenuBar {

    JMenuBar menuBar = new JMenuBar();

    MenuBar() {
        JMenu menu = new JMenu("File");

        menuBar.add(menu);

        menu.add(new JMenuItem("New"));
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
