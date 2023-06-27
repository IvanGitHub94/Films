package com.jpcsaturrday.springlibraryproject.library.service.userDetails;

import com.jpcsaturrday.springlibraryproject.library.constants.UserFRolesConstants;
import com.jpcsaturrday.springlibraryproject.library.model.UserF;
import com.jpcsaturrday.springlibraryproject.library.repository.UserFRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.jpcsaturrday.springlibraryproject.library.constants.UserFRolesConstants.ADMIN;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserFRepository userRepository;

    @Value("${spring.security.user.name}")
    private String adminUser;
    @Value("${spring.security.user.password}")
    private String adminPassword;

    public CustomUserDetailsService(UserFRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUser)) {
            return new CustomUserDetails(null, username, adminPassword, List.of(new SimpleGrantedAuthority("ROLE_" + ADMIN)));
        } else {
            Optional<UserF> userOpt = userRepository.findByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();

            if (userOpt.isPresent()) {
                UserF user = userOpt.get();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + UserFRolesConstants.USER));

                return new CustomUserDetails(user.getId().intValue(), username, user.getPassword(), authorities);
            }
            throw new IllegalArgumentException("No user found with login " + username);
        }
    }
}

