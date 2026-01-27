package org.example.projectweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.model.User;
import org.example.projectweb.service.DangKyService;
import org.example.projectweb.service.DangNhapService;

import java.io.IOException;

@WebServlet(name = "DangKyController", value = "/dang_ky")
public class DangKyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("dang_ky/dang_ky.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");

        DangKyService loginService = new DangKyService();

        // Giữ lại email nếu có lỗi
        request.setAttribute("email", email);

        // 1. Check rỗng
        if (email == null || email.isEmpty()
                || password == null || password.isEmpty()
                || confirm_password == null || confirm_password.isEmpty()) {

            request.setAttribute("err", "Vui lòng nhập đầy đủ thông tin");
            request.getRequestDispatcher("dang_ky/dang_ky.jsp")
                    .forward(request, response);
            return;
        }

        // 2. Check trùng mật khẩu
        if (!password.equals(confirm_password)) {
            request.setAttribute("err", "Mật khẩu nhập lại không khớp");
            request.getRequestDispatcher("dang_ky/dang_ky.jsp")
                    .forward(request, response);
            return;
        }

        // 3. Gọi Service
        boolean success = DangKyService.addUser(email, password, name);

        if (!success) {
            request.setAttribute("err", "Email đã tồn tại");
            request.getRequestDispatcher("dang_ky/dang_ky.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("/dang_nhap");
        }
    }
}
