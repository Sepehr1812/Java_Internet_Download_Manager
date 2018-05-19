import javax.swing.*;
import java.awt.*;

public class Toolbar{

    public JPanel createButtons(JFrame frame) {
        JPanel panel = new JPanel(new BorderLayout(5,5));

        JToolBar toolBar = new JToolBar();
        JButton button;

        Icon mainImage;
        mainImage = new ImageIcon("../GIFs/Main.gif");
        JLabel label = new JLabel(mainImage);
        panel.add(label, BorderLayout.WEST);

        //toolBar.setSize(600, 100);
//        toolBar.setLocation(0, 0);

        //Adding buttons.
        button = makeNavigationButton("New100", "Start a new download!", "New Download");
        button.addActionListener(e -> {
            MyFile newFile = NewDownload.startNewDownload(frame);
            FilePanel.addToMainPanel(frame, newFile);
        });
        toolBar.add(button);

        button = makeNavigationButton("ResumeAndPause100", "Resume or Pause downloading the file(s)!", "Resume");
        toolBar.add(button);

        button = makeNavigationButton("Cancel100", "Cancel downloading the file(s)!", "Cancel");
        toolBar.add(button);

        button = makeNavigationButton("Remove100", "Remove file from your list(s)!", "Remove");
        toolBar.add(button);

        button = makeNavigationButton("Setting100", "Go to settings!", "Settings");
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

        //TODO
//        button.addActionListener((ActionListener) this);

        button.setIcon(new ImageIcon(imgLocation, altText));

//        button.setSize(50, 50);
        button.setBackground(Color.darkGray);

        return button;
    }
}
