import javax.swing.*;

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
                FilePanel.downloadFiles.remove(i);
                FilePanel.downloadPanels.remove(i);
                FilePanel.downloadFiles.get(i).getTimer().stop();
            }
        }

        FilePanel.updateMainPanel(frame);
    }

    public static void cancel() {
        for (MyFile downloadFile : FilePanel.downloadFiles) {
            if (downloadFile.isSelected()) {
                downloadFile.getTimer().stop();
                downloadFile.setCanceled(true);
                downloadFile.canceling();
            }
        }
    }
}
