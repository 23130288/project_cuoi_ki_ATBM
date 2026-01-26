package org.example.projectweb.controller.support;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.Support;
import org.example.projectweb.model.SupportMessage;
import org.example.projectweb.model.User;
import org.example.projectweb.service.SupportService;
import org.example.projectweb.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowSupport", value = "/show-support")
public class ShowSupport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");

        UserService us = new UserService();
        User user = us.getUserById(1);

        if (user == null) {
            response.sendRedirect("dang_nhap");
            return;
        }

        int spid = Integer.parseInt(request.getParameter("spid"));

        SupportService ss = new SupportService();
        Support support = ss.getSupportBySpid(spid);
        if (support.getUid() != user.getUid()) {
            response.sendRedirect("trangChu");
            return;
        }
        List<SupportMessage> messages = ss.getMessagesBySpid(spid);
        request.setAttribute("support", support);
        request.setAttribute("messages", messages);

        request.getRequestDispatcher("ct_Support/ct_Support.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

