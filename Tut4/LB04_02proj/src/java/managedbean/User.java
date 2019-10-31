/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.CheckDatabaseCredentials;

/**
 *
 * @author jackg
 */
@Named(value = "user")
@SessionScoped
public class User implements Serializable
{

    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String SignIn()
    {
        CheckDatabaseCredentials dbOp = new CheckDatabaseCredentials(username, password);
        
        try
        {
            dbOp.execute();
            return "index";
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
