package com.spanel.beans;

import com.spanel.dao.CourseDAO;
import com.spanel.dao.DAOFactory;

/**
 * Created by Joel on 25/11/2016.
 */
public class Affectation {
    private Long id;
    private Long professorId;
    private Long classId;
    private Long moduleId;
    private Long courseId;
    private Long hours;
    private Long coefficient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Course getCourse(DAOFactory daoFactory){
        CourseDAO courseDAO =  daoFactory.getCourseDao();
        return courseDAO.find(courseId);
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getHours() { return hours; }

    public void setHours(Long hours) { this.hours = hours; }

    public Long getCoefficient() { return coefficient; }

    public void setCoefficient(Long coefficient) { this.coefficient = coefficient; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Affectation that = (Affectation) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
