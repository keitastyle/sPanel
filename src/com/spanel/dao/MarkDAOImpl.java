package com.spanel.dao;

import com.spanel.beans.Exam;
import com.spanel.beans.Mark;
import com.spanel.beans.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.spanel.dao.DAOUtilitary.initPreparedStatement;
import static com.spanel.dao.DAOUtilitary.silentClose;

/**
 * Created by Joel on 05/01/2017.
 */
public class MarkDAOImpl implements MarkDAO {
    private final static String SQL_INSERT =
            "INSERT INTO marks (exam_id, student_id, value) " +
                    "VALUES (?,?,?)";

    private DAOFactory daoFactory;

    MarkDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Mark mark) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet autoGenerateValues = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement( connection, SQL_INSERT, true, mark.getExamId(), mark.getStudentId(), mark.getValue());
            int state = preparedStatement.executeUpdate();
            if ( state == 0 ) {
                throw new DAOException( "Echec de la création de l'examen, aucune ligne ajoutée à la table." );
            }
            autoGenerateValues = preparedStatement.getGeneratedKeys();
            if ( autoGenerateValues.next() ) {
                mark.setId( autoGenerateValues.getLong( 1 ) );
            } else {
                throw new DAOException( "Echec de la création de l'examen, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClose( autoGenerateValues, preparedStatement, connection );
        }
    }

    @Override
    public List<Mark> findAll(Exam exam) throws DAOException {
        return null;
    }

    @Override
    public Mark find(Exam exam, Student student) throws DAOException {
        return null;
    }

    private Mark find(String sql, Object... objects ) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Mark mark = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement( connection, sql, false, objects );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                mark = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClose( resultSet, preparedStatement, connection );
        }

        return mark;
    }

    private List<Mark> findMany(String sql, Object... objects) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Mark> marks= new ArrayList<>();
        try{
            connection=daoFactory.getConnection();
            preparedStatement=initPreparedStatement(connection,sql,false,objects);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                marks.add(map(resultSet));
            }
        }catch(SQLException e){
            throw new DAOException(e);
        }finally {
            silentClose(resultSet,preparedStatement,connection);
        }

        return marks;
    }

    public static Mark map(ResultSet resultSet) throws SQLException{
        Mark mark = new Mark();
        mark.setId(resultSet.getLong("id"));
        mark.setExamId(resultSet.getLong("affectation_id"));
        mark.setStudentId(resultSet.getLong("student_id"));
        mark.setValue(resultSet.getFloat("value"));
        return mark;
    }
}
