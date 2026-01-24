package org.example.projectweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;
import org.example.projectweb.model.User;
import org.example.projectweb.model.VoucherUser;
import org.example.projectweb.service.UserService;
import org.example.projectweb.service.VoucherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowCart", value = "/cart")
public class ShowCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart c = (Cart) session.getAttribute("cart");
//        User u = (User) session.getAttribute("user");

        UserService us = new UserService();
        VoucherService vs = new VoucherService();
        User u = us.getUserById(1);
        List<VoucherUser> vouchers = vs.getVoucherUsersByUid(u.getUid());

        if (c == null) {
            c = new Cart();
        }
        session.setAttribute("cart", c);

        request.setAttribute("cart", c);
        request.setAttribute("cartItems", c.getList());
        request.setAttribute("user", u);
        request.setAttribute("vouchers", vouchers);

        request.getRequestDispatcher("cartPage/cartPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

