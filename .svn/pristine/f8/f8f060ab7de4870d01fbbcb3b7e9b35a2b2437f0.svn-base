package com.spanel;

import com.spanel.beans.User;
import com.spanel.config.Session;
import com.spanel.dao.DAOFactory;
import com.spanel.views.Layout;
import com.spanel.views.auth.Login;

/**
 * Created by Joel on 30/11/2016.
 */
public class App {
    private User user;
    private DAOFactory daoFactory;

    private App(){
        daoFactory =  DAOFactory.getInstance();
    }
    public static void main(String args[]){
        App main = new App();
        User user = Session.loadUserSession(main);
        if(user ==  null){
            new Login(main).setVisible(true);
        }else{
            main.user = user;
            new Layout(main).setVisible(true);
        }
    }

    public DAOFactory getDaoFactory() {
        return daoFactory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
