package com.spanel.dao;

import com.spanel.beans.Exam;
import com.spanel.beans.Mark;
import com.spanel.beans.Student;

import java.util.List;

/**
 * Created by Joel on 05/01/2017.
 */
public interface MarkDAO {
    void create(Mark mark) throws DAOException;

    List<Mark> findAll(Exam exam) throws DAOException;

    Mark find(Exam exam, Student student) throws DAOException;

}
