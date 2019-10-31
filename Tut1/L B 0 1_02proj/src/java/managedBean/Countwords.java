/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "countwords")
@RequestScoped

public class Countwords
{
    private String text;
    int counter = 0;
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
         
    public int getCounter()
    {   
        if (!(" ".equals(text.substring(0, 1))) || !(" ".equals(text.substring(text.length() -1))))
        {
            for (int i = 0; i < text.length(); i++)
            {
                if (text.charAt(i) == ' ') 
                {
                    counter++;
                }
            }
            counter = counter +1;
        }
       return counter;
    }
    
    public void setCounter(int counter)
    {
        this.counter = counter; 
   }
   
}