package com.spanel.beans;

/**
 * Created by Joel on 25/11/2016.
 */
public class Mark {
    private Long id;
    private Long examId;
    private Long studentId;
    private float value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark = (Mark) o;

        return id.equals(mark.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
