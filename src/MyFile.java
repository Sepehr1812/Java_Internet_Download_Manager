import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MyFile {

    private String name, link, directory, scale, time;
    private double size, speed;
    private int percent;
    private JProgressBar progressBar = new JProgressBar(0, 100);
    private boolean isSelected = false, isCanceled = false;
    private Timer timer;
    private JPanel panel;


    MyFile(String directory, double size, String scale, double speed, String time, int percent) {
        this.directory = directory;
        this.size = size;
        this.scale = scale;
        this.speed = speed;
        this.time = time;
        this.percent = percent;
    }

    private String getName() {
        return name;
    }

    private String getLink() {
        return link;
    }

    private String getDirectory() {
        return directory;
    }

    private double getSize() {
        return size;
    }

    private double getSpeed() {
        return speed;
    }

    private String getTime() {
        return time;
    }

    private String getScale() {
        return scale;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private int getPercent() {
        return percent;
    }

    private void setPercent(int percent) {
        this.percent = percent;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean isNotCanceled() {
        return !isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public JPanel convertToJPanel() {
        panel = new JPanel(new GridLayout(3, 1, 1, 1));
        panel.setBackground(Color.DARK_GRAY);
        panel.setForeground(Color.LIGHT_GRAY);

        JLabel speed = new JLabel(getSpeed() + " KB/s");
        JLabel name = new JLabel(getName());
        name.setHorizontalAlignment(SwingConstants.LEFT);
        name.setBackground(Color.DARK_GRAY);
        name.setForeground(Color.LIGHT_GRAY);

        name.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    try {
                        Desktop desktop = null;
                        if (Desktop.isDesktopSupported()) {
                            desktop = Desktop.getDesktop();
                        }

                        Objects.requireNonNull(desktop).open(new File(getDirectory() + "/" + getName()));
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                } else if (SwingUtilities.isRightMouseButton(e))
                    JOptionPane.showMessageDialog(panel, myToString(), "Download Info", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/Info.gif"));
                else if (SwingUtilities.isLeftMouseButton(e))
                    selecting(panel, name, speed);
            }
        });

        panel.add(name);


        progressBar.setStringPainted(true);
        progressBar.setValue(getPercent());
        progressBar.setString(getPercent() / 100.0 * getSize() + " " + getScale() + " / " + getSize() + getScale() + "  (" + getPercent() + "%)");
        UIManager.put("ProgressBar.background", Color.DARK_GRAY);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setForeground(Color.GRAY);

        panel.add(progressBar);


        speed.setHorizontalAlignment(SwingConstants.RIGHT);
        speed.setBackground(Color.DARK_GRAY);
        speed.setForeground(Color.LIGHT_GRAY);

        panel.add(speed);

        panel.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e))
                    JOptionPane.showMessageDialog(panel, myToString(), "Download Info", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/Info.gif"));
                else if (SwingUtilities.isLeftMouseButton(e))
                    selecting(panel, name, speed);
            }
        });

        fillProgressBar();

        return panel;
    }

    private String myToString() {
        return "Name: " + getName() + "\n\nSize: " + getSize() + " " + getScale() + "\n\nPercent of Download: " + getPercent() + "\n\nSpeed: "
                + getSpeed() + "\n\nLink: " + getLink() + "\n\nDirectory: " + getDirectory() + "\n\nTime of Start: " + getTime();
    }

    private void fillProgressBar() {
        final int[] c = {getPercent()};
        final int waitingTime = 15 * 1000; //15 seconds
        final int delay = waitingTime / 100;

        timer = new Timer(delay, e -> {
            if(c[0] <= 100) {
                progressBar.setValue(++ c[0]);
                setPercent(c[0]);
                progressBar.setString(getPercent() / 100.0 * getSize() + " " + getScale() + " / " + getSize() + getScale() + "  (" + getPercent() + "%)");
            }
        });

        progressBar.setValue(c[0]);

        timer.setInitialDelay(3000);
        timer.start();

        // adding a changeListener to the progress bar
        progressBar.addChangeListener(e -> {
            if(progressBar.getValue() == 100) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Download Completed!");
            }
        });
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
