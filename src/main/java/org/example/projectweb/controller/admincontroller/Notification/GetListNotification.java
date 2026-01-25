package org.example.projectweb.controller.admincontroller.Notification;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.model.Notification;
import org.example.projectweb.service.NotificationService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetListNotification", value = "/admin/Notification_load")
public class GetListNotification extends HttpServlet {

    final NotificationService ns = new NotificationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        List<Notification> notifications = ns.getAllNotifications();

        String json = new Gson().toJson(notifications);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}