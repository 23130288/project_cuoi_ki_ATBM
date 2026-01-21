package org.example.projectweb.dao;

import org.example.projectweb.model.Voucher;

import java.util.List;

public class VoucherDao extends BaseDao {

    public List<Voucher> getListVoucher() {
        return get().withHandle(h -> h.createQuery("select vid, name, discount, condition, expired_date, status from voucher")
                .mapToBean(Voucher.class).list());
    }

    public List<Voucher> getVouchersByUid(int uid) {
        return get().withHandle(h -> h.createQuery("""
                select v.name, v.discount, `condition`, v.image
                from voucher v join voucher_user vu on v.vid = vu.vid
                where vu.uid = :uid and v.status = 1
                group by v.vid
                """).bind("uid", uid).mapToBean(Voucher.class).list());
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