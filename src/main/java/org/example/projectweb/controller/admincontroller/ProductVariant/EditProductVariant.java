package org.example.projectweb.controller.admincontroller.ProductVariant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.projectweb.model.User;
import org.example.projectweb.service.ProductService;
import java.io.IOException;

@WebServlet(value = "/admin/product_variant_edit")
@MultipartConfig
public class EditProductVariant extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        int pvid = Integer.parseInt(request.getParameter("pvid"));
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // 3. Gọi Service (ném tất cả cho Service xử lý)
        boolean ok = ps.updateProductVariant(pvid, price, quantity);

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