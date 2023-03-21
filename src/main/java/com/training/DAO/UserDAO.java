package com.training.DAO;

import com.training.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User getUserById(Integer id) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    void addUser(User user) throws SQLException;
}
