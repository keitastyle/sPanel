package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.File;
import com.spanel.dao.DAOException;
import com.spanel.dao.FileDao;

import java.sql.Date;

/**
 * Created by koria on 30/12/2016.
 */
public class FileCreationForm extends Form {
    private FileDao fileDao;

    public static String AUTHOR_ID_FIELD = "id";
    public static String FILE_NAME_FIELD = "name";
    public static String FILE_PATH_FIELD = "path";
    public static String FILE_DATE_FIELD = "date";

    public FileCreationForm(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public File create(Request request) {
        File file = new File();
        Long authorId = Long.parseLong(request.getFieldValue(AUTHOR_ID_FIELD));
        String name = request.getFieldValue(FILE_NAME_FIELD);
        String path = request.getFieldValue(FILE_PATH_FIELD);
        Date date = Date.valueOf(request.getFieldValue(FILE_DATE_FIELD));

        validateName(name);
        validatePath(path);
        if (!hasErrors()) {
            file.setAuthorId(authorId);
            file.setName(name);
            file.setPath(path);
            file.setDate(date);
            file = fileDao.create(file);
        }
        return file;
    }
    public File findByPath(){
        File file =  fileDao.findByMaxId();
        Boolean success = false;
        if(file==null) {
            try {
                throw new FormValidationException("error, no file found!");
            } catch (FormValidationException e) {
                throw new DAOException(e);
            }

        }
        return file;

    }
    private void validateName(String name) {
        try {
            notNullValidation(FILE_NAME_FIELD, name);
        } catch (FormValidationException e) {
            setError(e.getMessage());
        }

    }
    private void validatePath(String path) {
        try {
            notNullValidation(FILE_PATH_FIELD, path);
        } catch (FormValidationException e) {
            setError(e.getMessage());
        }
    }
    private void validateType(String type) {
        try {
            notNullValidation(FILE_NAME_FIELD, type);
        } catch (FormValidationException e) {
            setError(e.getMessage());
        }
    }



}
