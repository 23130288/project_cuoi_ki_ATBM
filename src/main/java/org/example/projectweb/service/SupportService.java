package org.example.projectweb.service;

import org.example.projectweb.dao.SupportDao;
import org.example.projectweb.model.Support;
import org.example.projectweb.model.SupportMessage;

import java.util.List;

public class SupportService {
    final SupportDao spDao = new SupportDao();
    final NotificationService ns = new NotificationService();

    public void createSupport(int userId, String topic, String title, String message) {
        spDao.createSupport(userId, topic, title, message);
    }

    public List<Support> getSupports(String filter) {
        if ("all".equalsIgnoreCase(filter))
            return spDao.getSupports();
        else return spDao.getSupportsprocessing();
    }

    public List<Support> getSupportsKey(String filter) {
        if ("all".equalsIgnoreCase(filter))
            return spDao.getSupportskey();
        else return spDao.getSupportspkeyrocessing();
    }

    public List<Support> getSupportsByUid(int uid) {
        return spDao.getSupportsByUid(uid);
    }

    public Support getSupportBySpid(int spid) {
        return spDao.getSupportBySpid(spid);
    }

    public List<SupportMessage> getMessagesBySpid(int spid) {
        return spDao.getMessagesBySpid(spid);
    }

    public void createMessage(int spid, int uid, String message) {
        spDao.createMessage(spid, uid, message);
    }

    public void createreply(int spid, int aid, String uid, String message) {
        // Tạo thông báo
        ns.createGlobalNotification(uid, "Admin đã trả lời bạn", message);
        spDao.createMessage(spid, aid, message);
        spDao.SupportDone(spid);
    }

}
