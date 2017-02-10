package com.spanel.dao;

import com.spanel.beans.Administrator;
import com.spanel.beans.User;

/**
 * Created by koyre on 05/01/17.
 */
public interface AdministratorDAO {

    Administrator find(User user) throws DAOException;
}
