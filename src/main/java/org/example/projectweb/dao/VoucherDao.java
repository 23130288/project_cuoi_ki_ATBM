package org.example.projectweb.dao;

import org.example.projectweb.model.Voucher;

import java.util.List;

public class VoucherDao extends BaseDao {

    public List<Voucher> getListVoucher() {
        return get().withHandle(h -> h.createQuery("select vid, name, discount, condition, expired_date, status from voucher")
                .mapToBean(Voucher.class).list());
    }

//    public void updateStatus(int uid) {
//        get().useHandle(h ->
//                h.createUpdate(
//                                "UPDATE user SET status = IF(status = 1, 0, 1) WHERE uid = :uid"
//                        )
//                        .bind("uid", uid)
//                        .execute()
//        );
//    }
}