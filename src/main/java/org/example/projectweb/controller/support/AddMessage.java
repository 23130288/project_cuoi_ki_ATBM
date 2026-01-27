package org.example.projectweb.controller.support;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.User;
import org.example.projectweb.service.SupportService;
import org.example.projectweb.service.UserService;

import java.io.IOException;

@WebServlet(name = "AddMessage", value = "/add-message")
public class AddMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        int spid = Integer.parseInt(request.getParameter("spid"));
        String message = request.getParameter("mess");

        SupportService ss = new SupportService();
        ss.createMessage(spid, u.getUid(), message);

        response.sendRedirect("show-support?spid=" + spid);
    }
}

