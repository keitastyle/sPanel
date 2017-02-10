package com.spanel.views;


import com.spanel.App;
import com.spanel.app.Request;
import com.spanel.beans.Department;
import com.spanel.controllers.ClassController;
import com.spanel.controllers.CourseController;
import com.spanel.controllers.ModuleController;
import com.spanel.forms.ClassCreationForm;
import com.spanel.forms.CourseCreationForm;
import com.spanel.forms.ModuleCreationForm;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Joel on 04/12/2016.
 */
public class Create extends JDialog {
    private App main;
    private Layout parent;

    private JButton classAddButton;
    private JLabel classHeadLabel;
    private JPanel classPanel;
    private JLabel classTitleLabel;
    private JTextField classTitleTextField;
    private JButton courseAddButton;
    private JLabel courseHeadLabel;
    private JPanel coursePanel;
    private JLabel courseTitleLabel;
    private JTextField courseTitleTextField;
    private JComboBox<String> departmentComboBox;
    private JLabel departmentLabel;
    private JButton moduleAddButton;
    private JLabel moduleHeadLabel;
    private JPanel modulePanel;
    private JLabel moduleTitleLabel;
    private JTextField moduleTitleTextField;
    private JTabbedPane tabbedPane;

    public Create(Layout parent, boolean modal, java.util.List<Department> departments) {
        super(parent, modal);
        main = parent.getMain();
        this.parent = parent;

        String departmentsList[] = new String[departments.size()];
        for(int i =0; i<departments.size(); i++){
            departmentsList[i] = departments.get(i).getName();
        }

        initComponents();
        departmentComboBox.setModel(new DefaultComboBoxModel<>(departmentsList));
    }

    public void createClass(ActionEvent event){
        Request request = new Request(parent, main);
        request.addField(ClassCreationForm.CLASS_NAME_FIELD, classTitleTextField.getText());
        request.addField(ClassCreationForm.DEPARTMENT_NAME_FIELD, (String) departmentComboBox.getSelectedItem());

        if(ClassController.create(request)) classTitleTextField.setText("");
    }

    public void createModule(ActionEvent event){
        Request request = new Request(parent, main);
        request.addField(ModuleCreationForm.MODULE_NAME_FIELD, moduleTitleTextField.getText());
        if(ModuleController.create(request)) moduleTitleTextField.setText("");
    }

    public void createCourse(ActionEvent event){
        Request request = new Request(parent, main);
        request.addField(CourseCreationForm.COURSE_NAME_FIELD, courseTitleTextField.getText());
        if(CourseController.create(request)) courseTitleTextField.setText("");
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
        classPanel = new JPanel();
        classHeadLabel = new JLabel();
        classTitleLabel = new JLabel();
        classTitleTextField = new JTextField();
        classAddButton = new JButton();
        departmentLabel = new JLabel();
        departmentComboBox = new JComboBox<>();
        modulePanel = new JPanel();
        moduleHeadLabel = new JLabel();
        moduleTitleLabel = new JLabel();
        moduleTitleTextField = new JTextField();
        moduleAddButton = new JButton();
        coursePanel = new JPanel();
        courseHeadLabel = new JLabel();
        courseTitleLabel = new JLabel();
        courseTitleTextField = new JTextField();
        courseAddButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Création");
        setAlwaysOnTop(true);
        setBackground(new Color(255, 255, 255));

        classPanel.setBackground(new Color(255, 255, 255));

        classHeadLabel.setFont(classHeadLabel.getFont().deriveFont(classHeadLabel.getFont().getSize()+2f));
        classHeadLabel.setForeground(new Color(0, 102, 255));
        classHeadLabel.setText("Nouvelle classe");

        classTitleLabel.setText("Titre :");

        classAddButton.setText("Ajouter");
        classAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createClass(e);
            }
        });

        departmentLabel.setText("Département :");

        GroupLayout classPanelLayout = new GroupLayout(classPanel);
        classPanel.setLayout(classPanelLayout);
        classPanelLayout.setHorizontalGroup(
                classPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(classPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(classPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(classTitleLabel)
                                        .addComponent(classHeadLabel)
                                        .addComponent(departmentLabel))
                                .addGap(29, 29, 29)
                                .addGroup(classPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(classTitleTextField, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                        .addGroup(classPanelLayout.createSequentialGroup()
                                                .addComponent(classAddButton)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(departmentComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        classPanelLayout.setVerticalGroup(
                classPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(classPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(classHeadLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(classPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(classTitleLabel)
                                        .addComponent(classTitleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(classPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(departmentLabel)
                                        .addComponent(departmentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(classAddButton)
                                .addContainerGap(156, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Classe", classPanel);

        modulePanel.setBackground(new Color(255, 255, 255));

        moduleHeadLabel.setFont(moduleHeadLabel.getFont().deriveFont(moduleHeadLabel.getFont().getSize()+2f));
        moduleHeadLabel.setForeground(new Color(0, 102, 255));
        moduleHeadLabel.setText("Nouveau module");

        moduleTitleLabel.setText("Titre :");

        moduleAddButton.setText("Ajouter");
        moduleAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createModule(e);
            }

        });

        GroupLayout modulePanelLayout = new GroupLayout(modulePanel);
        modulePanel.setLayout(modulePanelLayout);
        modulePanelLayout.setHorizontalGroup(
                modulePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(modulePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(modulePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(moduleHeadLabel)
                                        .addGroup(modulePanelLayout.createSequentialGroup()
                                                .addComponent(moduleTitleLabel)
                                                .addGap(87, 87, 87)
                                                .addGroup(modulePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(moduleAddButton)
                                                        .addComponent(moduleTitleTextField, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        modulePanelLayout.setVerticalGroup(
                modulePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(modulePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(moduleHeadLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(modulePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(moduleTitleLabel)
                                        .addComponent(moduleTitleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(moduleAddButton)
                                .addContainerGap(187, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Module", modulePanel);

        coursePanel.setBackground(new Color(255, 255, 255));

        courseHeadLabel.setFont(courseHeadLabel.getFont().deriveFont(courseHeadLabel.getFont().getSize()+2f));
        courseHeadLabel.setForeground(new Color(0, 102, 255));
        courseHeadLabel.setText("Nouvelle matière");

        courseTitleLabel.setText("Titre :");

        courseAddButton.setText("Ajouter");
        courseAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCourse(e);
            }
        });

        GroupLayout coursePanelLayout = new GroupLayout(coursePanel);
        coursePanel.setLayout(coursePanelLayout);
        coursePanelLayout.setHorizontalGroup(
                coursePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(coursePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(coursePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(courseHeadLabel)
                                        .addGroup(coursePanelLayout.createSequentialGroup()
                                                .addComponent(courseTitleLabel)
                                                .addGap(87, 87, 87)
                                                .addGroup(coursePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(courseAddButton)
                                                        .addComponent(courseTitleTextField, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        coursePanelLayout.setVerticalGroup(
                coursePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(coursePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(courseHeadLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(coursePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(courseTitleLabel)
                                        .addComponent(courseTitleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(courseAddButton)
                                .addContainerGap(187, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Matière", coursePanel);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        pack();
    }
}