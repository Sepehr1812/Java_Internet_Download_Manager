import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPDownloader implements Serializable {

    private transient HttpURLConnection httpConn;
    private transient InputStream inputStream;

    /**
     * Downloads a file from a URL
     * @param file The file we want to download.
     * @throws IOException Exceptions.
     */
    public void downloadFile(MyFile file) throws IOException {
        URL url = new URL(file.getLink());
        httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        if (responseCode / 100 == 2) {
            String contentType = httpConn.getContentType();
            file.setSize(httpConn.getContentLength());

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Length = " + file.getSize());
            System.out.println("fileName = " + file.getName());

            // opens input stream from the HTTP connection
            inputStream = httpConn.getInputStream();
        } else {
            if (Main.isEnglish)
                JOptionPane.showMessageDialog(null, "Something goes wrong;\nHTTP respond code is: " + responseCode,
                        responseCode + " HTTP Respond code", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "چیزی اشتباه شده:\nHTTP respond code is: " + responseCode,
                        responseCode + " HTTP Respond code", JOptionPane.INFORMATION_MESSAGE);

            file.setProcessing(false);
            file.setCompleted(true);
            file.setInQueue(false);
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void disconnect() throws IOException {
        inputStream.close();
        httpConn.disconnect();
    }
}
