package org.example.projectweb.service;

import org.example.projectweb.cart.Cart;
import org.example.projectweb.dao.OrderDao;
import org.example.projectweb.dao.VoucherDao;

public class OrderService {
    private final OrderDao od = new OrderDao();
    private final VoucherDao vd = new VoucherDao();

    public boolean hasPurchased(int userId, int productId) {
        return od.hasPurchased(userId, productId);
    }

    public int createOrder(int uid, Integer uvid, String description) {
        int oid = od.createOrder(uid, uvid, description);
        if (uvid != null)
            vd.deleteUserVoucher(uvid);
        return oid;
    }

    public void createOrderDetails(int oid, Cart c) {
        od.createOrderDetails(oid, c);
    }
}
