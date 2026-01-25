package org.example.projectweb.controller.support;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.Support;
import org.example.projectweb.model.SupportMessage;
import org.example.projectweb.service.SupportService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowSupport", value = "/show-support")
public class ShowSupport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int spid = Integer.parseInt(request.getParameter("spid"));

        SupportService ss = new SupportService();
        Support support = ss.getSupportBySpid(spid);
        List<SupportMessage> messages = ss.getMessagesBySpid(spid);

        request.setAttribute("support", support);
        request.setAttribute("messages", messages);

        request.getRequestDispatcher("ct_Support/ct_Support.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

