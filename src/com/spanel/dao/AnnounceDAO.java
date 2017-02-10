package com.spanel.dao;

import com.spanel.beans.Announce;
import com.spanel.beans.Professor;
import com.spanel.beans.User;

import java.util.List;

/**
 * Created by koria on 21/12/2016.
 */
public interface AnnounceDAO  {
    void create(Announce announce) throws DAOException;
    void update (Announce announce ) throws DAOException;
    void update(Long id) throws DAOException;
    Class find(Long id) throws DAOException;
    Announce findByTitle (String title) throws DAOException;
    List<Announce> findAll() throws DAOException;
    List<Announce> findAll(User user) throws DAOException;
    List<Announce> findByClassId (Long classId);

}