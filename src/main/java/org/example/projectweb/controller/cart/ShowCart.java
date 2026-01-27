package org.example.projectweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;
import org.example.projectweb.model.User;
import org.example.projectweb.model.VoucherUser;
import org.example.projectweb.service.VoucherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowCart", value = "/cart")
public class ShowCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart c = (Cart) session.getAttribute("cart");
        User u = (User) session.getAttribute("user");

        VoucherService vs = new VoucherService();
        List<VoucherUser> vouchers = null;
        if (u != null) {
            vouchers = vs.getVoucherUsersByUid(u.getUid());
        }

        if (c == null) {
            c = new Cart();
        }
        session.setAttribute("cart", c);

        request.setAttribute("user", u);
        request.setAttribute("cart", c);
        request.setAttribute("cartItems", c.getList());
        request.setAttribute("vouchers", vouchers);

        request.getRequestDispatcher("cartPage/cartPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

