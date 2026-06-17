package org.example.projectweb.service;

import org.example.projectweb.dao.KeyDao;
import org.example.projectweb.model.Key;

public class KeyService {
    final KeyDao kDao = new KeyDao();

    private void disableKey(int uid) {
        Key key = kDao.findUsedKey(uid);
        int pk_id = key.getPkId();
        kDao.updateKeyStatus(pk_id);
    }

    private void saveKey(int uid, String publicKeyStr) {
        kDao.addPublicKey(uid, publicKeyStr);
    }
    public Key getKey(int uid) {
        return  kDao.findUsedKey(uid);
    }
}
