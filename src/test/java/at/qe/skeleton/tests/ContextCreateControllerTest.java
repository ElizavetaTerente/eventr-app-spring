package at.qe.skeleton.tests;

import static org.assertj.core.api.Assertions.assertThat;

import at.qe.skeleton.ui.controllers.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContextCreateControllerTest {

    @Autowired
    private SignUpController controller;

    //To convince that the context is creating the controller, adding an assertion :

    @Test
    public void contextCreateSignUpController() throws Exception {
        assertThat(controller).isNotNull();
    }

}
