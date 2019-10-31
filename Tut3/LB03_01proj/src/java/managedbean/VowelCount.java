package managedbean;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import model.UpdateTotalVowelsInDatabase;
import model.GetTotalVowelsFromDatabase;

@ManagedBean(name = "vowelCount")
@RequestScoped

public class VowelCount
{

    private String text;
    private Map<String, Integer> vowels = new HashMap<>(5);
    private Map<String, Integer> totalVowels;

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
            if (vowels.containsKey("" + c))
            {
                vowels.replace("" + c, vowels.get("" + c) + 1);
            }
        }
        updateTotalVowels();
        return "vowelCount";
    }

    private void updateTotalVowels()
    {
        UpdateTotalVowelsInDatabase dbOp = new UpdateTotalVowelsInDatabase(vowels);
        dbOp.execute();
        totalVowels = dbOp.getTotalVowels();
    }
}
