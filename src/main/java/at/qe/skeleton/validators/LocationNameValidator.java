package at.qe.skeleton.validators;

import at.qe.skeleton.services.LocationService;
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
public class LocationNameValidator implements Validator {

    @Autowired
    private LocationService locationService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (this.locationService.getLocationByName(value.toString().trim()) != null) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Location already exists", "Location already exists.");
            throw new ValidatorException(fmsg);
        }
    }
}