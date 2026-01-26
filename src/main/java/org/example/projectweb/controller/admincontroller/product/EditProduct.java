package org.example.projectweb.controller.admincontroller.product;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.projectweb.model.ImageProduct;
import org.example.projectweb.model.Product;
import org.example.projectweb.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/admin/product_edit")
@MultipartConfig
public class EditProduct extends HttpServlet {

    final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        int pid = Integer.parseInt(request.getParameter("pid"));

        Product product = productService.getProductForEdit(pid);
        List<ImageProduct> images = productService.getImagesByPid(pid);

        Map<String, Object> result = new HashMap<>();
        result.put("product", product);
        result.put("images", images);

        String json = new Gson().toJson(result);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();

        // 1. Lấy dữ liệu text
        int pid = Integer.parseInt(request.getParameter("pid"));
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
        boolean ok = ps.updateProduct(pid, name, type, style, material, producer, status, description, imageParts);

        // 4. Trả JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (ok) {
            response.getWriter().write("{\"success\":true}");
        } else {
            response.getWriter().write("{\"success\":false,\"message\":\"sửa thất bại.\"}");
        }
    }
}