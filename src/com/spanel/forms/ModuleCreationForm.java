package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.Module;
import com.spanel.dao.ModuleDAO;

/**
 * Created by koria on 04/12/2016.
 */
public final class ModuleCreationForm extends Form {
    public static String MODULE_NAME_FIELD = "name";
    private ModuleDAO moduleDAO;

    public ModuleCreationForm(ModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
    }

    public void create(Request request) {
        String name = request.getFieldValue(MODULE_NAME_FIELD);
        validateName(name);
        if(!hasErrors()){
            Module module = new Module();
            module.setName(name);
            moduleDAO.create(module);
        }
    }

    public void validateName(String name){
        try{
            notNullValidation(MODULE_NAME_FIELD, name);
            nameValidation(name);
        }catch (FormValidationException e){
            setError(e.getMessage());
        }
    }

    private void nameValidation(String name) throws FormValidationException{
        if(moduleDAO.findByName(name) != null){
            throw new FormValidationException("Module déjà existant");
        }
    }
}
