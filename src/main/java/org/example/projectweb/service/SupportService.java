package org.example.projectweb.service;

import org.example.projectweb.dao.SupportDao;
import org.example.projectweb.model.Support;
import org.example.projectweb.model.SupportMessage;

import java.util.List;

public class SupportService {
    private SupportDao spDao = new SupportDao();

    public void createSupport(int userId, String topic, String title, String message) {
        spDao.createSupport(userId, topic, title, message);
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
}
