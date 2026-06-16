package org.example.projectweb.controller.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;
import org.example.projectweb.model.User;
import org.example.projectweb.model.VoucherUser;
import org.example.projectweb.service.*;

import java.io.*;

@WebServlet(name = "CheckOutController", value = "/checkout")
public class CheckOutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Cart c = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (c == null || c.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }
        String description = request.getParameter("note");

        VoucherService vs = new VoucherService();
        OrderService os = new OrderService();

        VoucherUser v = c.getVoucher();
        Integer uvid = null;
        if (v != null) {
            vs.setApplicable(v.getUvid(), 0);
            uvid = v.getUvid();
        }
        // create order
        int oid = os.createOrder(user.getUid(), uvid, description, c.getFinalPrice());
        os.createOrderDetails(oid, c);
        os.createHashContent(oid);

        c.removeAll();
        c.setVoucher(null);
        response.sendRedirect("show-order?oid=" + oid);
    }
}

