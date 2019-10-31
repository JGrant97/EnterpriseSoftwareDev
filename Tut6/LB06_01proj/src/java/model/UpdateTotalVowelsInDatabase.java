/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author jackg
 */
public class UpdateTotalVowelsInDatabase extends DatabaseOperation
{

    private Map<String, Integer> vowels;
    private Map<String, Integer> totalVowels;
    
    public UpdateTotalVowelsInDatabase(Map<String, Integer> vowels)
    {
        this.vowels = vowels;
    }
    
    public void execute()
    {
        try
        {
            Connection conn;
            conn = getConnection();
            GetTotalVowelsFromDatabase dbOp = new GetTotalVowelsFromDatabase();
            dbOp.execute();
            totalVowels = dbOp.getTotalVowels();
           // totalVowels = new GetTotalVowelsFromDatabase().execute();

            PreparedStatement stmt = conn.prepareStatement("UPDATE VOWELCOUNT SET VCOUNT = ? WHERE VOWEL = ?");
            for (String key : vowels.keySet())
            {
                int count = vowels.get(key);
                if (count > 0)
                {
                    int newCount = totalVowels.get(key) + count;
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

    public Map<String, Integer> getTotalVowels()
    {
        return totalVowels;
    }
    
}
