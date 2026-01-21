package org.example.projectweb.service;

import org.example.projectweb.dao.userDao;
import org.example.projectweb.model.User;

import java.util.List;

public class UserService {
    final userDao userDao = new userDao();

    public List<User> getAllUsers() {
        return userDao.getListUser();
    }

    public User getUserById(int id) {
        User u = userDao.getUserById(id);
        if (u == null || !u.isStatus()) {
            return null;
        }
        return u;
    }
}
