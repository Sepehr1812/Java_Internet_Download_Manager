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

    public JPanel convertToJPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 1, 1));
        panel.setBackground(Color.DARK_GRAY);
        panel.setForeground(Color.LIGHT_GRAY);

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
                if (SwingUtilities.isLeftMouseButton(e)) {
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
            }
        });

        panel.add(name);


        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        progressBar.setValue(getPercent());
        progressBar.setString(getPercent() / 100.0 * getSize() + " " + getScale() + " / " + getSize() + getScale() + "  (" + getPercent() + "%)");
        UIManager.put("ProgressBar.background", Color.DARK_GRAY);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setForeground(Color.DARK_GRAY);

        panel.add(progressBar);


        JLabel speed = new JLabel(getSpeed() + " KB/s");
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
            }
        });

        return panel;
    }

    private String myToString() {
        return "Name: " + getName() + "\n\nSize: " + getSize() + " " + getScale() + "\n\nPercent of Download: " + getPercent() + "\n\nSpeed: "
                + getSpeed() + "\n\nLink: " + getLink() + "\n\nDirectory: " + getDirectory() + "\n\nTime of Start: " + getTime();
    }
}
