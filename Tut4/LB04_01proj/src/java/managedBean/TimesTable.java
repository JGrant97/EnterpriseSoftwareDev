/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import model.GetResult;

@ManagedBean(name = "timestable")
@RequestScoped

/**
 *
 * @author jackg
 */
public class TimesTable
{

    int number = 0;
    int result1 = 0;
    int result2 = 0;
    int result3 = 0;
    int result4 = 0;
    int result5 = 0;
    int result6 = 0;
    int result7 = 0;
    int result8 = 0;
    int result9 = 0;
    int result10 = 0;
    int result11 = 0;
    int result12 = 0;

    public String fetchResults(int num)
    {

        GetResult dbOp = new GetResult();
        num = number;
        dbOp.execute(num);
        result1 = dbOp.getResult1();
        result2 = dbOp.getResult2();
        result3 = dbOp.getResult3();
        result4 = dbOp.getResult4();
        result5 = dbOp.getResult5();
        result6 = dbOp.getResult6();
        result7 = dbOp.getResult7();
        result8 = dbOp.getResult8();
        result9 = dbOp.getResult9();
        result10 = dbOp.getResult10();
        result11 = dbOp.getResult11();
        result12 = dbOp.getResult12();

        return "output";
    }

    public int getNumber()
    {
        return number;
    }

    public int getResult1()
    {
        return result1;
    }

    public int getResult2()
    {
        return result2;
    }

    public int getResult3()
    {
        return result3;
    }

    public int getResult4()
    {
        return result4;
    }

    public int getResult5()
    {
        return result5;
    }

    public int getResult6()
    {
        return result6;
    }

    public int getResult7()
    {
        return result7;
    }

    public int getResult8()
    {
        return result8;
    }

    public int getResult9()
    {
        return result9;
    }

    public int getResult10()
    {
        return result10;
    }

    public int getResult11()
    {
        return result11;
    }

    public int getResult12()
    {
        return result12;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

}
