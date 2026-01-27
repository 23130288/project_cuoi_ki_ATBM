package org.example.projectweb.controller.admincontroller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.projectweb.model.User;
import org.example.projectweb.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddProduct", value = "/admin/product_add")
@MultipartConfig
public class AddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

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

        ProductService ps = new ProductService();

        // 1. Lấy dữ liệu text
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String style = request.getParameter("style");
        String material = request.getParameter("material");
        String producer = request.getParameter("producer");
        String status = request.getParameter("status");
        String description = request.getParameter("description");

        // 2. Lấy danh sách ảnh
        List<Part> imageParts = new ArrayList<>();
        for (Part part : request.getParts()) {
            if ("images".equals(part.getName()) && part.getSize() > 0) {
                imageParts.add(part);
            }
        }

        // 3. Gọi Service (ném tất cả cho Service xử lý)
        boolean ok = ps.addProduct(name, type, style, material, producer, status, description, imageParts);

        // 4. Trả JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (ok) {
            response.getWriter().write("{\"success\":true}");
        } else {
            response.getWriter().write("{\"success\":false,\"message\":\"Product đã tồn tại\"}");
        }
    }
}