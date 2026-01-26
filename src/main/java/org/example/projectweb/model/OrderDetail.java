package org.example.projectweb.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private int odid;
    private int oid;
    private int pvid;
    private int quantity;

    private String productName;
    private double unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(int odid, int oid, int pvid, int quantity) {
        this.odid = odid;
        this.oid = oid;
        this.pvid = pvid;
        this.quantity = quantity;
    }

    public int getOdid() {
        return odid;
    }

    public int getOid() {
        return oid;
    }

    public int getPvid() {
        return pvid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setOdid(int odid) {
        this.odid = odid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setPvid(int pvid) {
        this.pvid = pvid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
