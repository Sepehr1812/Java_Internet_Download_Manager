import javax.swing.*;
import java.awt.*;

public class Toolbar{

    public JPanel createButtons() {
        JPanel panel = new JPanel(new GridBagLayout());

        JToolBar toolBar = new JToolBar();
        JButton button;

        GridBagConstraints bagC = new GridBagConstraints();

        bagC.fill = GridBagConstraints.BOTH;
        bagC.weightx = 1.0;
        bagC.weighty = 1.0;

        bagC.gridwidth = 1;
        bagC.gridheight = 1;
        bagC.ipadx = 10;
        bagC.ipady = 15;

        //toolBar.setSize(600, 100);
        toolBar.setLocation(0, 0);

        //Adding buttons.
        button = makeNavigationButton("New100", "Start a new download!", "New Download");
        toolBar.add(button);

        button = makeNavigationButton("Resume100", "Resume downloading the file(s)!", "Resume");
        toolBar.add(button);

        button = makeNavigationButton("Pause100", "Pause downloading the file(s)!", "Pause");
        toolBar.add(button);

        button = makeNavigationButton("Cancel100", "Cancel downloading the file(s)!", "Cancel");
        toolBar.add(button);

        button = makeNavigationButton("Remove100", "Remove file from your list(s)!", "Remove");
        toolBar.add(button);

        button = makeNavigationButton("Setting100", "Go to setting!", "Setting");
        toolBar.add(button);

        panel.add(toolBar);

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

        button.setSize(50, 50);

        return button;
    }
}
