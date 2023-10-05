package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.Tag;
import at.qe.skeleton.services.TagService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("view")
public class TagDetailController implements Serializable {
    @Autowired
    private TagService tagService;

    private Tag tag;

    private String mode;

    public void setTag(Tag tag) {
        this.tag = tag;
        doReloadTag();
    }

    public Tag getTag() {
        return this.tag;
    }

    public void setMode(String mode) {
        if (mode.equals("add")) {
            this.tag = new Tag();
        }

        this.mode = mode;
    }

    public String getMode() {
        return this.mode;
    }

    public void doReloadTag() {
        tag = tagService.loadTag(tag.getName());
    }

    public void doSaveTag() {
        tag = this.tagService.saveTag(tag);
        tag = null;

        PrimeFaces.current().executeScript("PF('tagEditDialog').hide()");
    }

    public void doDeleteTag() {
        this.tagService.deleteTag(tag);
        tag = null;
    }
}
