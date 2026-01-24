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

    List<Product> products = productService.searchInFilter(
            request.getParameter("producer"),
            request.getParameter("category"),
            request.getParameter("color"),
            request.getParameter("size"),
            request.getParameter("minPrice"),
            request.getParameter("maxPrice"),
            request.getParameter("sort")
    );

    request.setAttribute("searchResults", products);

    request.getRequestDispatcher("trang_tim_kiem/trang_tim_kiem.jsp")
            .forward(request, response);
}




}
