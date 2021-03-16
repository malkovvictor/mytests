package ru.victormalkov.forumtest.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.victormalkov.forumtest.dto.UserDTO;
import ru.victormalkov.forumtest.model.User;
import ru.victormalkov.forumtest.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User registerNewAccount(@Valid UserDTO userdto) {
        log.info("Adding new user: {}", userdto);
        User user = new User();
        user.setName(userdto.getName());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }
}
