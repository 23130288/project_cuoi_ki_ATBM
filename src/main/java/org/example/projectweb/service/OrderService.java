package org.example.projectweb.service;

import org.example.projectweb.cart.Cart;
import org.example.projectweb.dao.OrderDao;
import org.example.projectweb.dao.VoucherDao;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetailView;
import org.example.projectweb.model.Voucher;

import java.util.List;

public class OrderService {
    private final OrderDao od = new OrderDao();

    public boolean hasPurchased(int userId, int productId) {
        return od.hasPurchased(userId, productId);
    }

    public int createOrder(int uid, Integer vid, String description) {
        return od.createOrder(uid, vid, description);
    }

    public void createOrderDetails(int oid, Cart c) {
        od.createOrderDetails(oid, c);
    }

    public Order getOrderByOid(int oid) {
        Order order = od.getOrderByOid(oid);
        order.setTotalPrice(getTotalPrice(order.getOid()));
        order.setDiscount(getDiscount(order.getOid()));
        order.setFinalPrice(getFinalPrice(order.getOid()));
        return order;
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
        double discount = getDiscount(oid);
        if (discount == 0)
            return getTotalPrice(oid);
        if (discount < 1 && discount > 0)
            return getTotalPrice(oid) * (1-discount);
        return getTotalPrice(oid) - discount;
    }


}
