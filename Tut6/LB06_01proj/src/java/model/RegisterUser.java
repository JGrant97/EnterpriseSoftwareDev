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
public class RegisterUser extends DatabaseOperation
{

    private String username;
    private String DBusername = null;
    private String pwd = "";
    private String name = "";

    public RegisterUser(String username, String name, String pwd)
    {
        this.username = username;
        this.name = name;
        this.pwd = pwd;
    }

    public String getUsername()
    {
        return username;
    }

    public String getDBusername()
    {
        return DBusername;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
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
            PreparedStatement stmt = conn.prepareStatement("SELECT UNAME FROM USERS WHERE UNAME =?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                DBusername = rs.getString("UNAME");
            }
            stmt.close();

            if (DBusername == null)
            {
                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO USERS(NAME, UNAME, PWD) VALUES(?,?,?)");
                stmt2.setString(1, name);
                stmt2.setString(2, username);
                stmt2.setString(3, pwd);
                stmt2.executeUpdate();
                stmt2.close();
            }
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
}
