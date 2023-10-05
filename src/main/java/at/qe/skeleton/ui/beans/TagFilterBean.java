package at.qe.skeleton.ui.beans;

import java.util.List;
import java.util.Locale;

import org.primefaces.util.LangUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import at.qe.skeleton.model.Tag;

@Component
@Scope("request")
public class TagFilterBean {

    public boolean filterByTag(Object value, Object filter, Locale locale) {

        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();

        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }

        List<Tag> tag_list = (List<Tag>) value;

        for (Tag tag : tag_list) {
            if (tag.getName().toLowerCase().contains(filterText)) {
                return true;
            }
        }

        // return true or false
        return false;
    }

}
