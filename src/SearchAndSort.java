import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Comparator;

/**
 * A class contains search methods and sort fields.
 *
 * @author Sepehr Akhoundi
 */
public class SearchAndSort {

    //Some fields specifies kind of searching.
    public static boolean sortByTime = true, sortByName = false, sortBySize = false, ascendant = true;

    /**
     * Creates a frame contains search field.
     * @param frame Main frame.
     */
    public static void searchFrame(JFrame frame) {
        JFrame searchFrame;

        //Setting Persian or English words.
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

            /**
             * To search when user released ENTER key.
             * @param e Releasing any key.
             */
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)
                    showSearchResults(textField.getText(), searchFrame);
            }
        });

        searchFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        searchFrame.setVisible(true);
    }

    /**
     * Creates a frame contains search results.
     * @param searched the expression user searches.
     * @param frame search frame.
     */
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

/**
 * A class to sorting files by their names.
 * It's for sort method in List class.
 */
class SortByName implements Comparator<MyFile> {
    public int compare(MyFile a, MyFile b) {
        if (SearchAndSort.ascendant)
            return a.getName().compareTo(b.getName());
        else return -(a.getName().compareTo(b.getName()));
    }
}

/**
 * A class to sorting files by their sizes.
 * It's for sort method in List class.
 */
class SortBySize implements Comparator<MyFile> {
    public int compare(MyFile a, MyFile b) {
        if (SearchAndSort.ascendant)
            return a.getSize() - b.getSize();
        else return -(a.getSize() - b.getSize());
    }
}

/**
 * A class to sorting files by their times of starting download.
 * It's for sort method in List class.
 */
class SortByTime implements Comparator<MyFile> {
    public int compare(MyFile a, MyFile b) {
        if (SearchAndSort.ascendant)
            return a.getTime().compareTo(b.getTime());
        else return -(a.getTime().compareTo(b.getTime()));
    }
}
