package com.safepass.password_manager;

import javax.persistence.*;
 
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(nullable = false, unique = true, length = 45)
    private String email;
     
    @Column(nullable = false, length = 64)
    private String accountPassword;
     
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
     
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    public Long getId() {
        return id;
    }
    public void setId(Long input_id) {
        this.id = input_id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String input_email){
        this.email = input_email;
    }
    public String getAccountPassword() {
        return accountPassword;
    }
    public void setAccountPassword(String input_password) {
        this.accountPassword = input_password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String input_firstName) {
        this.firstName = input_firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String input_lastName) {
        this.lastName = input_lastName;
    }
}
