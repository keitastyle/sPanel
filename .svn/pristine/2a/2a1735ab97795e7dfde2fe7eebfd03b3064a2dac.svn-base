package com.spanel.views.module;

import com.spanel.beans.Affectation;
import com.spanel.beans.Module;
import com.spanel.beans.User;
import com.spanel.dao.*;
import com.spanel.views.ClassIndex;
import com.spanel.views.module.files.Create;

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
    private JLabel coefficientLabel;
    private JLabel courseLabel;
    private JPanel headerPanel;
    private JLabel hoursLabel;
    private JScrollPane jScrollPane;
    private JPanel mainPanel;
    private JLabel moduleLabel;
    private JPanel modulesContainerPanel;
    private JLabel responsableLabel;

    private ClassIndex classIndex;

    public Index(ClassIndex classIndex, List<Module> modules) {
        this.classIndex =  classIndex;
        initComponents();
        modulesContainerPanel.setLayout(new java.awt.GridLayout(modules.size(), 1));
        for(Module module : modules){
            modulesContainerPanel.add(composeModuleItem(module, classIndex.getMain().getDaoFactory()));
        }
    }

    private void filesForAffectation(Affectation affectation){
        new com.spanel.views.module.files.Index(this, true, affectation).setVisible(true);
    }
    private void initComponents() {
        jScrollPane = new JScrollPane();
        mainPanel = new JPanel();
        headerPanel = new JPanel();
        moduleLabel = new JLabel();
        courseLabel = new JLabel();
        hoursLabel = new JLabel();
        coefficientLabel = new JLabel();
        responsableLabel = new JLabel();
        modulesContainerPanel = new JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(770, 520));
        setLayout(new java.awt.BorderLayout());

        jScrollPane.setBorder(null);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        headerPanel.setLayout(new java.awt.GridLayout(1, 0, 0, 0));

        moduleLabel.setFont(new java.awt.Font("Calibri", 1, 14));
        moduleLabel.setText("Module");
        moduleLabel.setForeground(new java.awt.Color(0, 102, 153));
        headerPanel.add(moduleLabel);

        courseLabel.setFont(new java.awt.Font("Calibri", 1, 14));
        courseLabel.setText("Matière");
        courseLabel.setForeground(new java.awt.Color(0, 102, 153));
        headerPanel.add(courseLabel);

        hoursLabel.setFont(new java.awt.Font("Calibri", 1, 14));
        hoursLabel.setText("Volume horaire");
        hoursLabel.setForeground(new java.awt.Color(0, 102, 153));
        headerPanel.add(hoursLabel);

        coefficientLabel.setFont(new java.awt.Font("Calibri", 1, 14));
        coefficientLabel.setText("Coefficient");
        coefficientLabel.setForeground(new java.awt.Color(0, 102, 153));
        headerPanel.add(coefficientLabel);

        responsableLabel.setFont(new java.awt.Font("Calibri", 1, 14));
        responsableLabel.setText("Responsable");
        responsableLabel.setForeground(new java.awt.Color(0, 102, 153));
        headerPanel.add(responsableLabel);

        modulesContainerPanel.setBackground(new java.awt.Color(255, 255, 255));

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                                        .addComponent(modulesContainerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modulesContainerPanel, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                .addContainerGap(376, Short.MAX_VALUE))
        );

        jScrollPane.setViewportView(mainPanel);

        add(jScrollPane, java.awt.BorderLayout.CENTER);
    }

    private JPanel composeModuleItem(Module module, DAOFactory daoFactory){
        JPanel moduleItemPanel =  new JPanel();

        AffectationDAO affectationDAO = daoFactory.getAffectationDao();
        UserDAO userDAO = daoFactory.getUserDao();
        CourseDAO courseDAO = daoFactory.getCourseDao();
        ProfessorDAO professorDAO = daoFactory.getProfessorDao();
        List<Affectation> affectations = affectationDAO.findAll(classIndex.getClassroom().getId(), module.getId());

        moduleItemPanel.setBackground(new java.awt.Color(255, 255, 255));
        moduleItemPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        moduleItemPanel.setLayout(new java.awt.GridLayout(affectations.size(), 1));

        boolean showModuleName = true;
        boolean showModuleResp = true;
        for(Affectation affectation : affectations){
            JPanel affectationItemPanel = new JPanel();
            affectationItemPanel.setBackground(new java.awt.Color(255, 255, 255));
            affectationItemPanel.setLayout(new java.awt.GridLayout(1, 5));
            affectationItemPanel.setMinimumSize(new Dimension(0,25));

            JLabel itemCoefficientLabel = new JLabel();
            JLabel itemCourseLabel = new JLabel();
            JLabel itemHoursLabel = new JLabel();
            JLabel itemModuleLabel = new JLabel();
            JLabel itemResponsableLabel = new JLabel();

            String moduleName = "";
            if(showModuleName){
                showModuleName = false;
                moduleName = module.getName();
            }

            itemModuleLabel.setText(moduleName);
            affectationItemPanel.add(itemModuleLabel);

            itemCourseLabel.setText(courseDAO.find(affectation.getCourseId()).getName());
            affectationItemPanel.add(itemCourseLabel);

            itemHoursLabel.setText(affectation.getHours() + "h");
            affectationItemPanel.add(itemHoursLabel);

            itemCoefficientLabel.setText(affectation.getCoefficient() + "%");
            affectationItemPanel.add(itemCoefficientLabel);

            String moduleResponsable = "";
            if(showModuleResp){
                showModuleResp = false;
                User responsable = userDAO.findProfessor(professorDAO.findResponsable(classIndex.getClassroom().getId(), module.getId()));
                moduleResponsable = responsable.getFirstName() + responsable.getLastName();
            }

            itemResponsableLabel.setText(moduleResponsable);
            affectationItemPanel.add(itemResponsableLabel);
            affectationItemPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            affectationItemPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    filesForAffectation(affectation);
                }
            });

            moduleItemPanel.add(affectationItemPanel);
        }

        return moduleItemPanel;
    }

    public ClassIndex getClassIndex() {
        return classIndex;
    }
}
