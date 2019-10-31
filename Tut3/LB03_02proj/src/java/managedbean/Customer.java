package managedbean;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import model.GetAllCustomers;
import model.GetCustomerDetails;

@ManagedBean(name = "customer")
@RequestScoped
public class Customer
{

    private String customerDetails = "";
    private int customerID = 0;
    private String customerName = "";
    private String address1 = "";
    private String address2 = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    ArrayList<String> customerSummaries;

    public ArrayList<String> getCustomerSummaries()
    {
        GetAllCustomers dbOp = new GetAllCustomers();
        dbOp.execute();
        customerSummaries = dbOp.GetCustomers();
        return customerSummaries;
    }

    public String fetchCustomerDetails(int CustID)
    {
        model.DatabaseOperation.CustID = CustID;
        GetCustomerDetails dbOp = new GetCustomerDetails();
        dbOp.execute();
        customerDetails = dbOp.getCustomerDetails();
        customerID = dbOp.getCustomerID();
        customerName = dbOp.getCustomerName();
        address1 = dbOp.getAddress1(); 
        address2 = dbOp.getAddress2();
        city = dbOp.getCity();
        state = dbOp.getState();
        zip = dbOp.getZip();

        return "details";
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
