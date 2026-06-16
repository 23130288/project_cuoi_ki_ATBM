package org.example.projectweb.controller.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.service.SupportService;

import java.io.IOException;

@WebServlet(name = "SupportOrderChanged", value = "/support-order-changed")
@MultipartConfig
public class SupportOrderChanged extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int oid = Integer.parseInt(request.getParameter("oid"));
        int uid = Integer.parseInt(request.getParameter("uid"));
        String message = request.getParameter("message");
        String topic = "Đơn hàng";
        String title = "Đơn hàng #" + oid + " bị thay đổi";

        SupportService ss = new SupportService();
        ss.createSupport(uid, topic, title, message);

        response.getWriter().print("{"
                + "\"success\":true"
                + "}");
    }
}

