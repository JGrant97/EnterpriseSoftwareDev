/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author jackg
 */
@FacesValidator(value = "ValidateProject")
public class ValidateProject implements Serializable, Validator
{
    @Override
    public void validate (FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
        int number = (int) value;
        
        //checks if the numbers are between 1 and 20. if not it displays an error message
        if (number > 20 || number <= 0)
        {
            throw new ValidatorException(new FacesMessage("Error: Please enter a value between 1 and 20"));
        }
    }
      
}
