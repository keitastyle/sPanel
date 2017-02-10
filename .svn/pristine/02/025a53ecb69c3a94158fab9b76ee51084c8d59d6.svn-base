package com.spanel.dao;

import com.spanel.beans.File;

import java.util.List;

/**
 * Created by koria on 30/12/2016.
 */
public interface FileDao  {
    File create(File file) throws DAOException;
    void delete(Long id) throws DAOException;
    File findById(Long id) throws DAOException;

    File find(String sql, Object... objects) throws DAOException;

    File findByMaxId() throws  DAOException;

    File findProfilePicture() throws DAOException;

    List<File> findAllSchedules() throws DAOException;
}

