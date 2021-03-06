package com.spanel.views.module.files;

import com.spanel.beans.Affectation;
import com.spanel.views.ClassIndex;

import javax.swing.*;

/**
 * Created by Joel on 05/01/2017.
 */
public class Create extends JDialog {
    private ClassIndex classIndex;
    private JLabel fileLabel;
    private JPanel mainPanel;
    private JButton postButton;
    private JComboBox<String> typeComboBox;
    private JLabel typeLabel;
    private JButton uploadButton;

    private Index index;

    private Affectation affectation;

    public Create(ClassIndex classIndex,Index index, boolean modal, Affectation affectation) {
        super(index.getIndex().getClassIndex().getAppLayout(), modal);
        this.affectation = affectation;
        this.index = index;
        this.classIndex = classIndex;
        initComponents();
    }

    public Create(Index index, boolean b, Affectation affectation) {
    }

    private void postButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new Upload(index.getIndex().getClassIndex().getAppLayout(), true, classIndex,affectation,typeComboBox.getSelectedItem().toString()).setVisible(true);
    }

    private void initComponents() {

        mainPanel = new JPanel();
        typeLabel = new JLabel();
        fileLabel = new JLabel();
        typeComboBox = new JComboBox<>();
        uploadButton = new JButton();
        postButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Uploader polycope");

        typeLabel.setText("Type :");

        fileLabel.setText("Fichier : ");

        typeComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Cours", "TD", "TP", "Autres" }));

        uploadButton.setText("Choisir le fichier");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        postButton.setText("Publier");
        postButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postButtonActionPerformed(evt);
            }
        });

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(typeLabel)
                                                        .addComponent(fileLabel))
                                                .addGap(58, 58, 58)
                                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(typeComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(uploadButton, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                                        .addComponent(postButton))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(typeLabel)
                                        .addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(uploadButton)
                                        .addComponent(fileLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(postButton)
                                .addContainerGap())
        );

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }
}
