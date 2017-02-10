package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.dao.AffectationDAO;
import com.spanel.dao.CourseDAO;
import com.spanel.dao.DAOFactory;
import com.spanel.dao.ExamDAO;
import com.spanel.forms.ExamCreationForm;
import com.spanel.forms.ExamModificationForm;

/**
 * Created by Joel on 02/01/2017.
 */
public class ExamController {
    ExamDAO examDAO;
    CourseDAO courseDAO;
    AffectationDAO affectationDAO;

    ExamController(DAOFactory daoFactory){
        examDAO = daoFactory.getExamDAO();
        courseDAO = daoFactory.getCourseDao();
        affectationDAO = daoFactory.getAffectationDao();
    }

    public static boolean create(Request request){
        ExamController controller = new ExamController(request.getMain().getDaoFactory());
        ExamCreationForm form = new ExamCreationForm(controller.examDAO, controller.courseDAO, controller.affectationDAO);
        form.create(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }

        return true;
    }

    public static boolean update(Request request){
        ExamController controller = new ExamController(request.getMain().getDaoFactory());
        ExamModificationForm form = new ExamModificationForm(controller.examDAO, controller.courseDAO, controller.affectationDAO);
        form.update(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }

        return true;
    }
}
