package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.User;
import org.example.projectweb.service.SupportService;

import java.io.IOException;

@WebServlet(name = "SupportController", value = "/support")
public class SupportController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("dang_nhap");
            return;
        }
        request.getRequestDispatcher("helpPage/helpPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SupportService ss = new SupportService();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String topic = request.getParameter("topic");
        String title = request.getParameter("title");
        String message = request.getParameter("message");

        ss.createSupport(user.getUid(), topic, title, message);

        response.sendRedirect("support");
    }
}

