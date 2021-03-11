package ru.victormalkov.forumtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindException;
import ru.victormalkov.forumtest.dto.UserDTO;
import ru.victormalkov.forumtest.model.User;
import ru.victormalkov.forumtest.services.UserService;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserService us;

    @Autowired
    PasswordEncoder enc;

    @Test
    public void testNewUserCreation() {
        UserDTO dto = new UserDTO();
        dto.setName("Test user 1");
        dto.setPassword("test pass");
        dto.setMatchingPassword("test pass");

        User u = us.registerNewAccount(dto);
        Assertions.assertNotNull(u);
        Assertions.assertEquals("Test user 1", u.getName());
        Assertions.assertTrue(enc.matches("test pass", u.getPassword()));
        Assertions.assertEquals("ROLE_USER", u.getRole());
    }

    @Test
    public void testNewUserCreationWithNotMatchingPassword() {
        UserDTO dto = new UserDTO();
        dto.setName("Test user 2");
        dto.setPassword("test pass");
        dto.setMatchingPassword("not matching test pass");

        Exception e = Assertions.assertThrows(BindException.class, () -> us.registerNewAccount(dto));
        Assertions.assertEquals("Passwords don't match", e.getMessage());
    }
}
