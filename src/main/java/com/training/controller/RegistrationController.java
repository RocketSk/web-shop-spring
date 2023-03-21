package com.training.controller;

import com.training.mapper.UserMapper;
import com.training.model.Role;
import com.training.model.User;
import com.training.service.impl.UserServiceImpl;
import com.training.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * This controller is used for registrations of {@link com.training.model.User}'s registration.
 *
 * @author Vlad Skrebunov
 * @version 1.1
 */

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private static final String TERMS = "TERMS";
    private final UserServiceImpl userService;

    @Autowired
    public RegistrationController(UserServiceImpl userService, UserMapper mapper) {
        this.userService = userService;
    }

    @GetMapping
    public String registration(@ModelAttribute("userForm") User userForm, Model model) {
        model.addAttribute("userForm", userForm);
        return "registration/registration";
    }

    @PostMapping
    public String registrationUser(@ModelAttribute("userForm") User userForm, HttpServletRequest request) throws SQLException {
        if (request.getParameter(TERMS) != null) {
            registrationProcess(userForm);
            return "redirect:/login";
        } else {
            return "registration/registration";
        }
    }

    private void registrationProcess(User user) throws SQLException {
        user.setRole(Role.USER);
        userService.addUser(user);
    }
}
