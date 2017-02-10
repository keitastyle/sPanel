package com.spanel.dao;

import com.spanel.beans.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.spanel.dao.DAOUtilitary.initPreparedStatement;
import static com.spanel.dao.DAOUtilitary.silentClose;

/**
 * Created by koria on 07/12/2016.
 */
public class DepartmentDAOImpl implements DepartmentDAO {
    private static final String SQL_SELECT_BY_ID =
            "SELECT * FROM departments WHERE id = ?";
    private static final String SQL_SELECT_BY_NAME =
            "SELECT id,name FROM departments WHERE name = ?";
    private static final String SQL_SELECT_ALL =
            "SELECT id, name FROM departments";

    private DAOFactory daoFactory;

    DepartmentDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    @Override
    public Department create(Department department) throws DAOException {
        return null;
    }

    @Override
    public void update(Long id) throws DAOException {
        //
    }

    @Override
    public Department find(Long id) throws DAOException {
        return find(SQL_SELECT_BY_ID, id);
    }

    @Override
    public Department findByName(String name) throws DAOException {
        return find(SQL_SELECT_BY_NAME, name);
    }

    @Override
    public List<Department> findAll() throws DAOException {
        return findMany(SQL_SELECT_ALL);
    }

    public Department find(String sql, Object... objects) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Department department = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, sql, false, objects);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                department = map(resultSet);

            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClose(resultSet, preparedStatement, connection);
        }

        return department;
    }

    public List<Department> findMany(String sql, Object... objects) throws DAOException {
        List<Department> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, sql, false, objects);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(map(resultSet));

            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClose(resultSet, preparedStatement, connection);
        }

        return list;
    }

    public static Department map(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong("id"));
        department.setName(resultSet.getString("name"));
        return department;
    }

}

