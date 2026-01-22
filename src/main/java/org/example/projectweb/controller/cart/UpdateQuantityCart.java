package org.example.projectweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;

import java.io.IOException;

@WebServlet(name = "UpdateQuantityCart", value = "/update-quantity-cart")
public class UpdateQuantityCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int pid = Integer.parseInt(request.getParameter("pid"));
        int delta = Integer.parseInt(request.getParameter("delta"));

        HttpSession session = request.getSession();
        Cart c = (Cart) session.getAttribute("cart");
        if (c == null)
            c = new Cart();
        int quantity = c.updateQuantity(pid, delta);
        session.setAttribute("cart", c);
        response.getWriter().print("{" +
                "\"success\":true," +
                "\"quantity\":" + quantity + "," +
                "\"itemTotalPrice\":" + c.getItemTotalPrice(pid) + "," +
                "\"cartTotalPrice\":" + c.getTotalPrice() + "," +
                "\"cartFinalPrice\":" + c.getFinalPrice() +
                "}");
    }
}

