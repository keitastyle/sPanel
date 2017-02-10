package com.spanel.views.module.files;

import com.spanel.App;
import com.spanel.app.Request;
import com.spanel.beans.Affectation;
import com.spanel.beans.Class;
import com.spanel.controllers.FileController;
import com.spanel.dao.AffectationFileDAO;
import com.spanel.dao.ScheduleDAO;
import com.spanel.forms.FileCreationForm;
import com.spanel.views.ClassIndex;
import com.spanel.views.Layout;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;


/**
 * Created by Joel on 23/12/2016.
 */
public class Upload extends JDialog {
    private JFileChooser jFileChooser;

    private App main;
    public File fichier;
    private Affectation affectation;
    private Layout parent;
    private String type;
    public Upload(Frame parent, boolean modal, ClassIndex classroom, Affectation affectation,String type) {
        super(parent, modal);
        this.main=classroom.getMain();
        this.affectation = affectation;
        this.type = type;
        initComponents();
    }
    private void initComponents() {

        jFileChooser = new JFileChooser();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //jFileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers"));
        jFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (evt.getActionCommand().equals("CancelSelection")) {
                    if ((JOptionPane.showConfirmDialog(null, "No file has been selected!", "Error", JOptionPane.OK_CANCEL_OPTION)) == JOptionPane.OK_OPTION) {
                        jFileChooser2ActionPerformed(evt);
                    } else {
                        //
                    }

                } else {
                    com.spanel.beans.File file = new com.spanel.beans.File();
                    fichier = jFileChooser.getSelectedFile();
                    AffectationFileDAO affectationFileDAO = main.getDaoFactory().getAffectationFileDAO();


                    FileController.saveFile(fichier.getAbsolutePath(), fichier.getName());
                    Request request = new Request(parent, main);
                    request.addField(FileCreationForm.AUTHOR_ID_FIELD, main.getUser().getId().toString());
                    request.addField(FileCreationForm.FILE_NAME_FIELD, fichier.getName());
                    request.addField(FileCreationForm.FILE_PATH_FIELD, "assets/uploads/img/" + fichier.getName());
                    request.addField(FileCreationForm.FILE_DATE_FIELD, "" + LocalDate.now() + "");
                    file = FileController.create(request);

                    if (file != null) {
                        affectationFileDAO.create(affectation.getId(),file.getId(),type );
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
        this.dispose();
    }
    private void   jFileChooser2ActionPerformed(java.awt.event.ActionEvent event){
            this.dispose();
    }

}
