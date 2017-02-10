package com.spanel.views;

import com.spanel.App;
import com.spanel.beans.*;

import com.spanel.dao.DAOFactory;
import com.spanel.dao.ExamDAO;
import com.spanel.dao.ModuleDAO;
import com.spanel.dao.UserDAO;
import com.spanel.views.allocation.Index;
import com.spanel.views.exam.*;
import com.spanel.views.exam.Create;
import com.spanel.views.schedule.View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Joel on 16/12/2016.
 */
public class ClassIndex extends JPanel{
    private JTabbedPane mainTabbedPane;

    private Layout layout;
    private com.spanel.beans.Class classroom;

    public ClassIndex(Layout layout, com.spanel.beans.Class classroom){
        this.layout = layout;
        this.classroom = classroom;
        initComponents();
        setTabComponents();
    }

    public App getMain() {
        return layout.getMain();
    }

    private void initComponents(){
        mainTabbedPane = new JTabbedPane();

        setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());

        mainTabbedPane.setBackground(new Color(255, 255, 255));
        mainTabbedPane.setToolTipText("");
        mainTabbedPane.setAutoscrolls(true);

        JPanel jPanel;
        jPanel = new JPanel();
        jPanel.setBackground(new Color(255, 255, 255));

        GroupLayout jPanelLayout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 806, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 516, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Annonces", new JPanel());
        mainTabbedPane.addTab("Emplois du temps", new JPanel());
        mainTabbedPane.addTab("Examens", new JPanel());
        mainTabbedPane.addTab("Notes", new JPanel());
        mainTabbedPane.addTab("Modules", new JPanel());
        mainTabbedPane.addTab("Etudiants", new JPanel());

        if(layout.getMain().getUser().isAdministrator())
            mainTabbedPane.addTab("Affectations", new JPanel());

        add(mainTabbedPane, BorderLayout.CENTER);
    }

    public void setTabComponents(){
        setTabComponents(1);
        setTabComponents(2);
        setTabComponents(3);
        setTabComponents(4);
        setTabComponents(5);

        if(layout.getMain().getUser().isAdministrator())
            setTabComponents(6);
    }

    public void setTabComponents(int i){
        DAOFactory daoFactory = layout.getMain().getDaoFactory();
        ExamDAO examDAO = daoFactory.getExamDAO();
        switch (i){
            case 0:
                List<Announce> announces = this.getMain().getDaoFactory().getAnnounceDao().findByClassId(classroom.getId());
                mainTabbedPane.setComponentAt(0,new com.spanel.views.announce.Index(this,announces));
                break;
            case 1:
                List<File> files = this.getMain().getDaoFactory().getFileDAO().findAllSchedules();
                mainTabbedPane.setComponentAt(1,new View(this,files));
                break;
            case 2 :
                List<Exam> exams = examDAO.findAll(classroom);
                mainTabbedPane.setComponentAt(2, new com.spanel.views.exam.Index(this, exams));
                break;
            case 3:
                List<Exam> examsWithResults = examDAO.findAllWithResults(classroom);
                mainTabbedPane.setComponentAt(3, new com.spanel.views.mark.Index(this, examsWithResults));
                break;
            case 4:
                ModuleDAO moduleDAO = daoFactory.getModuleDao();
                List<Module> modules = moduleDAO.findAllInClass(classroom.getId());
                mainTabbedPane.setComponentAt(4, new com.spanel.views.module.Index(this, modules));
                break;
            case 5:
                UserDAO userDAO = daoFactory.getUserDao();
                List<User> students = userDAO.findAllStudentsInClass(classroom.getId());
                mainTabbedPane.setComponentAt(5, new com.spanel.views.student.Index(this, students ));
                break;
            case 6:
                mainTabbedPane.setComponentAt(6, new Index(this));
                break;
            default:
                break;
        }
    }

    public void setSelectedIndex(int index){
        mainTabbedPane.setSelectedIndex(index);
    }

    public void goToTab(int index){
        setTabComponents(index);
        setSelectedIndex(index);
    }

    public com.spanel.beans.Class getClassroom() {
        return classroom;
    }

    public Layout getAppLayout() {
        return layout;
    }

}
