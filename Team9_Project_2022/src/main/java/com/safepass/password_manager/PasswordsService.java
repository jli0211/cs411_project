package com.safepass.password_manager;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/* This class is for the service layer */
@Service
@Transactional
/* The Qualifier annotation identifies this class by "passwordService" */
@Qualifier("passwdService")
public class PasswordsService {

    /* Generate an instance of PasswordsRepository */
    @Autowired
    @Qualifier("passwordRepo")
    private PasswordsRepository passwordRepo;

    public List<Passwords> listAll() {
        return passwordRepo.findAll();
    }
     
    public void save(Passwords passwd) {
        passwordRepo.save(passwd);
    }
     
    public Passwords get(long passwdID) {
        return passwordRepo.findById(passwdID).get();
    }
     
    public void delete(long passwdID) {
        passwordRepo.deleteById(passwdID);
    }
}
