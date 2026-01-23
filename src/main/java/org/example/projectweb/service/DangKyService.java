package org.example.projectweb.service;

import org.example.projectweb.dao.UserDao;

public class DangKyService {

    private static UserDao userDao = new UserDao();

    public static boolean addUser(String email, String password, String name) {
        // Check email đã tồn tại
        if (userDao.findByEmail(email) != null) {
            return false;
        }

        // Tạo user mới
        HashService hs = new HashService();
        userDao.addUser(email, hs.hashMd5(password), name);
        return true;
    }
}