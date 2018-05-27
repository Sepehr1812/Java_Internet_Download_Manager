import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class FileManager {

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

    public static void readFiles() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../Data/FilesInfo.wavy"))) {
            FilePanel.downloadFiles = (ArrayList<MyFile>) ois.readObject();
            for (MyFile downloadFile : FilePanel.downloadFiles) {
                FilePanel.downloadPanels.add(downloadFile.convertToJPanel());
                downloadFile.getTimer().stop();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File didn't find.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    public static void readBannedLinksFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../Data/BannedLinks.wavy"))) {
            try {
                SettingsFrame.setBannedAddressesArray((ArrayList<String>) ois.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File didn't find.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
