package com.training.security;

import com.training.DAO.impl.UserDaoImpl;
import com.training.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * It's user details service for {@link User}
 *
 * @author Vlad Skrebunov
 */

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDaoImpl userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        return SecurityUser.fromUser(user);
    }
}