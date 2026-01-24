package org.example.projectweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;
import org.example.projectweb.cart.CartItem;
import org.example.projectweb.model.ProductVariant;
import org.example.projectweb.service.ProductService;

import java.io.IOException;

@WebServlet(name = "UpdateVaraintCart", value = "/update-variant-cart")
public class UpdateVaraintCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int pid = Integer.parseInt(request.getParameter("pid"));
        int newPvid = Integer.parseInt(request.getParameter("newPvid"));
        ProductService ps = new ProductService();
        ProductVariant pv = ps.getVariantByPvid(newPvid);

        HttpSession session = request.getSession();
        Cart c = (Cart) session.getAttribute("cart");
        if (c == null)
            c = new Cart();
        boolean success = c.updateVariant(pid, pv);
        session.setAttribute("cart", c);

        if (!success) {
            response.getWriter().print("{\"success\":false}");
            return;
        }

        CartItem item = c.getItemByPid(pid);
        response.getWriter().print("{" +
                "\"success\":true," +
                "\"price\":" + item.getPrice() + "," +
                "\"itemTotalPrice\":" + c.getItemTotalPrice(pid) + "," +
                "\"cartTotalPrice\":" + c.getTotalPrice() + "," +
                "\"cartFinalPrice\":" + c.getFinalPrice() +
                "}");
    }
}

