package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.User;
import org.example.projectweb.service.OrderService;
import org.example.projectweb.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TaiKhoanController", value = "/tai_khoan")
public class TaiKhoanController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
        UserService us = new UserService();
        User user = us.getUserById(1);

        OrderService os = new OrderService();
        List<Order> orders = os.getOrdersByUid(user.getUid());
        List<Order> deliveredOrders = os.getOrdersByUidAndStatus(user.getUid(), "delivered");
        List<Order> deliveringOrder = os.getOrdersByUidAndStatus(user.getUid(), "delivering");
        List<Order> canceledOrder = os.getOrdersByUidAndStatus(user.getUid(), "canceled");

        request.setAttribute("user", user);

        request.setAttribute("orders", orders);
        request.setAttribute("deliveredOrders", deliveredOrders);
        request.setAttribute("deliveringOrder", deliveringOrder);
        request.setAttribute("canceledOrder", canceledOrder);

        request.getRequestDispatcher("tai_khoan/tai_khoan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}