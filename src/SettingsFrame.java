import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class SettingsFrame {

    private static String directory = "C://Users/suzi/Desktop";
    private static boolean[] selectionOfRadioButtons = {true, false, false};
    private static int limitNumber = 0;
    private static ArrayList<String> bannedAddresses = new ArrayList<>();

    public static void createSettings(JFrame frame) {
        JFrame settingsFrame = new JFrame("Settings");
        settingsFrame.setIconImage(new ImageIcon("../Images/Setting.png").getImage());
        JPanel panel  = new JPanel(new GridBagLayout());

        settingsFrame.setBounds(frame.getX() + 20, frame.getY() + 20, 50, 50);


        JLabel limitLabel = new JLabel("Number of downloading files at the same time (0 for unlimited):", SwingConstants.LEFT);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(limitNumber, 0, Integer.SIZE, 1);
        JSpinner limitSpinner = new JSpinner(spinnerNumberModel);

        JLabel fileLocationLabel = new JLabel("Choose the directory you want save your files:", SwingConstants.LEFT);

        JButton fileChooserButton  = new JButton("Select Folder...");
        fileChooserButton .addActionListener(ae -> {
            JFileChooser chooser = new JFileChooser(directory);
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Choose Folder...");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
                directory = chooser.getSelectedFile().getAbsolutePath();
            else System.out.println("No Selection.");
        });

        JLabel bannedAddressesLabel = new JLabel("Banned Addresses: ", SwingConstants.LEFT);

        JTextArea bannedAddresses = new JTextArea(10, 30);
        bannedAddresses.setBackground(Color.LIGHT_GRAY);
        bannedAddresses.setForeground(Color.DARK_GRAY);
        JScrollPane scrollPaneForTextArea = new JScrollPane(bannedAddresses);

        JLabel setLAFLabel = new JLabel("Set one of these look-and-feels for Wavy Downloader:", SwingConstants.LEFT);

        Container radioGroupContainer = new Container();
        radioGroupContainer.setLayout(new GridLayout(0, 1));
        ButtonGroup radioButtonGroup = new ButtonGroup();

        JRadioButton[] option =  new JRadioButton[3];
        option[0] = new JRadioButton("Default", selectionOfRadioButtons[0]);
        radioButtonGroup.add(option[0]);
        radioGroupContainer.add(option[0]);

        option[1] = new JRadioButton("Metal", selectionOfRadioButtons[1]);
        radioButtonGroup.add(option[1]);
        radioGroupContainer.add(option[1]);

        option[2] = new JRadioButton("Motif", selectionOfRadioButtons[2]);
        radioButtonGroup.add(option[2]);
        radioGroupContainer.add(option[2]);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            limitNumber = (int) limitSpinner.getValue();

            for (int i = 0; i < 3; i ++) {
                if (option[i].isSelected()) {
                    setLookAndFeel(option[i].getText(), frame);
                    selectionOfRadioButtons[i] = true;
                } else selectionOfRadioButtons[i] = false;
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

class SettingsFile implements Serializable {
    private String directory, lookAndFeel;
    private int limitNumber;
    private boolean[] selectionOfLookAndFeel;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getLookAndFeel() {
        return lookAndFeel;
    }

    public void setLookAndFeel(String lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
    }

    public int getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(int limitNumber) {
        this.limitNumber = limitNumber;
    }

    public boolean[] getSelectionOfLookAndFeel() {
        return selectionOfLookAndFeel;
    }

    public void setSelectionOfLookAndFeel(boolean[] selectionOfLookAndFeel) {
        this.selectionOfLookAndFeel = selectionOfLookAndFeel;
    }
}
