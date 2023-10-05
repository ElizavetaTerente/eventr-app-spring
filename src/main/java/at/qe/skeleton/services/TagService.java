package at.qe.skeleton.services;

import at.qe.skeleton.model.Tag;
import at.qe.skeleton.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Scope("application")
public class TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private UserService userService;

    private Map<String, Tag> tagsAsMap;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag loadTag(String tagName) {
        return tagRepository.findByName(tagName);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public Tag saveTag(Tag tag) {
        if (tag.isNew()) {
            tag.setCreateDate(new Date());
            tag.setCreateUser(userService.getAuthenticatedUser());
            return tagRepository.save(tag);
        }

        return tag;
    }

    public Map<String, Tag> getTagsAsMap() {
        tagsAsMap = getAllTags().stream().collect(Collectors.toMap(Tag::getId, tag -> tag));

        return tagsAsMap;
    }
}
