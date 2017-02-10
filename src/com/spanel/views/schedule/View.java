package com.spanel.views.schedule;

import com.spanel.app.Request;
import com.spanel.beans.Schedule;
import com.spanel.controllers.FileController;
import com.spanel.dao.FileDao;
import com.spanel.helpers.ImagePanel;
import com.spanel.views.ClassIndex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;


/**
 * Created by Joel on 24/11/2016.
 */
public class View extends JPanel {
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JButton downloadButton;

    private JScrollPane jScrollPane;
    private JPanel mainPanel;
    private JLabel oldVersionLabel;
    private JPanel oldVersionsContainer;
    private JPanel rightPanel;
    private JPanel schedulePanel;
    private JButton uploadButton;
    private JLabel scheduleLabel;

    private com.spanel.views.ClassIndex classIndex;
    private Request request ;
    private com.spanel.beans.File file1;

    public View(ClassIndex classIndex, List<com.spanel.beans.File> schedules) {
        this.classIndex=classIndex;
        if(schedules.size()==0){
            schedulePanel= new ImagePanel("assets/uploads/img/b.jpg", schedulePanel);
        }else {
            schedulePanel = new ImagePanel(schedules.get(0).getPath(), schedulePanel);
        }

        initComponents();

        setVisible(true);
        if(schedules.size()>0) {
            oldVersionsContainer.setLayout(new GridLayout(schedules.size() - 1, 1, 2, 5));
            int i = 0;
            if(schedules.size()>1) {
                file1 = schedules.get(0);
            }
            for (com.spanel.beans.File schedule : schedules) {
                if (i++ == 1) continue;
                oldVersionsContainer.add(composeScheduleItem(schedule, i));
            }
        }

    }

    private void initComponents() {

        jScrollPane = new JScrollPane();
        mainPanel = new JPanel();
        rightPanel = new JPanel();
        oldVersionLabel = new JLabel();
        oldVersionsContainer = new JPanel();
        centerPanel = new JPanel();
        downloadButton = new JButton();
        bottomPanel = new JPanel();
        uploadButton = new JButton();
        scheduleLabel = new JLabel();

        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(770, 520));
        setLayout(new BorderLayout());

        jScrollPane.setBackground(new Color(255, 255, 255));
        jScrollPane.setBorder(null);

        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout());

        rightPanel.setBackground(new Color(255, 255, 255));
        rightPanel.setPreferredSize(new Dimension(250, 20));

        oldVersionLabel.setFont(oldVersionLabel.getFont().deriveFont(oldVersionLabel.getFont().getSize() + 3f));
        oldVersionLabel.setText("Versions precédentes");

        GroupLayout rightPanelLayout = new GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(rightPanelLayout.createSequentialGroup()
                                                .addComponent(oldVersionLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(oldVersionsContainer, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(oldVersionLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oldVersionsContainer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(420, Short.MAX_VALUE))
        );

        mainPanel.add(rightPanel, BorderLayout.LINE_END);

        centerPanel.setBackground(new Color(255, 255, 255));

        downloadButton.setText("Télécharger");
        downloadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        GroupLayout centerPanelLayout = new GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
                centerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(centerPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(centerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(schedulePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(centerPanelLayout.createSequentialGroup()
                                                .addComponent(downloadButton)
                                                .addGap(0, 411, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        centerPanelLayout.setVerticalGroup(
                centerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(centerPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(schedulePanel, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(downloadButton)
                                .addContainerGap(101, Short.MAX_VALUE))
        );

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        jScrollPane.setViewportView(mainPanel);

        add(jScrollPane, BorderLayout.CENTER);

        bottomPanel.setBackground(new Color(255, 255, 255));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));

        if(classIndex.getMain().getUser().isAdministrator()) {
            uploadButton.setText("Uploader");
        }
        uploadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Upload(classIndex.getAppLayout(),true,classIndex).setVisible(true);
            }
        });
        bottomPanel.add(uploadButton);

        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private JPanel composeScheduleItem(com.spanel.beans.File schedule, int i) {
        JPanel itemPanel;
        JButton itemDownloadButton;
        JLabel itemLabel;

        itemLabel = new JLabel();
        itemDownloadButton = new JButton();
        itemPanel = new JPanel();

        itemPanel.setLayout(new GridLayout(1, 2, 2, 5));
        itemPanel.setPreferredSize(new Dimension(100, 25));

        itemLabel.setForeground(new Color(0, 51, 102));
        itemLabel.setText(schedule.getDate().toString());
        itemPanel.add(itemLabel);

        itemDownloadButton.setText("Télécharger");
        itemDownloadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (i%2==0) itemPanel.setBackground(Color.gray);
        itemDownloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemDownloadButtonActionPerformed(schedule);
            }
        });
        itemPanel.add(itemDownloadButton);
        System.out.println("sdf");
        return itemPanel;
    }

    private void itemDownloadButtonActionPerformed(com.spanel.beans.File schedule) {
        FileController.saveFileOnDisk(file1.getPath(),schedule.getName());
        JOptionPane.showMessageDialog(this, "the file "+schedule.getName()+" has been downloaded in C:\\Users\\"+System.getProperty("user.name")+"\\Downloads", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

    }

    private void downloadButtonActionPerformed(ActionEvent evt) {
        FileController.saveFileOnDisk(file1.getPath(),file1.getName());
        JOptionPane.showMessageDialog(this, "the file "+file1.getName()+" has been downloaded in C:\\Users\\"+System.getProperty("user.name")+"\\Downloads", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }


}
