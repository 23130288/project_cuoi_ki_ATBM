package org.example.projectweb.dao;

import org.example.projectweb.cart.Cart;
import org.example.projectweb.cart.CartItem;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetailView;

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

    public int createOrder(int uid, Integer uvid, String description) {
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

    public Order getOrderByOid(int oid) {
        return get().withHandle(h -> h.createQuery("""
                        select oid, uid, uvid, description, created_date as createdDate, status
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
        return get().withHandle(h -> h.createQuery("""
                        select sum(pv.price * od.quantity)
                        from order_detail od join product_variant pv on pv.pvid = od.pvid
                        where od.oid = :oid
                        """).bind("oid", oid)
                .mapTo(Double.class).one());
    }

    public double getDiscount(int oid) {
        return get().withHandle(h -> h.createQuery("""
                        select coalesce(v.discount, 0)
                        from `order` o
                        left join voucher_user vu on o.uvid = vu.uvid
                        left join voucher v on vu.vid = v.vid
                        where o.oid = :oid
                        """).bind("oid", oid)
                .mapTo(Double.class).one());
    }

    public List<Order> getOrdersByUid(int uid) {
        return get().withHandle(h -> h.createQuery("""
                        select o.oid, o.uvid, o.created_date as createdDate, o.status,
                        sum(od.quantity * pv.price) -
                        COALESCE(
                            case
                                when v.discount > 0 and v.discount < 1
                                    then sum(od.quantity * pv.price) * v.discount
                                else 0
                            end
                        , 0)
                        as finalPrice
                        
                        from `order` o
                        join order_detail od on o.oid = od.oid
                        join product_variant pv on od.pvid = pv.pvid
                        left join voucher_user vu on o.uvid = vu.uvid
                        left join voucher v on vu.vid = v.vid
                        where o.uid = :uid
                        group by o.oid, o.uvid, o.created_date, o.status
                        """).bind("uid", uid)
                .mapToBean(Order.class).list());
    }

    public List<Order> getOrdersByUidAndStatus(int uid, String status) {
        return get().withHandle(h -> h.createQuery("""
                        select o.oid, o.uvid, o.created_date as createdDate, o.status,
                        sum(od.quantity * pv.price) -
                        COALESCE(
                            case
                                when v.discount > 0 and v.discount < 1
                                    then sum(od.quantity * pv.price) * v.discount
                                else 0
                            end
                        , 0)
                        as finalPrice
                        
                        from `order` o
                        join order_detail od on o.oid = od.oid
                        join product_variant pv on od.pvid = pv.pvid
                        left join voucher_user vu on o.uvid = vu.uvid
                        left join voucher v on vu.vid = v.vid
                        where o.uid = :uid and o.status = :status
                        group by o.oid, o.uvid, o.created_date, o.status
                        """).bind("uid", uid).bind("status", status)
                .mapToBean(Order.class).list());
    }
}
