package com.spanel.beans;

import com.spanel.dao.DAOFactory;

/**
 * Created by Joel on 24/11/2016.
 */
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phone;
    private String password;
    private Long userableId;
    private String userableType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserableId() {
        return userableId;
    }

    public void setUserableId(Long userableId) {
        this.userableId = userableId;
    }

    public String getUserableType() {
        return userableType;
    }

    public void setUserableType(String userableType) {
        this.userableType = userableType;
    }



    public Object getUserable(DAOFactory daoFactory){
        if(isStudent()) return daoFactory.getStudentDao().find(this);
        if(isProfessor()) return daoFactory.getProfessorDao().find(this);
        //if(isAdministrator()) return daoFactory.getAdministratorDao().find(this);
        return null;
    }

    public boolean isStudent(){
        return userableType.equals("students");
    }

    public boolean isAdministrator(){
        return userableType.equals("administrators");
    }

    public boolean isProfessor(){
        return userableType.equals("professors");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                ", userableId=" + userableId +
                ", userableType='" + userableType + '\'' +
                '}';
    }
}
