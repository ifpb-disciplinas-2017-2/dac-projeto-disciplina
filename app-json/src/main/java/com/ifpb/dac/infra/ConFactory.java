
package com.ifpb.dac.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {
    
//    private static final String URLBANCOLOCAL = "jdbc:postgresql://localhost:5432/DAC";
    private static final String URLBANCOREMOTO = "jdbc:postgresql://host-banco:5432/DAC";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "12345";
       
    public static Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URLBANCOREMOTO, USUARIO, SENHA);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
