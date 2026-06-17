package org.example.projectweb.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int uid;
    private String name;
    private String email;
    private String password;
    private String address;
    private String bdate;
    private String avatar;
    private String role;
    private String phone;
    private boolean status;
    private boolean canUpKey;

    public User() {
    }

    public User(int uid, String name, String email, String password, String address, String bdate, String avatar, String role, String phone, boolean status, boolean canUpKey) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.bdate = bdate;
        this.avatar = avatar;
        this.role = role;
        this.phone = phone;
        this.status = status;
        this.canUpKey = canUpKey;
    }

    // ===== getter / setter =====
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isCanUpKey() {
        return canUpKey;
    }

    public void setCanUpKey(boolean canUpKey) {
        this.canUpKey = canUpKey;
    }
}