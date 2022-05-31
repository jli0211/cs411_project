package com.safepass.password_manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    /* Generate an instance of UserRepository */
    @Autowired
    @Qualifier("userRepo")
    private UserRepository userRepo;
    /* Generate an instance of PasswordsService */
    @Autowired
    @Qualifier("passwdService")
    private PasswordsService passwdService;

    /* Users will see the index.html file when they first access the page */
    @GetMapping("/safepass")
    public String viewHomePage() {
        return "index";
    }

    /* allows the user to register if they have do not have an account */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

     /* 
     The user's account password is encrypted through the BCryptPasswordEncoder package.
     Saves the newly created user to the database.
     */
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getAccountPassword());
        user.setAccountPassword(encodedPassword);

        userRepo.save(user);
     
        return "register_success";
    }

    /* implements the list of passwords into a table for users to access */
    @GetMapping("/passwords")
    public String viewPasswordsPage(Model model) {
        List<Passwords> listPasswds = passwdService.listAll();
        model.addAttribute("listPasswds", listPasswds);
        return "passwd_page";
    }

    /* users can add a new password */
    @RequestMapping("/new")
    public String showNewPasswordsPage(Model model) {
        Passwords passwd = new Passwords();
        model.addAttribute("passwd", passwd);
        return "new_password";
    }

    /* saves the password to the database */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePassword(@ModelAttribute("passwd") Passwords passwd) {
        passwdService.save(passwd);
        return "redirect:/passwords";
    }

    /* users can edit the password that they stored */
    @RequestMapping("/edit/{passwordID}")
    public ModelAndView showEditPasswordPage(@PathVariable(name = "passwordID") int id) {
    ModelAndView MAV = new ModelAndView("edit_passwd");
        Passwords passwd = passwdService.get(id);
        MAV.addObject("passwd", passwd);
        return MAV;
    }

    /* users can delete a password */
    @RequestMapping("/delete/{passwordID}")
    public String deleteProduct(@PathVariable(name = "passwordID") int id) {
        passwdService.delete(id);
        return "redirect:/passwords";       
    }


}
