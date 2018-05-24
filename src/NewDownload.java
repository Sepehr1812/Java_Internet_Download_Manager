import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewDownload {

    public static int initialDelay;

    public static void startNewDownload(JFrame frame) {
        JFrame newFrame = new JFrame();

        newFrame.setSize(300, 200);
        newFrame.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        newFrame.setContentPane(panel);

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
            MyFile file = new MyFile(SettingsFrame.getDirectory(), 500.00, "MB", 850.0,
                    new SimpleDateFormat("yyyy/MM/dd , HH:mm:ss").format(Calendar.getInstance().getTime()), 0);

            file.setLink(link.getText());
            file.setName(name.getText());

            if (checkBox.isSelected())
                file.setInQueue(true);
            else file.setInQueue(false);

            initialDelay = (int) scheduleSpinner.getValue();

            FilePanel.addToMainPanel(frame, file);
            newFrame.setVisible(false);
        });

        JButton cancelButton = new JButton(" Cancel ");
        cancelButton.addActionListener(e -> newFrame.setVisible(false));

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
}
