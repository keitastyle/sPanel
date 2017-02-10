package com.spanel.views.profile;

import com.spanel.beans.*;
import com.spanel.beans.Class;
import com.spanel.dao.DAOFactory;
import com.spanel.views.Layout;

import javax.swing.*;

/**
 * Created by Joel on 05/01/2017.
 */
public class View extends JDialog {

    User user;
    Layout parent;

    public View(Layout parent, boolean modal, User user) {
        super(parent, modal);
        this.user = user;
        this.parent = parent;

        initComponents();
        DAOFactory daoFactory = parent.getMain().getDaoFactory();
        int i;
        String [] list;
        if (user.isAdministrator()){
            i=2;
            list = new String[i];
            list[0]="Mr "+user.getFirstName()+" "+user.getLastName();
            list[1]= getAdministrator(user).getFunction();
            setTitle(list[0]);
        }else if (user.isStudent()){
            list = new String[3];
            i=1;
            Class classroom = daoFactory.getClassDAO().find(getStudent(user).getClassId());

            list[0]=user.getFirstName()+" "+user.getLastName();

            if (classroom != null){
                i=3;
                Department department  = daoFactory.getDepartmentDAO().find(classroom.getDepartmentId());
                list[1]= daoFactory.getClassDAO().find(getStudent(user).getClassId()).getName();
                list[2]=department.getName();
            }
            setTitle(list[0]);

        }else {
            i=2;

            list = new String[i];
            list[0] = getProfessor(user).getGrade()+" "+user.getFirstName()+" "+user.getLastName();
            list[1] = daoFactory.getDepartmentDAO().find(getProfessor(user).getDepartmentId()).getName();

            setTitle(list[0]);
        }
        userInformationPanel.setLayout(new java.awt.GridLayout(i, 0, 0, 5));

        for (int j=0;j<i;j++){
            userInformationPanel.add(composeItem(list[j]));
        }

        phoneValueLabel.setText(user.getPhone().toString());
        emailValueLabel.setText(user.getEmail());

    }
    private void initComponents() {

        mainPanel = new JPanel();
        leftPanel = new JPanel();
        picturePanel = new JPanel();
        rightPanel = new JPanel();
        userInformationPanel = new JPanel();
        itemLabe1l = new JLabel();
        itemLabel3 = new JLabel();
        userDetailsPanel = new JPanel();
        phoneLabel = new JLabel();
        emailLabel = new JLabel();
        phoneValueLabel = new JLabel();
        emailValueLabel = new JLabel();
        editButton = new JButton();
        itemLabel2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.GridLayout(1, 2));

        leftPanel.setBackground(new java.awt.Color(255, 255, 255));

        GroupLayout picturePanelLayout = new GroupLayout(picturePanel);
        picturePanel.setLayout(picturePanelLayout);
        picturePanelLayout.setHorizontalGroup(
                picturePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 180, Short.MAX_VALUE)
        );
        picturePanelLayout.setVerticalGroup(
                picturePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 180, Short.MAX_VALUE)
        );

        GroupLayout leftPanelLayout = new GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
                leftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(picturePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
                leftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(picturePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        mainPanel.add(leftPanel);

        rightPanel.setBackground(new java.awt.Color(255, 255, 255));

        userInformationPanel.setBackground(new java.awt.Color(255, 255, 255));


        userDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));

        phoneLabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        phoneLabel.setForeground(new java.awt.Color(0, 102, 153));
        phoneLabel.setText("Tel : ");

        emailLabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(0, 102, 153));
        emailLabel.setText("Email : ");

        phoneValueLabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        //phoneValueLabel.setText(user.getPhone().toString());

        emailValueLabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        //emailValueLabel.setText(user.getEmail());

        GroupLayout userDetailsPanelLayout = new GroupLayout(userDetailsPanel);
        userDetailsPanel.setLayout(userDetailsPanelLayout);
        userDetailsPanelLayout.setHorizontalGroup(
                userDetailsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userDetailsPanelLayout.createSequentialGroup()
                                .addComponent(emailLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailValueLabel, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                        .addGroup(userDetailsPanelLayout.createSequentialGroup()
                                .addComponent(phoneLabel)
                                .addGap(18, 18, 18)
                                .addComponent(phoneValueLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userDetailsPanelLayout.setVerticalGroup(
                userDetailsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userDetailsPanelLayout.createSequentialGroup()
                                .addGroup(userDetailsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(phoneLabel)
                                        .addComponent(phoneValueLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(userDetailsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailLabel)
                                        .addComponent(emailValueLabel)))
        );

        editButton.setText("Modifier");

        GroupLayout rightPanelLayout = new GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userInformationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(userDetailsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(rightPanelLayout.createSequentialGroup()
                                                .addComponent(editButton)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(userInformationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userDetailsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(editButton)
                                .addContainerGap())
        );

        mainPanel.add(rightPanel);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }

    private Professor getProfessor (User user){
        return parent.getMain().getDaoFactory().getProfessorDao().find(user);
    }
    private Student getStudent (User user){
        return parent.getMain().getDaoFactory().getStudentDao().find(user.getUserableId());
    }

    private Administrator getAdministrator (User user){
        return parent.getMain().getDaoFactory().getAdministratorDao().find(user);
    }

    private JLabel composeItem (String string){
        JLabel itemLabel1;

        itemLabel1= new JLabel();
        itemLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        itemLabel1.setText(string);

       return itemLabel1;
    }

    private JButton editButton;
    private JLabel emailLabel;
    private JLabel emailValueLabel;
    private JLabel itemLabe1l;
    private JLabel itemLabel2;
    private JLabel itemLabel3;
    private JPanel leftPanel;
    private JPanel mainPanel;
    private JLabel phoneLabel;
    private JLabel phoneValueLabel;
    private JPanel picturePanel;
    private JPanel rightPanel;
    private JPanel userDetailsPanel;
    private JPanel userInformationPanel;
}