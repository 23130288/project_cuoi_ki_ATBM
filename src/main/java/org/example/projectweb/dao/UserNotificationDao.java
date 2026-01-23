package org.example.projectweb.dao;

public class UserNotificationDao extends BaseDao {

    public void insertUserNotification(int nid, int uid, boolean isRead, String receivedDate) {
        get().useHandle(handle -> handle.createUpdate("INSERT INTO user_notification (nid, uid, is_read, received_date) VALUES (:nid, :uid, :isRead, :receivedDate)")
                .bind("nid", nid).bind("uid", uid).bind("isRead", isRead).bind("receivedDate", receivedDate).execute()
        );
    }
}