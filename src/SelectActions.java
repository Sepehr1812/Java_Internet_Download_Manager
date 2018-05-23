import javax.swing.*;
import java.util.Collections;

public class SelectActions {

    public static void pause() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected()) {
                downloadFile.getTimer().stop();
            }
        }
    }

    public static void resume() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected() && downloadFile.isNotCanceled()) {
                downloadFile.getTimer().start();
            }
        }
    }

    public static void pauseOrResume() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected()) {
                if (downloadFile.getTimer().isRunning())
                    downloadFile.getTimer().stop();
                else if (downloadFile.isNotCanceled())
                    downloadFile.getTimer().start();
            }
        }
    }

    public static void remove(JFrame frame) {
        for (int i = 0; i < FilePanel.downloadFiles.size(); i ++) {
            if (FilePanel.downloadFiles.get(i).isSelected()) {
                FilePanel.downloadFiles.get(i).getTimer().stop();
                FilePanel.downloadFiles.remove(i);
                FilePanel.downloadPanels.remove(i);
            }
        }

        FilePanel.updateMainPanel(frame);
    }

    public static void cancel() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected()) {
                downloadFile.getTimer().stop();
                downloadFile.setCanceled(true);
                downloadFile.setCompleted(false);
                downloadFile.setProcessing(false);
                downloadFile.setInQueue(false);
                downloadFile.canceling();
            }
        }
    }

    public static void addToQueue() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected() && ! downloadFile.isInQueue() && downloadFile.isProcessing()) {
                downloadFile.setInQueue(true);
                downloadFile.setSelected(false);
            }
        }
    }

    public static void removeFromQueue() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected() && downloadFile.isInQueue() && downloadFile.isProcessing()) {
                downloadFile.setInQueue(false);
                downloadFile.setSelected(false);
            }
        }
    }

    public static void swapInQueue() {
        int numberOfSelectedFiles = 0;
        int fileNumberOne = 0, fileNumberTwo = 0;
        for (int i = 0; i < FilePanel.downloadFiles.size(); i ++) {
            if (FilePanel.downloadFiles.get(i).isSelected() && FilePanel.downloadFiles.get(i).isInQueue()) {
                numberOfSelectedFiles ++;

                if (numberOfSelectedFiles == 1) {
                    fileNumberOne = i;
                    System.out.println("number one");
                } else if (numberOfSelectedFiles == 2) {
                    fileNumberTwo = i;
                    System.out.println("number two");
                } else if (numberOfSelectedFiles == 3)
                    break;
            }
        }

        if (numberOfSelectedFiles == 2) {
            System.out.println("set set");
            Collections.swap(FilePanel.downloadFiles, fileNumberOne, fileNumberTwo);
            Collections.swap(FilePanel.downloadPanels, fileNumberOne, fileNumberTwo);
        }
    }
}
