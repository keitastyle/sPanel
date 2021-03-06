package com.spanel.views.allocation;
import com.spanel.app.Request;
import com.spanel.beans.Class;
import com.spanel.beans.User;
import com.spanel.controllers.StudentController;
import com.spanel.forms.StudentAllocationForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Joel on 03/12/2016.
 */
public class Students extends JPanel {
    private JButton confirmButton;
    private JLabel emailLabel;
    private JLabel firstNameLabel;
    private JLabel gapLabel;
    private JPanel headerPanel;
    private JPanel itemsContainerPanel;
    private JScrollPane jScrollPane;
    private JLabel lastNameLabel;
    private JPanel mainPanel;
    private JCheckBox itemCheckBox;
    private JLabel itemEmailLabel;
    private JLabel itemFirstNameLabel;
    private JLabel itemLastNameLabel;
    private JPanel itemLeftPanel;
    private JPanel itemPanel;
    private JPanel itemPicturePanel;
    int i=1;

    private Index index;
    private List<Long> selectedStudent;


    public Students(Index index, List<User> students){

        this.index = index;

        setBackground(Color.WHITE);
        initComponents();
        itemsContainerPanel.setLayout(new GridLayout(students.size(), 1));
        for(User user : students) {
            itemsContainerPanel.add(composeStudentItem(user,i));
            i++;
        }
        selectedStudent = new ArrayList<>();
    }

    private void allocateStudents() {
        Request request = new Request(index.getClassIndex().getAppLayout(), index.getMain());
        String studentsList = new String ();
        for(Long id: selectedStudent){
            studentsList+= "," + id;

        }
        request.addField(StudentAllocationForm.STUDENTS_ID_FIELD, studentsList);
        request.addField(StudentAllocationForm.CLASS_ID_FIELD, getClassroom().getId().toString());
        if(StudentController.allocate(request)){
            index.setTabComponents();
            index.getClassIndex().setTabComponents(5);
        }
    }

    private void initComponents() {
        jScrollPane = new JScrollPane();
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        gapLabel = new JLabel();
        lastNameLabel = new JLabel();
        firstNameLabel = new JLabel();
        emailLabel = new JLabel();
        confirmButton = new JButton();
        itemsContainerPanel = new JPanel();

        setMinimumSize(new Dimension(21, 22));
        setPreferredSize(new Dimension(790, 530));
        setLayout(new BorderLayout());

        jScrollPane.setBorder(null);

        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setPreferredSize(new Dimension(770, 520));

        headerPanel.setBackground(new Color(255, 255, 255));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204, 204, 204)));
        headerPanel.setLayout(new GridLayout(1, 4));
        headerPanel.add(gapLabel);

        lastNameLabel.setFont(new java.awt.Font("Calibri", 1, 14));
        lastNameLabel.setText("Nom");
        lastNameLabel.setForeground(new java.awt.Color(0, 102, 153));
        headerPanel.add(lastNameLabel);

        firstNameLabel.setFont(new java.awt.Font("Calibri", 1, 14));
        firstNameLabel.setText("Prénom");
        firstNameLabel.setForeground(new java.awt.Color(0, 102, 153));
        headerPanel.add(firstNameLabel);

        emailLabel.setFont(new java.awt.Font("Calibri", 1, 14));
        emailLabel.setText("Email");
        emailLabel.setForeground(new java.awt.Color(0, 102, 153));
        headerPanel.add(emailLabel);

        confirmButton.setFont(new java.awt.Font("Calibri", 1, 14));
        confirmButton.setText("Confirmer");


        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(confirmButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 671, Short.MAX_VALUE))
                                        .addComponent(itemsContainerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(itemsContainerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(confirmButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(391, Short.MAX_VALUE))
        );

        jScrollPane.setViewportView(mainPanel);

        add(jScrollPane, BorderLayout.CENTER);
    }

    private void itemCheckBoxStateChanged(User user,ActionEvent e){
        if(((JCheckBox )e.getSource()).isSelected()){
            selectedStudent.add(user.getUserableId());
        }else{
            selectedStudent.remove(user.getUserableId());
        }
    }

    private JPanel composeStudentItem(User user,int i){
        itemPanel = new JPanel();
        itemLeftPanel = new JPanel();
        itemCheckBox = new JCheckBox();
        itemPicturePanel = new JPanel();
        itemFirstNameLabel = new JLabel();
        itemLastNameLabel = new JLabel();
        itemEmailLabel = new JLabel();

        itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        itemPanel.setLayout(new GridLayout(1, 4));

        itemPicturePanel.setBackground(new Color(204, 204, 204));

        GroupLayout itemPicturePanelLayout = new GroupLayout(itemPicturePanel);
        itemPicturePanel.setLayout(itemPicturePanelLayout);
        itemPicturePanelLayout.setHorizontalGroup(
                itemPicturePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 38, Short.MAX_VALUE)
        );
        itemPicturePanelLayout.setVerticalGroup(
                itemPicturePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        GroupLayout itemLeftPanelLayout = new GroupLayout(itemLeftPanel);
        itemLeftPanel.setLayout(itemLeftPanelLayout);
        itemLeftPanelLayout.setHorizontalGroup(
                itemLeftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(itemLeftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(itemCheckBox)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(itemPicturePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
        );
        itemLeftPanelLayout.setVerticalGroup(
                itemLeftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(itemLeftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(itemCheckBox)
                                .addContainerGap(10, Short.MAX_VALUE))
                        .addComponent(itemPicturePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        itemPanel.add(itemLeftPanel);

        itemFirstNameLabel.setText(user.getFirstName());
        itemPanel.add(itemFirstNameLabel);

        itemLastNameLabel.setText(user.getLastName());
        itemPanel.add(itemLastNameLabel);

        itemEmailLabel.setText(user.getEmail());
        itemPanel.add(itemEmailLabel);
        if(i%2==1){
            itemLeftPanel.setBackground(Color.white);
            itemPanel.setBackground(Color.white);
        }

        itemCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                System.out.println(itemCheckBox.isSelected());
                itemCheckBoxStateChanged(user,e);
            }
        });


        return itemPanel;
    }

    private Class getClassroom(){ return index.getClassroom(); }
}
