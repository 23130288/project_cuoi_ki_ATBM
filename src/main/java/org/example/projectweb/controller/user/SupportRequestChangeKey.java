package org.example.projectweb.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.service.PublicKeyService;
import org.example.projectweb.service.SupportService;
import org.example.projectweb.service.UserService;

import java.io.IOException;

@WebServlet(name = "SupportRequestChangeKey", value = "/support-request-change-key")
@MultipartConfig
public class SupportRequestChangeKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int uid = Integer.parseInt(request.getParameter("uid"));
        String message = request.getParameter("message");
        String topic = "Khóa";
        String title = "Request thay đổi khóa User#" + uid;

        PublicKeyService pks = new PublicKeyService();
        pks.disableKeyByUid(uid);

        SupportService ss = new SupportService();
        ss.createSupport(uid, topic, title, message);

        response.getWriter().print("{"
                + "\"success\":true"
                + "}");
    }
}

