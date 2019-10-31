package managedbean;

import dto.CustomerDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "customer")
@SessionScoped
public class Customer implements Serializable
{

    private CustomerDTO customerDetails = null;
    private int customerIDToUpdate;
    private int totalCustomers = 0;

    private String updateName;
    private String updateEmail;
    private String updateAddress1;
    private String updateAddress2;
    private String updateCity;
    private String updateState;
    private String updateZip;
    private String updateDiscountCode;
    private int updatePhone;
    private int updateFax;
    private int updateCreditLimit;

    protected Connection getConnection() throws SQLException
    {
        DriverManager.registerDriver(
                new org.apache.derby.jdbc.ClientDriver());
        return DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
    }

    public int getCustomerIDToUpdate()
    {
        return customerIDToUpdate;
    }

    public ArrayList<CustomerDTO> getCustomerSummaries()
    {
        ArrayList<CustomerDTO> customerSummaries = new ArrayList<>();
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT Customer_ID, Name FROM Customer");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                CustomerDTO cust = new CustomerDTO(rs.getInt("Customer_ID"), rs.getString("Name"), "", "", "", "", "");
                customerSummaries.add(cust);
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        totalCustomers = customerSummaries.size();
        return customerSummaries;
    }

    public String fetchCustomerDetails(int CustID)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer WHERE Customer_ID = ?");
            stmt.setInt(1, CustID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                customerDetails = new CustomerDTO(
                        rs.getInt("Customer_ID"),
                        rs.getString("Name"),
                        rs.getString("AddressLine1"),
                        rs.getString("AddressLine2"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getString("Zip"));
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return "viewCustomer";
    }

    public String prepareToUpdateCustomer(int custId)
    {
        customerIDToUpdate = custId;
        return "update";
    }

    public String UpdateCustomer()
    {
        try (Connection conn = getConnection();)
        {
            if (updateName != null)
            {
                PreparedStatement stmt = conn.prepareStatement("UPDATE CUSTOMER SET NAME = ? WHERE CUSTOMER.CUSTOMER_ID = ?");
                stmt.setString(1, updateName);
                stmt.setInt(2, customerIDToUpdate);

                stmt.execute();
            }

            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return "index";
    }

    public String DeleteCustomer(int CustID)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Purchase_Order WHERE Customer_ID = ?");
            stmt.setInt(1, CustID);

            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Customer WHERE Customer_ID = ?");
            stmt2.setInt(1, CustID);

            stmt.execute();
            stmt2.execute();

            stmt.close();
            stmt2.close();
            conn.close();
        }

        catch (SQLException sqle)
        {
        }
        return "index";
    }

    public String getUpdateName()
    {
        return updateName;
    }

    public String getUpdateEmail()
    {
        return updateEmail;
    }

    public void setUpdateEmail(String updateEmail)
    {
        this.updateEmail = updateEmail;
    }

    public String getUpdateAddress1()
    {
        return updateAddress1;
    }

    public void setUpdateAddress1(String updateAddress1)
    {
        this.updateAddress1 = updateAddress1;
    }

    public String getUpdateAddress2()
    {
        return updateAddress2;
    }

    public void setUpdateAddress2(String updateAddress2)
    {
        this.updateAddress2 = updateAddress2;
    }

    public String getUpdateCity()
    {
        return updateCity;
    }

    public void setUpdateCity(String updateCity)
    {
        this.updateCity = updateCity;
    }

    public String getUpdateState()
    {
        return updateState;
    }

    public void setUpdateState(String updateState)
    {
        this.updateState = updateState;
    }

    public String getUpdateZip()
    {
        return updateZip;
    }

    public void setUpdateZip(String updateZip)
    {
        this.updateZip = updateZip;
    }

    public int getUpdatePhone()
    {
        return updatePhone;
    }

    public void setUpdatePhone(int updatePhone)
    {
        this.updatePhone = updatePhone;
    }

    public int getUpdateFax()
    {
        return updateFax;
    }

    public void setUpdateFax(int updateFax)
    {
        this.updateFax = updateFax;
    }

    public int getUpdateCreditLimit()
    {
        return updateCreditLimit;
    }

    public void setUpdateCreditLimit(int updateCreditLimit)
    {
        this.updateCreditLimit = updateCreditLimit;
    }

    public String getUpdateDiscountCode()
    {
        return updateDiscountCode;
    }

    public void setUpdateDiscountCode(String updateDiscountCode)
    {
        this.updateDiscountCode = updateDiscountCode;
    }

    public void setUpdateName(String updateName)
    {
        this.updateName = updateName;
    }

    public CustomerDTO getCustomerDetails()
    {
        return customerDetails;
    }

    public int getTotalCustomers()
    {
        return totalCustomers;
    }
}
