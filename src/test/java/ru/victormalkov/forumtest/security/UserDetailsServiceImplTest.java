package ru.victormalkov.forumtest.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.victormalkov.forumtest.dto.UserDTO;
import ru.victormalkov.forumtest.services.UserService;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailsServiceImplTest {
    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService detailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    void testLoadUserByUsername() {
        UserDTO dto = new UserDTO();
        String name = "Test user 1";
        String pwd = "test pass";
        dto.setName(name);
        dto.setPassword(pwd);
        dto.setMatchingPassword(pwd);
        userService.registerNewAccount(dto);

        UserDetails ud = detailsService.loadUserByUsername(name);
        assertTrue(ud instanceof MyUserDetails);
        assertEquals(name, ud.getUsername());
        assertTrue(passwordEncoder.matches(pwd, ud.getPassword()));
        assertEquals(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")), ud.getAuthorities());
        assertTrue(ud.isAccountNonExpired());
        assertTrue(ud.isAccountNonLocked());
        assertTrue(ud.isEnabled());
        assertTrue(ud.isCredentialsNonExpired());
    }

    @Test
    void testLoadNotExistingUserShouldFail() {
        String notExistingName = "Some random name dnjojgoirjweq";
        assertThrows(UsernameNotFoundException.class, () -> detailsService.loadUserByUsername(notExistingName));
    }
}