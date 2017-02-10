package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.Course;
import com.spanel.dao.CourseDAO;

/**
 * Created by koyre on 12/6/16.
 */
public class CourseCreationForm extends Form {

    private CourseDAO courseDAO;

    public static final String COURSE_NAME_FIELD = "name";

    public CourseCreationForm(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void create (Request request)  {
        String name = request.getFieldValue(COURSE_NAME_FIELD);

        validateName(name);
        if(!hasErrors()){
            Course course = new Course();
            course.setName(name);
            courseDAO.create(course);
        }
    }

    private void validateName(String name){
        try{
            notNullValidation(COURSE_NAME_FIELD,name);
            nameValidation(name);
        }catch(FormValidationException e){
            setError(e.getMessage());
        }
    }

    private void nameValidation(String name) throws FormValidationException{
        if(courseDAO.findByName(name) != null){
            throw new FormValidationException("Matière déjà existante");
        }
    }
}
