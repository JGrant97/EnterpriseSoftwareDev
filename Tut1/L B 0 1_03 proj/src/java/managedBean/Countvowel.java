/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "vowels")
@RequestScoped

public class Countvowel
{
    private String text;
    int counterA = 0;
    int counterE = 0;
    int counterI = 0;
    int counterO = 0;
    int counterU = 0;
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
       
    //Counter for A
    public int getCounterA()
    {   
        if (!(" ".equals(text.substring(0, 1))) || !(" ".equals(text.substring(text.length() -1))))
        {
            for (int i = 0; i < text.length(); i++)
            {
               /* if (text.charAt(i) == ' ') 
                {
                    counter++;
                }*/
                
                if (text.charAt(i) == 'a' || text.charAt(i) == 'A' ) 
                {
                    counterA++;
                }
            }
            
        }
       return counterA;
    }
    
    public void setCounterA(int counterA)
    {
        this.counterA = counterA; 
    }
    //counter for E
     public int getCounterE()
    {   
        if (!(" ".equals(text.substring(0, 1))) || !(" ".equals(text.substring(text.length() -1))))
        {
            for (int i = 0; i < text.length(); i++)
            {
               /* if (text.charAt(i) == ' ') 
                {
                    counter++;
                }*/
                
                if (text.charAt(i) == 'e'|| text.charAt(i) == 'E') 
                {
                    counterE++;
                }
            }
            
        }
       return counterE;
    }
    
    public void setCounterE(int counterE)
    {
        this.counterE = counterE; 
    }
    //Counter for I
     public int getCounterI()
    {   
        if (!(" ".equals(text.substring(0, 1))) || !(" ".equals(text.substring(text.length() -1))))
        {
            for (int i = 0; i < text.length(); i++)
            {
               /* if (text.charAt(i) == ' ') 
                {
                    counter++;
                }*/
                
                if (text.charAt(i) == 'i' || text.charAt(i) == 'I') 
                {
                    counterI++;
                }
            }
            
        }
       return counterI;
    }
    
    public void setCounterI(int counterI)
    {
        this.counterI = counterI; 
    }
    //Counter for O
     public int getCounterO()
    {   
        if (!(" ".equals(text.substring(0, 1))) || !(" ".equals(text.substring(text.length() -1))))
        {
            for (int i = 0; i < text.length(); i++)
            {
               /* if (text.charAt(i) == ' ') 
                {
                    counter++;
                }*/
                
                if (text.charAt(i) == 'o' || text.charAt(i) == 'O') 
                {
                    counterO++;
                }
            }
            
        }
       return counterO;
    }
    
    public void setCounterO(int counterO)
    {
        this.counterO = counterO; 
    }
    //counter for U
     public int getCounterU()
    {   
        if (!(" ".equals(text.substring(0, 1))) || !(" ".equals(text.substring(text.length() -1))))
        {
            for (int i = 0; i < text.length(); i++)
            {
               /* if (text.charAt(i) == ' ') 
                {
                    counter++;
                }*/
                
                if (text.charAt(i) == 'u' || text.charAt(i) == 'U') 
                {
                    counterU++;
                }
            }
            
        }
       return counterU;
    }
    
    public void setCounterU(int counter)
    {
        this.counterU = counterU; 
    }
}