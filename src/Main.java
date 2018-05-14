import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

        Toolbar toolbar = new Toolbar();
        MenuBar menuBar = new MenuBar();

        mainFrame.getMainFrame().setJMenuBar(menuBar.getMenuBar());

        mainFrame.getMainFrame().add(toolbar.getToolBar());

        mainFrame.getMainFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.getMainFrame().pack();
        mainFrame.getMainFrame().setVisible(true);
    }
}
