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

    public int createOrder(int uid, Integer uvid, String description) {
        return od.createOrder(uid, uvid, description);
    }

    public void createOrderDetails(int oid, Cart c) {
        od.createOrderDetails(oid, c);
    }

    public Order getOrderByOid(int oid) {
        Order order = od.getOrderByOid(oid);
        order.setTotalPrice(getTotalPrice(oid));
        order.setVoucher(getVoucherByOid(oid));
        order.setFinalPrice(getFinalPrice(oid));
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
}
