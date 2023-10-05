package at.qe.skeleton.validators;

import at.qe.skeleton.services.LocationService;
import at.qe.skeleton.services.TagService;
import at.qe.skeleton.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@ApplicationScope
public class UserNameValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (this.userService.loadUser(value.toString().trim()) != null) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User already exists", "User already exists.");
            throw new ValidatorException(fmsg);
        }
    }
}