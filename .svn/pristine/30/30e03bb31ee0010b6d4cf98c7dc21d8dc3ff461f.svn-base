package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.dao.*;
import com.spanel.forms.ModuleAllocationForm;
import com.spanel.forms.ModuleCreationForm;

/**
 * Created by koria on 04/12/2016.
 */
public class ModuleController {
    private ModuleDAO moduleDAO;
    private ProfessorDAO professorDAO;

    ModuleController(DAOFactory daoFactory) {
        moduleDAO = daoFactory.getModuleDao();
        professorDAO = daoFactory.getProfessorDao();
    }

    public static boolean create(Request request){
        ModuleController controller=new ModuleController(request.getMain().getDaoFactory());
        ModuleCreationForm form = new ModuleCreationForm(controller.moduleDAO);
        form.create(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }

        return true;
    }

    public static boolean allocate(Request request){
        ModuleController controller = new ModuleController(request.getMain().getDaoFactory());
        ModuleAllocationForm form = new ModuleAllocationForm(controller.moduleDAO, controller.professorDAO);
        form.allocate(request);
        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }else{
            return true;
        }
    }
}





