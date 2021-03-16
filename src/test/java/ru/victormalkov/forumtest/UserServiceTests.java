package ru.victormalkov.forumtest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.victormalkov.forumtest.dto.UserDTO;
import ru.victormalkov.forumtest.model.User;
import ru.victormalkov.forumtest.services.UserService;

import javax.transaction.Transactional;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserService us;

    @Autowired
    PasswordEncoder enc;

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        validator = vf.usingContext().getValidator();
    }

    @Test
    @Transactional
    public void testNewUserCreation() {
        UserDTO dto = new UserDTO();
        dto.setName("Test user 1");
        dto.setPassword("test pass");
        dto.setMatchingPassword("test pass");

        User u = us.registerNewAccount(dto);
        assertNotNull(u);
        assertEquals("Test user 1", u.getName());
        assertTrue(enc.matches("test pass", u.getPassword()));
        assertEquals("ROLE_USER", u.getRole());

        // try to create user with the same name
        assertThrows(DataIntegrityViolationException.class, () -> us.registerNewAccount(dto));
    }
}
