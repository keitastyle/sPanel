package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.dao.DAOException;
import com.spanel.dao.ModuleDAO;
import com.spanel.dao.ProfessorDAO;

/**
 * Created by Yasmine on 28/12/2016.
 */

public class ModuleAllocationForm extends Form{
    private ModuleDAO moduleDAO;
    private ProfessorDAO professorDAO;

    public static String PROFESSOR_NAME_FIELD = "professor";
    public static String MODULE_NAME_FIELD = "module";
    public static String CLASS_ID_FIELD = "class_id";

    public ModuleAllocationForm(ModuleDAO moduleDAO, ProfessorDAO professorDAO) {
        this.moduleDAO = moduleDAO;
        this.professorDAO = professorDAO;
    }

    public void allocate(Request request) {
        String module = request.getFieldValue(MODULE_NAME_FIELD);
        String professor = request.getFieldValue(PROFESSOR_NAME_FIELD);
        Long classId = Long.parseLong(request.getFieldValue(CLASS_ID_FIELD));
        validateNotNullFields(request);

        if(!hasErrors()){
            try{
                Long professorId = professorDAO.findByName(professor).getId();
                Long moduleId = moduleDAO.findByName(module).getId();
                moduleDAO.allocate(classId, moduleId, professorId);
            }catch (DAOException e) {
                setError(e.getMessage() );
                e.printStackTrace();
            }
        }
    }
}
