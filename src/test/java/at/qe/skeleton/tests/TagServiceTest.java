package at.qe.skeleton.tests;

import at.qe.skeleton.model.Location;
import at.qe.skeleton.model.Tag;
import at.qe.skeleton.model.Voting;
import at.qe.skeleton.services.TagService;
import at.qe.skeleton.services.UserService;
import at.qe.skeleton.services.VotingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@SpringBootTest
@WebAppConfiguration
public class TagServiceTest {

    @Autowired
    TagService tagService;

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    public void datainitializationTest() {
        Assertions.assertEquals(7, tagService.getAllTags().size(),
                "Insufficient amount of tags initialized for test data source");
        for (Tag tag : tagService.getAllTags()) {
            if ("Backwaren".equals(tag.getName())) {
                Assertions.assertNotNull(tag.getCreateUser(), "Tag \"" + tag + "\" does not have a createUser defined");
                Assertions.assertNotNull(tag.getCreateDate(), "Tag \"" + tag + "\" has a CreateDate defined");
            } else if ("Tagessuppe".equals(tag.getName())) {
                Assertions.assertNotNull(tag.getCreateUser(), "Tag \"" + tag + "\" does not have a createUser defined");
                Assertions.assertNotNull(tag.getCreateDate(), "Tag \"" + tag + "\" has a createDate defined");
            } else if ("familienfreundlich".equals(tag.getName())) {
                Assertions.assertNotNull(tag.getCreateUser(), "Tag \"" + tag + "\" does not have a createUser defined");
                Assertions.assertNotNull(tag.getCreateDate(), "Tag \"" + tag + "\" has a createDate defined");
            } else if ("chinesisch".equals(tag.getName())) {
                Assertions.assertNotNull(tag.getCreateUser(), "Tag \"" + tag + "\" does not have a createUser defined");
                Assertions.assertNotNull(tag.getCreateDate(), "Tag \"" + tag + "\" has a createDate defined");
            } else if ("Pizza".equals(tag.getName())) {
                Assertions.assertNotNull(tag.getCreateUser(), "Tag \"" + tag + "\" does not have a createUser defined");
                Assertions.assertNotNull(tag.getCreateDate(), "Tag \"" + tag + "\" has a createDate defined");
            } else if ("Burritos".equals(tag.getName())) {
                Assertions.assertNotNull(tag.getCreateUser(), "Tag \"" + tag + "\" does not have a createUser defined");
                Assertions.assertNotNull(tag.getCreateDate(), "Tag \"" + tag + "\" has a createDate defined");
            } else if ("Alkohol".equals(tag.getName())) {
                Assertions.assertNotNull(tag.getCreateUser(), "Tag \"" + tag + "\" does not have a createUser defined");
                Assertions.assertNotNull(tag.getCreateDate(), "Tag \"" + tag + "\" has a createDate defined");
            } else {
                Assertions.fail("Unknown  \"" + tag.getName() + "\" loaded from test data source ");
            }
        }

    }

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN" })
    public void CreateAndSafeTagAsAdminTest() {

        String tagName = "testTag";

        Tag toBeCreatedTag = new Tag();
        toBeCreatedTag.setName(tagName);

        tagService.saveTag(toBeCreatedTag);

        Location locaiton = new Location();
        List ll = new ArrayList();
        ll.add(locaiton);

        toBeCreatedTag.setLocations(ll);

        Tag freshlyCreatedTag = tagService.loadTag(tagName);
        Assertions.assertNotNull(freshlyCreatedTag,
                "New tag could not be loaded from test data source after being saved");
        Assertions.assertEquals(tagName, freshlyCreatedTag.getName(),
                "New tag could not be loaded from test data source after being saved");
        Assertions.assertNotNull(freshlyCreatedTag.getCreateDate(),
                "Tag \"" + tagName + "\" does not have a createDate defined after being saved");
        Assertions.assertNotNull(freshlyCreatedTag.getLocations());

        Assertions.assertEquals(8, tagService.getAllTags().size(),
                "Insufficient amount of tags initialized for test data source");

    }

    @Test
    @WithMockUser(username = "manager", authorities = { "MANAGER" })
    public void CreateAndSafeTagAsManagerTest() {

        String tagName = "testTag";

        Tag toBeCreatedTag = new Tag();
        toBeCreatedTag.setName(tagName);

        tagService.saveTag(toBeCreatedTag);

        Tag freshlyCreatedTag = tagService.loadTag(tagName);
        Assertions.assertNotNull(freshlyCreatedTag,
                "New tag could not be loaded from test data source after being saved");
        Assertions.assertEquals(tagName, freshlyCreatedTag.getName(),
                "New tag could not be loaded from test data source after being saved");
        Assertions.assertNotNull(freshlyCreatedTag.getCreateDate(),
                "Tag \"" + tagName + "\" does not have a createDate defined after being saved");

        Assertions.assertEquals(8, tagService.getAllTags().size(),
                "Insufficient amount of tags initialized for test data source");

    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    public void CreateAndSafeTagAsUserTest() {

        Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {

            String tagName = "testTag";

            Tag toBeCreatedTag = new Tag();
            toBeCreatedTag.setName(tagName);

            Tag newTag = tagService.saveTag(toBeCreatedTag);

        });
    }

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN" })
    public void deleteTagAsAdminTest() {

        String tagName = "testTag";

        Tag toBeCreatedTag = new Tag();
        toBeCreatedTag.setName(tagName);

        tagService.saveTag(toBeCreatedTag);

        Tag toBeDeletedEvent = tagService.loadTag(tagName);

        tagService.deleteTag(toBeDeletedEvent);

        Assertions.assertEquals(7, tagService.getAllTags().size(), "No tags has been deleted");
        Tag deletedTag = tagService.loadTag(tagName);
        Assertions.assertNull(deletedTag,
                "Deleted Tag \"" + tagName + "\" could still be loaded from test data source  ");

    }

    @Test
    @WithMockUser(username = "manager", authorities = { "MANAGER" })
    public void deleteTagAsManagerTest() {

        String tagName = "testTag";

        Tag toBeCreatedTag = new Tag();
        toBeCreatedTag.setName(tagName);

        tagService.saveTag(toBeCreatedTag);

        Tag toBeDeletedEvent = tagService.loadTag(tagName);

        tagService.deleteTag(toBeDeletedEvent);

        Assertions.assertEquals(7, tagService.getAllTags().size(), "No tags has been deleted");
        Tag deletedTag = tagService.loadTag(tagName);
        Assertions.assertNull(deletedTag,
                "Deleted Tag \"" + tagName + "\" could still be loaded from test data source  ");

    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    public void deleteTagAsUserTest() {

        Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> {

            String tagName = "testTag";

            Tag toBeDeletedEvent = tagService.loadTag(tagName);

            tagService.deleteTag(toBeDeletedEvent);
        });
    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    public void getTagAsMapTest() {
        Map<String, Tag> map = new HashMap<>();
        map = tagService.getTagsAsMap();
        Assertions.assertEquals(map, tagService.getTagsAsMap());
    }

}
