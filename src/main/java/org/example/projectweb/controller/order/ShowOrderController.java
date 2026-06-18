package org.example.projectweb.controller.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetailView;
import org.example.projectweb.model.User;
import org.example.projectweb.service.OrderService;
import org.example.projectweb.service.PublicKeyService;

import java.io.*;
import java.util.List;

@WebServlet(name = "ShowOrderController", value = "/show-order")
public class ShowOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("dang_nhap");
            return;
        }
        int oid = Integer.parseInt(request.getParameter("oid"));
        OrderService os = new OrderService();

        Order order = os.getOrderByOid(oid);
        List<OrderDetailView> orderDetails = os.getOrderDetailViewByOid(oid);
        order.setChanged(os.isOrderChanged(order, orderDetails));

        PublicKeyService pks = new PublicKeyService();

        request.setAttribute("user", user);
        request.setAttribute("order", order);
        request.setAttribute("orderDetails", orderDetails);
        request.setAttribute("key", pks.getPublicKeyByUid(user.getUid()));

        request.getRequestDispatcher("ct_Order/ct_Order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

