package org.example.projectweb.dao;

import org.example.projectweb.model.Support;
import org.example.projectweb.model.SupportMessage;

import java.util.List;

public class SupportDao extends BaseDao {

    public void createSupport(int userId, String topic, String title, String message) {
        get().useHandle(h -> {
            int spid = h.createUpdate("""
                            insert into support (uid, topic, title, created_date, status)
                            values (:uid, :topic, :title, NOW(), "processing")
                            """).bind("uid", userId).bind("topic", topic).bind("title", title)
                    .executeAndReturnGeneratedKeys("spid").mapTo(Integer.class).one();
            createMessage(spid, userId, message);
        });

    }

    public void createMessage(int spid, int senderId, String message) {
        get().useHandle(h -> h.createUpdate("""
                        insert into support_message (spid, sender_id, message, created_date)
                        values (:spid, :sender_id, :message, NOW())
                        """).bind("spid", spid).bind("sender_id", senderId).bind("message", message)
                .execute());
    }

    public List<Support> getSupportsByUid(int uid) {
        return get().withHandle(h -> h.createQuery("""
                        select spid, topic, title, created_date as createdDate, status
                        from support where uid = :uid
                        """).bind("uid", uid)
                .mapToBean(Support.class).list());
    }

    public Support getSupportBySpid(int spid) {
        return get().withHandle(h -> h.createQuery("""
                        select spid, uid, topic, title, status
                        from support where spid = :spid
                        """).bind("spid", spid)
                .mapToBean(Support.class).one());

    }

    public List<SupportMessage> getMessagesBySpid(int spid) {
        return get().withHandle(h -> {
            return h.createQuery("""
                            select sender_id, message, created_date
                            from support_message where spid = :spid
                            """).bind("spid", spid)
                    .mapToBean(SupportMessage.class).list();
        });
    }
}
