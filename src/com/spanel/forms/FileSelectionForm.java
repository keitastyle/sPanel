package com.spanel.forms;

import com.spanel.app.Request;
import com.spanel.beans.File;
import com.spanel.dao.DAOException;
import com.spanel.dao.FileDao;

import java.util.List;


/**
 * Created by koria on 30/12/2016.
 */
public class FileSelectionForm extends Form {

    private FileDao fileDao;

    public FileSelectionForm(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public File findByMaxId(){
        File file = null ;
        file=fileDao.findByMaxId();
        if(file==null) {
            try {
                throw new FormValidationException("error, no file found!");
            } catch (FormValidationException e) {
                throw new DAOException(e);
            }

        }
        return file;

    }

    public File findProfilePicture(){
        File file = null ;
        file=fileDao.findProfilePicture();
        if(file==null) {
            try {
                throw new FormValidationException("error, no file found!");
            } catch (FormValidationException e) {
                throw new DAOException(e);
            }

        }
        return file;


    }


}


