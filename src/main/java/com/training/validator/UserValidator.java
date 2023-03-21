package com.training.validator;

import com.training.model.User;
import com.training.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.SQLException;

/**
 * Validator for {@link com.training.model.User} class,
 * implements {@link org.springframework.validation.Validator}
 *
 * @author Vlad Skrebunov
 * @version 1.0
 */

@Component
public class UserValidator implements Validator {

    private final UserServiceImpl userService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.reject("username", "Size.userForm.username");
        }
        try {
            if (userService.getUserByUsername(user.getUsername()) != null) {
                errors.reject("username", "Duplicate.userForm.username");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.reject("password", "Size.userForm.password");
        }

        try {
            if (encoder.matches(user.getPassword(), userService.getUserByUsername(user.getUsername()).getPassword())) {
                errors.reject("password", "Different.userForm.password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
