package org.example.projectweb.dao;

import org.example.projectweb.model.Voucher;
import org.example.projectweb.model.VoucherUser;

import java.util.List;

public class VoucherDao extends BaseDao {

    public void addVoucher(String name, double discount, double condition, String expiredDate, String image, boolean status) {
        get().useHandle(h -> h.createUpdate("INSERT INTO voucher (name, discount, `condition`, expired_date, image, status) VALUES (:name, :discount, :condition, :expiredDate, :image, :status)")
                .bind("name", name).bind("discount", discount).bind("condition", condition).bind("expiredDate", java.sql.Date.valueOf(expiredDate)).bind("image", image).bind("status", status).execute()
        );
    }

    public List<Voucher> getListVoucher() {
        return get().withHandle(h -> h.createQuery("select vid, name, discount, `condition`, expired_date, status from voucher")
                .mapToBean(Voucher.class).list());
    }

    public Voucher getVoucherById(int voucherId) {
        return get().withHandle(h ->
                h.createQuery("select vid, name, discount, `condition`, expired_date, status from voucher where vid = :voucherId")
                        .bind("voucherId", voucherId).mapToBean(Voucher.class).findOne().orElse(null)
        );
    }

    public List<VoucherUser> getVoucherUsersByUid(int uid) {
        return get().withHandle(h -> h.createQuery("""
                select min(vu.uvid) as uvid, v.vid, v.name, v.discount, v.`condition`
                from voucher v join voucher_user vu on v.vid = vu.vid
                where vu.uid = :uid and v.status = 1 and vu.applicable = 1
                group by v.vid, v.name, v.discount, v.condition
                """).bind("uid", uid).mapToBean(VoucherUser.class).list());
    }

    public VoucherUser getVoucherUserByUvid(int uvid) {
        return get().withHandle(h -> h.createQuery("""
                select vu.uvid, v.vid, v.name, v.discount, v.`condition`, vu.applicable
                from voucher v join voucher_user vu on v.vid = vu.vid
                where vu.uvid = :uvid and v.status = 1 and vu.applicable = 1
                """).bind("uvid", uvid).mapToBean(VoucherUser.class).one());
    }

    public void deleteUserVoucherByUvid(int uvid) {
        get().useHandle(h -> h.createUpdate("""
                delete from voucher_user
                where uvid = :uvid
                """).bind("uvid", uvid).execute());
    }

    public void updateStatus(int vid) {
        get().useHandle(h ->
                h.createUpdate("UPDATE voucher SET status = IF(status = 1, 0, 1) WHERE vid = :vid")
                        .bind("vid", vid)
                        .execute()
        );
    }

    public void setApplicable(int uvid, int bool) {
        get().useHandle(h -> {
            h.createUpdate("update voucher_user set applicable = :bool where uvid = :uvid")
                    .bind("bool", bool).bind("uvid", uvid)
                    .execute();

        });
    }
    public List<Voucher> getLoadVouchers() {
        return get().withHandle(h ->
                h.createQuery(" SELECT vid, name, discount, `condition`, expired_date, image, status FROM voucher WHERE status = 1 AND expired_date >= NOW() ")
                        .mapToBean(Voucher.class)
                        .list()
        );
    }

    public boolean isVoucherReceived(int uid, int vid) {
        String sql = " SELECT uvid FROM voucher_user WHERE uid = :uid AND vid = :vid ";

    return get().withHandle(h ->
            h.createQuery(sql)
                    .bind("uid", uid)
                    .bind("vid", vid)
                    .mapTo(Integer.class)
                    .findOne()
                    .isPresent()
    );
}


    public void receiveVoucher(int uid, int vid) {
        String sql = " INSERT INTO voucher_user(uid, vid, applicable) VALUES (:uid, :vid, 1) ";

        get().useHandle(h ->
                h.createUpdate(sql)
                        .bind("uid", uid)
                        .bind("vid", vid)
                        .execute()
        );
    }

}
