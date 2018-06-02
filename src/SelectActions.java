import javax.swing.*;
import java.io.IOException;
import java.util.Collections;

public class SelectActions {

    public static void pause() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected())
                pausing(downloadFile);
        }
    }

    public static void resume() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected() && downloadFile.isNotCanceled())
                resuming(downloadFile);
        }
    }

    public static void pauseOrResume() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected()) {
                if (downloadFile.isNotPaused())
                    pausing(downloadFile);
                else if (downloadFile.isNotCanceled())
                    resuming(downloadFile);
            }
        }
    }

    public static void remove(JFrame frame) {
        for (int i = 0; i < FilePanel.downloadFiles.size(); i ++) {
            if (FilePanel.downloadFiles.get(i).isSelected()) {
                if (FilePanel.downloadFiles.get(i).isNotPaused())
                    pausing(FilePanel.downloadFiles.get(i));
                FileManager.addToRemovedList(FilePanel.downloadFiles.get(i));
                FilePanel.downloadFiles.remove(i);
                FilePanel.downloadPanels.remove(i);
            }
        }

        FilePanel.updateMainPanel(frame);
    }

    public static void cancel() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected()) {
                if (downloadFile.isNotPaused()) {
                    pausing(downloadFile);
                    downloadFile.setPaused(true);
                }
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

                if (numberOfSelectedFiles == 1)
                    fileNumberOne = i;
                else if (numberOfSelectedFiles == 2)
                    fileNumberTwo = i;
                else if (numberOfSelectedFiles == 3)
                    break;
            }
        }

        if (numberOfSelectedFiles == 2) {
            Collections.swap(FilePanel.downloadFiles, fileNumberOne, fileNumberTwo);
            Collections.swap(FilePanel.downloadPanels, fileNumberOne, fileNumberTwo);
        }
    }

    public static void resuming(MyFile file) {
        DownloadThread downloadThread = new DownloadThread(file);
        file.setDownloadThread(downloadThread);

        downloadThread.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("progress")) {
                int newValue = (Integer) evt.getNewValue();
                file.getProgressBar().setValue(newValue);
                file.setPercent(newValue);
                file.getProgressBar().setString(file.getPercent() / 100.0 * file.getSize() + " "+ " / " + file.getSize() +
                        " Bytes  (" + file.getPercent() + "%)");
            }
        });

        file.setPaused(false);

        if (!file.isInQueue())
            NewDownload.pool.execute(new Thread(downloadThread));
        else NewDownload.queuePool.execute(new Thread(downloadThread));
    }

    private static void pausing(MyFile file) {
        file.getDownloadThread().cancel(true);
        try {
            file.getDownloadThread().getDownloader().disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.setPaused(true);
    }
}
