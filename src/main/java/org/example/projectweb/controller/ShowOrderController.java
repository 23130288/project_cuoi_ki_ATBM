package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetailView;
import org.example.projectweb.model.User;
import org.example.projectweb.service.OrderService;
import org.example.projectweb.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowOrderController", value = "/show-order")
public class ShowOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User u = (User) session.getAttribute("user");
        UserService us = new UserService();
        User u = us.getUserById(1);

        int oid = Integer.parseInt(request.getParameter("oid"));

        OrderService os = new OrderService();

        Order order = os.getOrderByOid(oid);
        List<OrderDetailView> orderDetails = os.getOrderDetailViewByOid(oid);

        request.setAttribute("user", u);
        request.setAttribute("order", order);
        request.setAttribute("orderDetails", orderDetails);

        request.getRequestDispatcher("ct_Order/ct_Order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

