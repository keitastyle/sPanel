package com.spanel.dao;

import com.spanel.beans.Module;
import com.spanel.beans.Professor;
import com.spanel.beans.User;

import java.util.List;

/**
 * Created by Joel on 06/12/2016.
 */
public interface ProfessorDAO {
    void create(Professor professor) throws DAOException;

    Professor find(Long id) throws DAOException;

    Professor find(User user) throws DAOException;

    List<Professor> findAll() throws DAOException;

    Professor findByName(String name) throws DAOException;

    Professor findResponsable(Long classId, Long moduleId) throws DAOException;
}

