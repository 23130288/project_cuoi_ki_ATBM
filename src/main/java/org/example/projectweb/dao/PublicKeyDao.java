package org.example.projectweb.dao;

import org.example.projectweb.model.PublicKeyModel;

public class PublicKeyDao extends BaseDao {
    public PublicKeyModel getPublicKeyByUid(int uid) {
        return get().withHandle(h ->
                h.createQuery("select pk_id, uid, public_key_str from public_key where uid = :uid and status = 1")
                        .bind("uid", uid).mapToBean(PublicKeyModel.class).findOne().orElse(null)
        );
    }

    public PublicKeyModel getPublicKeyByPkId(int pkId) {
        return get().withHandle(h ->
                h.createQuery("select pk_id, uid, public_key_str from public_key where pk_id = :pkId")
                        .bind("pkId", pkId).mapToBean(PublicKeyModel.class).findOne().orElse(null)
        );
    }

    public void disableKeyByUid(int uid) {
        get().useHandle(h -> h.createUpdate("""
                update public_key set status = 0 WHERE uid = :uid and status = 1
                """).bind("uid", uid).execute());
    }

    public void uploadKey(int uid, String key) {
        get().useHandle(h -> h.createUpdate("""
                insert into public_key (uid, public_key_str, status)
                values (:uid, :key, 1)
                """).bind("uid", uid).bind("key", key).execute());
    }
}
