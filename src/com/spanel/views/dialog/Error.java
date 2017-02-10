package com.spanel.views.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Joel on 02/12/2016.
 */
public class Error extends JDialog {
    private JLabel errorLabel;
    private JPanel errorsContainerPanel;
    private JSeparator jSeparator;
    private JPanel mainPanel;
    private JPanel messagePanel;
    private JButton okButton;
    private JPanel optionPanel;

    public Error(Frame parent, boolean modal, java.util.List<String> errors) {
        super(parent, "Erreur", modal);

        errorsContainerPanel = new JPanel();
        errorsContainerPanel.setLayout(new GridLayout(errors.size(), 1));
        errorsContainerPanel.setPreferredSize(new Dimension(0, errors.size() * 15));
        for(String e: errors){
            JLabel jLabel =  new JLabel();
            jLabel.setFont(jLabel.getFont().deriveFont(jLabel.getFont().getSize()+1f));
            jLabel.setForeground(new Color(102, 0, 0));
            jLabel.setText(e);
            errorsContainerPanel.add(jLabel);
        }

        initComponents();
        setVisible(true);
    }
    
    private void initComponents(){
        mainPanel = new JPanel();
        optionPanel = new JPanel();
        okButton = new JButton();
        messagePanel = new JPanel();
        errorLabel = new JLabel();
        jSeparator = new JSeparator();


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());

        optionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        okButton.setText("Ok");
        okButton.setMaximumSize(new Dimension(65, 33));
        okButton.setMinimumSize(new Dimension(65, 25));
        okButton.setPreferredSize(new Dimension(65, 25));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        optionPanel.add(okButton);

        mainPanel.add(optionPanel, BorderLayout.PAGE_END);

        errorLabel.setBackground(new Color(102, 102, 102));
        errorLabel.setFont(new Font("Calibri Light", 0, 18)); // NOI18N
        errorLabel.setForeground(new Color(102, 102, 102));
        errorLabel.setText("Certaines erreurs sont survenues");

        jSeparator.setForeground(new Color(204, 204, 204));

        GroupLayout messagePanelLayout = new GroupLayout(messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
                messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(messagePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator)
                                        .addGroup(messagePanelLayout.createSequentialGroup()
                                                .addComponent(errorLabel)
                                                .addGap(0, 150, Short.MAX_VALUE))
                                        .addComponent(errorsContainerPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        messagePanelLayout.setVerticalGroup(
                messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(messagePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(errorLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(errorsContainerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
        );

        mainPanel.add(messagePanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    private void okButtonActionPerformed(ActionEvent evt) {
        dispose();
    }

}
