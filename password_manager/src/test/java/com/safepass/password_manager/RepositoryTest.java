package com.safepass.password_manager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    @Qualifier("userRepo")
    private UserRepository userRepo;

    @Autowired
    @Qualifier("passwordRepo")
    private PasswordsRepository passwordRepo;
    
    /* This is a test to see if User is successfully created in the database */
    @Test
    public void testCreateUser() {
    User user = new User();
    user.setEmail("terrier2022@bu.edu");
    user.setAccountPassword("safepass411");
    user.setFirstName("John");
    user.setLastName("Smith");
     
    User savedUser = userRepo.save(user);
     
    User existUser = entityManager.find(User.class, savedUser.getId());
     
    assertThat(user.getEmail()).isEqualTo(existUser.getEmail());    
    }

    /* This is a test to see if a password is successfully created in the database */
    @Test
    public void testCreatePasswd() {
        Passwords passwd = new Passwords();
        passwd.setPlatform("Netflix");
        passwd.setPasswordUsername("BU_Terrier");
        passwd.setPasswordVal("breakingbad1");

        Passwords savedPasswords = passwordRepo.save(passwd);

        Passwords existPasswords = entityManager.find(Passwords.class, savedPasswords.getPasswordID());

        assertThat(passwd.getPasswordVal()).isEqualTo(existPasswords.getPasswordVal());
    }
}
