package org.example.projectweb.dao;

import org.example.projectweb.model.User;

import java.util.List;

public class userDao extends BaseDao {
    public User findByEmail(String email) {
        return get().withHandle(h ->
                h.createQuery("select uid, name, email, phone, password, address, bdate, avatar, role, status from user where email = :email")
                        .bind("email", email).mapToBean(User.class).findOne().orElse(null)
        );
    }

    public User getUserById(int userId) {
        return get().withHandle(h ->
                h.createQuery("select uid, name, email, phone, password, address, bdate, avatar, role, status from user where uid = :userId")
                        .bind("userId", userId).mapToBean(User.class).findOne().orElse(null)
        );
    }

    public void addUser(String email, String pass, String name) {
        get().useHandle(h ->
                h.createUpdate("INSERT INTO user (name, email, password, role, status) VALUES (:name, :email, :password, :role, :status)")
                        .bind("name", name).bind("email", email).bind("password", pass).bind("role", "user").bind("status", true).execute()
        );
    }

    public List<User> getListUser() {
        return get().withHandle(h -> h.createQuery("select uid, name, email, phone, password, address, bdate, avatar, role, status from user")
                .mapToBean(User.class).list());
    }

    public void updateStatus(int uid) {
        get().useHandle(h ->
                h.createUpdate("UPDATE user SET status = IF(status = 1, 0, 1) WHERE uid = :uid")
                        .bind("uid", uid)
                        .execute()
        );
    }
}