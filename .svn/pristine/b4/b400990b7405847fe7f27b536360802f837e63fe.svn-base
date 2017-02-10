package com.spanel.beans;

import com.spanel.dao.DAOFactory;
import com.spanel.dao.DepartmentDAO;

/**
 * Created by Joel on 25/11/2016.
 */
public class Class {
    private Long id;
    private String name;
    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Department getDepartment(DepartmentDAO departmentDAO){
        return departmentDAO.find(departmentId);
    }*/


    public Long getDepartmentId() {
        return departmentId;
    }

    public Department getDepartment(DAOFactory daoFactory){
        DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
        return departmentDAO.find(departmentId);
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Class aClass = (Class) o;

        return id.equals(aClass.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
