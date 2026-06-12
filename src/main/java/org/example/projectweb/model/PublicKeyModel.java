package org.example.projectweb.model;

import java.io.Serializable;

public class PublicKeyModel implements Serializable {
    private int pkId;
    private int uid;
    private String publicKeyStr;

    public PublicKeyModel() {
    }

    public PublicKeyModel(int pk_id, int uid, String public_key_str) {
        this.pkId = pk_id;
        this.uid = uid;
        this.publicKeyStr = public_key_str;
    }

    public int getPkId() {
        return pkId;
    }

    public int getUid() {
        return uid;
    }

    public String getPublicKeyStr() {
        return publicKeyStr;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setPublicKeyStr(String publicKeyStr) {
        this.publicKeyStr = publicKeyStr;
    }
}
