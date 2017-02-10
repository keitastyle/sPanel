package com.spanel.app;

import com.spanel.App;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joel on 02/12/2016.
 */
public class Request {
    private App main;
    private JFrame parent;
    private Map<String, String> fields;

    public Request(JFrame parent){
        this.parent = parent;
        fields = new HashMap<>();
    }

    public Request(JFrame parent, App main){
        this(parent);
        this.main = main;
    }


    public JFrame getParent(){
        return parent;
    }

    public App getMain() {
        return main;
    }

    public void addField(String key, String value){
        fields.put(key, value);
    }

    public String getFieldValue(String key){
        return fields.get(key);
    }

    public Map<String, String> getFields() {
        return fields;
    }


}
