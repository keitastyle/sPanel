package com.spanel.dao;

import com.spanel.beans.Module;

import java.util.List;

/**
 * Created by koria on 04/12/2016.
 */
public interface ModuleDAO {
    void create(Module module) throws DAOException;
    void update(Long id) throws DAOException;
    void allocate(Long classId, Long moduleId, Long professorId) throws DAOException;

    Module find(Long id) throws DAOException;

    Module findByName(String name) throws DAOException;

    List<Module> findAll() throws DAOException;

    List<Module> findAllInClass(Long classId) throws DAOException;

    List<Module> findAllNotInClass(Long classId) throws DAOException;

}
