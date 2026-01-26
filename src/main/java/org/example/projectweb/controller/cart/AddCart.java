package org.example.projectweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;
import org.example.projectweb.model.Product;
import org.example.projectweb.model.ProductVariant;
import org.example.projectweb.model.User;
import org.example.projectweb.service.ProductService;

import java.io.IOException;

@WebServlet(name = "AddCart", value = "/add-cart")
public class AddCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("{\"success\": false, \"login\": false}");
            return;
        }

        int pid = Integer.parseInt(request.getParameter("pid"));
        int pvid = Integer.parseInt(request.getParameter("pvid"));
        String mainImg = request.getParameter("mainImg");
        int q = Integer.parseInt(request.getParameter("q"));

        ProductService ps = new ProductService();
        Product product = ps.getProductById(pid);
        product.setVariants(ps.getVariantsByPid(product.getPid())); // set cac list variants
        ProductVariant productVariant = ps.getVariantByPvid(pvid);

        Cart c = (Cart) session.getAttribute("cart");
        if (c == null)
            c = new Cart();
        c.addProduct(product, productVariant, mainImg, q);
        session.setAttribute("cart", c);

        response.getWriter().write("{\"success\": true}");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

