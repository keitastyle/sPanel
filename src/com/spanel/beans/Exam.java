package com.spanel.beans;

import com.spanel.dao.AffectationDAO;
import com.spanel.dao.DAOFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Joel on 25/11/2016.
 */
public class Exam {
    private Long id;
    private Long affectationId;
    private Timestamp date;
    private String type;
    private String room;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Affectation getAffectation(DAOFactory daoFactory){
        AffectationDAO affectationDAO = daoFactory.getAffectationDao();
        return affectationDAO.find(affectationId);
    }

    public Long getAffectationId() {
        return affectationId;
    }

    public void setAffectationId(Long affectationId) {
        this.affectationId = affectationId;
    }

    public String getHour(){
        SimpleDateFormat format = new SimpleDateFormat("H:mm");
        java.util.Date hour = new java.util.Date(date.getTime());
        return format.format(hour);
    }

    public String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        java.util.Date date = new java.util.Date(this.date.getTime());
        return format.format(date);
    }

    public Timestamp getTimestamp() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exam exam = (Exam) o;
        return id.equals(exam.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
