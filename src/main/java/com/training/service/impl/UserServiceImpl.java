package com.training.service.impl;

import com.training.DAO.impl.UserDaoImpl;
import com.training.model.User;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * It's service for {@link User}
 *
 * @author Vlad Skrebunov
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    @Autowired
    public UserServiceImpl(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void addUser(User user) throws SQLException {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUsername(user.getUsername().toLowerCase());
        userDao.addUser(user);
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUserById(Integer id) throws SQLException {
        return userDao.getUserById(id);
    }

}
