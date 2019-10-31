package managedbean;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import model.UpdateTotalVowelsInDatabase;
import model.GetTotalVowelsFromDatabase;
import model.LoginUser;
import model.RegisterUser;

@ManagedBean(name = "vowelCount")
@RequestScoped

public class VowelCount
{

    private String text;
    private String Username;
    private String name;
    private String pwd;
    private String error = "";
    private String confirmPWD;
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

    public String getUsername()
    {
        return Username;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUsername(String Username)
    {
        this.Username = Username;
    }

    public String getConfirmPWD()
    {
        return confirmPWD;
    }

    public void setConfirmPWD(String confirmPWD)
    {
        this.confirmPWD = confirmPWD;
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

    public String LoginUser() throws NoSuchAlgorithmException
    {
        byte[] hash = MessageDigest.getInstance("SHA-256").digest(pwd.getBytes(StandardCharsets.UTF_8));
        String encodedPass = Base64.getEncoder().encodeToString(hash);
        
        LoginUser dbOp = new LoginUser(Username);
        dbOp.execute();
        if (encodedPass.equals(dbOp.getPassword()))
        {
            return "index";
        }
        return "login";
    }

    public String RegisterUser() throws NoSuchAlgorithmException
    {
        byte[] hash = MessageDigest.getInstance("SHA-256").digest(pwd.getBytes(StandardCharsets.UTF_8));
        String encodedPass = Base64.getEncoder().encodeToString(hash);

        byte[] hash2 = MessageDigest.getInstance("SHA-256").digest(confirmPWD.getBytes(StandardCharsets.UTF_8));
        String encodedConfPass = Base64.getEncoder().encodeToString(hash2);

        pwd = encodedPass;
        confirmPWD = encodedConfPass;

        RegisterUser dbOp = new RegisterUser(name, Username, pwd);
        dbOp.execute();
        String DBusername = dbOp.getDBusername();

        if (encodedPass.equals(encodedConfPass) && dbOp.getDBusername() == null)
        {
            pwd = null;
            confirmPWD = null;

            return "login";
        }
        if (DBusername != null)
        {
            error = "ERROR: username is already in use";
            throw new RuntimeException(error);
        }
        return "register";
    }
}
