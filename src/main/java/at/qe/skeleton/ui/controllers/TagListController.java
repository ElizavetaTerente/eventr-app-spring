package at.qe.skeleton.ui.controllers;

import at.qe.skeleton.model.Tag;
import at.qe.skeleton.services.TagService;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("view")
public class TagListController implements Serializable {
    @Autowired
    private TagService tagService;

    private List<Tag> tags;
    private List<Tag> filteredTags;

    @PostConstruct
    public void init() {
        this.tags = tagService.getAllTags();
    }

    public List<Tag> getTags() {
        return tagService.getAllTags();
    }

    public List<Tag> getFilteredTags() {
        return this.filteredTags;
    }

    public void setFilteredTags(List<Tag> filteredTags) {
        this.filteredTags = filteredTags;
    }
}