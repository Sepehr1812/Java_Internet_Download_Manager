import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewDownload {

    public static ExecutorService pool = Executors.newFixedThreadPool(SettingsFrame.getLimitNumber());
    public static ExecutorService queuePool = Executors.newFixedThreadPool(1);

    public static void startNewDownload(JFrame frame) {
        String[] nameOfThings;
        String[] englishNames = {"New Download", "Download Link: ", "File Name: ", "Adding to Queue: ", "Add", "Delay Before Starting (min): ", "Start", "Cancel"};
        String[] persianNames = {"دانلود جدید", " : لینک دانلود", " : نام فایل", " : اضافه کردن به صف", "اضافه کردن", " : تاخیر قبل از شروع (دقیقه)", "شروع", "متوقف"};
        if (Main.isEnglish)
            nameOfThings = englishNames;
        else nameOfThings = persianNames;

        JFrame newFrame = new JFrame(nameOfThings[0]);

        newFrame.setSize(400, 200);
        newFrame.setLocationRelativeTo(frame);
        newFrame.setIconImage(new ImageIcon("../Images/New Download Icon.png").getImage());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        newFrame.setContentPane(panel);

        final MyFile[] file = new MyFile[1];

        JLabel linkLabel = new JLabel(nameOfThings[1]);
        linkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField link = new JTextField();
        link.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel nameLabel = new JLabel(nameOfThings[2]);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField name = new JTextField();
        name.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel queueLabel = new JLabel(nameOfThings[3]);
        queueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JCheckBox checkBox = new JCheckBox(nameOfThings[4], false);
        checkBox.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel scheduleLabel = new JLabel(nameOfThings[5]);
        scheduleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, Integer.SIZE, 1);
        JSpinner scheduleSpinner = new JSpinner(spinnerNumberModel);

        JButton startButton = new JButton(nameOfThings[6]);
        startButton.addActionListener(e -> {
            if (! isLinkBanned(link.getText())) {
                file[0] = new MyFile(SettingsFrame.getDirectory(), new SimpleDateFormat("yyyy/MM/dd , HH:mm:ss").format(Calendar.getInstance().getTime()),
                        System.currentTimeMillis(), 0);

                file[0].setLink(link.getText());
                file[0].setName(name.getText());

                if (checkBox.isSelected())
                    file[0].setInQueue(true);
                else file[0].setInQueue(false);

                file[0].setRemainTime(Integer.parseInt(String.valueOf(scheduleSpinner.getValue())));

                FilePanel.addToMainPanel(frame, file[0]);

                //Downloading...
                file[0].getProgressBar().setValue(0);

                DownloadThread downloadThread = new DownloadThread(file[0]);
                Thread threadOfDownload= new Thread(downloadThread);
                file[0].setDownloadThread(downloadThread);

                //To select the pool
                if (checkBox.isSelected())
                    queuePool.execute(threadOfDownload);
                else pool.execute(threadOfDownload);

                //For progress bar filling
                downloadThread.addPropertyChangeListener(evt -> {
                    if (evt.getPropertyName().equals("progress")) {
                        int newValue = (Integer) evt.getNewValue();
                        file[0].getProgressBar().setValue(newValue);
                        file[0].setPercent(newValue);
                        file[0].getProgressBar().setString(file[0].getPercent() / 100.0 * file[0].getSize() + " " + " / " + file[0].getSize() +
                                " Bytes  (" + file[0].getPercent() + "%)");
                    }
                });

                newFrame.setVisible(false);
            } else {
                if (Main.isEnglish)
                    JOptionPane.showMessageDialog(newFrame, "You banned this link before.\n" +
                        "Enter another link or remove this link from banned addresses list in Settings.", "Banned Address", JOptionPane.ERROR_MESSAGE);
                else JOptionPane.showMessageDialog(newFrame, "شما قبلا این لینک را قدغن کرده اید.\n" +
                        "لینک دیگری وارد کنید یا این لینک را از لیست آدرس های قدغن شده حذف کنید.", "آدرس قدغن شده", JOptionPane.ERROR_MESSAGE);

                newFrame.setVisible(false);
                startNewDownload(frame);
            }
        });

        JButton cancelButton = new JButton(nameOfThings[7]);
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
