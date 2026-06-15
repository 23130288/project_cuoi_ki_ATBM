package org.example.projectweb.model;

import java.io.Serializable;

public class Order implements Serializable {
    private int oid;
    private int uid;
    private int uvid;
    private String description;
    private String createdDate;
    private double finalPrice;
    private String status;

    private String hash;
    private String signature;
    private boolean signStatus;
    private int pkId;
    private boolean changed;

    private String customer;
    private double totalPrice;
    private double discount;
    private Voucher voucher;

    public Order() {
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

    public String getSignature() {
        return signature;
    }

    public int getPkId() {
        return pkId;
    }

    public boolean isChanged() {
        return changed;
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

    public Voucher getVoucher() {
        return voucher;
    }

    public String getHash() {
        return hash;
    }

    public boolean isSignStatus() {
        return signStatus;
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

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setSignStatus(boolean signStatus) {
        this.signStatus = signStatus;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
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

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
}
