package ru.vsu.cs.solodovnikova;

import ru.vsu.cs.solodovnikova.util.ArrayUtils;
import ru.vsu.cs.solodovnikova.util.JTableUtils;
import ru.vsu.cs.solodovnikova.util.SwingUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

public class FrameMain extends JFrame {

    private JPanel panelMain;
    private JButton resume;
    private JTable tableInput;
    private JButton buttonRandomInput;
    private JButton solveButtonUp;
    private JButton solveButtonDown;
    private JTable tableJI;
    private JButton previous;
    private JButton next;
    private JButton stop;
    private JButton help;
    public int now = -1;
    private boolean var = true;
    public SortState sort = new SortState();

    public FrameMain() throws IOException {
        this.setTitle("Task 4: Visualisation of BUBBLE SORT");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(ImageIO.read(new File("res/favicon.png")));
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, false, true, false, true);
        String column_names[]= {"j","i"};
        TableModel model = new DefaultTableModel(column_names,2);
        tableJI.setModel(model);
        this.setPreferredSize(new Dimension(800, 300));
        JTableUtils.writeArrayToJTable(tableInput, new int[]{0, 1, 2, 3, 4, 5});
        JTableUtils.writeArrayToJTable(tableJI, new int[]{0, 0});
        Timer timer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        this.pack();


        buttonRandomInput.addActionListener(e -> {
            try {
                int[] arr = ArrayUtils.createRandomIntArray(tableInput.getColumnCount(), 100);
                JTableUtils.writeArrayToJTable(tableInput, arr);
                now = -1;
                sort.list.clear();
                sort.ji.clear();
                JTableUtils.writeArrayToJTable(tableJI, new int[]{0, 0});
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }

        });
        solveButtonDown.addActionListener(e -> {
            try {
                if (now == -1) {
                    try {
                        Logic.sortDown(JTableUtils.readIntArrayFromJTable(tableInput), sort);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
                ActionListener what = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (now >= sort.list.size() - 1) {
                            timer.stop();
                        }
                        if (now < sort.list.size() - 1) {
                            now++;
                        }
                        JTableUtils.writeArrayToJTable(tableInput, sort.list.get(now));
                        JTableUtils.writeArrayToJTable(tableJI, sort.ji.get(now));
                    }
                };
                if (now == -1)
                    timer.addActionListener(what);
                if (var)
                    timer.start();
                var = false;
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                now--;
                JTableUtils.writeArrayToJTable(tableJI, sort.ji.get(now));
                JTableUtils.writeArrayToJTable(tableInput, sort.list.get(now));
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                if (now >= sort.list.size() - 1) {
                    timer.stop();
                } else {
                    now++;
                }
                JTableUtils.writeArrayToJTable(tableJI, sort.ji.get(now));
                JTableUtils.writeArrayToJTable(tableInput, sort.list.get(now));
            }
        });
        solveButtonUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (now == -1) {
                    try {
                        Logic.sortUp(JTableUtils.readIntArrayFromJTable(tableInput), sort);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
                ActionListener what = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (now >= sort.list.size() - 1) {
                            timer.stop();
                        }
                        if (now < sort.list.size() - 1) {
                            now++;
                        }
                        JTableUtils.writeArrayToJTable(tableInput, sort.list.get(now));
                        JTableUtils.writeArrayToJTable(tableJI, sort.ji.get(now));
                    }
                };
                if (now == -1)
                    timer.addActionListener(what);
                if (var)
                    timer.start();
                var = false;
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Для того, чтобы автоматически заполнить таблицу, нажмите кнопку \"Заполнить\". \n" +
                        "Чтобы начать выполнение сортировки, нажмите \"Выполнить сортировку по возрастающей/убывающей автоматически\". \n" +
                        "В таблице указана переменная j внешнего и i внутреннего циклов. \n" +
                        "Чтобы остановить процесс сортировки, нажмите кнопку \"Стоп\". \n" +
                        "Чтобы возобновить процесс, нажмите кнопку \"Продолжить\" \n" +
                        "Чтобы вручную управлять сортировкой, нажимайте кнопку \"<--\" или \"-->\" ", "Помощь", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (now != -1)
                    timer.start();
            }
        });
    }
}
