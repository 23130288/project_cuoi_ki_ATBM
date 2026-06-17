package org.example.projectweb.dao;

import org.example.projectweb.model.Key;

public class KeyDao extends BaseDao {
    public void addPublicKey(int uid, String publicKeyStr) {
        get().useHandle(h ->
                h.createUpdate("""
                                INSERT INTO public_key(uid, public_key_str, status)
                                VALUES (:uid, :publicKeyStr, 1)
                                """)
                        .bind("uid", uid).bind("publicKeyStr", publicKeyStr).execute()
        );
    }

    public Key findUsedKey(int uid) {
        return get().withHandle(h ->
                h.createQuery("""
                                    select pk_id, uid, public_key_str, status
                                    from public_key
                                    where uid = :uid and status = 1
                                """)
                        .bind("uid", uid).mapToBean(Key.class).findOne().orElse(null)
        );
    }

    public void updateKeyStatus(int pk_id) {
        get().useHandle(h ->
                h.createUpdate("""
                                UPDATE public_key
                                SET status = 0
                                WHERE pk_id = :pk_id
                                """)
                        .bind("pk_id", pk_id)
                        .execute()
        );
    }
}



