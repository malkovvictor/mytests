package ru.victormalkov.forumtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class RegistrationControllerTest {
    public static final String ERROR_XPATH = "//form//p";

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    public void testFormShow() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(xpath("//form//input[@id='name']").exists())
                .andExpect(xpath(ERROR_XPATH).doesNotExist());
    }

    @Test
    @Transactional
    public void testUserCreation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/registration")
                .param("name", "Test user 1")
                .param("password", "Test password")
                .param("matchingPassword", "Test password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    @Transactional
    public void userCreationWithoutNameShouldFail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/registration")
                .param("Noname", "Test user 1")
                .param("password", "Test password")
                .param("matchingPassword", "Test password"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(xpath(ERROR_XPATH).exists());
    }

    @Test
    @Transactional
    public void userCreationWithoutPasswordShouldFail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/registration")
                .param("name", "Test user 1")
                .param("NoPassword", "Test password")
                .param("matchingPassword", "Test password"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(xpath(ERROR_XPATH).exists());
    }

    @Test
    @Transactional
    public void userCreationWithNotMatchingPasswordShouldFail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/registration")
                .param("name", "Test user 1")
                .param("password", "Test password")
                .param("matchingPassword", "Not matching password"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(xpath(ERROR_XPATH).exists());
    }

}