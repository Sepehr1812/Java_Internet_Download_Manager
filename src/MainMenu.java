import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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
        JButton button = null;
        //Create and initialize the button.
        if (Main.isEnglish)
            button = new JButton(imageName, new ImageIcon(imgLocation, altText));
        else {
            switch (imageName) {
                case "All":
                    button = new JButton("همه", new ImageIcon(imgLocation, altText));
                    break;
                case "Processing":
                    button = new JButton("در حال پردازش", new ImageIcon(imgLocation, altText));
                    break;
                case "Completed":
                    button = new JButton("کامل شده", new ImageIcon(imgLocation, altText));
                    break;
                case "Queue":
                    button = new JButton("صف", new ImageIcon(imgLocation, altText));
                    break;
            }
        }

        Objects.requireNonNull(button).setBackground(Color.BLACK);
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
