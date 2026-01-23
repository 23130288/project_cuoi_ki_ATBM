package org.example.projectweb.model;

import java.io.Serializable;

public class Service_Policy implements Serializable {
    private int spid;
    private String title;
    private String comment;
    private String lasted_update;
    private boolean isService;
    private boolean status;

    public Service_Policy() {
    }

    public Service_Policy(int spid, String title, String comment, String lasted_update, boolean isService, boolean status) {
        this.spid = spid;
        this.title = title;
        this.comment = comment;
        this.lasted_update = lasted_update;
        this.isService = isService;
        this.status = status;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLastedUpdate() {
        return lasted_update;
    }

    public void setLastedUpdate(String lasted_update) {
        this.lasted_update = lasted_update;
    }

    public boolean isService() {
        return isService;
    }

    public void setService(boolean service) {
        isService = service;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
