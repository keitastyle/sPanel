package com.spanel.dao;

import java.sql.*;

/**
 * Created by Joel on 04/08/2016.
 */
public class DAOUtilitary {

    private DAOUtilitary() {
    }

    public static void silentClose( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Echec de la fermeture du ResultSet : " + e.getMessage() );
            }
        }
    }

    public static void silentClose( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Echec de la fermeture du Statement : " + e.getMessage() );
            }
        }
    }

    public static void silentClose( Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                System.out.println( "Echec de la fermeture de la connection : " + e.getMessage() );
            }
        }
    }

    public static void silentClose( Statement statement, Connection connection ) {
        silentClose( statement );
        silentClose( connection );
    }

    public static void silentClose( ResultSet resultSet, Statement statement, Connection connection ) {
        silentClose( resultSet );
        silentClose( statement );
        silentClose( connection );
    }

    public static PreparedStatement initPreparedStatement(Connection connection, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
}
