import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * A class to manage files we need them.
 *
 * @author Sepehr Akhoundi
 */
public class FileManager {

    /**
     * A method to write whole files and files of queue of program to data folder.
     * @param files files we need to write.
     */
    public static void writeFiles(ArrayList<MyFile> files) {
        try (ObjectOutputStream oosAll = new ObjectOutputStream(new FileOutputStream("../Data/FilesInfo.wavy", false));
             ObjectOutputStream oosQueue = new ObjectOutputStream(new FileOutputStream("../Data/QueueFilesInfo.wavy", false))) {
            oosAll.writeObject(files);

            for (MyFile file : files) {
                if (file.isInQueue())
                    oosQueue.writeObject(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File didn't find.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to reed whole files of program from data folder.
     */
    public static void readFiles(JFrame frame) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../Data/FilesInfo.wavy"))) {
            FilePanel.downloadFiles = (ArrayList<MyFile>) ois.readObject();
            for (MyFile downloadFile : FilePanel.downloadFiles) {
                FilePanel.downloadPanels.add(downloadFile.convertToJPanel(frame));
//                downloadFile.getTimer().stop();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File of whole files didn't find.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * To write removed files for backup.
     * @param file files we need to write.
     */
    public static void addToRemovedList(MyFile file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../Data/Removed.wavy", true))) {
            oos.writeObject("Name: " + file.getName() + " , Link: " + file.getLink());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File didn't find.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To write settings data.
     */
    public static void writeSettingsFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../Data/SettingsFile.wavy", false))) {
            oos.writeObject(SettingsFrame.createSettingsFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File didn't find.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To read and apply settings file.
     * @param frame Main frame.
     * @throws IOException when there is no file to read; for setting look and feel by default.
     */
    public static void readSettingsFile(JFrame frame) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../Data/SettingsFile.wavy"))) {
            SettingsFile settingsFile = (SettingsFile) ois.readObject();

            SettingsFrame.setDirectory(settingsFile.getDirectory());
            SettingsFrame.setLimitNumber(settingsFile.getLimitNumber());
            SettingsFrame.setSelectionOfRadioButtons(settingsFile.getSelectionOfLookAndFeel());
            SettingsFrame.setLookAndFeel(settingsFile.getLookAndFeel(), frame);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * To write data of banned links.
     */
    public static void writeBannedLinksFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../Data/BannedLinks.wavy"))) {
            oos.writeObject(SettingsFrame.getBannedAddressesArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File didn't find.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To read data of banned links.
     */
    public static void readBannedLinksFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../Data/BannedLinks.wavy"))) {
            try {
                SettingsFrame.setBannedAddressesArray((ArrayList<String>) ois.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File of banned links didn't find.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To write language selection from it's file.
     * @param isEnglish if {@code true} program must run in English.
     */
    public static void writeLanguageFile(boolean isEnglish) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../Data/LanguageFile.wavy"))) {
            oos.writeObject(isEnglish);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File didn't find.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To read language selection from it's file.
     */
    public static void readLanguageFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../Data/LanguageFile.wavy"))) {
            try {
                Main.isEnglish = (boolean) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            Main.isEnglish = true;
            System.err.println("File of languages didn't find.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
