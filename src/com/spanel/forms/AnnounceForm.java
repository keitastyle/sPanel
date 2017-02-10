package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.Announce;
import com.spanel.dao.AnnounceDAO;
import com.spanel.dao.ClassDAO;
import com.spanel.dao.DepartmentDAO;
import com.spanel.dao.UserDAO;

import java.util.Date;

/**
 * Created by koria on 21/12/2016.
 */
public class AnnounceForm extends Form {
    private ClassDAO classDAO;
    private UserDAO userDAO;
    private AnnounceDAO announceDAO;

    public AnnounceForm(AnnounceDAO announceDAO, ClassDAO classDAO, UserDAO userDAO){
        this.announceDAO = announceDAO;
        this.classDAO = classDAO;
        this.userDAO = userDAO;
    }

    public static String ANNOUNCE_TITLE_FIELD= "title";
    public static String ANNOUNCE_TYPE_FIELD = "type";
    public static String CLASS_NAME_FIELD = "classe" ;
    public static String USER_EMAIL_FIELD = "user";
    public static String ANNOUNCE_DESCRIPTION_FIELD = "description";
    public java.sql.Date DATE;
    public void create(Request request){
        String title = request.getFieldValue(ANNOUNCE_TITLE_FIELD);
        String type = request.getFieldValue(ANNOUNCE_TYPE_FIELD);
        String class_name = request.getFieldValue(CLASS_NAME_FIELD);
        String email = request.getFieldValue(USER_EMAIL_FIELD);
        String description = request.getFieldValue(ANNOUNCE_DESCRIPTION_FIELD);

        validateDescription(description);
        if(!hasErrors()){
            Announce announce = new Announce();
            Long id = classDAO.findByName(class_name).getId();
            Long user_id = userDAO.find(email).getId();
            announce.setClassId(id);
            announce.setAuthorId(user_id);
            announce.setDate(DATE);
            announce.setDescription(description);
            announce.setTitle(title);
            announce.setType(type);

            announceDAO.create(announce);

        }

    }

    private void validateDescription(String description) {
        try{
            notNullValidation(ANNOUNCE_DESCRIPTION_FIELD, description);
        }catch (FormValidationException e){
            setError(e.getMessage());
        }
    }
}
