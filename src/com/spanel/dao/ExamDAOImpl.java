package com.spanel.dao;

import com.spanel.beans.Class;
import com.spanel.beans.Exam;
import com.spanel.beans.Professor;
import com.spanel.beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.spanel.dao.DAOUtilitary.initPreparedStatement;
import static com.spanel.dao.DAOUtilitary.silentClose;

/**
 * Created by Joel on 02/01/2017.
 */
public class ExamDAOImpl implements ExamDAO {
    private DAOFactory daoFactory;

    private final static String SQL_INSERT =
            "INSERT INTO exams (affectation_id, date, type, room, description) " +
            "VALUES (?,?,?,?,?)";
    private final static String SQL_SELECT_IN_CLASS_AT_DATE_BY_ID =
            "SELECT * FROM exams WHERE affectation_id IN (" +
                    "SELECT id FROM affectations WHERE class_module_id IN (" +
                            "SELECT id FROM class_module WHERE class_id = ?" +
                    ")" +
            ") AND date = ?";
    private final static String SQL_SELECT_IN_CLASS_AT_DATE=
            "SELECT * FROM exams WHERE affectation_id IN (" +
                    "SELECT id FROM affectations WHERE class_module_id IN (" +
                    "SELECT id FROM class_module WHERE class_id = ?" +
                    ")" +
                    ") AND date = ? AND id <> ?";

    private final static String SQL_SELECT_IN_CLASS_BY_ID =
            "SELECT * FROM exams WHERE affectation_id IN (" +
                    "SELECT id FROM affectations WHERE class_module_id IN (" +
                            "SELECT id FROM class_module WHERE class_id = ?" +
                    ")" +
            ") AND date >= NOW() ORDER BY date";

    private final static String SQL_SELECT_CLASS_PROFESSOR_BY_ID =
            "SELECT * FROM exams WHERE affectation_id IN (" +
                    "SELECT id FROM affectations WHERE class_module_id IN (" +
                            "SELECT id FROM class_module WHERE class_id = ?" +
                    ") AND professor_id = ?" +
            ") ORDER BY date";
    private final static String SQL_SELECT_CLASS_PROFESSOR_WITHOUT_RESULTS_BY_ID =
            "SELECT * FROM exams WHERE affectation_id IN (" +
                    "SELECT id FROM affectations WHERE class_module_id IN (" +
                            "SELECT id FROM class_module WHERE class_id = ?" +
                    ") AND professor_id = ?" +
            ") AND NOT EXISTS(" +
                    "SELECT * FROM marks WHERE exam_id = exams.id" +
            ") ORDER BY date";

    private final static String SQL_SELECT_IN_CLASS_WITH_RESULTS_BY_ID =
            "SELECT * FROM exams WHERE affectation_id IN (" +
                    "SELECT id FROM affectations WHERE class_module_id IN (" +
                            "SELECT id FROM class_module WHERE class_id = ?" +
                    ")" +
            ") AND EXISTS(" +
                    "SELECT * FROM marks WHERE exam_id = exams.id" +
            ") ORDER BY date";

    private static final String SQL_UPDATE =
            "UPDATE exams SET affectation_id = ?, date = ?, type = ?, room = ?, description = ? WHERE id = ?";

    private static final String SQL_FIND_BY_ID ="SELECT * FROM exams WHERE id=?";

            ExamDAOImpl(DAOFactory daoFactory){
        this.daoFactory=daoFactory;
    }

    @Override
    public void create(Exam exam) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet autoGenerateValues = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement( connection, SQL_INSERT, true, exam.getAffectationId(), exam.getTimestamp(), exam.getType(), exam.getRoom(), exam.getDescription());
            int state = preparedStatement.executeUpdate();
            if ( state == 0 ) {
                throw new DAOException( "Echec de la création de l'examen, aucune ligne ajoutée à la table." );
            }
            autoGenerateValues = preparedStatement.getGeneratedKeys();
            if ( autoGenerateValues.next() ) {
                exam.setId( autoGenerateValues.getLong( 1 ) );
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
    public void update(Exam exam) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement( connection, SQL_UPDATE, true, exam.getAffectationId(), exam.getTimestamp(), exam.getType(), exam.getRoom(), exam.getDescription(), exam.getId());
            int state = preparedStatement.executeUpdate();
            if ( state == 0 ) {
                throw new DAOException("Failed to modify exam,No rows have been added to the table ");
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClose( preparedStatement, connection );
        }
    }

    @Override
    public Exam find(Long id) throws DAOException {
        return find (SQL_FIND_BY_ID, id);
    }

    @Override
    public Exam find(Long classId, Timestamp date) throws DAOException {
        return find(SQL_SELECT_IN_CLASS_AT_DATE_BY_ID, classId, date);
    }

    @Override
    public List<Exam> findAll(Class classroom) throws DAOException {
        return findMany(SQL_SELECT_IN_CLASS_BY_ID, classroom.getId());
    }

    @Override
    public List<Exam> findAll(Class classroom, Professor professor) throws DAOException {
        return findMany(SQL_SELECT_CLASS_PROFESSOR_BY_ID, classroom.getId(), professor.getId());
    }

    @Override
    public List<Exam> findAllWithoutResults(Class classroom, Professor professor) throws DAOException {
        return findMany(SQL_SELECT_CLASS_PROFESSOR_WITHOUT_RESULTS_BY_ID, classroom.getId(), professor.getId());
    }

    @Override
    public List<Exam> findAllWithResults(Class classroom) throws DAOException {
        return findMany(SQL_SELECT_IN_CLASS_WITH_RESULTS_BY_ID, classroom.getId());
    }

    private Exam find(String sql, Object... objects ) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Exam exam = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement( connection, sql, false, objects );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                exam = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClose( resultSet, preparedStatement, connection );
        }

        return exam;
    }

    private List<Exam> findMany(String sql, Object... objects) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Exam> exams = new ArrayList<>();
        try{
            connection=daoFactory.getConnection();
            preparedStatement=initPreparedStatement(connection,sql,false,objects);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                exams.add(map(resultSet));
            }
        }catch(SQLException e){
            throw new DAOException(e);
        }finally {
            silentClose(resultSet,preparedStatement,connection);
        }

        return exams;
    }

    public static Exam map(ResultSet resultSet) throws SQLException{
        Exam exam = new Exam();
        exam.setId(resultSet.getLong("id"));
        exam.setAffectationId(resultSet.getLong("affectation_id"));
        exam.setDate(resultSet.getTimestamp("date"));
        exam.setType(resultSet.getString("type"));
        exam.setRoom(resultSet.getString("room"));
        exam.setDescription(resultSet.getString("description"));
        return exam;
    }
}
