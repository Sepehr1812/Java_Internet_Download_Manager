import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewDownload {

    public static int initialDelay;

    public static void startNewDownload(JFrame frame) {
        JFrame newFrame = new JFrame("New Download");

        newFrame.setSize(400, 200);
        newFrame.setLocationRelativeTo(frame);
        newFrame.setIconImage(new ImageIcon("../Images/New Download Icon.png").getImage());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        newFrame.setContentPane(panel);

        final MyFile[] file = new MyFile[1];

        JLabel linkLabel = new JLabel("Your Download Link: ");
        linkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField link = new JTextField();
        link.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel nameLabel = new JLabel("File Name: ");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField name = new JTextField();
        name.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel queueLabel = new JLabel("Adding to Queue: ");
        queueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JCheckBox checkBox = new JCheckBox("Add", false);
        checkBox.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel scheduleLabel = new JLabel("Delay before starting (min): ");
        scheduleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, Integer.SIZE, 1);
        JSpinner scheduleSpinner = new JSpinner(spinnerNumberModel);

        JButton startButton = new JButton(" start ");
        startButton.addActionListener(e -> {
            if (! isLinkBanned(link.getText())) {
                file[0] = new MyFile(SettingsFrame.getDirectory(), 500.00, "MB", 850.0,
                        new SimpleDateFormat("yyyy/MM/dd , HH:mm:ss").format(Calendar.getInstance().getTime()), 0);

                file[0].setLink(link.getText());
                file[0].setName(name.getText());

                if (checkBox.isSelected())
                    file[0].setInQueue(true);
                else file[0].setInQueue(false);

                initialDelay = (int) scheduleSpinner.getValue();

                FilePanel.addToMainPanel(frame, file[0]);

                newFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(newFrame, "You banned this link before.\n" +
                        "Enter another link or remove this link from banned addresses list in Settings.", "Banned Address", JOptionPane.ERROR_MESSAGE);

                newFrame.setVisible(false);
                startNewDownload(frame);
            }
        });

        JButton cancelButton = new JButton(" Cancel ");
        cancelButton.addActionListener(e -> newFrame.setVisible(false));

        setColors(panel, linkLabel, link, nameLabel, name, queueLabel, checkBox, scheduleLabel, scheduleSpinner, startButton, cancelButton);

        panel.add(linkLabel);
        panel.add(link);
        panel.add(nameLabel);
        panel.add(name);
        panel.add(queueLabel);
        panel.add(checkBox);
        panel.add(scheduleLabel);
        panel.add(scheduleSpinner);
        panel.add(startButton);
        panel.add(cancelButton);

        newFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        newFrame.setVisible(true);
    }

    private static void setColors(Component... components) {
        for (Component component : components) {
            component.setBackground(Color.DARK_GRAY);
            component.setForeground(Color.LIGHT_GRAY);
        }
    }

    private static boolean isLinkBanned(String inputLink) {
        for (String bannedAddress : SettingsFrame.getBannedAddressesArray()) {
            if (inputLink.startsWith(bannedAddress))
                return true;
        }

        return false;
    }
}
