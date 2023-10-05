package at.qe.skeleton.ui.beans;

import at.qe.skeleton.model.Tag;
import at.qe.skeleton.services.TagService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "tagConverter", managed = true)
public class TagConverter implements Converter<Tag> {

    @Inject
    private TagService tagService;

    @Override
    public Tag getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                System.out.println(tagService.getTagsAsMap().get(value));
                return tagService.getTagsAsMap().get(value);
            } catch (NumberFormatException e) {
                throw new ConverterException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Tag value) {
        if (value != null) {
            return String.valueOf(value.getId());
        } else {
            return null;
        }
    }
}