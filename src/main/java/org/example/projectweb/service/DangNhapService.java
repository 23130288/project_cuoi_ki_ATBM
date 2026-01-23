package org.example.projectweb.service;

import org.example.projectweb.dao.UserDao;
import org.example.projectweb.model.User;

public class DangNhapService {

    final UserDao userDao = new UserDao();

    public User login(String email, String password) {
        User user = userDao.findByEmail(email);
        //không tìm thấy email
        if (user == null) return null;

        //sai mật khẩu
        HashService hs = new HashService();
        if (!user.getPassword().equals(hs.hashMd5(password))) return null;
        return user;
    }
}
