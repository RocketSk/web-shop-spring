package com.training.service;

import com.training.model.User;

import java.sql.SQLException;

public interface UserService {

    void addUser(User user) throws SQLException;

    User getUserByUsername(String username) throws SQLException;

    User getUserById(Integer id) throws SQLException;
}
