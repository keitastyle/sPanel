package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.dao.*;
import com.spanel.forms.MarksCreationForm;

/**
 * Created by Joel on 05/01/2017.
 */
public class MarkController {
    private ExamDAO examDAO;
    private UserDAO userDAO;
    private MarkDAO markDAO;

    public MarkController(DAOFactory daoFactory) {
        examDAO = daoFactory.getExamDAO();
        userDAO = daoFactory.getUserDao();
        markDAO = daoFactory.getMarkDAO();
    }

    public static boolean create(Request request){
        MarkController controller=new MarkController(request.getMain().getDaoFactory());

        MarksCreationForm form = new MarksCreationForm(controller.examDAO, controller.userDAO, controller.markDAO);
        form.create(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }
        return true;
    }
}
