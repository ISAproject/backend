package com.example.ISA2023.back.user;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path="api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User addUnverified(@RequestBody User user){
        user.setVerified(false);
        User savedUser = userService.save(user);
        userService.sendVerificationEmail(savedUser);

        return savedUser;
    }
    @GetMapping("/verify/{id}")
    public User verify(@PathVariable Long id){
        User user = userService.findById(id);
        user.setVerified(true);
        return userService.update(id, user);
    }
    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email){
        var user = userService.findByEmail(email);
        return user;
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User updatedUser){
        return userService.update(id,updatedUser);}

    @GetMapping("/companyAdministrators")
    public List<User> getCompanyAdministrators(){
        return userService.getCompanyAdministrators();
    }
    @GetMapping("/getlastuser")
    public User getLastUser()
    {
        return userService.getLastUser();
    }
    @GetMapping("/username/{username}")
    public User getLoggedUser(@PathVariable String username){
        return userService.findByUsername(username)
                          .orElseThrow();
    }
}
