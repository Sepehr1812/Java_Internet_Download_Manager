import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Thread of each download. Downloading and multithreading happens in this class.
 *
 * @author Sepehr Akhoundi
 * @version 1.5
 */
public class DownloadThread extends SwingWorker<Void, Void> implements Runnable, Serializable {

    private MyFile file;
    private HTTPDownloader downloader;

    /**
     * Constructor for DownloadThread class.
     * @param file the file we want to download.
     */
    DownloadThread(MyFile file) {
        super();
        this.file = file;
    }

    /**
     * @return HTTP Connection we use.
     */
    public HTTPDownloader getDownloader() {
        return downloader;
    }

    /**
     * Downloading in background to avoid program becoming gore.
     * @return null.
     * @throws Exception any exception may happen; like IOException.
     */
    @Override
    protected Void doInBackground() throws Exception {
        downloader = new HTTPDownloader();

        //for considering initial delay time for schedule downloading.
        long currentTime = System.currentTimeMillis();
        while (true) {
            if ((System.currentTimeMillis() - currentTime) / 1000.0 / 60 >= file.getRemainTime())
                break;
        }

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

        //Skipping bytes we downloaded before.
        inputStream.skip(file.getTotalBytesRead());

        //Downloading...
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            long start = System.nanoTime(); //for calculating the speed of download.

            outputStream.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;
            percentCompleted = (int) (totalBytesRead * 100 / fileSize);
            file.setTotalBytesRead(totalBytesRead); //to know in resuming where we must start.

            setProgress(percentCompleted);

            long end = System.nanoTime();

            file.setSpeed(Math.round((double) bytesRead / (end - start) * 1000)); //speed of download per KB/s.
            file.getSpeedLabel().setText(file.getSpeed() + " KB/sec");
        }

        file.setCompleted(true);

        outputStream.close();
        downloader.disconnect();

        return null;
    }

    /**
     * The task must be done when download completed.
     */
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
