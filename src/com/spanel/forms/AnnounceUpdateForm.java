package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.Announce;
import com.spanel.dao.AnnounceDAO;
import com.spanel.dao.DAOException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Created by koyre on 01/01/17.
 */
public class AnnounceUpdateForm extends Form {
    public static final String TYPE_FIELD = "type";
    public static final String TITLE_FIELD = "title";
    public static final String CLASS_ID_FIELD = "class";
    public static final String AUTHOR_ID_FIELD = "author";
    public static final String DESCRIPTION = "description";
    public static final String EXPIRATION_DATE_FIELD = "date";
    public static final String ID = "id";

    private AnnounceDAO announceDAO;

    public AnnounceUpdateForm(AnnounceDAO announceDAO) {
        this.announceDAO = announceDAO;
    }

    public void update(Request request)  {

        validateNotNullFields(request);
        if(hasErrors()) return;

        try{

            String type = request.getFieldValue(TYPE_FIELD);
            String title = request.getFieldValue(TITLE_FIELD);
            String description = request.getFieldValue(DESCRIPTION);
            Long class_id =Long.parseLong(request.getFieldValue(CLASS_ID_FIELD)) ;
            Long author_id =Long.parseLong(request.getFieldValue(AUTHOR_ID_FIELD));
            String expirationDate = request.getFieldValue(EXPIRATION_DATE_FIELD);
            Long id = Long.parseLong(request.getFieldValue(ID));

            Announce announce = new Announce();
            announce.setAuthorId(author_id);
            announce.setClassId(class_id);
            announce.setDescription(description);
            announce.setTitle(title);
            announce.setType(type);
            announce.setId(id);

            validateDate(announce,expirationDate);

            if(!hasErrors()){
                announceDAO.update(announce);

            }
        }catch(DAOException e ){
            System.out.println(e.getMessage());
        }
    }
    private void validateDate(Announce announce,String date){
        try{
            dateValidation(date);
        }catch (FormValidationException e){
            setError(e.getMessage());
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        try{
            java.util.Date javaDate = format.parse(date);
            Date sqlDate = new Date(javaDate.getTime());
            java.util.Date currentDate = format.parse(format.format(new java.util.Date()));
            if(javaDate.compareTo(currentDate)<0)
                setError("Date déjà passée");
            else
                announce.setExpirationDate(sqlDate);
        }catch (ParseException e){
            setError("Format de date ou heure invalide");
        }catch (DAOException e){
            setError("Examen déjà programmé à cette date");
        }
    }

    public void dateValidation(String date) throws FormValidationException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        try{
            format.parse(date);
        }catch (ParseException e){
            throw new FormValidationException("Format de date invalide");
        }
    }

    public void validatetitle(String title){
        try{
            notNullValidation(TITLE_FIELD, title);
        }catch (FormValidationException e){
            setError(e.getMessage());
        }
    }

    public void validateDescription(String description){
        try{
            notNullValidation(DESCRIPTION, description);
        }catch (FormValidationException e){
            setError(e.getMessage());
        }
    }
}
