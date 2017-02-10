package com.spanel.views.allocation;

import com.spanel.app.Request;
import com.spanel.beans.*;
import com.spanel.controllers.AffectationController;
import com.spanel.forms.CourseAllocationForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Joel on 03/12/2016.
 */
public class Courses extends JPanel {
    private JLabel coefficientLabel;
    private JSpinner coefficientSpinner;
    private JButton confirmButton;
    private JComboBox<String> courseComboBox;
    private JLabel courseLabel;
    private JLabel gapLabel;
    private JLabel hoursLabel;
    private JSpinner hoursSpinner;
    private JPanel formPanel;
    private JScrollPane jScrollPane;
    private JPanel leftPanel;
    private JPanel mainPanel;
    private JComboBox<String> moduleComboBox;
    private JLabel moduleLabel;
    private JComboBox<String> professorComboBox;
    private JLabel professorLabel;
    private JPanel rightPanel;

    private Index index;

    public Courses(
            Index index,
            java.util.List<Module> modules,
            java.util.List<Course> courses,
            java.util.List<User> professors) {

        this.index = index;
        setBackground(Color.WHITE);
        String[] modulesList = new String[modules.size()];
        String[] professorsList = new String[professors.size()];
        String[] coursesList = new String[courses.size()];
        for (int i = 0; i<modules.size(); i++){
            modulesList[i]=modules.get(i).getName();
        }
        for (int i = 0; i<courses.size(); i++){
            coursesList[i]=courses.get(i).getName();
        }
        for (int i = 0; i<professors.size(); i++){
            professorsList[i]=professors.get(i).getFirstName() + " " + professors.get(i).getLastName();
        }

        initComponents();

        courseComboBox.setModel(new DefaultComboBoxModel<>(coursesList));
        moduleComboBox.setModel(new DefaultComboBoxModel<>(modulesList));
        professorComboBox.setModel(new DefaultComboBoxModel<>(professorsList));

    }

    private void initComponents() {

        jScrollPane = new JScrollPane();
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        formPanel = new JPanel();
        moduleLabel = new JLabel();
        moduleComboBox = new JComboBox<>();
        courseLabel = new JLabel();
        courseComboBox = new JComboBox<>();
        professorLabel = new JLabel();
        professorComboBox = new JComboBox<>();
        hoursLabel = new JLabel();
        hoursSpinner = new JSpinner();
        coefficientLabel = new JLabel();
        coefficientSpinner = new JSpinner();
        gapLabel = new JLabel();
        confirmButton = new JButton();
        rightPanel = new JPanel();

        setLayout(new BorderLayout());

        jScrollPane.setBorder(null);
        jScrollPane.setPreferredSize(new Dimension(770, 520));

        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setPreferredSize(new Dimension(770, 520));
        mainPanel.setLayout(new GridLayout(1, 2));

        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(6, 2, 0, 15));

        moduleLabel.setText("Module :");
        formPanel.add(moduleLabel);

        formPanel.add(moduleComboBox);

        courseLabel.setText("Matière :");
        formPanel.add(courseLabel);

        formPanel.add(courseComboBox);

        professorLabel.setText("Professeur :");
        formPanel.add(professorLabel);

        formPanel.add(professorComboBox);

        hoursLabel.setText("Heures :");
        formPanel.add(hoursLabel);
        formPanel.add(hoursSpinner);

        coefficientLabel.setText("Coefficient :");
        formPanel.add(coefficientLabel);
        formPanel.add(coefficientSpinner);
        formPanel.add(gapLabel);

        confirmButton.setText("Confirmer");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allocate(e);
            }
        });
        formPanel.add(confirmButton);

        leftPanel.setBackground(new Color(255, 255, 255));
        GroupLayout leftPanelLayout = new GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
                leftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(formPanel, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
                leftPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(formPanel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(247, Short.MAX_VALUE))
        );

        mainPanel.add(leftPanel);

        rightPanel.setBackground(new Color(255, 255, 255));
        GroupLayout rightPanelLayout = new GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 385, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
                rightPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 520, Short.MAX_VALUE)
        );

        mainPanel.add(rightPanel);

        jScrollPane.setViewportView(mainPanel);

        add(jScrollPane, BorderLayout.CENTER);
    }

    public void allocate (ActionEvent e){
        Request request = new Request(this.index.getClassIndex().getAppLayout(), this.index.getClassIndex().getAppLayout().getMain());
        request.addField(CourseAllocationForm.COURSE_NAME_FIELD, (String) courseComboBox.getSelectedItem());
        request.addField(CourseAllocationForm.MODULE_NAME_FIELD, moduleComboBox.getSelectedItem().toString());
        request.addField(CourseAllocationForm.PROFESSOR_NAME_FIELD, (String) professorComboBox.getSelectedItem());
        request.addField(CourseAllocationForm.HOURS_FIELD, Integer.toString((Integer) hoursSpinner.getValue()));
        request.addField(CourseAllocationForm.COEFFICIENT_FIELD, Integer.toString((Integer) coefficientSpinner.getValue()));
        request.addField(CourseAllocationForm.CLASS_ID_FIELD, Long.toString(index.getClassroom().getId()));

        if (AffectationController.create(request)){
            index.setTabComponents();
            index.goToTab(2);
            index.getClassIndex().setTabComponents(4);
        }
    }
}
