package com.spanel.beans;

/**
 * Created by Joel on 24/11/2016.
 */
public class Student {
    private Long id;
    private String studentId;
    private Long classId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id.equals(student.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Object getStudentClass(){
        return new Object();
    }
}
