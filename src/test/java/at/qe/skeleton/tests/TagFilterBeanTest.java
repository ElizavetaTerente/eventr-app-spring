package at.qe.skeleton.tests;

import at.qe.skeleton.model.Location;
import at.qe.skeleton.model.Tag;
import at.qe.skeleton.services.LocationService;
import at.qe.skeleton.services.TagService;
import at.qe.skeleton.ui.beans.TagFilterBean;
import at.qe.skeleton.ui.beans.TimeFilterBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Transactional
@SpringBootTest
@WebAppConfiguration

public class TagFilterBeanTest {

    @Autowired
    TagFilterBean tagFilterBean;

    @Autowired
    TagService tagService;


    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void datainitializationTest() {

        String tagName = "testTag";

        Tag toBeCreatedTag = new Tag();
        toBeCreatedTag.setName(tagName);

        Locale austria = new Locale("de", "AT");

        Location locaiton = new Location();
        List ll = new ArrayList();
        ll.add(locaiton);
        toBeCreatedTag.setLocations(ll);

        List list = new ArrayList();
        list.add(toBeCreatedTag);

        Assertions.assertTrue(tagFilterBean.filterByTag(list,null,austria));
        Assertions.assertFalse(tagFilterBean.filterByTag(list,list,austria));

    }
}
