package at.qe.skeleton.tests;

import at.qe.skeleton.ui.beans.SecurityTestBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
@WebAppConfiguration
public class SecurityTestBeanTest {

    @Autowired
    SecurityTestBean securityTestBean;

    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    public void simpleTest() {

        Assertions.assertFalse(securityTestBean.isShowOkDialog());
        Assertions.assertEquals("NONE",securityTestBean.getPerformedAction());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void doAdminActionTest() {
        securityTestBean.doAdminAction();
        Assertions.assertTrue(securityTestBean.isShowOkDialog());
        Assertions.assertEquals("ADMIN",securityTestBean.getPerformedAction());

    }

    @Test
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    public void doAManagerActionTest() {
        securityTestBean.doManagerAction();
        Assertions.assertTrue(securityTestBean.isShowOkDialog());
        Assertions.assertEquals("MANAGER",securityTestBean.getPerformedAction());

    }

    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    public void doUserActionTest() {
        securityTestBean.doUserAction();
        Assertions.assertTrue(securityTestBean.isShowOkDialog());
        Assertions.assertEquals("USER",securityTestBean.getPerformedAction());
        securityTestBean.doHideOkDialog();
        Assertions.assertFalse(securityTestBean.isShowOkDialog());



    }

}
