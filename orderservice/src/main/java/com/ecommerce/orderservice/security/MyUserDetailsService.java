package com.ecommerce.orderservice.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        if ("admin".equals(username)) {
            return User.withUsername("admin")
                       .password("{noop}admin123") 
                       .roles("ADMIN")
                       .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}

