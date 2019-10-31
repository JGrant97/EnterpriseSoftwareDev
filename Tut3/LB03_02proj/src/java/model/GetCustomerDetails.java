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
public class GetCustomerDetails extends DatabaseOperation
{

    public String customerDetails = "";
    public int customerID = 0;
    public String customerName = "";
    public String address1 = "";
    public String address2 = "";
    public String city = "";
    public String state = "";
    public String zip = "";

    public void execute()
    {
        customerDetails = "";
        customerID = 0;
        customerName = "";
        address1 = "";
        address2 = "";
        city = "";
        state = "";
        zip = "";

        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer WHERE Customer_ID = ?");
            stmt.setInt(1, CustID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                customerDetails = rs.getInt("Customer_ID") + ": " + rs.getString("Name")
                        + ": " + rs.getString("AddressLine1")
                        + ": " + rs.getString("AddressLine2")
                        + ": " + rs.getString("City")
                        + ": " + rs.getString("State")
                        + ": " + rs.getString("Zip");

                customerID = rs.getInt("Customer_ID");
                customerName = rs.getString("Name");
                address1 = rs.getString("AddressLine1");
                address2 = rs.getString("AddressLine2");
                city = rs.getString("City");
                state = rs.getString("State");
                zip = rs.getString("Zip");
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

    }

    public String getCustomerDetails()
    {
        return customerDetails;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getAddress1()
    {
        return address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public String getZip()
    {
        return zip;
    }
}
