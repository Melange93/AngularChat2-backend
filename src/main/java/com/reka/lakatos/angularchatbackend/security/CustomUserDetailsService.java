package com.reka.lakatos.angularchatbackend.security;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.exception.AppUserNotFoundException;
import com.reka.lakatos.angularchatbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AppUser user = userService.findUserByUserName(username);
            return new User(user.getUserName(), user.getPassword(),
                    user.getRolesInString().stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList()));
        } catch (AppUserNotFoundException e) {
            throw new UsernameNotFoundException("User: " + username + " not found");
        }
    }
}
