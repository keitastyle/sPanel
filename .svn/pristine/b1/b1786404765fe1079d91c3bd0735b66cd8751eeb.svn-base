package com.spanel.views.schedule;

import com.spanel.App;
import com.spanel.app.Request;
import com.spanel.beans.Class;
import com.spanel.beans.Schedule;
import com.spanel.beans.User;
import com.spanel.controllers.FileController;


import com.spanel.dao.ScheduleDAO;
import com.spanel.forms.FileCreationForm;
import com.spanel.views.Layout;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.time.LocalDate;


/**
 * Created by Joel on 23/12/2016.
 */
public class Upload extends JDialog {
    private JFileChooser jFileChooser;
    private App main;
    private Layout parent;
    public File fichier;
    private User user;
    private int i = 0;
    private Class classroom;
    public Upload(Layout parent, boolean modal, com.spanel.views.ClassIndex classroom) {
        super(parent, modal);
        this.main = classroom.getMain();
        this.classroom = classroom.getClassroom();
        initComponents();
    }
    public File getFichier(){
        return fichier;
    }
    private void initComponents() {

        jFileChooser = new JFileChooser();
        user = main.getUser();


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jFileChooser.setFileFilter(new FileNameExtensionFilter("Image", "png", "jpg", "jpeg"));
        jFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(evt.getActionCommand().equals("CancelSelection")){
                    if((JOptionPane.showConfirmDialog(null, "No file has been selected!", "Error", JOptionPane.OK_CANCEL_OPTION))==JOptionPane.OK_OPTION) {
                        jFileChooser2ActionPerformed(evt);
                    }else {
                        //
                    }

                }else {
                    i++;
                    com.spanel.beans.File file = new com.spanel.beans.File();
                    fichier = jFileChooser.getSelectedFile();
                    ScheduleDAO scheduleDAO = main.getDaoFactory().getScheduleDAO();
                    Schedule schedule = new Schedule();

                    FileController.saveFile(fichier.getAbsolutePath(),fichier.getName());
                    Request request = new Request(parent, main);
                    request.addField(FileCreationForm.AUTHOR_ID_FIELD,main.getUser().getId().toString());
                    request.addField(FileCreationForm.FILE_NAME_FIELD,fichier.getName());
                    request.addField(FileCreationForm.FILE_PATH_FIELD,"assets/uploads/img/"+fichier.getName());
                    request.addField(FileCreationForm.FILE_DATE_FIELD,""+ LocalDate.now()+"");
                    file=FileController.create(request);

                    if(file!=null){
                        schedule.setClassId(classroom.getId());
                        schedule.setFileId(file.getId());
                        System.out.println("id ="+file.getId());
                        scheduleDAO.create(schedule);
                        jFileChooserActionPerformed(evt);
                    }


                }
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jFileChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jFileChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    private void jFileChooserActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getName()+" from "+evt.getActionCommand());
        this.dispose();

    }
    private void jFileChooser2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("No file has been selected!");
        this.dispose();

    }

}
