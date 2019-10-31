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
import java.util.ArrayList;

/**
 *
 * @author jackg
 */
public class GetAllCustomers extends DatabaseOperation
{

    ArrayList<String> customerSummaries = new ArrayList<>();

    public void execute()
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT Customer_ID, Name FROM Customer");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                String custSummary = rs.getInt("Customer_ID") + ": " + rs.getString("Name");
                customerSummaries.add(custSummary);
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

    }

    public ArrayList<String> GetCustomers()
    {
        return customerSummaries;
    }
}
