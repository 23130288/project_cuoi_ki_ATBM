package org.example.projectweb.model;

import java.io.Serializable;

public class ProductVariant implements Serializable {
    private int pvid;
    private int pid;
    private String productName;
    private String size;
    private String color;
    private double price;
    private int quantity;

    public ProductVariant() {}
    public ProductVariant(int pvid, int pid, String size, String color, double price, int quantity) {
        this.pvid = pvid;
        this.pid = pid;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }

    public int getPvid() {
        return pvid;
    }

    public int getPid() {
        return this.pid;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPvid(int pvid) {
        this.pvid = pvid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
