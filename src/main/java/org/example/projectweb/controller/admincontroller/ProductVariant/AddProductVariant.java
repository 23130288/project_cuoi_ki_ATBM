package org.example.projectweb.controller.admincontroller.ProductVariant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.User;
import org.example.projectweb.service.ProductService;

import java.io.IOException;

@WebServlet(name = "ProductVariant", value = "/admin/productVariant_add")
public class AddProductVariant extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    final ProductService ps = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //check thẩm quyền
        HttpSession session = request.getSession();
        User check = (User) session.getAttribute("user");
        session.setAttribute("user", check);
        if (check == null) {
            request.getRequestDispatcher("/dang_nhap").forward(request, response);
            return;
        }

        if (!"admin".equalsIgnoreCase(check.getRole())) {
            request.getRequestDispatcher("/tham_quyen").forward(request, response);
            return;
        }

        int pid = Integer.parseInt(request.getParameter("pid"));
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        boolean ok = ps.addProductVariant(pid, size, color, price, quantity);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (ok) {
            response.getWriter().write("{\"success\":true}");
        } else {
            response.getWriter().write("{\"success\":false,\"message\":\"Biến thể đã tồn tại\"}");
        }
    }
}