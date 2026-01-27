package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.*;
import org.example.projectweb.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@WebServlet(name = "ProductPageController", value = "/productPage")
public class ProductPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        ReviewService rs = new ReviewService();
        WishlistService ws = new WishlistService();

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int productId = Integer.parseInt(request.getParameter("pid"));

        Product p = ps.getProductById(productId);

        List<ProductVariant> pvs = ps.getVariantsByPid(productId);
        Set<String> colors = new TreeSet<>();
        Set<String> sizes = new TreeSet<>();
        for (ProductVariant pv : pvs) {
            colors.add(pv.getColor());
            sizes.add(pv.getSize());
        }
        Review userReview = null;
        boolean inWishlist = false;
        if (u != null) {
            userReview = rs.getReviewByUidAndPid(u.getUid(), productId);
            inWishlist = ws.inWishlist(u.getUid(), productId);
        }


        // product
        request.setAttribute("p", p);
        request.setAttribute("pvs", pvs);
        request.setAttribute("colors", colors);
        request.setAttribute("sizes", sizes);
        request.setAttribute("imgs", ps.getImgsByPid(productId));
        request.setAttribute("mainImg", ps.getMainImg(productId));
        request.setAttribute("avgRating", rs.getAvgRating(productId));
        request.setAttribute("inWishlist", inWishlist);

        // user
        request.setAttribute("user", u);
        request.setAttribute("userReview", userReview);
        request.setAttribute("reviews", rs.getReviewsForProduct(productId));

        request.getRequestDispatcher("productPage/productPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null) {
            response.sendRedirect("dang_nhap");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        WishlistService ws = new WishlistService();

        if (ws.inWishlist(u.getUid(), productId)) {
            ws.removeFromWishlist(u.getUid(), productId);
        } else {
            ws.addToWishlist(u.getUid(), productId);
        }

        response.sendRedirect("productPage?pid=" + productId);
    }
}

