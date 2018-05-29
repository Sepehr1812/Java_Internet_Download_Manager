import javax.swing.*;
import java.awt.*;

public class Toolbar{

    public JPanel createButtons(JFrame frame) {
        String[] nameOfThings;
        String[] englishNames = {"Start a new download!", "Resume or Pause downloading the file(s)!", "Cancel downloading the file(s)!",
                                    "Remove file(s) from your list!", "Go to settings!"};
        String[] persianNames = {"یک دانلود جدید را شروع کنید!", "دانلود فایل (ها) را ادامه دهید یا بایستانید!", "دانلود فایل (ها) را متوقف کنید!",
                                    "فایل (ها) را از لیستتان حدف کنید!", "به تنظیمات بروید!"};
        if (Main.isEnglish)
            nameOfThings = englishNames;
        else nameOfThings = persianNames;

        JPanel panel = new JPanel(new BorderLayout(5,5));

        JToolBar toolBar = new JToolBar();
        JButton button;

        Icon mainImage;
        mainImage = new ImageIcon("../GIFs/Main.gif");
        JLabel label = new JLabel(mainImage);
        panel.add(label, BorderLayout.WEST);


        //Adding buttons.
        button = makeNavigationButton("New100", nameOfThings[0], "New Download");
        button.addActionListener(e -> NewDownload.startNewDownload(frame));
        toolBar.add(button);

        button = makeNavigationButton("ResumeAndPause100", nameOfThings[1], "Resume");
        toolBar.add(button);
        button.addActionListener(e -> SelectActions.pauseOrResume());

        button = makeNavigationButton("Cancel100", nameOfThings[2], "Cancel");
        button.addActionListener(e -> SelectActions.cancel());
        toolBar.add(button);

        button = makeNavigationButton("Remove100", nameOfThings[3], "Remove");
        button.addActionListener(e -> SelectActions.remove(frame));
        toolBar.add(button);

        button = makeNavigationButton("Setting100", nameOfThings[4], "Settings");
        button.addActionListener(e -> SettingsFrame.createSettings(frame));
        toolBar.add(button);

        toolBar.setBackground(Color.darkGray);

        panel.add(toolBar, BorderLayout.CENTER);
        panel.setBackground(Color.darkGray);

        return panel;
    }

    private JButton makeNavigationButton(String imageName, String toolTipText, String altText) {
        //Image Location.
        String imgLocation = "../GIFs/" + imageName + ".gif";

        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);

        button.setIcon(new ImageIcon(imgLocation, altText));

        button.setBackground(Color.darkGray);

        return button;
    }
}
