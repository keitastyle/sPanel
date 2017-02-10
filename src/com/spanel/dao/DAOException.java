package com.spanel.dao;

/**
 * Created by Joel on 04/08/2016.
 */
public class DAOException extends RuntimeException{

    public DAOException( String message ) {
        super( message );
    }

    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOException( Throwable cause ) {
        super( cause );
    }
}
