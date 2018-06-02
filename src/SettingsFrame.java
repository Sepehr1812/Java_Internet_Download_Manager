import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class SettingsFrame {

    private static String directory = "C://Users/suzi/Desktop";
    private static boolean[] selectionOfRadioButtons = {true, false, false};
    private static int limitNumber = 0;
    private static ArrayList<String> bannedAddressesArray = new ArrayList<>();

    public static void createSettings(JFrame frame) {
        String[] nameOfThings;
        String[] englishNames = {"ُSettings", "Number of downloading files at the same time (0 for unlimited):", "Choose the directory you want save your files:",
                "Select Folder...", "Choose Folder...", "Banned Addresses (Enter a new line after each link): ", "Set one of these look-and-feels for Wavy Downloader:",
                "Default", "Metal", "Motif", "OK"};
        String[] persianNames = {"تنظیمات", ": تعداد فایل هایی که در یک زمان دانلود شوند (0 برای تعداد نامحدود)",
                ": مکانی که میخواهید فایل هایتان در آنجا ذخیره شوند را انتخاب کنید", "پوشه را انتخاب کنید...", "پوشه را انتخاب کنید...",
                ": آدرس های قدغن شده (یک خط جدید بعد از هر لینک وارد کنید)", ": یکی از این رویه ها را برای Wavy Downloader انتخاب کنید", "پیشفرض", "Metal", "Motif", "قبول"};
        if (Main.isEnglish)
            nameOfThings = englishNames;
        else nameOfThings = persianNames;

        JFrame settingsFrame = new JFrame(nameOfThings[0]);
        settingsFrame.setIconImage(new ImageIcon("../Images/Setting.png").getImage());
        JPanel panel  = new JPanel(new GridBagLayout());

        settingsFrame.setBounds(frame.getX() + 20, frame.getY() + 20, 50, 50);


        JLabel limitLabel = new JLabel(nameOfThings[1], SwingConstants.LEFT);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(limitNumber, 0, Integer.SIZE, 1);
        JSpinner limitSpinner = new JSpinner(spinnerNumberModel);

        JLabel fileLocationLabel = new JLabel(nameOfThings[2], SwingConstants.LEFT);

        JButton fileChooserButton  = new JButton(nameOfThings[3]);
        fileChooserButton .addActionListener(ae -> {
            JFileChooser chooser = new JFileChooser(directory);
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(nameOfThings[4]);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
                directory = chooser.getSelectedFile().getAbsolutePath();
            else System.out.println("No Selection.");
        });

        JLabel bannedAddressesLabel = new JLabel(nameOfThings[5], SwingConstants.LEFT);

        JTextArea bannedAddresses = new JTextArea(10, 30);

        String buffString = "";
        for (String aBannedAddress : bannedAddressesArray) {
            buffString = buffString.concat(aBannedAddress + "\n");
        }
        bannedAddresses.setText(buffString);

        bannedAddresses.setBackground(Color.LIGHT_GRAY);
        bannedAddresses.setForeground(Color.DARK_GRAY);
        JScrollPane scrollPaneForTextArea = new JScrollPane(bannedAddresses);

        JLabel setLAFLabel = new JLabel(nameOfThings[6], SwingConstants.LEFT);

        Container radioGroupContainer = new Container();
        radioGroupContainer.setLayout(new GridLayout(0, 1));
        ButtonGroup radioButtonGroup = new ButtonGroup();

        JRadioButton[] option =  new JRadioButton[3];
        option[0] = new JRadioButton(nameOfThings[7], selectionOfRadioButtons[0]);
        radioButtonGroup.add(option[0]);
        radioGroupContainer.add(option[0]);

        option[1] = new JRadioButton(nameOfThings[8], selectionOfRadioButtons[1]);
        radioButtonGroup.add(option[1]);
        radioGroupContainer.add(option[1]);

        option[2] = new JRadioButton(nameOfThings[9], selectionOfRadioButtons[2]);
        radioButtonGroup.add(option[2]);
        radioGroupContainer.add(option[2]);

        JButton okButton = new JButton(nameOfThings[10]);
        okButton.addActionListener(e -> {
            limitNumber = (int) limitSpinner.getValue();
            DownloadThread.setLimitOfDownloads((int) limitSpinner.getValue());

            for (int i = 0; i < 3; i ++) {
                if (option[i].isSelected()) {
                    switch (i) {
                        case 0:
                            setLookAndFeel("Default", frame);
                            break;
                        case 1:
                            setLookAndFeel("Metal", frame);
                            break;
                        case 2:
                            setLookAndFeel("Motif", frame);
                            break;
                    }
                    selectionOfRadioButtons[i] = true;
                } else selectionOfRadioButtons[i] = false;
            }

            char[] buffCharArray = bannedAddresses.getText().toCharArray();
            bannedAddressesArray.clear();
            String aBannedAddress;
            int lastBachSlashN = 0;
            for (int i = 0; i < buffCharArray.length; i ++) {
                if (buffCharArray[i] == '\n') {
                    aBannedAddress = bannedAddresses.getText().substring(lastBachSlashN, i);
                    lastBachSlashN = i + 1;
                    bannedAddressesArray.add(aBannedAddress);
                }
            }

            settingsFrame.setVisible(false);
        });

        addComponent(panel, limitLabel, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(panel, limitSpinner, 2, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(panel, fileLocationLabel, 0, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(panel, fileChooserButton, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(panel, bannedAddressesLabel, 0, 4, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(panel, scrollPaneForTextArea, 2, 4, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(panel, setLAFLabel, 0, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(panel, radioGroupContainer, 2, 6, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(panel, okButton, 2, 8, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        setColors(panel, limitLabel, limitSpinner, fileLocationLabel, fileChooserButton, bannedAddressesLabel,
                setLAFLabel, radioGroupContainer, okButton, option[0], option[1], option[2]);

        settingsFrame.setContentPane(panel);
        settingsFrame.pack();

        settingsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        settingsFrame.setVisible(true);
    }

    private static void addComponent(Container container, Component component, int gridX, int gridY, int anchor, int fill) {
        Insets insets = new Insets(20, 10, 20, 10);
        GridBagConstraints gbc = new GridBagConstraints(gridX, gridY, 1, 1, 1.0, 1.0,
                anchor, fill, insets, 10, 10);
        container.add(component, gbc);
    }

    private static void setColors(Component... components) {
        for (Component component : components) {
            component.setBackground(Color.DARK_GRAY);
            component.setForeground(Color.LIGHT_GRAY);
        }
    }

    public static void setLookAndFeel(String LAF, JFrame frame) {
        String lnfName = null;
        switch (LAF) {
            case "Default":
                lnfName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                break;
            case "Metal":
                lnfName = "javax.swing.plaf.metal.MetalLookAndFeel";
                break;
            case "Motif":
                lnfName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                break;
        }

        try {
            UIManager.setLookAndFeel(lnfName);
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (UnsupportedLookAndFeelException ex1) {
            System.err.println("Unsupported LookAndFeel: " + lnfName);
        } catch (ClassNotFoundException ex2) {
            System.err.println("LookAndFeel class not found: " + lnfName);
        } catch (InstantiationException ex3) {
            System.err.println("Could not load LookAndFeel: " + lnfName);
        } catch (IllegalAccessException ex4) {
            System.err.println("Cannot use LookAndFeel: " + lnfName);
        }
    }

    public static void setSelectionOfRadioButtons(boolean[] selectionOfRadioButtons) {
        SettingsFrame.selectionOfRadioButtons = selectionOfRadioButtons;
    }

    public static void setDirectory(String directory) {
        SettingsFrame.directory = directory;
    }

    public static void setLimitNumber(int limitNumber) {
        SettingsFrame.limitNumber = limitNumber;
    }

    public static String getDirectory() {
        return directory;
    }

    public static ArrayList<String> getBannedAddressesArray() {
        return bannedAddressesArray;
    }

    public static void setBannedAddressesArray(ArrayList<String> bannedAddressesArray) {
        SettingsFrame.bannedAddressesArray = bannedAddressesArray;
    }

    public static SettingsFile createSettingsFile() {
        SettingsFile settingsFile = new SettingsFile();

        settingsFile.setDirectory(directory);
        settingsFile.setLimitNumber(limitNumber);
        settingsFile.setSelectionOfLookAndFeel(selectionOfRadioButtons);

        if (selectionOfRadioButtons[0])
            settingsFile.setLookAndFeel("Default");
        else if (selectionOfRadioButtons[1])
            settingsFile.setLookAndFeel("Metal");
        else if (selectionOfRadioButtons[2])
            settingsFile.setLookAndFeel("Motif");

        return settingsFile;
    }
}

/**
 * A class that settings data saves in that to save in a file.
 */
class SettingsFile implements Serializable {
    private String directory, lookAndFeel;
    private int limitNumber;
    private boolean[] selectionOfLookAndFeel;

    /**
     * @return Directory of file.
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * @param directory Directory of file.
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * @return Look and feel of file.
     */
    public String getLookAndFeel() {
        return lookAndFeel;
    }

    /**
     * @param lookAndFeel Look and feel of file.
     */
    public void setLookAndFeel(String lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
    }

    /**
     * @return Limit number of downloads.
     */
    public int getLimitNumber() {
        return limitNumber;
    }

    /**
     * @param limitNumber Limit number of downloads.
     */
    public void setLimitNumber(int limitNumber) {
        this.limitNumber = limitNumber;
    }

    /**
     * @return the array of look and file radio buttons.
     */
    public boolean[] getSelectionOfLookAndFeel() {
        return selectionOfLookAndFeel;
    }

    /**
     * @param selectionOfLookAndFeel the array of look and file radio buttons.
     */
    public void setSelectionOfLookAndFeel(boolean[] selectionOfLookAndFeel) {
        this.selectionOfLookAndFeel = selectionOfLookAndFeel;
    }
}
