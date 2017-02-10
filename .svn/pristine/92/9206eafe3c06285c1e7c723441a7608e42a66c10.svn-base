package com.spanel.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ConnectionPropertiesImpl;
import com.mysql.jdbc.PreparedStatement;
import com.spanel.beans.Class;
import com.spanel.beans.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.spanel.dao.DAOUtilitary.*;

/**
 * Created by koyre on 12/5/16.
 */
public class CourseDAOImpl implements CourseDAO {

    private DAOFactory daoFactory;
    private static final String SQL_INSERT =
            "INSERT INTO courses (name) values (?)";
    private static final String SQL_FIND_ALL =
            "SELECT * FROM courses";
    private static final String SQL_SELECT_BY_ID =
            "SELECT * FROM courses WHERE id = ?";
    private static final String SQL_FIND_BY_NAME =
            "SELECT * FROM courses WHERE name = ?";
    private static final String SQL_FIND_ALL_NOT_IN_CLASS =
            "SELECT * FROM courses WHERE id NOT IN (" +
                "SELECT course_id FROM affectations WHERE class_module_id IN (" +
                    "SELECT id FROM class_module WHERE class_id = ?" +
                ")" +
            ")";


    public CourseDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Course course) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet autoGenerateValue =null;
        try {
            connection = (Connection) daoFactory.getConnection();
            preparedStatement = (PreparedStatement) initPreparedStatement(connection, SQL_INSERT, true, course.getName());
            int status = preparedStatement.executeUpdate();

            if (status == 0){
                throw new DAOException("Failed to create Course,No rows have been added to the table ");
            }
            autoGenerateValue = preparedStatement.getGeneratedKeys();
            if (autoGenerateValue.next()){
                    course.setId(autoGenerateValue.getLong(1));
                    System.out.println(course.getId());
                    System.out.println(course.getName());
            }else {
                throw new DAOException("Failed to create Course in database,No id auto generated value");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClose(autoGenerateValue,preparedStatement,connection);
        }

    }

    @Override
    public Course find(Long id) throws DAOException{
        return find(SQL_SELECT_BY_ID, id);
    }

    @Override
    public Course findByName(String name){
        return find(SQL_FIND_BY_NAME,name);
    }

    @Override
    public List<Course> findAll() throws DAOException{
        return findMany(SQL_FIND_ALL);
    }

    @Override
    public List<Course> findAllNotInClass(Long classId) throws DAOException {
        return findMany(SQL_FIND_ALL_NOT_IN_CLASS, classId);
    }

    public Course find(String sql, Object... objects) throws DAOException {
        java.sql.Connection connection = null;
        java.sql.PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course course  = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, sql, false, objects);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClose(resultSet, preparedStatement, connection);
        }

        return course;
    }

    private List<Course> findMany(String sql, Object... objects ) throws DAOException{
        java.sql.Connection connection = null;
        java.sql.PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Course> courses = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement( connection, sql, false, objects );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                courses.add(map( resultSet )) ;
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClose( resultSet, preparedStatement, connection );
        }

        return courses;
    }

    private static Course map( ResultSet resultSet ) throws SQLException {
        Course course = new Course();
        course.setId( resultSet.getLong( "id" ) );
        course.setName( resultSet.getString("name"));
        return course;
    }

}
