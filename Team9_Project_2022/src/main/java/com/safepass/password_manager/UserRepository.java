package com.safepass.password_manager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/* The Qualifier annotation identifies this interface by 'userRepo' */
@Qualifier("userRepo")
public interface UserRepository extends JpaRepository<User, Long> {
    
    /* This returns a user based on their email */
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
}
