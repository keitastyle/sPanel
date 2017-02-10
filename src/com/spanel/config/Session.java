package com.spanel.config;

import com.spanel.App;
import com.spanel.beans.User;
import com.spanel.dao.UserDAO;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Joel on 04/12/2016.
 */
public class Session {
    public static String SESSION_PATH = "com/spanel/session/";
    public static String USER_SESSION_FILE = "com/spanel/session/user.properties";
    public static  String USER_SESSION_EMAIL_PROPERTY = "email";
    public static  String USER_SESSION_PASSWORD_PROPERTY = "password";

    public static void deleteUserSession(){
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            URL resourceUrl = classLoader.getResource(USER_SESSION_FILE);
            File file = new File(resourceUrl.toURI());
            OutputStream userSessionFile = new FileOutputStream(file);
            properties.setProperty(USER_SESSION_EMAIL_PROPERTY, "");
            properties.setProperty(USER_SESSION_PASSWORD_PROPERTY, "");
            properties.store(userSessionFile, null);
            userSessionFile.close();
        } catch (IOException | URISyntaxException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveUserSession(User user){
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            URL resourceUrl = classLoader.getResource(USER_SESSION_FILE);
            File file = new File(resourceUrl.toURI());
            OutputStream userSessionFile = new FileOutputStream(file);
            properties.setProperty(USER_SESSION_EMAIL_PROPERTY, user.getEmail());
            properties.setProperty(USER_SESSION_PASSWORD_PROPERTY, user.getPassword());
            properties.store(userSessionFile, null);
            userSessionFile.close();
        } catch (IOException | URISyntaxException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Nullable
    public static User loadUserSession(App main){
        User user;

        Properties properties = new Properties();
        String email;
        String password;


        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream userSessionFile = classLoader.getResourceAsStream( USER_SESSION_FILE );
            properties.load(userSessionFile);
            email = properties.getProperty(USER_SESSION_EMAIL_PROPERTY);
            password = properties.getProperty(USER_SESSION_PASSWORD_PROPERTY);
            userSessionFile.close();
        } catch ( Exception e ) {
            System.out.println("Impossible de charger le fichier properties " + USER_SESSION_FILE);
            System.out.println(e.getMessage());
            return null;
        }

        UserDAO userDAO = main.getDaoFactory().getUserDao();
        user = userDAO.find(email);
        if(user == null){
            System.out.println("Utilisateur inexistant");
            deleteUserSession();
            return null;
        }

        // Password verification


        return user;
    }


}
