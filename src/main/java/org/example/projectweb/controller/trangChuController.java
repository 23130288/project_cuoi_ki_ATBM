package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.Product;
import org.example.projectweb.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "trangChuController", value = "/trangChu")
public class trangChuController extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> hotProducts = productService.getHotProducts();
        List<Product> valiProducts = productService.getValiProducts();
        List<Product> baloProducts = productService.getBaloProducts();

        request.setAttribute("hotProducts", hotProducts);
        request.setAttribute("valiProducts", valiProducts);
        request.setAttribute("baloProducts", baloProducts);

        request.getRequestDispatcher("trang_chu/trang_chu.jsp")
                .forward(request, response);
    }
}