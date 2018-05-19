import javax.swing.*;
import java.awt.*;

public class SettingsFrame {

    private static String directory = "C://Users/suzi/Desktop";

    public static void createSettings(JFrame frame) {
        JFrame settingsFrame = new JFrame("Settings");
        settingsFrame.setIconImage(new ImageIcon("../Images/Setting.png").getImage());
        JPanel panel  = new JPanel(new GridBagLayout());

        settingsFrame.setBounds(frame.getX() + 20, frame.getY() + 20, 50, 50);
//        settingsFrame.setLayout(new GridBagLayout());


        JLabel limitLabel = new JLabel("Number of downloading files at the same time (0 for unlimited):", SwingConstants.LEFT);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, Integer.SIZE, 1);
        JSpinner limitSpinner = new JSpinner(spinnerNumberModel);

//        limitSpinner.setMaximumSize(new Dimension(20, 10));

        JLabel fileLocationLabel = new JLabel("Choose the directory you want save your files:", SwingConstants.LEFT);

        JButton fileChooserButton  = new JButton("Select Folder...");
        fileChooserButton .addActionListener(ae -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Choose Folder...");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//                System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                directory = chooser.getSelectedFile().getAbsolutePath();

            } else {
                System.out.println("No Selection");
            }
        });

        JLabel setLAFLabel = new JLabel("Set one of these look-and-feels for Wavy Downloader:", SwingConstants.LEFT);

        Container radioGroupContainer = new Container();
        radioGroupContainer.setLayout(new GridLayout(0, 1));
        ButtonGroup radioButtonGroup = new ButtonGroup();

        JRadioButton[] option =  new JRadioButton[3];
        option[0] = new JRadioButton("Default", true);
        radioButtonGroup.add(option[0]);
        radioGroupContainer.add(option[0]);

        option[1] = new JRadioButton("Nimbus", false);
        radioButtonGroup.add(option[1]);
        radioGroupContainer.add(option[1]);

        option[2] = new JRadioButton("Motif", false);
        radioButtonGroup.add(option[2]);
        radioGroupContainer.add(option[2]);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            for (JRadioButton anOption : option) {
                if (anOption.isSelected())
                    setLookAndFeel(anOption.getText(), frame);
            }
            settingsFrame.setVisible(false);
        });

        addComponent(panel, limitLabel, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(panel, limitSpinner, 2, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(panel, fileLocationLabel, 0, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(panel, fileChooserButton, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(panel, setLAFLabel, 0, 4, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(panel, radioGroupContainer, 2, 4, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(panel, okButton, 2, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        setColors(panel, limitLabel, limitSpinner, fileLocationLabel, fileChooserButton, setLAFLabel, radioGroupContainer, okButton, option[0], option[1], option[2]);

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

    private static void setLookAndFeel(String LAF, JFrame frame) {
        String lnfName = null;
        switch (LAF) {
            case "Default":
                lnfName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                break;
            case "Nimbus":
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

    public static String getDirectory() {
        return directory;
    }
}
