package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.dao.*;
import com.spanel.forms.CourseCreationForm;

/**
 * Created by koyre on 12/6/16.
 */
public class CourseController {
    private CourseDAO courseDAO;

    public CourseController(DAOFactory daoFactory) {
        this.courseDAO =  daoFactory.getCourseDao();
    }

    public static boolean create (Request request)  {
        CourseController controller = new CourseController(request.getMain().getDaoFactory());

        CourseCreationForm form = new CourseCreationForm(controller.courseDAO);

        form.create(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }

        return true;
    }


}
