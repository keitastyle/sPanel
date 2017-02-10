package com.spanel.dao;

import com.spanel.beans.Professor;
import com.spanel.beans.User;

import java.util.List;

/**
 * Created by Joel on 25/11/2016.
 */
public interface UserDAO {

    void create( User user ) throws DAOException;

    void update( User user ) throws DAOException;

    User find( String email ) throws DAOException;

    User find( Long id ) throws DAOException;

    User findByPhone(Long phone) throws DAOException;

    List<User> findNonAffectedStudents() throws DAOException;

    List<User> findAllStudentsInClass(Long classId) throws DAOException;

    void link(User user, Object object) throws DAOException;

    User findProfessor(Professor professor) throws DAOException;

    User findProfessor(Long professorID) throws DAOException;

    List<User> findAllProfessors() throws DAOException ;


}
