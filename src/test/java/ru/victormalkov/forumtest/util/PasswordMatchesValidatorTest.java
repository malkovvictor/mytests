package ru.victormalkov.forumtest.util;

import org.junit.jupiter.api.Assertions;
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
        Assertions.assertTrue(myValidator.isValid(dto, null));
    }

    @Test
    void validationShouldFailWhenPasswordAndConfirmationDiffer() {
        UserDTO dto = new UserDTO();
        dto.setPassword("test password");
        dto.setMatchingPassword("not matching");
        Assertions.assertFalse(myValidator.isValid(dto, null));
    }
}