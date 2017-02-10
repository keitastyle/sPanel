package com.spanel.views.announce;

import com.spanel.App;
import com.spanel.app.Request;
import com.spanel.beans.Announce;
import com.spanel.controllers.AnnounceController;
import com.spanel.forms.AnnounceCreationForm;
import com.spanel.forms.AnnounceUpdateForm;
import com.spanel.views.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

/**
 * Created by Joel on 24/11/2016.
 */
public class Edit extends JDialog {

    private JLabel dateLabel;
    private JFormattedTextField dateTextField;
    private JLabel descriptionLabel;
    private JScrollPane descriptionScrollPane;
    private JTextArea descriptionTextArea;
    private JPanel mainPanel;
    private JButton submitButton;
    private JLabel titleLabel;
    private JTextField titleTextField;
    private JComboBox<String> typeComboBox;
    private JLabel typeLabel;

    private Index index;
    private Long announceId;

    public Edit(Index index,boolean modal,String [] typesList,Announce announce) {

        super(index.getClassIndex().getAppLayout(), modal);

        this.index = index;
        this.announceId = announce.getId();

        initComponents();

        String [] parts= announce.getDate().toString().split("-");
        String date =parts[2]+"/"+parts[1]+"/"+parts[0];
        typeComboBox.setModel(new DefaultComboBoxModel<>(typesList));
        typeComboBox.setSelectedItem(announce.getType());
        dateTextField.setText(date);
        titleTextField.setText(announce.getTitle());
        descriptionTextArea.setText(announce.getDescription());
    }

    private void initComponents() {

        mainPanel = new JPanel();
        titleLabel = new JLabel();
        titleTextField = new JTextField();
        typeLabel = new JLabel();
        typeComboBox = new JComboBox<>();
        dateLabel = new JLabel();
        dateTextField = new JFormattedTextField();
        descriptionLabel = new JLabel();
        descriptionScrollPane = new JScrollPane();
        descriptionTextArea = new JTextArea();
        submitButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Publier une annonce");

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLabel.setText("Titre :");

        typeLabel.setText("Type :");


        dateLabel.setText("Date :");

        dateTextField.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(new java.text.SimpleDateFormat("dd/mm/yyyy"))));
        dateTextField.setText("dd/mm/yyyy");

        descriptionLabel.setText("Description :");

        descriptionScrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(new Font("Tahoma", 0, 11)); // NOI18N
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionScrollPane.setViewportView(descriptionTextArea);

        submitButton.setText("Modifier");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAnnounce(e);
            }
        });

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(titleLabel)
                                        .addComponent(typeLabel)
                                        .addComponent(dateLabel)
                                        .addComponent(descriptionLabel))
                                .addGap(79, 79, 79)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(titleTextField)
                                        .addComponent(descriptionScrollPane, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(submitButton)
                                                        .addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(titleLabel)
                                        .addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(typeLabel)
                                        .addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateLabel)
                                        .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(descriptionLabel)
                                        .addComponent(descriptionScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(submitButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    public void editAnnounce (ActionEvent e)  {

        Request request = new Request(getAppLayout(),getMain());


        request.addField(AnnounceUpdateForm.TITLE_FIELD,titleTextField.getText());
        request.addField(AnnounceUpdateForm.TYPE_FIELD,typeComboBox.getSelectedItem().toString());
        request.addField(AnnounceUpdateForm.DESCRIPTION,descriptionTextArea.getText().toString());
        request.addField(AnnounceUpdateForm.AUTHOR_ID_FIELD,getMain().getUser().getId().toString());
        request.addField(AnnounceUpdateForm.CLASS_ID_FIELD,this.index.getClassIndex().getClassroom().getId().toString());
        request.addField(AnnounceUpdateForm.EXPIRATION_DATE_FIELD,dateTextField.getText());
        request.addField(AnnounceUpdateForm.ID,announceId.toString());


        if(AnnounceController.update(request)){
            index.getClassIndex().setTabComponents(0);
            dispose();
        }
    }

    public App getMain(){ return index.getClassIndex().getMain();}
    public Layout getAppLayout(){
        return index.getClassIndex().getAppLayout();
    }

}
