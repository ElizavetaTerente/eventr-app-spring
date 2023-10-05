package at.qe.skeleton.validators;

import at.qe.skeleton.services.LocationService;
import at.qe.skeleton.services.TagService;
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
public class TagNameValidator implements Validator {

    @Autowired
    private TagService tagService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (this.tagService.loadTag(value.toString().trim()) != null) {
            FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tag already exists", "Tag already exists.");
            throw new ValidatorException(fmsg);
        }
    }
}