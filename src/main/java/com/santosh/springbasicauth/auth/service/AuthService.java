package com.santosh.springbasicauth.auth.service;

import com.santosh.springbasicauth.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load user by username function called : {}", username);
        com.santosh.springbasicauth.model.UserDetails userDetails = userDAO.getUser(username);
        log.info("user details : " + userDetails);
        if (userDetails == null) {// should have proper handling of Exception
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userDetails.getRole());
        org.springframework.security.core.userdetails.UserDetails details = new User(userDetails.getUsername(),
                userDetails.getPassword(), Arrays.asList(grantedAuthority));
        return details;
    }
}
