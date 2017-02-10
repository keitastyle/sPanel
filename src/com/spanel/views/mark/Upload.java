package com.spanel.views.mark;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by Joel on 23/12/2016.
 */
public class Upload extends JDialog {
    private JFileChooser jFileChooser;

    public Upload(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    private void initComponents() {

        jFileChooser = new JFileChooser();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jFileChooser.setFileFilter(new FileNameExtensionFilter("Classeur Excel", "xls", "xlxs"));
        jFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooserActionPerformed(evt);
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
        //
    }

}
