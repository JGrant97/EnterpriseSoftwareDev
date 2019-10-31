package managedbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "vowelCount")
@RequestScoped
public class VowelCount
{

    private String text;
    private Map<String,Integer> vowels = new HashMap<>(5);
    private Map<String,Integer> totalVowels = new HashMap<>(5);
    
    public VowelCount()
    {
        vowels.put("A", 0);
        vowels.put("E", 0);
        vowels.put("I", 0);
        vowels.put("O", 0);
        vowels.put("U", 0);
    }

    public String getText()
    {
        return text;
    }

    public int getCounterA()
    {
        return vowels.get("A");
    }

    public int getCounterE()
    {
        return vowels.get("E");
    }

    public int getCounterI()
    {
        return vowels.get("I");
    }

    public int getCounterO()
    {
        return vowels.get("O");
    }

    public int getCounterU()
    {
        return vowels.get("U");
    }

    public int getTotalA()
    {
        return totalVowels.get("A");
    }

    public int getTotalE()
    {
        return totalVowels.get("E");
    }

    public int getTotalI()
    {
        return totalVowels.get("I");
    }

    public int getTotalO()
    {
        return totalVowels.get("O");
    }

    public int getTotalU()
    {
        return totalVowels.get("U");
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String countVowels()
    {
        for (char c : text.toUpperCase().toCharArray())
        {
            if (vowels.containsKey(""+c))
            {
                vowels.replace(""+c, vowels.get(""+c)+1);
            }
        }
        updateTotalVowels();
        return "vowelCount";
    }

    private Connection getConnection() throws SQLException
    {
        DriverManager.registerDriver(
                new org.apache.derby.jdbc.ClientDriver());
        return DriverManager.getConnection("jdbc:derby://localhost:1527/MyDatabase", "jack", "password");
    }

    private void getTotalVowels(Connection conn) throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM VOWELCOUNT");
        ResultSet result = stmt.executeQuery();

        while (result.next())
        {
            String vStr = result.getString("VOWEL").toUpperCase();
            totalVowels.put(vStr, result.getInt("VCOUNT"));
        }

        stmt.close();
    }

    private void updateTotalVowels()
    {
        Connection conn;
        try
        {
            conn = getConnection();
            getTotalVowels(conn);
            
            PreparedStatement stmt = conn.prepareStatement("UPDATE VOWELCOUNT SET VCOUNT = ? WHERE VOWEL = ?");
            for (String key : vowels.keySet())
            {
                int count = vowels.get(key);
                if (count > 0)
                {
                    int newCount = totalVowels.get(key)+count;
                    totalVowels.replace(key, newCount);
                    stmt.setInt(1, newCount);
                    stmt.setString(2, key);
                    stmt.executeUpdate();
                }
            }
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

    }
}
