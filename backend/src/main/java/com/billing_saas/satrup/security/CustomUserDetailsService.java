package com.billing_saas.satrup.security;

import com.billing_saas.satrup.entities.User;
import com.billing_saas.satrup.repositories.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepository;

    public CustomUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Long userId = Long.parseLong(username);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserId().toString())
                    .password("")
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(!user.getIsActive())
                    .credentialsExpired(false)
                    .disabled(!user.getIsActive())
                    .build();
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid user id format");
        }
    }
}