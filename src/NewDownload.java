import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewDownload {

    public static void startNewDownload(JFrame frame) {
        JFrame newFrame = new JFrame();

        newFrame.setSize(300, 200);
        newFrame.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
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

        JButton startButton = new JButton(" start ");
        startButton.addActionListener(e -> {
            MyFile file = new MyFile(SettingsFrame.getDirectory(), 500.00, "MB", 850.0,
                    new SimpleDateFormat("yyyy/MM/dd , HH:mm:ss").format(Calendar.getInstance().getTime()), 0);

            file.setLink(link.getText());
            file.setName(name.getText());

            if (checkBox.isSelected())
                file.setInQueue(true);
            else file.setInQueue(false);

            FilePanel.addToMainPanel(frame, file);
            newFrame.setVisible(false);
        });

        JButton cancelButton = new JButton(" Cancel ");
        cancelButton.addActionListener(e -> newFrame.setVisible(false));

        newFrame.add(linkLabel);
        newFrame.add(link);
        newFrame.add(nameLabel);
        newFrame.add(name);
        newFrame.add(queueLabel);
        newFrame.add(checkBox);
        newFrame.add(startButton);
        newFrame.add(cancelButton);

        newFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        newFrame.setVisible(true);

    }
}
