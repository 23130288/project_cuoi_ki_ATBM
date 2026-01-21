package org.example.projectweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.model.Product;
import org.example.projectweb.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchController", value = "/search")
public class searchController extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("trang_tim_kiem/trang_tim_kiem.jsp")
                .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String producer = request.getParameter("producer");
        String category = request.getParameter("category");
        String color    = request.getParameter("color");
        String size     = request.getParameter("size");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String sort     = request.getParameter("sort");

        List<Product> products = productService.searchInFilter(producer, category, color, size, minPrice, maxPrice, sort);

        request.setAttribute("producer", producer);
        request.setAttribute("category", category);
        request.setAttribute("color", color);
        request.setAttribute("size", size);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("sort", sort);

        request.getRequestDispatcher("trang_tim_kiem/trang_tim_kiem.jsp")
                .forward(request, response);
    }
}
