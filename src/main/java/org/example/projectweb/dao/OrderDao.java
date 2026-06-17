package org.example.projectweb.dao;

import org.example.projectweb.cart.Cart;
import org.example.projectweb.cart.CartItem;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetail;
import org.example.projectweb.model.OrderDetailView;

import java.util.List;

public class OrderDao extends BaseDao {

    public List<Order> getListOrderAdmin() {
        return get().withHandle(h -> h.createQuery("""
                        SELECT o.oid, u.name AS customer,SUM(od.quantity * pv.price) AS totalPrice,o.created_date AS createdDate,o.status,
                            os.hash,os.signature,os.sign_status AS signStatus,os.expired_date AS expiredDate, os.pk_id AS pkId
                        FROM `order` o
                        JOIN user u ON o.uid = u.uid
                        JOIN order_detail od ON o.oid = od.oid
                        JOIN product_variant pv ON od.pvid = pv.pvid
                        LEFT JOIN order_signature os ON o.oid = os.oid
                        GROUP BY o.oid,u.name,o.created_date, o.status,os.hash,os.signature,os.sign_status,os.expired_date,os.pk_id
                        ORDER BY o.created_date DESC
                        """)
                .mapToBean(Order.class)
                .list());
    }

    public int countAllOrders() {
        return get().withHandle(h ->
                h.createQuery("SELECT COUNT(oid) FROM `order`")
                        .mapTo(Integer.class).one()
        );
    }

    public int countCompletedOrders() {
        return get().withHandle(h ->
                h.createQuery("SELECT COUNT(oid) FROM `order` WHERE status = 'delivered'")
                        .mapTo(Integer.class).one()
        );
    }

    public double getMonthlyRevenue(int month, int year) {
        return get().withHandle(h ->
                h.createQuery("""
                                    SELECT COALESCE(SUM(final_price), 0) AS revenue
                                                                          FROM (
                                                                              SELECT
                                                                                  o.oid,
                                                                                  SUM(od.quantity * pv.price)
                                                                                  -
                                                                                  COALESCE(
                                                                                      CASE
                                                                                          WHEN v.name = 'phan_tram'
                                                                                              THEN SUM(od.quantity * pv.price) * v.discount
                                                                                          WHEN v.name = 'giam_gia'
                                                                                              THEN v.discount
                                                                                          ELSE 0
                                                                                      END
                                                                                  , 0) AS final_price
                                                                              FROM `order` o
                                                                              JOIN order_detail od ON o.oid = od.oid
                                                                              JOIN product_variant pv ON od.pvid = pv.pvid
                                                                              LEFT JOIN voucher_user vu ON o.uvid = vu.uvid
                                                                              LEFT JOIN voucher v ON vu.vid = v.vid
                                                                              WHERE MONTH(o.created_date) = :month
                                                                                AND YEAR(o.created_date) = :year
                                                                              GROUP BY o.oid
                                                                          ) t;
                                
                                """)
                        .bind("month", month)
                        .bind("year", year)
                        .mapTo(Double.class)
                        .list()
                        .stream().mapToDouble(Double::doubleValue).sum()
        );
    }

    public int getTotalProductsSold() {
        return get().withHandle(h ->
                h.createQuery("SELECT COALESCE(SUM(quantity), 0) FROM order_detail")
                        .mapTo(Integer.class).one()
        );
    }

    public List<Order> getListOrderByCostomerAdmin(String name) {
        return get().withHandle(h -> h.createQuery("""
                        SELECT o.oid, u.name AS customer,SUM(od.quantity * pv.price) AS totalPrice, o.created_date AS createdDate,
                            o.status, os.hash, os.signature,os.sign_status AS signStatus, os.expired_date AS expiredDate,os.pk_id AS pkId
                        FROM `order` o
                        JOIN user u ON o.uid = u.uid
                        JOIN order_detail od ON o.oid = od.oid
                        JOIN product_variant pv ON od.pvid = pv.pvid
                        LEFT JOIN order_signature os ON o.oid = os.oid
                        WHERE LOWER(u.name) LIKE LOWER(:name)
                        GROUP BY o.oid,u.name,o.created_date,o.status,os.hash,os.signature,os.sign_status,os.expired_date,os.pk_id
                        ORDER BY o.created_date DESC
                        """)
                .bind("name", "%" + name + "%")
                .mapToBean(Order.class)
                .list());
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

    public int createOrder(int uid, Integer uvid, String description, double finalPrice) {
        return get().withHandle(h -> h.createUpdate("""
                        insert into `order` (uid, uvid, description, created_date, final_price, status)
                        values (:uid, :uvid, :description, now(), :finalPrice, 'waiting')
                        """).bind("uid", uid).bind("uvid", uvid).bind("description", description).bind("finalPrice", finalPrice)
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
                        select o.oid, o.uid, o.uvid, o.description, o.created_date as createdDate, o.final_price as finalPrice, o.status,
                                os.hash, os.signature, os.sign_status as signStatus, os.expired_date as expiredDate, os.pk_id as pkId
                        from `order` o join order_signature os on o.oid = os.oid
                        where o.oid = :oid
                        """).bind("oid", oid)
                .mapToBean(Order.class).one());
    }

    public List<OrderDetailView> getOrderDetailViewByOid(int oid) {
        return get().withHandle(h -> h.createQuery("""
                        select od.pvid, p.name, ip.image, p.producer, p.type, p.material, p.style, pv.color, pv.size, pv.price, od.quantity
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

    public double getFinalPriceByOid(int oid) {
        return get().withHandle(h -> h.createQuery("""
                        select sum(od.quantity * pv.price) -
                        COALESCE(
                            case
                                when v.name = 'phan_tram'
                                    then sum(od.quantity * pv.price) * v.discount
                                when v.name = 'giam_gia'
                                    then v.discount
                                else 0
                            end
                        , 0)
                        as finalPrice
                        from `order` o
                        join order_detail od on o.oid = od.oid
                        join product_variant pv on od.pvid = pv.pvid
                        left join voucher_user vu on o.uvid = vu.uvid
                        left join voucher v on vu.vid = v.vid
                        where o.oid = :oid
                        group by o.oid
                        """).bind("oid", oid)
                .mapTo(Double.class).one());
    }

    public List<Order> getOrdersByUid(int uid) {
        return get().withHandle(h -> h.createQuery("""
                        select o.oid, o.uid, o.uvid, o.description, o.created_date as createdDate, sum(pv.price * od.quantity) as totalPrice, o.final_price as finalPrice, o.status,
                                os.hash, os.signature, os.sign_status as signStatus, os.expired_date as expiredDate, os.pk_id as pkId
                        from `order` o
                        join order_detail od on o.oid = od.oid
                        left join order_signature os on o.oid = os.oid
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
                                when v.name = 'phan_tram'
                                    then sum(od.quantity * pv.price) * v.discount
                                when v.name = 'giam_gia'
                                    then v.discount
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

    public void updateSignature(int oid, String signature, int pkId) {
        get().useHandle(h -> h.createUpdate("""
                update order_signature
                set signature = :signature,
                    sign_status = 1,
                    sign_time = NOW(),
                    pk_id = :pkId
                where oid = :oid
                """).bind("oid", oid).bind("signature", signature).bind("pkId", pkId).execute());
    }

    public void createOrderSignatureHolder(int oid) {
        get().withHandle(h -> h.createUpdate("""
                        insert into order_signature (oid, sign_status, expired_date)
                        values (:oid, 0, DATE_ADD(NOW(), INTERVAL 2 DAY))
                        """)
                .bind("oid", oid).execute());
    }

    public void insertHash(int oid, String hash) {
        get().useHandle(h -> h.createUpdate("""
                update order_signature
                set hash = :hash
                where oid = :oid
                """).bind("oid", oid).bind("hash", hash).execute());
    }

    public void updateFinalPrice(int oid) {
        get().useHandle(h ->
                h.createUpdate("""
                        update `order` o
                        set o.final_price = (
                            select sum(od.quantity * pv.price) -
                            COALESCE(
                                case
                                    when v.name = 'phan_tram'
                                        then sum(od.quantity * pv.price) * v.discount
                                    when v.name = 'giam_gia'
                                        then v.discount
                                    else 0
                                end
                            , 0)
                            from order_detail od
                            join product_variant pv on od.pvid = pv.pvid
                            left join voucher_user vu on o.uvid = vu.uvid
                            left join voucher v on vu.vid = v.vid
                            where od.oid = o.oid
                        )
                        where o.oid = :oid
                        """).bind("oid", oid).execute()
        );
    }

    public void updateOrderStatus(int oid, String status) {
        get().useHandle(h -> h.createUpdate("""
                update `order`
                set status = :status
                where oid = :oid
                """).bind("oid", oid).bind("status", status).execute());
    }
}
