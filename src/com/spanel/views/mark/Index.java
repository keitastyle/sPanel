package com.spanel.views.mark;

import com.spanel.beans.Class;
import com.spanel.beans.Exam;
import com.spanel.beans.Professor;
import com.spanel.beans.User;
import com.spanel.dao.DAOFactory;
import com.spanel.dao.ExamDAO;
import com.spanel.dao.UserDAO;
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

    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JPanel headerPanel;
    private JPanel itemsContainerPanel;
    private JButton addButton;
    private JButton recapButton;
    private JLabel courseLabel;
    private JLabel typeLabel;
    private JLabel dateLabel;
    private JScrollPane jScrollPane;
    private JPanel mainPanel;
    private JPanel topPanel;

    private ClassIndex classIndex;

    public Index(ClassIndex classIndex, java.util.List<Exam> exams) {
        this.classIndex = classIndex;
        initComponents();

        DAOFactory daoFactory = classIndex.getMain().getDaoFactory();
        itemsContainerPanel.setLayout(new GridLayout(exams.size(), 1));
        for(Exam exam : exams){
            itemsContainerPanel.add(composeMarkItem(exam, daoFactory));
        }
    }

    private void addButtonActionPerformed(ActionEvent evt) {
        User user = classIndex.getMain().getUser();
        DAOFactory daoFactory = classIndex.getMain().getDaoFactory();
        Class classroom = classIndex.getClassroom();
        UserDAO userDAO = daoFactory.getUserDao();
        ExamDAO examDAO = daoFactory.getExamDAO();
        List<User> students = userDAO.findAllStudentsInClass(classroom.getId());
        List<Exam> exams = examDAO.findAllWithoutResults(classroom, (Professor) user.getUserable(daoFactory));
        new Create(this, true, exams, students).setVisible(true);
    }

    private void recapButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void initComponents() {
        topPanel = new JPanel();
        recapButton = new JButton();
        centerPanel = new JPanel();
        jScrollPane = new JScrollPane();
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        courseLabel = new JLabel();
        typeLabel = new JLabel();
        dateLabel = new JLabel();
        itemsContainerPanel = new JPanel();
        bottomPanel = new JPanel();
        addButton = new JButton();

        DAOFactory daoFactory =  classIndex.getMain().getDaoFactory();
        User user = classIndex.getMain().getUser();

        setBackground(new Color(255, 255, 255));
        setMinimumSize(null);
        setLayout(new BorderLayout());

        topPanel.setBackground(new Color(255, 255, 255));
        topPanel.setPreferredSize(null);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));

        recapButton.setText("Récapitulatif");
        recapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                recapButtonActionPerformed(evt);
            }
        });
        topPanel.add(recapButton);

        add(topPanel, BorderLayout.PAGE_START);

        centerPanel.setBackground(new Color(255, 255, 255));
        centerPanel.setLayout(new BorderLayout());

        jScrollPane.setBackground(new Color(255, 255, 255));
        jScrollPane.setBorder(null);

        mainPanel.setBackground(new Color(255, 255, 255));

        headerPanel.setBackground(new Color(255, 255, 255));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204, 204, 204)));
        headerPanel.setLayout(new GridLayout(1, 3));

        courseLabel.setFont(new Font("Calibri", 1, 14)); // NOI18N
        courseLabel.setForeground(new Color(0, 102, 153));
        courseLabel.setText("Matière");
        headerPanel.add(courseLabel);

        typeLabel.setFont(new Font("Calibri", 1, 14)); // NOI18N
        typeLabel.setForeground(new Color(0, 102, 153));
        typeLabel.setText("Type");
        headerPanel.add(typeLabel);

        dateLabel.setFont(new Font("Calibri", 1, 14)); // NOI18N
        dateLabel.setForeground(new Color(0, 102, 153));
        dateLabel.setText("Date");
        headerPanel.add(dateLabel);

        itemsContainerPanel.setBackground(new Color(255, 255, 255));

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(itemsContainerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(itemsContainerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(366, Short.MAX_VALUE))
        );

        jScrollPane.setViewportView(mainPanel);

        centerPanel.add(jScrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        bottomPanel.setBackground(new Color(255, 255, 255));
        bottomPanel.setPreferredSize(null);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));

        addButton.setText("Ajouter");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(addButton);

        if(user.isProfessor())
            add(bottomPanel, BorderLayout.PAGE_END);
    }

    private JPanel composeMarkItem(Exam exam, DAOFactory daoFactory){
        JLabel itemCourseNameLabel;
        JPanel itemPanel;
        JLabel itemTypeLabel;
        JLabel itemDateLabel;

        itemPanel = new JPanel();
        itemCourseNameLabel = new JLabel();
        itemTypeLabel = new JLabel();
        itemDateLabel = new JLabel();

        itemPanel.setBackground(new Color(255, 255, 255));
        itemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204, 204, 204)));
        itemPanel.setMinimumSize(new Dimension(237, 25));
        itemPanel.setLayout(new GridLayout(1, 3));

        itemCourseNameLabel.setFont(itemCourseNameLabel.getFont().deriveFont(itemCourseNameLabel.getFont().getStyle() | Font.BOLD));
        itemCourseNameLabel.setText(exam.getAffectation(daoFactory).getCourse(daoFactory).getName());
        itemCourseNameLabel.setMaximumSize(new Dimension(79, 25));
        itemCourseNameLabel.setMinimumSize(new Dimension(79, 25));
        itemCourseNameLabel.setPreferredSize(new Dimension(79, 25));
        itemPanel.add(itemCourseNameLabel);

        itemTypeLabel.setText(exam.getType());
        itemPanel.add(itemTypeLabel);

        itemDateLabel.setText(exam.getDate());
        itemPanel.add(itemDateLabel);
        itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        itemPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                //viewMarks(classroom);
            }
        });

        return itemPanel;
    }

    public ClassIndex getClassIndex() {
        return classIndex;
    }
}
