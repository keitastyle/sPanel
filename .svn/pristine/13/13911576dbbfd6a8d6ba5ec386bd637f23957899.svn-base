package com.spanel.dao;

import com.spanel.beans.Course;

import java.util.List;

/**
 * Created by koyre on 12/5/16.
 */
public interface CourseDAO {

    void create (Course course) throws DAOException;
    Course find (Long id ) throws DAOException;
    List<Course> findAll() throws DAOException;
    List<Course> findAllNotInClass(Long classId) throws DAOException;

    Course findByName(String nameCourse);
}
