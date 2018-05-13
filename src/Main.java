public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Toolbar toolbar = new Toolbar();

        mainFrame.getMainFrame().add(toolbar.getToolBar());

        //mainFrame.pack();
        mainFrame.getMainFrame().setVisible(true);
    }
}
