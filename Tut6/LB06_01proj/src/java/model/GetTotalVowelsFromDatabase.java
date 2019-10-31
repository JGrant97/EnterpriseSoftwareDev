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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jackg
 */
public class GetTotalVowelsFromDatabase extends DatabaseOperation
{
    private Map<String, Integer> totalVowels = new HashMap<>(5);
    public void execute()
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM VOWELCOUNT");
            ResultSet result = stmt.executeQuery();

            while (result.next())
            {
                String vStr = result.getString("VOWEL").toUpperCase();
                totalVowels.put(vStr, result.getInt("VCOUNT"));
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
