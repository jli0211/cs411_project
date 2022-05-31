package com.safepass.password_manager;

import javax.persistence.*;

@Entity
@Table(name = "passwords")
public class Passwords {
    /* This creates the table and names the columns of the table "paswords" */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passwordID;

    @Column(name = "platform", nullable = false, length = 20)
    private String passwordPlatform;

    @Column(name = "username", nullable = false, length = 20)
    private String passwordUsername;

    @Column(name = "password", length = 64)
    private String passwordVal;

    /* get and set methods for Passwords */
    public Long getPasswordID() {
        return passwordID;
    }
    public void setPasswordID(Long passwdID) {
        this.passwordID = passwdID;
    }
    public String getPlatform() {
        return passwordPlatform;
    }
    public void setPlatform(String platform_name) {
        this.passwordPlatform = platform_name;
    }
    public String getPasswordUsername() {
        return passwordUsername;
    }
    public void setPasswordUsername(String passwdUsername) {
        this.passwordUsername = passwdUsername;
    }
    public String getPasswordVal() {
        return passwordVal;
    }
    public void setPasswordVal(String passwd) {
        this.passwordVal = passwd;
    }

    protected Passwords() {
    }
}
