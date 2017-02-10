package com.spanel.controllers;

import com.spanel.app.Request;
import com.spanel.beans.User;
import com.spanel.config.Session;
import com.spanel.dao.*;
import com.spanel.forms.InscriptionForm;
import com.spanel.forms.LoginForm;
import com.spanel.forms.RegistrationForm;
//import com.spanel.forms.ConnectionForm;
import com.spanel.forms.Form;
import com.spanel.views.Layout;
import com.spanel.App;

/**
 * Created by Joel on 30/11/2016.
 */
public class AuthController {
    public static final String CODING_ALGORITHM = "SHA-256";

    private UserDAO userDAO;
    private ProfessorDAO professorDAO;
    private StudentDAO studentDAO;
    private DepartmentDAO departmentDAO;

    AuthController(DAOFactory daoFactory){
        userDAO = daoFactory.getUserDao();
        professorDAO = daoFactory.getProfessorDao();
        studentDAO = daoFactory.getStudentDao();
        departmentDAO = daoFactory.getDepartmentDAO();
    }

    public static void login(Request request){
        AuthController controller = new AuthController(request.getMain().getDaoFactory());
        LoginForm form = new LoginForm(controller.userDAO);
        User user = form.connect(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
        }else{
            connect(request, user);
        }

    }

    public static void logout(){
        Session.deleteUserSession();
        App.main(null);
    }

    public static void register(Request request){
        AuthController controller = new AuthController(request.getMain().getDaoFactory());
        RegistrationForm form = new RegistrationForm(controller.userDAO, controller.studentDAO, controller.professorDAO,controller.departmentDAO);
        //InscriptionForm form = new InscriptionForm(controller.userDAO, controller.studentDAO, controller.professorDAO,controller.departmentDAO);
        User user = form.register(request);

        if(form.hasErrors()){
            new com.spanel.views.dialog.Error(request.getParent(), true, form.getErrors());
        }else{
            connect(request, user);
        }
    }

    private static void connect(Request request, User user){
        App main = request.getMain();
        main.setUser(user);
        Session.saveUserSession(user);

        Layout layout = new Layout(main);
        request.getParent().dispose();
        layout.setVisible(true);
    }
}
