package org.example.projectweb.model;

public class SupportMessage {
    private int spmid;
    private int spid;
    private int sender_id;
    private String message;
    private String createdDate;

    public SupportMessage() {
    }

    public SupportMessage(int spmid, int spid, int sender_id, String message, String createdDate) {
        this.spmid = spmid;
        this.spid = spid;
        this.sender_id = sender_id;
        this.message = message;
        this.createdDate = createdDate;
    }

    /**
     * GETTERS
     */
    public int getSpmid() {
        return spmid;
    }

    public int getSpid() {
        return spid;
    }

    public int getSender_id() {
        return sender_id;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * SETTERS
     */
    public void setSpmid(int spmid) {
        this.spmid = spmid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
