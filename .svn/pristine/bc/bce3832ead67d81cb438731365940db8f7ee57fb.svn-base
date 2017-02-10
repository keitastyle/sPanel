package com.spanel.beans;

/**
 * Created by Joel on 25/11/2016.
 */
public class Module {
    private Long id;
    private String name;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Module module = (Module) o;

        return id.equals(module.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
