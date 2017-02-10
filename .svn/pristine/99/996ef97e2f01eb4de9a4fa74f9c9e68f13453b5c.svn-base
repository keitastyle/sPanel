package com.spanel.forms;

import com.spanel.app.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Joel on 06/08/2016.
 */
public class Form {

    protected String              result;
    protected List<String>        errors    = new ArrayList<>();

    protected void setError(String message ) {
        errors.add(message );
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getResult() {
        return result;
    }

    public boolean hasErrors(){
        return errors.size()>0;
    }

    /*public static String getFieldValue(Request request, String field) {
        return request.getFieldValue(field);
    }*/

    protected void notNullValidation( String field, String value ) throws FormValidationException {
        if ( value == null || value.equals("")) {
            throw new FormValidationException( "Champ " + field + " non saisie" );
        }
    }

    protected void validateNotNullFields(Request request,String... fields){
        try{
            if(fields.length !=0){
                for(String key: fields){
                    if(request.getFields().containsKey(key)){
                        notNullValidation(key, request.getFieldValue(key));
                    }
                }
            }else{
                for(String key: request.getFields().keySet()){
                    notNullValidation(key, request.getFieldValue(key));
                }
            }
        }catch (FormValidationException e){
            setError(e.getMessage());
        }

    }


}
