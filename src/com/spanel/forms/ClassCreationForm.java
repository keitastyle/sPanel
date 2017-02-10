package com.spanel.forms;


import com.spanel.app.Request;
import com.spanel.beans.Class;
import com.spanel.dao.ClassDAO;
import com.spanel.dao.DepartmentDAO;


/**
 * Created by koria on 05/12/2016.
 */
public class ClassCreationForm extends Form {
    private ClassDAO classDAO;
    private DepartmentDAO departmentDAO ;

    public static String DEPARTMENT_NAME_FIELD = "name";
    public static String CLASS_NAME_FIELD = "class";

    public ClassCreationForm(ClassDAO classDAO, DepartmentDAO departmentDAO) {
        this.classDAO = classDAO;
        this.departmentDAO = departmentDAO;
    }
    public void create(Request request) {
        String name = request.getFieldValue(CLASS_NAME_FIELD);
        String department = request.getFieldValue(DEPARTMENT_NAME_FIELD);

        validateName(name);
        if(!hasErrors()){
            Class classroom = new Class();
            classroom.setName(name);
            Long departmentId = departmentDAO.findByName(department).getId();
            classroom.setDepartmentId(departmentId);

            classDAO.create(classroom);
        }
    }

    public void validateName(String name){
        try{
            notNullValidation(CLASS_NAME_FIELD, name);
            nameValidation(name);
        }catch (FormValidationException e){
            setError(e.getMessage());
        }
    }

    private void nameValidation(String name) throws FormValidationException{
        if(classDAO.findByName(name) != null){
            throw new FormValidationException("Classe déjà existante");
        }
    }
}



