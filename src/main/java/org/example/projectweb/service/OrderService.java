package org.example.projectweb.service;

import org.example.projectweb.cart.Cart;
import org.example.projectweb.dao.OrderDao;
import org.example.projectweb.dao.VoucherDao;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetail;
import org.example.projectweb.model.OrderDetailView;
import org.example.projectweb.model.Voucher;

import java.util.List;

public class OrderService {
    private final OrderDao od = new OrderDao();
    private final VoucherDao vd = new VoucherDao();

    private final HashService hs = new HashService();

    public List<Order> getListOrderAdmin() {
        return od.getListOrderAdmin();
    }

    public List<Order> orderSearch(String keyword) {
        return od.getListOrderByCostomerAdmin(keyword);
    }

    public List<OrderDetail> getOrderDetailsByOid(int oid) {
        return od.getOrderDetailsByOid(oid);
    }

    public boolean hasPurchased(int userId, int productId) {
        return od.hasPurchased(userId, productId);
    }

    public int createOrder(int uid, Integer uvid, String description, double finalPrice) {
        return od.createOrder(uid, uvid, description, finalPrice);
    }

    public void createOrderDetails(int oid, Cart c) {
        od.createOrderDetails(oid, c);
    }

    public Order getOrderByOid(int oid) {
        Order order = od.getOrderByOid(oid);
        order.setTotalPrice(getTotalPrice(oid));
        order.setVoucher(getVoucherByOid(oid));
        return order;
    }

    private Voucher getVoucherByOid(int oid) {
        return vd.getVoucherByOid(oid);
    }

    public List<OrderDetailView> getOrderDetailViewByOid(int oid) {
        return od.getOrderDetailViewByOid(oid);
    }

    public double getTotalPrice(int oid) {
        return od.getTotalPrice(oid);
    }

    public double getDiscount(int oid) {
        return od.getDiscount(oid);
    }

    public double getFinalPrice(int oid) {
        return od.getFinalPriceByOid(oid);
    }


    public List<Order> getOrdersByUid(int uid) {
        return od.getOrdersByUid(uid);
    }

    public List<Order> getOrdersByUidAndStatus(int uid, String status) {
        return od.getOrdersByUidAndStatus(uid, status);
    }

    public void createHashContent(int oid) {
        od.createOrderSignatureHolder(oid);
        Order order = getOrderByOid(oid);
        String content = getOrderContents(order);
        String hash = hs.hashMd5(content);
        od.insertHash(oid, hash);
    }

    public String getOrderContents(Order order) {
        return getOrderContents(order, od.getOrderDetailViewByOid(order.getOid()));
    }

    public String getOrderContents(Order order, List<OrderDetailView> orderDetails) {
        StringBuilder res = new StringBuilder();
        res.append("Order#").append(order.getOid()).append("\n");
        res.append("User#").append(order.getUid()).append("\n");
        res.append("Description: ").append(order.getDescription()).append("\n");
        res.append("Created at: ").append(order.getCreatedDate()).append("\n\n");
        for (int i = 0; i < orderDetails.size(); i++) {
            res.append(i + 1).append(". ").append(orderDetails.get(i).toString()).append("\n\n");
        }
        res.append("Total price: ").append(order.getTotalPrice()).append("\n");
        Voucher v = order.getVoucher();
        if (v != null && v.getName() != null) {
            res.append("Voucher#").append(order.getUvid()).append(": ").append(v).append("\n");
        } else
            res.append("Voucher: null").append("\n");
        res.append("Final price: ").append(order.getFinalPrice());
        return res.toString();
    }

    public boolean isOrderChanged(Order order) {
        return !order.getHash().equals(hs.hashMd5(getOrderContents(order)));
    }
}
