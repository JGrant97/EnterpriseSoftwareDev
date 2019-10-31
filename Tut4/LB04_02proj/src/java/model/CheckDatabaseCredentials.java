/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jackg
 */
public class CheckDatabaseCredentials extends DatabaseOperation
{

    private final String username;
    private final String password;

    public CheckDatabaseCredentials(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public void execute()
    {
        try
        {
            Connection conn = getConnection(username, password);
            conn.close();
        }
        catch (SQLException sqle)
        {
            throw new RuntimeException("Database credentials are incorrect", sqle);
        }

    }
}
