package at.qe.skeleton.tests;

import at.qe.skeleton.services.LocationService;
import at.qe.skeleton.ui.beans.HelloWorldBean;
import at.qe.skeleton.ui.beans.TimeFilterBean;
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

public class HelloWorldBeanTest {

    @Autowired
    HelloWorldBean helloWorldBean;


    @Test
    public void simpleTest() {
        Assertions.assertEquals("Hello from a JSF-managed bean!",helloWorldBean.getHello());
    }
}
