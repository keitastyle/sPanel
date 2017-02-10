package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.dao.AnnounceDAO;
import com.spanel.dao.DAOFactory;
import com.spanel.forms.AnnounceCreationForm;
import com.spanel.forms.AnnounceUpdateForm;

/**
 * Created by koyre on 30/12/16.
 */
public class AnnounceController {

    private AnnounceDAO announceDAO;

    public AnnounceController(DAOFactory daoFactory){
        announceDAO = daoFactory.getAnnounceDao();
    }

    public static boolean create (Request request) {
        AnnounceController controller = new AnnounceController(request.getMain().getDaoFactory());

        AnnounceCreationForm form = new AnnounceCreationForm(controller.announceDAO);

        form.create(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }
        return true;
    }

    public static boolean update (Request request) {
        AnnounceController controller = new AnnounceController(request.getMain().getDaoFactory());

        AnnounceUpdateForm form = new AnnounceUpdateForm(controller.announceDAO);

        form.update(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return false;
        }
        return true;
    }

}
