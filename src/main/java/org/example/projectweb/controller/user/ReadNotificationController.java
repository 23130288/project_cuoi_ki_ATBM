package org.example.projectweb.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.User;
import org.example.projectweb.service.NotificationService;

import java.io.IOException;

@WebServlet(name = "ReadNotificationController", value = "/read-notification")
public class ReadNotificationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        int nid = Integer.parseInt(request.getParameter("nid"));

        NotificationService ns = new NotificationService();
        ns.markAsRead(user.getUid(), nid);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}

