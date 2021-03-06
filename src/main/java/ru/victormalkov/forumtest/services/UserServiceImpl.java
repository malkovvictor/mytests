package ru.victormalkov.forumtest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.victormalkov.forumtest.dto.UserDTO;
import ru.victormalkov.forumtest.model.User;
import ru.victormalkov.forumtest.repository.UserRepository;

import javax.transaction.Transactional;
@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User registerNewAccount(UserDTO userdto) {
        logger.error("Adding new user: {}", userdto);
        User user = new User();
        user.setName(userdto.getName());
        user.setPassword(userdto.getPassword());
        return userRepository.save(user);
    }
}
