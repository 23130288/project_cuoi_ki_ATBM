package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.User;
import org.example.projectweb.service.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TaiKhoanController", value = "/tai_khoan")
public class TaiKhoanController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("dang_nhap").forward(request, response);
            return;
        }

        request.setAttribute("user", user);

        // Notifications
        NotificationService ns = new NotificationService();
        request.setAttribute("notifications", ns.getNotificationsByUid(user.getUid()));

        // Orders
        OrderService os = new OrderService();
        VoucherService vs = new VoucherService();
        List<Order> orders = os.getOrdersByUid(user.getUid());
        for (Order order : orders) {
            if (order.getUvid() != 0)
                order.setVoucher(vs.getVoucherUserByUvid(order.getUvid()));
            boolean changed = os.isOrderChanged(order);
            order.setChanged(changed);
        }
        request.setAttribute("orders", orders);

        // Vouchers
        request.setAttribute("vouchers", vs.getVoucherUsersByUid(user.getUid()));

        // Supports
        SupportService ss = new SupportService();
        request.setAttribute("supports", ss.getSupportsByUid(user.getUid()));

        request.getRequestDispatcher("tai_khoan/tai_khoan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}