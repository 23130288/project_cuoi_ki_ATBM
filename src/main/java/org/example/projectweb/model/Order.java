package org.example.projectweb.model;

import java.io.Serializable;

public class Order implements Serializable {
    private int oid;
    private int uid;
    private int uvid;
    private String description;
    private String createdDate;
    private String status;

    private String customer;
    private double totalPrice;
    private double discount;
    private double finalPrice;

    public Order() {
    }

    public Order(int oid, int uid, int uvid, String description, String createdDate, String status) {
        this.oid = oid;
        this.uid = uid;
        this.uvid = uvid;
        this.description = description;
        this.createdDate = createdDate;
        this.status = status;
    }

    /**
     * GETTERS
     */
    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }


    public int getOid() {
        return oid;
    }

    public int getUid() {
        return uid;
    }

    public int getUvid() {
        return uvid;
    }

    public String getCustomer() {
        return customer;
    }



    public String getCreatedDate() {
        return createdDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    /**
     * SETTERS
     */
    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUvid(int uvid) {
        this.uvid = uvid;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
