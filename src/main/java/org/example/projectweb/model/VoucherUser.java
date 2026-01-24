package org.example.projectweb.model;

import java.io.Serializable;

public class VoucherUser extends Voucher implements Serializable {
    private int uvid;
    private boolean applicable;

    public VoucherUser() {
    }

    public VoucherUser(int vid, String name, double discount, double condition, String expiredDate, String image, boolean status, int uvid, boolean applicable) {
        super(vid, name, discount, condition, expiredDate, image, status);
        this.uvid = uvid;
        this.applicable = applicable;
    }

    /**
     * GETTERS
     */
    public int getUvid() {
        return uvid;
    }

    public boolean isApplicable() {
        return applicable;
    }

    /**
     * SETTERS
     */
    public void setUvid(int uvid) {
        this.uvid = uvid;
    }

    public void setApplicable(boolean applicable) {
        this.applicable = applicable;
    }
}
