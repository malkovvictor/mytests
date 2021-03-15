package ru.victormalkov.forumtest.util;

import lombok.extern.slf4j.Slf4j;
import ru.victormalkov.forumtest.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDTO user = (UserDTO) obj;
        boolean result = (user.getPassword() == null && user.getMatchingPassword() == null) ||
                        user.getPassword() != null && user.getPassword().equals(user.getMatchingPassword());
        log.debug("Calling password matches validator on {}\nresult should be {}", user, result);
        return result;
    }
}