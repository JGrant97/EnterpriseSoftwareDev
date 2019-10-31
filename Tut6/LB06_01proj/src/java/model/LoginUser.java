/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jackg
 */
public class LoginUser extends DatabaseOperation
{

    private final String username;
    private String pwd = "";
    private String name = "";

    public LoginUser(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return pwd;
    }

    public void execute()
    {
        try
        {
            Connection conn;
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE UNAME =?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                pwd = rs.getString("pwd");
                name = rs.getString("name");
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
}
