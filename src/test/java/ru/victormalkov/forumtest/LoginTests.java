package ru.victormalkov.forumtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class LoginTests {
    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void homeShouldRedirectToLoginPage() throws Exception {
        mvc
                .perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login.html"));
    }

    @Test
    public void randomUrlShouldRedirectToLoginPage() throws Exception {
        mvc
                .perform(get("/abracadabra/fjoirjewfoijo")) // chosen by fare dice roll, guaranteed to be random
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login.html"));
    }

    @Test
    @WithMockUser("Some User")
    public void homepageWithAuth() throws Exception {
        mvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
