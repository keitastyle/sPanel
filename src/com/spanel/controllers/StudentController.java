package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.dao.ClassDAO;
import com.spanel.dao.DAOFactory;
import com.spanel.dao.StudentDAO;
import com.spanel.dao.UserDAO;
import com.spanel.forms.StudentAllocationForm;

/**
 * Created by koria on 13/12/2016.
 */
public class StudentController {
    ClassDAO classDAO;
    StudentDAO studentDAO;
    UserDAO userDAO;
    public StudentController(DAOFactory daoFactory){
        classDAO = daoFactory.getClassDAO();
        studentDAO = daoFactory.getStudentDao();
        userDAO = daoFactory.getUserDao();
    }

    public static void update(Request request){
        //
    }

    public static boolean allocate(Request request){
        StudentController controller = new StudentController(request.getMain().getDaoFactory());
        StudentAllocationForm form = new StudentAllocationForm(controller.studentDAO);
        form.allocate(request);
        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }else{
            return true;
        }
    }

}
