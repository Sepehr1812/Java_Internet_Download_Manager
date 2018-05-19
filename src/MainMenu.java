import javax.swing.*;
import java.awt.*;

public class MainMenu {

    public JPanel createMainMenu() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        panel.add(createButton("All", "All"));
        panel.add(createButton("Processing", "Processing"));
        panel.add(createButton("Completed", "Completed"));
        panel.add(createButton("Queue", "Queue"));

        panel.setBackground(Color.darkGray);

        return panel;
    }

    private JButton createButton(String imageName, String altText) {
        String imgLocation = "../Images/" + imageName + "25.png";

        //Create and initialize the button.
        JButton button = new JButton(imageName, new ImageIcon(imgLocation, altText));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);

        //TODO
//       button.addActionListener((ActionListener) this);

//        button.setIcon(new ImageIcon(imgLocation, altText));

//        button.setSize(50, 50);

        return button;
    }
}
