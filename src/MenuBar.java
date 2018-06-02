import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MenuBar {

    public JMenuBar createMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        String[] nameOfThings;
        String[] englishNames = {"Download", "View", "Sort", "Ascendant", "Anticlimactic", "By Time", "By Name", "By Size", "Languages",
                                    "English", "Persian", "Reopen Wavy Downloader to apply your changes.", "Reopen to Apply", "Queue", "Add to Queue",
                                        "Remove from Queue", "Swap", "Help", "There is not any newer version :(", "Check For Updates...",
                                        "You are REGISTERED! :)", "Register...", "About"};
        String[] persianNames = {"دانلود", "نمایش", "مرتب سازی", "صعودی", "نزولی", "بر اساس زمان", "بر اساس نام", "بر اساس اندازه",
                                    "زبان ها", "انگلیسی", "فارسی", "Wavy Downloader را برای اعمال تغییرات دوباره باز کنید.", "دوباره باز کردن برای اعمال",
                                    "صف", "اضافه کردن به صف", "حذف از صف", "جابجا کردن", "کمک", "نسخه جدیدتری وجود ندارد ؛(",
                                    "چک کردن آپدیت ها...", "شما ثبت شده اید! :)", "ثبت کردن...", "درباره"};
        if (Main.isEnglish)
            nameOfThings = englishNames;
        else nameOfThings = persianNames;

        menu = new JMenu(nameOfThings[0]);
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        String[] menuItems;
        String[] englishMenuItems = {"New Download", "Resume", "Pause", "Cancel", "Remove", "Search", "Export", "Settings", "Exit"};
        String[] persianMenuItems = {"دانلود جدید", "ادامه", "ایست", "متوقف", "حذف", "جستجو", "صادر کردن", "تنظیمات", "خروج"};

        if (Main.isEnglish)
            menuItems = englishMenuItems;
        else menuItems = persianMenuItems;

        for (int i = 0; i < menuItems.length; i ++) {
            menuItem = new JMenuItem(menuItems[i]);

            switch (i) {
                case 0:
                    menuItem.setMnemonic(KeyEvent.VK_N);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
                    menuItem.addActionListener(e -> NewDownload.startNewDownload(frame));

                    break;
                case 1:
                    menuItem.setMnemonic(KeyEvent.VK_R);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK));
                    menuItem.addActionListener(e -> SelectActions.resume());

                    break;
                case 2:
                    menuItem.setMnemonic(KeyEvent.VK_P);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_MASK));
                    menuItem.addActionListener(e -> SelectActions.pause());

                    break;
                case 3:
                    menuItem.setMnemonic(KeyEvent.VK_C);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_MASK));
                    menuItem.addActionListener(e -> SelectActions.cancel());

                    break;
                case 4:
                    menuItem.setMnemonic(KeyEvent.VK_E);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
                    menuItem.addActionListener(e -> SelectActions.remove(frame));

                    break;
                case 5:
                    menu.addSeparator();
                    menuItem.setMnemonic(KeyEvent.VK_A);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke("control shift S"));

                    menuItem.addActionListener(e -> SearchAndSort.searchFrame(frame));

                    break;
                case 6:
                    menuItem.setMnemonic(KeyEvent.VK_T);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl alt E"));

                    menuItem.addActionListener(e -> {
                        File[] files = new File[6];
                        files[0] = new File("../Data/FilesInfo.wavy");
                        files[1] = new File("../Data/BannedLinks.wavy");
                        files[2] = new File("../Data/QueueFilesInfo.wavy");
                        files[3] = new File("../Data/Removed.wavy");
                        files[4] = new File("../Data/SettingsFile.wavy");
                        files[5] = new File("../Data/LanguageFile.wavy");

                        try (FileOutputStream fos = new FileOutputStream(new File("../WavyDataFiles.zip"));
                             ZipOutputStream zos = new ZipOutputStream(fos)) {
                            byte[] buffer = new byte[128];
                            for (File currentFile : files) {
                                if (!currentFile.isDirectory()) {
                                    ZipEntry entry = new ZipEntry(currentFile.getName());
                                    FileInputStream fis = new FileInputStream(currentFile);
                                    zos.putNextEntry(entry);

                                    int read;
                                    while ((read = fis.read(buffer)) != -1)
                                        zos.write(buffer, 0, read);

                                    zos.closeEntry();
                                    fis.close();
                                }
                            }
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                            System.err.println("File didn't find.");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });

                    break;
                case 7:
                    menuItem.setMnemonic(KeyEvent.VK_S);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

                    menuItem.addActionListener(e -> SettingsFrame.createSettings(frame));

                    break;
                case 8:
                    menu.addSeparator();
                    menuItem.setMnemonic(KeyEvent.VK_X);
                    menuItem.setAccelerator(KeyStroke.getKeyStroke("control alt X"));

                    menuItem.addActionListener(e -> {
                        FileManager.writeFiles(FilePanel.downloadFiles);
                        FileManager.writeSettingsFile();
                        FileManager.writeBannedLinksFile();
                        System.exit(0);
                    });

                    break;
            }

            menu.add(menuItem);
        }

        menuBar.add(menu);

        menu = new JMenu(nameOfThings[1]);
        menu.setMnemonic(KeyEvent.VK_V);

        JMenu sortOptionsMenu = new JMenu(nameOfThings[2]);
        sortOptionsMenu.setMnemonic(KeyEvent.VK_S);
        menu.add(sortOptionsMenu);

        // ButtonGroup for radio buttons
        ButtonGroup directionGroup = new ButtonGroup();

        JRadioButtonMenuItem ascendantMenuItem = new JRadioButtonMenuItem(nameOfThings[3], true);
        ascendantMenuItem.setMnemonic(KeyEvent.VK_S);
        ascendantMenuItem.setAccelerator(KeyStroke.getKeyStroke("alt shift ctrl S"));
        ascendantMenuItem.addActionListener(e -> {
            SearchAndSort.ascendant = true;
            FilePanel.updateMainPanel(frame);
        });
        sortOptionsMenu.add(ascendantMenuItem);
        directionGroup.add(ascendantMenuItem);

        JRadioButtonMenuItem anticlimacticMenuItem = new JRadioButtonMenuItem(nameOfThings[4], false);
        anticlimacticMenuItem.setAccelerator(KeyStroke.getKeyStroke("alt shift ctrl N"));
        anticlimacticMenuItem.setMnemonic(KeyEvent.VK_N);
        anticlimacticMenuItem.addActionListener(e -> {
            SearchAndSort.ascendant = false;
            FilePanel.updateMainPanel(frame);
        });
        sortOptionsMenu.add(anticlimacticMenuItem);
        directionGroup.add(anticlimacticMenuItem);

        sortOptionsMenu.addSeparator();

        JCheckBoxMenuItem time = new JCheckBoxMenuItem(nameOfThings[5], true);
        JCheckBoxMenuItem name = new JCheckBoxMenuItem(nameOfThings[6], false);
        JCheckBoxMenuItem size = new JCheckBoxMenuItem(nameOfThings[7], false);

        time.setHorizontalTextPosition(JMenuItem.RIGHT);
        time.setMnemonic(KeyEvent.VK_T);
        time.setAccelerator(KeyStroke.getKeyStroke("alt shift T"));
        time.addActionListener(e -> sort(frame, time, name, size));

        name.setHorizontalTextPosition(JMenuItem.RIGHT);
        name.setMnemonic(KeyEvent.VK_A);
        name.setAccelerator(KeyStroke.getKeyStroke("alt shift N"));
        name.addActionListener(e -> sort(frame, time, name, size));

        size.setHorizontalTextPosition(JMenuItem.RIGHT);
        size.setMnemonic(KeyEvent.VK_S);
        size.setAccelerator(KeyStroke.getKeyStroke("alt shift S"));
        size.addActionListener(e -> sort(frame, time, name, size));


        sortOptionsMenu.add(time);
        sortOptionsMenu.add(name);
        sortOptionsMenu.add(size);

        menu.add(sortOptionsMenu);

        JMenu languagesOptionsMenu = new JMenu(nameOfThings[8]);
        languagesOptionsMenu.setMnemonic(KeyEvent.VK_L);
        menu.add(languagesOptionsMenu);

        ButtonGroup languagesGroup = new ButtonGroup();

        JRadioButtonMenuItem english = new JRadioButtonMenuItem(nameOfThings[9], Main.isEnglish);
        english.setMnemonic(KeyEvent.VK_E);
        english.setAccelerator(KeyStroke.getKeyStroke("alt shift ctrl E"));
        english.addActionListener(e -> {
            FileManager.writeLanguageFile(true);
            FileManager.writeFiles(FilePanel.downloadFiles);
            FileManager.writeSettingsFile();
            FileManager.writeBannedLinksFile();
            JOptionPane.showMessageDialog(frame, nameOfThings[11], nameOfThings[12], JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });
        languagesOptionsMenu.add(english);
        languagesGroup.add(english);

        JRadioButtonMenuItem persian = new JRadioButtonMenuItem(nameOfThings[10], ! Main.isEnglish);
        persian.setAccelerator(KeyStroke.getKeyStroke("alt shift ctrl P"));
        persian.setMnemonic(KeyEvent.VK_P);
        persian.addActionListener(e -> {
            FileManager.writeLanguageFile(false);
            FileManager.writeFiles(FilePanel.downloadFiles);
            FileManager.writeSettingsFile();
            FileManager.writeBannedLinksFile();
            JOptionPane.showMessageDialog(frame, nameOfThings[11], nameOfThings[12], JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });
        languagesOptionsMenu.add(persian);
        languagesGroup.add(persian);

        menuBar.add(menu);


        menu = new JMenu(nameOfThings[13]);
        menu.setMnemonic(KeyEvent.VK_Q);

        menuItem = new JMenuItem(nameOfThings[14]);
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> SelectActions.addToQueue());

        menu.add(menuItem);

        menuItem = new JMenuItem(nameOfThings[15]);
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> SelectActions.removeFromQueue());

        menu.add(menuItem);

        menuItem = new JMenuItem(nameOfThings[16]);
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> SelectActions.swapInQueue());

        menu.add(menuItem);

        menuBar.add(menu);


        menu = new JMenu(nameOfThings[17]);
        menu.setMnemonic(KeyEvent.VK_H);

        menuItem = new JMenuItem(nameOfThings[19]);
        menuItem.setMnemonic(KeyEvent.VK_U);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, nameOfThings[18],
                nameOfThings[19], JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/Update.gif")));

        menu.add(menuItem);

        menuItem = new JMenuItem(nameOfThings[21]);
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, nameOfThings[20],
                nameOfThings[21], JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/Thank you.gif")));

        menu.add(menuItem);

        menuItem = new JMenuItem(nameOfThings[22]);
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        if (Main.isEnglish)
            menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "\nWavy Downloader!\n\nDeveloper: Sepehr Akhoundi" +
                        "\nID Number: 9631002\n\nDate of Start: 2018/12/05\nDate of End: \n\nYou can use Wavy Downloader EASY & FREE!",
                "About", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/About.gif")));
        else
            menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "\nWavy Downloader!\n\nتوسعه دهنده: سپهر آخوندی" +
                            "\nشماره دانشجویی: 9631002\n\nتاریخ شروع: 2018/12/05\nتاریخ پایان: \n\nشما میتوانید از Wavy Downloader آسان و رایگان استفاده کنید!",
                    "درباره", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../GIFs/About.gif")));

        menu.add(menuItem);

        menuBar.add(menu);


        return menuBar;
    }

    private void sort(JFrame frame, JCheckBoxMenuItem time, JCheckBoxMenuItem name, JCheckBoxMenuItem size) {
        SearchAndSort.sortByTime = time.isSelected();
        SearchAndSort.sortByName = name.isSelected();
        SearchAndSort.sortBySize = size.isSelected();

        FilePanel.updateMainPanel(frame);
    }
}
