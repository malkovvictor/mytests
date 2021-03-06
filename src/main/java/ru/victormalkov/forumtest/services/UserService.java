package ru.victormalkov.forumtest.services;

import ru.victormalkov.forumtest.dto.UserDTO;
import ru.victormalkov.forumtest.model.User;

public interface UserService {
    User registerNewAccount(UserDTO userdto);
}
