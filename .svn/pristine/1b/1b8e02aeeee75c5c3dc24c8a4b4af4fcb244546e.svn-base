package com.spanel.views.announce;


import com.spanel.beans.Announce;
import com.spanel.beans.Professor;
import com.spanel.beans.User;
import com.spanel.dao.DAOFactory;
import com.spanel.views.ClassIndex;

import com.spanel.views.ClassIndex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Joel on 24/11/2016.
 */
public class Index extends JPanel {
    private JButton addButton;
    private JPanel bottomPanel;
    private JPanel itemsContainerPanel;
    private JScrollPane jScrollPane;
    private JPanel mainPanel;


    private ClassIndex classIndex;

    public Index(ClassIndex classIndex,List<Announce> announces) {

        this.classIndex = classIndex;

        initComponents();
        DAOFactory daoFactory = classIndex.getMain().getDaoFactory();
        int i = announces.size();
        int size = i/3+1;
        itemsContainerPanel.setLayout(new java.awt.GridLayout(size, 3, 10, 10));
        itemsContainerPanel.setPreferredSize(new java.awt.Dimension(750, (size * 215)));
        for (Announce announce : announces)
            itemsContainerPanel.add(composeAnnounceItem(announce));
        if(i<3 & i!=0) {
            for (int j = 0; j < 3 - i; j++){
                JPanel whitePanel = new JPanel();
                whitePanel.setBackground(Color.white);
                itemsContainerPanel.add(whitePanel);
            }
        }
    }

    private void initComponents() {

        jScrollPane = new JScrollPane();
        mainPanel = new JPanel();
        itemsContainerPanel = new JPanel();
        bottomPanel = new JPanel();
        addButton = new JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(770, 520));
        setLayout(new java.awt.BorderLayout());

        jScrollPane.setBorder(null);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        itemsContainerPanel.setBackground(new java.awt.Color(255, 255, 255));

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(itemsContainerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(itemsContainerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(251, Short.MAX_VALUE))
        );

        jScrollPane.setViewportView(mainPanel);

        add(jScrollPane, java.awt.BorderLayout.CENTER);

        bottomPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 10));

        addButton.setText("Ajouter");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAnnounce(e);
            }
        });
        bottomPanel.add(addButton);

        add(bottomPanel, java.awt.BorderLayout.PAGE_END);
    }

    public void createAnnounce (ActionEvent e){

        String[] typesList ={ "Rattrapage de cours", "Report de cours", "Annulation de cours", "Report d'examen", "Annulation d'examen" , "Sortie" , "Conférence", "Autre" };
        new Create(this,true,typesList).setVisible(true);
    }

    public ClassIndex getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(ClassIndex classIndex) {
        this.classIndex = classIndex;
    }

    private JPanel composeAnnounceItem(Announce announce){

        JPanel itemPanel;
        JLabel titleLabel;
        JLabel typeLabel;
        JLabel dateLabel;
        JTextArea descripiotnTextArea;
        JScrollPane descriptionScrollPane;
        JLabel authorLabel;


        itemPanel = new JPanel();
        typeLabel = new JLabel();
        titleLabel = new JLabel();
        dateLabel = new JLabel();
        authorLabel = new JLabel();
        descriptionScrollPane = new JScrollPane();
        descripiotnTextArea = new JTextArea();


        itemPanel.setPreferredSize(new java.awt.Dimension(0, 100));

        typeLabel.setFont(typeLabel.getFont().deriveFont(typeLabel.getFont().getStyle() | java.awt.Font.BOLD));
        typeLabel.setText(announce.getType());

        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getSize()+2f));
        titleLabel.setText(announce.getTitle());

        dateLabel.setForeground(new java.awt.Color(0, 102, 204));

        String [] parts= announce.getDate().toString().split("-");
        String date =parts[2]+"/"+parts[1]+"/"+parts[0];
        dateLabel.setText(date);


        authorLabel.setText("Mr "+classIndex.getAppLayout().getMain().getDaoFactory().getUserDao().find(announce.getAuthorId()).getLastName());

        descriptionScrollPane.setBorder(null);

        descripiotnTextArea.setEditable(false);
        descripiotnTextArea.setBackground(new java.awt.Color(240, 240, 240));
        descripiotnTextArea.setColumns(20);
        descripiotnTextArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        descripiotnTextArea.setLineWrap(true);
        descripiotnTextArea.setRows(5);
        descripiotnTextArea.setText(announce.getDescription());
        descripiotnTextArea.setFocusable(false);
        descriptionScrollPane.setViewportView(descripiotnTextArea);


        GroupLayout itemPanelLayout = new GroupLayout(itemPanel);
        itemPanel.setLayout(itemPanelLayout);
        itemPanelLayout.setHorizontalGroup(
                itemPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(itemPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(itemPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dateLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(descriptionScrollPane)
                                        .addComponent(authorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(itemPanelLayout.createSequentialGroup()
                                                .addComponent(typeLabel, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                                .addContainerGap())
        );
        itemPanelLayout.setVerticalGroup(
                itemPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(itemPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(itemPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(typeLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(titleLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(authorLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionScrollPane, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addContainerGap())
        );


        User user = getClassIndex().getMain().getUser();
        if(user.isProfessor()){
            if(announce.getAuthorId()== user.getId()){
                itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                itemPanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        editAnnounce(announce);
                    }
                });
            }
        }else
            if (user.isAdministrator()){
                itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                itemPanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        editAnnounce(announce);
                    }
                });
            }
        return itemPanel;
    }

    private void  editAnnounce(Announce announce){

        String[] typesList ={ "Rattrapage de cours", "Report de cours", "Annulation de cours", "Report d'examen", "Annulation d'examen" , "Sortie" , "Conférence", "Autre" };
        new Edit(this,true,typesList,announce).setVisible(true);

    }
}
