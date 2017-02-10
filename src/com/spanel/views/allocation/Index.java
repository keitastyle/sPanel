package com.spanel.views.allocation;

import com.spanel.App;
import com.spanel.beans.Module;
import com.spanel.beans.User;
import com.spanel.dao.DAOFactory;
import com.spanel.views.ClassIndex;
import com.spanel.beans.Course;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Joel on 03/12/2016.
 */
public class Index extends JPanel{
    private ClassIndex classIndex;
    private DAOFactory daoFactory;

    private javax.swing.JTabbedPane allocationTabbedPane;

    public Index(ClassIndex classIndex){
        this.classIndex = classIndex;
        daoFactory = classIndex.getAppLayout().getMain().getDaoFactory();

        initComponents();
        setTabComponents();
    }

    private void initComponents() {

        allocationTabbedPane = new JTabbedPane();
        allocationTabbedPane.addTab("Etudiants", new JPanel());
        allocationTabbedPane.addTab("Modules", new JPanel());
        allocationTabbedPane.addTab("Cours", new JPanel());

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(allocationTabbedPane, BorderLayout.CENTER);

    }

    public void setTabComponents(){
        List<Module> modules = daoFactory.getModuleDao().findAllNotInClass(getClassroom().getId());
        List<Module> classModules = daoFactory.getModuleDao().findAllInClass(getClassroom().getId());
        List<Course> courses = daoFactory.getCourseDao().findAllNotInClass(getClassroom().getId());

        List<User> professors = daoFactory.getUserDao().findAllProfessors();
        List<User> students = daoFactory.getUserDao().findNonAffectedStudents();

        allocationTabbedPane.setComponentAt(0, new Students(this, students));
        allocationTabbedPane.setComponentAt(1, new Modules(this, modules, professors));
        allocationTabbedPane.setComponentAt(2, new Courses(this, classModules, courses, professors));
    }

    public void goToTab(int i){
        allocationTabbedPane.setSelectedIndex(i);
    }

    public App getMain() {
        return classIndex.getMain();
    }

    public ClassIndex getClassIndex() {
        return classIndex;
    }

    public com.spanel.beans.Class getClassroom(){
        return classIndex.getClassroom();
    }

}
