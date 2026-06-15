package org.example.projectweb.model;

import java.io.Serializable;

public class Voucher implements Serializable {
    private int vid;
    private String name;
    private double discount;
    private double condition;
    private String expiredDate;
    private String image;
    private boolean status;

    public Voucher() {
    }

    public Voucher(int vid, String name, double discount, double condition, String expiredDate, String image, boolean status) {
        this.vid = vid;
        this.name = name;
        this.discount = discount;
        this.condition = condition;
        this.expiredDate = expiredDate;
        this.image = image;
        this.status = status;
    }

    // ===== getter / setter =====
    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCondition() {
        return condition;
    }

    public void setCondition(double condition) {
        this.condition = condition;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(name).append("\n").append("\tDiscount: ").append(discount);
        if (discount > 0 && discount < 1)
            res.append("đ");
        else res.append("%");
        res.append("\t").append("Condition: ").append(condition).append("\n");
        res.append("\t").append("Expired date: ").append(expiredDate);
        return res.toString();
    }
}
