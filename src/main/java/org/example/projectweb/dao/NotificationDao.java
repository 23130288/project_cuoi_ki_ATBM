package org.example.projectweb.dao;

import org.example.projectweb.model.Notification;
import java.util.List;

public class NotificationDao extends BaseDao {

    public Notification getNotificationById(int nid) {
        return get().withHandle(h ->
                h.createQuery("select nid, type, title, content, created_date, status from notification where nid = :nid")
                        .bind("nid", nid).mapToBean(Notification.class).findOne().orElse(null)
        );
    }

    public void updateStatus(int nid) {
        get().useHandle(h ->
                h.createUpdate("UPDATE notification SET status = IF(status = 1, 0, 1) WHERE nid = :nid")
                        .bind("nid", nid).execute()
        );
    }

    public List<Notification> getListNotificatio() {
        return get().withHandle(h -> h.createQuery("select nid, type, title, content, created_date, status from notification")
                .mapToBean(Notification.class).list());
    }

    public List<Notification> getListNotificatio(String title) {
        return get().withHandle(h -> h.createQuery("select nid, type, title, content, created_date, status from notification WHERE LOWER(title) LIKE LOWER(:title)")
                .bind("title", "%" + title + "%").mapToBean(Notification.class).list());
    }

    public int insertNotification(String type, String title, String content, String createdDate) {
        return get().withHandle(handle -> handle.createUpdate("INSERT INTO notification (type, title, content, created_date, status) VALUES (:type, :title, :content, :createdDate, true)")
                .bind("type", type).bind("title", title).bind("content", content).bind("createdDate", createdDate).executeAndReturnGeneratedKeys("nid").mapTo(int.class).one()
        );
    }
}