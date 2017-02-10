package com.spanel.dao;

import com.spanel.beans.Administrator;
import com.spanel.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.spanel.dao.DAOUtilitary.initPreparedStatement;
import static com.spanel.dao.DAOUtilitary.silentClose;

/**
 * Created by koyre on 05/01/17.
 */
public class AdministratorDAOImpl implements  AdministratorDAO {

    private DAOFactory daoFactory;

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM administrators WHERE id = ?";

    public AdministratorDAOImpl(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public Administrator find(User user) throws DAOException {
        if (user.isAdministrator())
            return find(SQL_SELECT_BY_ID,user.getUserableId());
        return null;
    }

    private Administrator find(String sql, Object... objects ) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Administrator administrator = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement( connection, sql, false, objects );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                administrator = map( resultSet );
                System.out.println(administrator.getId());
                System.out.println(administrator.getFunction());

            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClose( resultSet, preparedStatement, connection );
        }

        return administrator;
    }

    public static Administrator  map(ResultSet resultSet ) throws SQLException{
        Administrator administrator = new Administrator();
        administrator.setId(resultSet.getLong("id"));
        administrator.setFunction(resultSet.getString("function"));
        return administrator;
    }
}
