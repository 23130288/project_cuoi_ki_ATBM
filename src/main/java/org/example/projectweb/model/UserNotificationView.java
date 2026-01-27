package org.example.projectweb.model;

public class UserNotificationView {
    private int nid;
    private int uid;
    private String title;
    private String content;
    private boolean isRead;
    private String receivedDate;

    public UserNotificationView() {
    }

    public UserNotificationView(int nid, int uid, String title, String content, boolean isRead, String receivedDate) {
        this.nid = nid;
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.isRead = isRead;
        this.receivedDate = receivedDate;
    }

    /**
     * GETTERS
     */
    public int getNid() {
        return nid;
    }

    public int getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    /**
     * SETTERS
     */
    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }
}
