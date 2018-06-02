import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class MyFile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name, link, directory, time;
    private double speed = 0;
    private int size = 0;
    private int percent;
    private JProgressBar progressBar = new JProgressBar(0, 100);
    private JLabel speedLabel;
    private boolean isSelected = false, isCanceled = false;
    private JPanel panel;
    private boolean isProcessing = false, isCompleted = false, isInQueue = false, isPaused = false;
    private long totalBytesRead = 0;
    private DownloadThread downloadThread;

    MyFile(String directory, String time, int percent) {
        this.directory = directory;
        this.time = time;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getDirectory() {
        return directory;
    }

    public int getSize() {
        return size;
    }

    public double getSpeed() {
        return speed;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isNotCanceled() {
        return !isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isProcessing() {
        return isProcessing;
    }

    public void setProcessing(boolean processing) {
        isProcessing = processing;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isInQueue() {
        return isInQueue;
    }

    public void setInQueue(boolean inQueue) {
        isInQueue = inQueue;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public JLabel getSpeedLabel() {
        return speedLabel;
    }

    public long getTotalBytesRead() {
        return totalBytesRead;
    }

    public void setTotalBytesRead(long totalBytesRead) {
        this.totalBytesRead = totalBytesRead;
    }

    public DownloadThread getDownloadThread() {
        return downloadThread;
    }

    public void setDownloadThread(DownloadThread downloadThread) {
        this.downloadThread = downloadThread;
    }

    public boolean isNotPaused() {
        return !isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public JPanel convertToJPanel(JFrame frame) {
        panel = new JPanel(new GridLayout(3, 1, 1, 1));
        panel.setBackground(Color.DARK_GRAY);
        panel.setForeground(Color.LIGHT_GRAY);

        JLabel name = new JLabel(getName());
        name.setHorizontalAlignment(SwingConstants.LEFT);
        name.setBackground(Color.DARK_GRAY);
        name.setForeground(Color.LIGHT_GRAY);

        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (! isCompleted) {
                        if (Main.isEnglish)
                            JOptionPane.showMessageDialog(frame, "Download is not completed yet!", "Download Not Completed", JOptionPane.ERROR_MESSAGE);
                        else JOptionPane.showMessageDialog(frame, "دانلود هنوز کامل نشده!", "دانلود کامل نشده", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            Desktop desktop = null;
                            if (Desktop.isDesktopSupported())
                                desktop = Desktop.getDesktop();

                            Objects.requireNonNull(desktop).open(new File(getDirectory() + "/" + getName()));
                        } catch (IllegalArgumentException iae) {
                            if (Main.isEnglish)
                                JOptionPane.showMessageDialog(frame, "There is not this file in its directory.", "File Not Found", JOptionPane.ERROR_MESSAGE);
                            else JOptionPane.showMessageDialog(frame, "این فایل در مکانش نیست.", "فایل پیدا نشد", JOptionPane.ERROR_MESSAGE);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                } else if (SwingUtilities.isRightMouseButton(e))
                    JOptionPane.showMessageDialog(panel, myToString(), "Download Info", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/Info.gif"));
                else if (SwingUtilities.isLeftMouseButton(e))
                    selecting(panel, name, speedLabel);
            }
        });

        panel.add(name);


        progressBar.setStringPainted(true);
        progressBar.setValue(getPercent());
        progressBar.setString(getPercent() / 100.0 * getSize() + " " + " / " + getSize() + "  (" + getPercent() + "%)");
        UIManager.put("ProgressBar.background", Color.DARK_GRAY);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setForeground(Color.GRAY);

        panel.add(progressBar);

        speedLabel = new JLabel(getSpeed() + " KB/s");
        speedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        speedLabel.setBackground(Color.DARK_GRAY);
        speedLabel.setForeground(Color.LIGHT_GRAY);

        panel.add(speedLabel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e))
                    JOptionPane.showMessageDialog(panel, myToString(), "Download Info", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/Info.gif"));
                else if (SwingUtilities.isLeftMouseButton(e))
                    selecting(panel, name, speedLabel);
            }
        });

        if (isCanceled)
            canceling();

        isSelected = false;

        return panel;
    }

    private String myToString() {
        if (Main.isEnglish)
        return "Name: " + getName() + "\n\nSize: " + getSize() + " " + "\n\nPercent of Download: " + getPercent() + "\n\nSpeed: "
                + getSpeed() + "\n\nLink: " + getLink() + "\n\nDirectory: " + getDirectory() + "\n\nTime of Start: " + getTime();
        else return  "نام: " + getName() + "\n\nاندازه: " + getSize() + " " + "\n\nدرصد دانلود: " + getPercent() + "\n\nسرعت: "
                + getSpeed() + "\n\nلینک: " + getLink() + "\n\nمکان: " + getDirectory() + "\n\nزمان شروع: " + getTime();
    }

    private void selecting(JPanel panel, JLabel name, JLabel speed) {
        if (! isCanceled) {
            if (! isSelected) {
                panel.setBackground(Color.LIGHT_GRAY);
                name.setBackground(Color.LIGHT_GRAY);
                name.setForeground(Color.BLACK);
                speed.setBackground(Color.LIGHT_GRAY);
                speed.setForeground(Color.BLACK);

                isSelected = true;
            } else {
                panel.setBackground(Color.DARK_GRAY);
                name.setBackground(Color.DARK_GRAY);
                name.setForeground(Color.LIGHT_GRAY);
                speed.setBackground(Color.DARK_GRAY);
                speed.setForeground(Color.LIGHT_GRAY);

                isSelected = false;
            }
        } else {
            if (! isSelected) {
                Color cancelColor = new Color(217,  17, 27);

                panel.setBackground(cancelColor);
                name.setBackground(Color.LIGHT_GRAY);
                name.setForeground(Color.BLACK);
                speed.setBackground(Color.LIGHT_GRAY);
                speed.setForeground(Color.BLACK);

                isSelected = true;
            } else {
                Color cancelColor = new Color(126, 10, 16);

                panel.setBackground(cancelColor);

                isSelected = false;
            }
        }
    }

    public void canceling() {
        Color cancelColor = new Color(126, 10, 16);
        panel.setBackground(cancelColor);

        isSelected = false;
    }
}
