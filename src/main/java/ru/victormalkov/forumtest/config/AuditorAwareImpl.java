package ru.victormalkov.forumtest.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.victormalkov.forumtest.model.User;
import ru.victormalkov.forumtest.security.MyUserDetails;

import java.util.Optional;

class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(MyUserDetails.class::cast)
                .map(MyUserDetails::getUser);
    }
}