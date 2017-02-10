package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.dao.*;
import com.spanel.forms.CourseAllocationForm;

/**
 * Created by koyre on 24/12/16.
 */
public class AffectationController {
    private ModuleDAO moduleDAO;
    private CourseDAO courseDAO;
    private AffectationDAO affectationDAO;

    private ProfessorDAO professorDAO;

    public AffectationController(DAOFactory daoFactory) {
        moduleDAO = daoFactory.getModuleDao();
        courseDAO = daoFactory.getCourseDao();
        affectationDAO= daoFactory.getAffectationDao();

        professorDAO = daoFactory.getProfessorDao();
    }

    public static boolean create(Request request){
        AffectationController controller = new AffectationController(request.getMain().getDaoFactory());
        CourseAllocationForm form =
                new CourseAllocationForm(
                        controller.moduleDAO,
                        controller.courseDAO,
                        controller.affectationDAO,
                        controller.professorDAO
                        );
        form.allocate(request);
        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }
        return true;
    }
}
