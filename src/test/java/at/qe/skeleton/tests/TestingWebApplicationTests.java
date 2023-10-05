package at.qe.skeleton.tests;

import at.qe.skeleton.Main;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class TestingWebApplicationTests {

    //simple check test that will fail if the application context cannot start
    @Test
    public void contextLoads() {
    }

    //works when port 8080 is free
    @Test
    public void applicationContextTest() {
        Main.main(new String[] {});
    }
}
