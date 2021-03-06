package com.spanel.beans;

/**
 * Created by Joel on 25/11/2016.
 */

import java.sql.Date;

public class Schedule {
    private Long id;
    private Long classId;
    private Long fileId;
    private Date date;
    private String path;


    public String getPath() {
        return path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        return id != null ? id.equals(schedule.id) : schedule.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
