package ru.victormalkov.forumtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
class RegistrationControllerTest {
    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    @Transactional
    public void testUserCreation() throws Exception {
        MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/registration")
                .param("name", "Test user 1")
                .param("password", "Test password")
                .param("matchingPassword", "Test password"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andReturn();
    }

    @Test
    @Transactional
    public void userCreationWithoutNameShouldFail() throws Exception {
        MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/registration")
                .param("Noname", "Test user 1")
                .param("password", "Test password")
                .param("matchingPassword", "Test password"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andReturn();
    }

    @Test
    @Transactional
    public void userCreationWithoutPasswordShouldFail() throws Exception {
        MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/registration")
                .param("name", "Test user 1")
                .param("NoPassword", "Test password")
                .param("matchingPassword", "Test password"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andReturn();
    }

    @Test
    @Transactional
    public void userCreationWithNotMatchingPasswordShouldFail() throws Exception {
        MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/registration")
                .param("name", "Test user 1")
                .param("password", "Test password")
                .param("matchingPassword", "Not matching password"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andReturn();
    }

}