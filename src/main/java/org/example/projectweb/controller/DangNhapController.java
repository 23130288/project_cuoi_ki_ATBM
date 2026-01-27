package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import org.example.projectweb.model.User;
import org.example.projectweb.service.DangNhapService;

@WebServlet(name = "DangNhapController", value = "/dang_nhap")
public class DangNhapController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("dang_nhap/dang_nhap.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        DangNhapService loginService = new DangNhapService();
        User user = loginService.login(email, pass);

        if (user != null) {
            // đăng nhập thành công
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //check status
            if (!user.isStatus()) {
                request.setAttribute("err", "Tài khoản của bạn bị khóa");
                request.getRequestDispatcher("dang_nhap/dang_nhap.jsp").forward(request, response);
            }
            //check role
            if ("admin".equalsIgnoreCase(user.getRole()))
                response.sendRedirect("admin");
            else
                response.sendRedirect("tai_khoan");
        } else {
            // đăng nhập thất bại
            request.setAttribute("err", "Bạn nhập sai email hoặc mật khẩu");
            request.getRequestDispatcher("dang_nhap/dang_nhap.jsp").forward(request, response);
        }
    }
}