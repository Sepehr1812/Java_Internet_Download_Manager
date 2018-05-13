import javax.swing.*;
import java.awt.*;
import java.net.URL;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class Toolbar extends JPanel {

    private JToolBar toolBar = new JToolBar();

    Toolbar() {
        super(new BorderLayout());

        makeButton();
    }

    private void makeButton() {
        JButton button;

        //Adding buttons.
        button = makeNavigationButton("new", "Start a new download!", "New Download");
        toolBar.add(button);

        button = makeNavigationButton("Back24", "Back to previous something-or-other", "Previous");
        toolBar.add(button);

        button = makeNavigationButton("Back24", "Back to previous something-or-other", "Previous");
        toolBar.add(button);

        button = makeNavigationButton("Back24", "Back to previous something-or-other", "Previous");
        toolBar.add(button);

        button = makeNavigationButton("Back24", "Back to previous something-or-other", "Previous");
        toolBar.add(button);

        button = makeNavigationButton("Back24", "Back to previous something-or-other", "Previous");
        toolBar.add(button);
    }

    private JButton makeNavigationButton(String imageName, String toolTipText, String altText) {
        //Image URL.
        String imgLocation = "GIFs/" + imageName + ".gif";

        URL imageURL = getResource(imgLocation);

        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        //TODO
//        button.addActionListener((ActionListener) this);

        if (imageURL != null) {                      //Image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //Image didn't find
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }
}
