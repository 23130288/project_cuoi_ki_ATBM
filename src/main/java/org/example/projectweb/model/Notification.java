package org.example.projectweb.model;

import java.io.Serializable;

public class Notification implements Serializable {
    private int nid;
    private String type;
    private String title;
    private String content;
    private String createdDate;

    public Notification() {
    }

    public Notification(int nid, String type, String title, String content, String createdDate) {
        this.nid = nid;
        this.type = type;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    // ===== Getter / Setter =====

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}