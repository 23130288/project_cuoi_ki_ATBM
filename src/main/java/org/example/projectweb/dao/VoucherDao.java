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
                select min(vu.uvid) as uvid, v.vid, v.name, v.discount, v.`condition`, v.expired_date as expiredDate, v.image
                from voucher v join voucher_user vu on v.vid = vu.vid
                where vu.uid = :uid and v.status = 1 and vu.applicable = 1
                group by v.vid, v.name, v.discount, v.condition, v.expired_date, v.image
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
        get().useHandle(h -> h.createUpdate("update voucher_user set applicable = :bool where uvid = :uvid")
                .bind("bool", bool).bind("uvid", uvid)
                .execute());
    }

    public Voucher getVoucherByOid(int oid) {
        return get().withHandle(h -> h.createQuery("""
                        select v.name, v.discount
                        from `order` o
                        left join voucher_user vu on o.uvid = vu.uvid
                        left join voucher v on vu.vid = v.vid
                        where o.oid = :oid
                        """).bind("oid", oid)
                .mapToBean(Voucher.class).one());
    }

    public List<VoucherUser> getVoucherUsersByUidAndName(int uid, String name) {
        return get().withHandle(h -> h.createQuery("""
                select v.name, v.discount, v.condition, v.expired_date as expiredDate, v.image
                from voucher v join voucher_user vu on v.vid = vu.vid
                where vu.uid = :uid and v.name = :name and vu.applicable = 1
                """).bind("uid", uid).bind("name", name)
                .mapToBean(VoucherUser.class).list());
    }
}