package com.pccw.backend.modules.user;

import com.pccw.backend.modules.mail.MailService;
import com.pccw.backend.persistence.Page;
import com.pccw.backend.persistence.PageRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;


    @PostMapping
    public void createUser(@RequestBody @Valid User user) {
        user.setPassword(PasswordGenerator.generatePassword());
        userService.save(user);
        mailService.sendAsync(user);
    }

    @PutMapping("/{userId}")
    public void updateUser(@RequestBody @Valid UpdateUserInput user, @PathVariable Long userId) {
        userService.update(user, userId);
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping
    public Page<User> findByCriteria(@Valid UserCriteria userCriteria) {
        if (userCriteria.getPageRequest() == null) {
            userCriteria.setPageRequest(new PageRequest());
        }
        return userService.findPageByCriteria(userCriteria);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

}
