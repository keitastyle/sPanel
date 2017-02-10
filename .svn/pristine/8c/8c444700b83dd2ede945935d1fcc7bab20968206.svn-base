package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.beans.File;
import com.spanel.dao.DAOFactory;
import com.spanel.dao.FileDao;
import com.spanel.forms.FileCreationForm;
import com.spanel.forms.FileSelectionForm;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.util.List;

import static com.sun.org.apache.xerces.internal.xinclude.XIncludeHandler.BUFFER_SIZE;

/**
 * Created by koria on 30/12/2016.
 */
public class FileController {
    private FileDao fileDao;
    private String file;
    static FileImageOutputStream fileOutputStream = null;
    private static final int BUFFER_SIZE = 4096;



    public FileController(DAOFactory daoFactory) {
        fileDao = daoFactory.getFileDAO();
    }

    public static File create(Request request){
        File file = null;
        FileController controller=new FileController(request.getMain().getDaoFactory());

        FileCreationForm form = new FileCreationForm(controller.fileDao);
        file = form.create(request);

        if(file==null){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
            return null;
        }
        return file;
    }

    public static File find(Request request){
        File file = null;
        FileController fileController = new FileController(request.getMain().getDaoFactory());
        FileSelectionForm form = new FileSelectionForm(fileController.fileDao);
        file = form.findByMaxId();
        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(),true,form.getErrors());

        }

        return file;
    }


    public static File findProfilePicture(Request request){
        File file = null;
        FileController fileController = new FileController(request.getMain().getDaoFactory());
        FileSelectionForm form = new FileSelectionForm(fileController.fileDao);
        file = form.findProfilePicture();
        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(),true,form.getErrors());

        }

        return file;
    }
    public static void saveFile(String path,String name){

        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new java.io.File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(originalImage, "jpg", new java.io.File("assets/uploads/img/"+name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveFileOnDisk(String path,String name){

        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new java.io.File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(originalImage, "jpg", new java.io.File("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads\\"+name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
