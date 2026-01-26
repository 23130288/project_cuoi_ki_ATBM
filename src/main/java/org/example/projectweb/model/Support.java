package org.example.projectweb.model;

import java.io.Serializable;

public class Support implements Serializable {
    private int spid;
    private int uid;
    private String topic;
    private String title;
    private String createdDate;
    private int adminUid;
    private String status;

    private String message;
    public Support() {
    }

    public Support(int spid, int uid, String topic, String title, String createdDate, int adminUid, String status) {
        this.spid = spid;
        this.uid = uid;
        this.topic = topic;
        this.title = title;
        this.createdDate = createdDate;
        this.adminUid = adminUid;
        this.status = status;
    }

    /**
     * GETTERS
     */
    public int getSpid() {
        return spid;
    }

    public int getUid() {
        return uid;
    }

    public String getTopic() {
        return topic;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getAdminUid() {
        return adminUid;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
    /**
     * SETTERS
     */
    public void setSpid(int spid) {
        this.spid = spid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setAdminUid(int adminUid) {
        this.adminUid = adminUid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
