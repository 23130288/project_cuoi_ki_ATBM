package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.Product;
import org.example.projectweb.model.User;
import org.example.projectweb.service.ProductService;
import org.example.projectweb.service.ReviewService;
import org.example.projectweb.service.WishlistService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WishlistController", value = "/wishlist")
public class WishlistController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("dang_nhap");
            return;
        }

        WishlistService ws = new WishlistService();
        ReviewService rs = new ReviewService();

        List<Product> list = ws.getProductsForWishlist(user.getUid());

        request.setAttribute("list", list);
        request.setAttribute("listMainImgs", ws.getMainImgsForWishlist(user.getUid()));
        request.setAttribute("listAvgs", rs.getAvgRatingsForWishlist(user.getUid()));
        request.setAttribute("listQuantities", ws.getQuantitiesForWishlist(user.getUid()));
        request.getRequestDispatcher("wishList/wishListPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String action = request.getParameter("action");

        if ("remove".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            WishlistService ws = new WishlistService();
            ws.removeFromWishlist(user.getUid(), productId);
        }

        response.sendRedirect("wishlist");
    }
}

