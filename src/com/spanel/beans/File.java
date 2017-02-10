package com.spanel.beans;

import java.sql.Date;

/**
 * Created by Joel on 25/11/2016.
 */
public class File {
    private Long id;
    private Long authorId;
    private Long classId;
    private String name;
    private String path;
    private String type;
    private String description;
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        File file = (File) o;

        return id.equals(file.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
