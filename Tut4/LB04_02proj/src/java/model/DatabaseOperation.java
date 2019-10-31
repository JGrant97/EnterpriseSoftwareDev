/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jackg
 */
public abstract class DatabaseOperation
{
    protected Connection getConnection(String username, String password) throws SQLException
    {
        DriverManager.registerDriver(
                new org.apache.derby.jdbc.ClientDriver());
        //url = jdbc:derby://localhost:1527/MyDatabase
        //username = jack
        //password = password
        return DriverManager.getConnection("jdbc:derby://localhost:1527/MyDatabase", username, password);
    }
    
      public abstract void execute();
}
