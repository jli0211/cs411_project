package com.safepass.password_manager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

/*
The @Qualifier annotation is used to identify this interface by "passwordRepo."
Domain type is identified as Passwords and ID type is Long for Spring Data JPA.
*/
@Qualifier("passwordRepo")
public interface PasswordsRepository extends JpaRepository<Passwords, Long> {
    
}
