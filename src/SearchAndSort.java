import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Comparator;

public class SearchAndSort {

    public static boolean sortByTime = true, sortByName = false, sortBySize = false, ascendant = true;

    public static void searchFrame(JFrame frame) {
        JFrame searchFrame;
        if (Main.isEnglish)
            searchFrame = new JFrame("Search");
        else searchFrame = new JFrame("جستجو");

        searchFrame.setIconImage(new ImageIcon("../Images/Search.png").getImage());
        searchFrame.setSize(400, 70);
        searchFrame.setLocationRelativeTo(frame);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        JTextField textField = new JTextField(30);
        textField.setBackground(Color.DARK_GRAY);
        textField.setForeground(Color.LIGHT_GRAY);

        panel.add(textField);
        searchFrame.add(panel);

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)
                    showSearchResults(textField.getText(), searchFrame);
            }
        });

        searchFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        searchFrame.setVisible(true);
    }

    private static void showSearchResults(String searched, JFrame frame) {
        String[] nameOfThings;
        String[] englishNames = {"Search Results for", "No download found!", "Not Found"};
        String[] persianNames = {"نتیجه های جستجو برای", "دانلودی پیدا نشد!", "پیدا نشد"};
        if (Main.isEnglish)
            nameOfThings = englishNames;
        else nameOfThings = persianNames;

        JFrame resultsFrame = new JFrame(nameOfThings[0] + " \"" + searched + "\"");
        resultsFrame.setIconImage(new ImageIcon("../Images/Search Results.png").getImage());
        resultsFrame.setLocationRelativeTo(frame);
        resultsFrame.setSize(600, 300);

        JPanel panel = new JPanel();

        int numberOfFoundFiles = 0;
        for (MyFile file : FilePanel.downloadFiles) {
            if (file.getName().contains(searched) || file.getLink().contains(searched))
                numberOfFoundFiles ++ ;
        }

        if (numberOfFoundFiles != 0) {
            panel.setLayout(new GridLayout(numberOfFoundFiles, 0, 2, 2));
            panel.setBackground(Color.BLACK);

            for (int i = 0; i < FilePanel.downloadFiles.size(); i++) {
                if (FilePanel.downloadFiles.get(i).getName().contains(searched) || FilePanel.downloadFiles.get(i).getLink().contains(searched))
                    panel.add(FilePanel.downloadPanels.get(i));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            resultsFrame.add(scrollPane);

            resultsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            resultsFrame.setVisible(true);
        } else
            JOptionPane.showMessageDialog(frame, nameOfThings[1], nameOfThings[2], JOptionPane.ERROR_MESSAGE);
    }
}

class SortByName implements Comparator<MyFile> {
    public int compare(MyFile a, MyFile b) {
        if (SearchAndSort.ascendant)
            return a.getName().compareTo(b.getName());
        else return -(a.getName().compareTo(b.getName()));
    }
}

class SortBySize implements Comparator<MyFile> {
    public int compare(MyFile a, MyFile b) {
        if (SearchAndSort.ascendant)
            return (int) (a.getSize() - b.getSize());
        else return (int) (-(a.getSize() - b.getSize()));
    }
}

class SortByTime implements Comparator<MyFile> {
    public int compare(MyFile a, MyFile b) {
        if (SearchAndSort.ascendant)
            return a.getTime().compareTo(b.getTime());
        else return -(a.getTime().compareTo(b.getTime()));
    }
}
