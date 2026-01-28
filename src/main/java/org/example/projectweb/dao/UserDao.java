package org.example.projectweb.dao;

import org.example.projectweb.model.User;

import java.util.List;

public class UserDao extends BaseDao {
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

    public List<Integer> getListIdUser() {
        return get().withHandle(h -> h.createQuery("SELECT uid FROM user WHERE status = true  AND role <> 'ADMIN'")
                .mapTo(Integer.class).list());
    }

    public List<User> getListUser() {
        return get().withHandle(h -> h.createQuery("select uid, name, email, role, status from user")
                .mapToBean(User.class).list());
    }

    public int countCustomers() {
        return get().withHandle(h ->
                h.createQuery("SELECT COUNT(DISTINCT o.uid) FROM `order` o JOIN user u ON o.uid = u.uid WHERE u.role = 'user' AND u.status = true")
                        .mapTo(Integer.class).one()
        );
    }

    public List<User> getAllUsersNameLike(String name) {
        return get().withHandle(h -> h.createQuery("select uid, name, email, role, status from user " +
                        "where LOWER(name) LIKE LOWER(:name)")
                .bind("name", "%" + name + "%").mapToBean(User.class).list());
    }

    public void updateStatus(int uid) {
        get().useHandle(h ->
                h.createUpdate("UPDATE user SET status = IF(status = 1, 0, 1) WHERE uid = :uid")
                        .bind("uid", uid)
                        .execute()
        );
    }

    public void updateRole(int uid) {
        get().useHandle(h ->
                h.createUpdate("UPDATE user SET role = IF(role = 'user', 'admin', 'user') WHERE uid = :uid")
                        .bind("uid", uid).execute()
        );
    }

    public void updatePassword(int uid, String newPassword) {
        get().useHandle(h -> h.createUpdate("""
                        update user set password = :newPassword where uid = :uid
                        """).bind("newPassword", newPassword).bind("uid", uid)
                .execute());
    }

    public void changeUserInfo(int uid, String name, String phone, String address) {
        get().useHandle(h -> h.createUpdate("""
                        update user 
                        set name = :name, phone = :phone, address = :address
                        where uid = :uid
                        """).bind("name", name).bind("phone", phone).bind("address", address).bind("uid", uid)
                .execute());
    }
}