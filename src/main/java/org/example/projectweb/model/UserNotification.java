package org.example.projectweb.model;

import java.io.Serializable;

public class UserNotification implements Serializable {
    private int unid;
    private int nid;
    private int uid;
    private boolean isRead;
    private String receivedDate;

    public UserNotification() {
    }

    public UserNotification(int unid, int nid, int uid, boolean isRead, String receivedDate) {
        this.unid = unid;
        this.nid = nid;
        this.uid = uid;
        this.isRead = isRead;
        this.receivedDate = receivedDate;
    }

    // ===== Getter / Setter =====

    public int getUnid() {
        return unid;
    }

    public void setUnid(int unid) {
        this.unid = unid;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }
}