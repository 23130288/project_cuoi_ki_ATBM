package org.example.projectweb.controller.admincontroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.User;
import org.example.projectweb.service.UserService;

import java.io.IOException;

@WebServlet(name = "AdminController", value = "/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
        UserService us = new UserService();
        User user = us.getUserById(2);
        session.setAttribute("user", user);

        if (user == null) {
            request.getRequestDispatcher("dang_nhap").forward(request, response);
            return;
        }

        if (!"admin".equalsIgnoreCase(user.getRole())) {
            request.getRequestDispatcher("tham_quyen").forward(request, response);
            return;
        }

        request.getRequestDispatcher("admin/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}