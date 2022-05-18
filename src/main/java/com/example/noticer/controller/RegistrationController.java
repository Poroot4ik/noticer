package com.example.noticer.controller;

import com.example.noticer.domain.Role;
import com.example.noticer.domain.User;
import com.example.noticer.repos.UsersRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UsersRepo usersRepo;

    public RegistrationController(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = usersRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        usersRepo.save(user);
        return "redirect:/login";
    }
}
