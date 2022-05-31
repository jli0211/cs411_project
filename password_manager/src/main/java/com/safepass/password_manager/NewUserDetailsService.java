package com.safepass.password_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NewUserDetailsService implements UserDetailsService {

    /* Generate an instance of UserRepository */
    @Autowired
    @Qualifier("userRepo")
    private UserRepository userRepo;
    
    /* 
    When Spring Security uses the loadUserbyUsername() method to authenticate the user,
    it will create a NewUserDetails object to represent the authenticated user.
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new NewUserDetails(user);
    }
}
