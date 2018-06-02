import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Contains HttpURLConnection to downloading files.
 */
public class HTTPDownloader implements Serializable {

    private transient HttpURLConnection httpConn;
    private transient InputStream inputStream;

    /**
     * Downloads a file from a URL
     * @param file The file we want to download.
     * @throws IOException Exceptions may occur in IO files.
     */
    public void downloadFile(MyFile file) throws IOException {
        URL url = new URL(file.getLink());
        httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        if (responseCode / 100 == 2) { //When server accepted our request.
            file.setSize(httpConn.getContentLength());

            /*

            String contentType = httpConn.getContentType();
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Length = " + file.getSize());
            System.out.println("fileName = " + file.getName());
            */

            //opens input stream from the HTTP connection.
            inputStream = httpConn.getInputStream();
        } else { //When server refused our request.
            if (Main.isEnglish)
                JOptionPane.showMessageDialog(null, "Something goes wrong;\nHTTP respond code is: " + responseCode,
                        responseCode + " HTTP Respond code", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "چیزی اشتباه شده:\nHTTP respond code is: " + responseCode,
                        responseCode + " HTTP Respond code", JOptionPane.INFORMATION_MESSAGE);

            file.setProcessing(false);
            file.setCompleted(false);
            file.setInQueue(false);
        }
    }

    /**
     * @return The input stream we use for downloading.
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * To disconnect us from the server.
     * @throws IOException any exceptions in IO files.
     */
    public void disconnect() throws IOException {
        inputStream.close();
        httpConn.disconnect();
    }
}
