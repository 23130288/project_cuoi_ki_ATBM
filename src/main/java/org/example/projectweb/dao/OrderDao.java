package org.example.projectweb.dao;

import org.example.projectweb.cart.Cart;
import org.example.projectweb.cart.CartItem;

public class OrderDao extends BaseDao {

    public boolean hasPurchased(int userId, int productId) {
        return get().withHandle(h -> h.createQuery("""
                        select exists (select 1
                            from `order` o join order_detail od on o.oid = od.oid
                            join product_variant pv on od.pvid = pv.pvid
                            where o.uid = :uid and pv.pid = :pid)
                        """)
                .bind("uid", userId).bind("pid", productId)
                .mapTo(Boolean.class).first());
    }

    public int createOrder(int uid, Integer  uvid, String description) {
        return get().withHandle(h -> h.createUpdate("""
                        insert into `order` (uid, uvid, description, created_date, status)
                        values (:uid, :uvid, :description, now(), 'delivering')
                        """).bind("uid", uid).bind("uvid", uvid).bind("description", description)
                .executeAndReturnGeneratedKeys("oid")
                .mapTo(Integer.class).one());
    }

    public void createOrderDetails(int oid, Cart c) {
        get().useHandle(h -> {
            for (CartItem item : c.getList()) {
                h.createUpdate("""
                                insert into order_detail (oid, pvid, quantity)
                                values (:oid, :pvid, :quantity)
                                """).bind("oid", oid)
                        .bind("pvid", item.getProductVariant().getPvid()).bind("quantity", item.getQuantity())
                        .execute();
            }
        });
    }
}
