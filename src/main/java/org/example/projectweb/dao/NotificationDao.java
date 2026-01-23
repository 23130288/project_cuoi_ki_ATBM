package org.example.projectweb.dao;

public class NotificationDao extends BaseDao {

    public int insertNotification(String type, String title, String content, String createdDate) {
        return get().withHandle(handle -> handle.createUpdate("INSERT INTO notification (type, title, content, created_date) VALUES (:type, :title, :content, :createdDate)")
                .bind("type", type).bind("title", title).bind("content", content).bind("createdDate", createdDate).executeAndReturnGeneratedKeys("nid").mapTo(int.class).one()
        );
    }
}