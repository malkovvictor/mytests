package ru.victormalkov.forumtest.dto;

import lombok.Data;
import ru.victormalkov.forumtest.util.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches(message="{registration.notmatchingpass}")
public class UserDTO {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
}
