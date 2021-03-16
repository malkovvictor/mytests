package ru.victormalkov.forumtest.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.victormalkov.forumtest.dto.UserDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordMatchesValidatorTest {
    private static PasswordMatchesValidator myValidator;

    @BeforeAll
    public static void setup() {
        myValidator = new PasswordMatchesValidator();
    }

    @Test
    void validationShouldPassWhenPasswordAndConfirmationAreEqual() {
        UserDTO dto = new UserDTO();
        dto.setPassword("test password");
        dto.setMatchingPassword("test password");
        assertTrue(myValidator.isValid(dto, null));
    }

    @Test
    void validationShouldFailWhenPasswordAndConfirmationDiffer() {
        UserDTO dto = new UserDTO();
        dto.setPassword("test password");
        dto.setMatchingPassword("not matching");
        assertFalse(myValidator.isValid(dto, null));
    }

    @Test
    void passwordMatchesValidator_when_emptyPasswords_then_accept() {
        UserDTO dto = new UserDTO();
        dto.setPassword("");
        dto.setMatchingPassword("");
        assertTrue(myValidator.isValid(dto, null));
    }

    @Test
    void passwordMatchesValidator_when_nullPasswords_then_accept() {
        UserDTO dto = new UserDTO();
        assertTrue(myValidator.isValid(dto, null));
    }
}