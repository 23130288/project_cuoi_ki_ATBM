package org.example.projectweb.service;

import org.example.projectweb.dao.PublicKeyDao;
import org.example.projectweb.model.PublicKeyModel;

public class PublicKeyService {
    private PublicKeyDao pkDao = new PublicKeyDao();

    public PublicKeyModel getPublicKeyByUid(int uid) {
        return pkDao.getPublicKeyByUid(uid);
    }

    public PublicKeyModel getPublicKeyByPkId(int pkId) {
        return pkDao.getPublicKeyByPkId(pkId);
    }
}
