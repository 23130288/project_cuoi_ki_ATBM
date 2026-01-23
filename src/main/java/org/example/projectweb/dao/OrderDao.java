package org.example.projectweb.dao;

import org.example.projectweb.cart.Cart;
import org.example.projectweb.cart.CartItem;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetailView;
import org.example.projectweb.model.Voucher;

import java.util.List;

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

    public int createOrder(int uid, Integer vid, String description) {
        return get().withHandle(h -> h.createUpdate("""
                        insert into `order` (uid, vid, description, created_date, status)
                        values (:uid, :vid, :description, now(), 'delivering')
                        """).bind("uid", uid).bind("vid", vid).bind("description", description)
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

    public Order getOrderByOid(int oid) {
        return get().withHandle(h -> h.createQuery("""
                        select oid, uid, vid, description, created_date as createdDate, status
                        from `order` where oid = :oid
                        """).bind("oid", oid)
                .mapToBean(Order.class).one());
    }

    public List<OrderDetailView> getOrderDetailViewByOid(int oid) {
        return get().withHandle(h -> h.createQuery("""
                select od.pvid, p.name, ip.image, pv.color, pv.size, pv.price, od.quantity
                from `order` o join order_detail od on o.oid = od.oid
                join product_variant pv on pv.pvid = od.pvid
                join product p on p.pid = pv.pid
                join image_product ip on ip.pid = p.pid and ip.is_main = 1
                where od.oid = :oid
                """).bind("oid", oid)
                .mapToBean(OrderDetailView.class).list());
    }

    public double getTotalPrice(int oid) {
        return  get().withHandle(h -> {
            return h.createQuery("""
                    select sum(pv.price * od.quantity)
                    from order_detail od join product_variant pv on pv.pvid = od.pvid
                    where od.oid = :oid
                    """).bind("oid", oid)
                    .mapTo(Double.class).one();
        });
    }

    public double getDiscount(int oid) {
        return  get().withHandle(h -> {
            return h.createQuery("""
                    select coalesce(v.discount, 0)
                    from `order` o left join voucher v on o.vid = v.vid
                    where o.oid = :oid
                    """).bind("oid", oid)
                    .mapTo(Double.class).one();
        });
    }
}
