import javax.swing.*;
import java.awt.*;

public class MainMenu {

    public JPanel createMainMenu(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        panel.add(createButton("All", "All", frame));
        panel.add(createButton("Processing", "Processing", frame));
        panel.add(createButton("Completed", "Completed", frame));
        panel.add(createButton("Queue", "Queue", frame));

        panel.setBackground(Color.darkGray);

        return panel;
    }

    private JButton createButton(String imageName, String altText, JFrame frame) {
        String imgLocation = "../Images/" + imageName + "25.png";

        //Create and initialize the button.
        JButton button = new JButton(imageName, new ImageIcon(imgLocation, altText));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);

        switch (imageName) {
            case "All":
                button.addActionListener(e -> FilePanel.updateMainPanel(frame));
                break;
            case "Processing":
                button.addActionListener(e -> FilePanel.updateProcessingMenu(frame));
                break;
            case "Completed":
                button.addActionListener(e -> FilePanel.updateCompletedMenu(frame));
                break;
            case "Queue":
                button.addActionListener(e -> FilePanel.updateQueueMenu(frame));
                break;
        }


        return button;
    }
}
