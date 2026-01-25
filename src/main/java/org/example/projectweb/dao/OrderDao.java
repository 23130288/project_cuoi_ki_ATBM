package org.example.projectweb.dao;

import org.example.projectweb.cart.Cart;
import org.example.projectweb.cart.CartItem;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetail;
import org.example.projectweb.model.OrderDetailView;

import java.util.List;

public class OrderDao extends BaseDao {

    public List<Order> getListOrderAdmin() {
        return get().withHandle(h -> h.createQuery("SELECT o.oid,u.name AS customer,SUM(od.quantity*pv.price) AS totalPrice,o.created_date AS createdDate,o.status " +
                        "FROM `order` o JOIN user u ON o.uid=u.uid " +
                        "JOIN order_detail od ON o.oid=od.oid JOIN product_variant pv ON od.pvid=pv.pvid " +
                        "GROUP BY o.oid,u.name,o.created_date,o.status ORDER BY o.created_date DESC")
                .mapToBean(Order.class).list());
    }

    public List<Order> getListOrderByCostomerAdmin(String name) {
        return get().withHandle(h -> h.createQuery("SELECT o.oid,u.name AS customer,SUM(od.quantity*pv.price) AS totalPrice,o.created_date AS createdDate,o.status " +
                        "FROM `order` o JOIN user u ON o.uid=u.uid " +
                        "JOIN order_detail od ON o.oid=od.oid JOIN product_variant pv ON od.pvid=pv.pvid " +
                        "Where LOWER(u.name) LIKE LOWER(:name) " +
                        "GROUP BY o.oid,u.name,o.created_date,o.status ORDER BY o.created_date DESC")
                .bind("name", "%" + name + "%")
                .mapToBean(Order.class).list());
    }

    public List<OrderDetail> getOrderDetailsByOid(int oid) {
        return get().withHandle(h -> h.createQuery("SELECT p.name AS productName,od.quantity,pv.price AS unitPrice " +
                        "FROM order_detail od JOIN product_variant pv ON od.pvid = pv.pvid JOIN product p ON pv.pid = p.pid " +
                        "WHERE od.oid = :oid")
                .bind("oid", oid)
                .mapToBean(OrderDetail.class).list());
    }

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
        return get().withHandle(h -> {
            return h.createQuery("""
                            select sum(pv.price * od.quantity)
                            from order_detail od join product_variant pv on pv.pvid = od.pvid
                            where od.oid = :oid
                            """).bind("oid", oid)
                    .mapTo(Double.class).one();
        });
    }

    public double getDiscount(int oid) {
        return get().withHandle(h -> {
            return h.createQuery("""
                            select coalesce(v.discount, 0)
                            from `order` o
                            left join voucher_user vu on o.uvid = vu.uvid
                            left join voucher v on vu.vid = v.vid
                            where o.oid = :oid
                            """).bind("oid", oid)
                    .mapTo(Double.class).one();
        });
    }
}
