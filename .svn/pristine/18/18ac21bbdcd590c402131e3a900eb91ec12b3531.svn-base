package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.dao.*;
import com.spanel.forms.ClassCreationForm;

/**
 * Created by koria on 04/12/2016.
 */
public class ClassController {
    private ClassDAO classDAO;
    private DepartmentDAO departmentDAO;



    public ClassController(DAOFactory daoFactory) {
        classDAO = daoFactory.getClassDAO();
        departmentDAO = daoFactory.getDepartmentDAO();
    }

    public static boolean create(Request request){
        ClassController controller=new ClassController(request.getMain().getDaoFactory());

        ClassCreationForm form = new ClassCreationForm(controller.classDAO, controller.departmentDAO);
        form.create(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }
        return true;
    }

}
