package org.example.projectweb.service;

import org.example.projectweb.dao.PublicKeyDao;
import org.example.projectweb.dao.UserDao;
import org.example.projectweb.model.PublicKeyModel;

public class PublicKeyService {
    private PublicKeyDao pkDao = new PublicKeyDao();
    final UserDao userDao = new UserDao();

    public PublicKeyModel getPublicKeyByUid(int uid) {
        return pkDao.getPublicKeyByUid(uid);
    }

    public PublicKeyModel getPublicKeyByPkId(int pkId) {
        return pkDao.getPublicKeyByPkId(pkId);
    }

    public void disableKeyByUid(int uid) {
        pkDao.disableKeyByUid(uid);
    }

    public void uploadKey(int uid, String key) {
        pkDao.uploadKey(uid, key);
    }

    public void canUploadKey(int uid) { userDao.changeCanUpKey(uid, 1);}
}
