import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;

public class DownloadThread extends SwingWorker<Void, Void> implements Runnable, Serializable {

    private MyFile file;
    private static int limitOfDownloads;
    private HTTPDownloader downloader;

    DownloadThread(MyFile file) {
        super();
        this.file = file;
    }

    public static int getLimitOfDownloads() {
        if (limitOfDownloads != 0)
            return limitOfDownloads;
        else return 10000;
    }

    public static void setLimitOfDownloads(int limitOfDownloads) {
        DownloadThread.limitOfDownloads = limitOfDownloads;
    }

    public HTTPDownloader getDownloader() {
        return downloader;
    }

    @Override
    protected Void doInBackground() throws Exception {
        downloader = new HTTPDownloader();

        downloader.downloadFile(file);

        String saveFilePath = file.getDirectory() + File.separator + file.getName();

        InputStream inputStream = downloader.getInputStream();

        // opens an output stream to save into file
        FileOutputStream outputStream = new FileOutputStream(saveFilePath, true);

        byte[] buffer = new byte[4096];
        int bytesRead;
        long totalBytesRead = file.getTotalBytesRead();
        int percentCompleted;
        int fileSize = file.getSize();

        inputStream.skip(file.getTotalBytesRead());

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            long start = System.nanoTime();

            outputStream.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;
            percentCompleted = (int) (totalBytesRead * 100 / fileSize);
            file.setTotalBytesRead(totalBytesRead);

            setProgress(percentCompleted);

            long end = System.nanoTime();

            file.setSpeed(Math.round((double) bytesRead / (end - start) * 1000)); //per KB/s.
            file.getSpeedLabel().setText(file.getSpeed() + " KB/sec");
        }

        file.setCompleted(true);

        outputStream.close();
        downloader.disconnect();

        return null;
    }

    @Override
    protected void done() {
        if (file.isCompleted()) {
            if (Main.isEnglish)
                JOptionPane.showMessageDialog(null, "Download Completed!", "Completed!", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "دانلود کامل شد!", "کامل شد!", JOptionPane.INFORMATION_MESSAGE);

            file.setSpeed(0);
            file.getSpeedLabel().setText("0.00 KB/sec");

            file.setProcessing(false);
            file.setCompleted(true);
            file.setInQueue(false);
        }
    }
}
