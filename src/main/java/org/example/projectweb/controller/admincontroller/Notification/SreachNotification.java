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

@WebServlet(name = "SreachNotification", value = "/admin/Notification_Sreach")
public class SreachNotification extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        System.out.println("1");

        String title = request.getParameter("keyword");
        NotificationService ns = new NotificationService();
        List<Notification> notifications = ns.getAllNotificationsTitleLike(title);
        System.out.println("2");

        String json = new Gson().toJson(notifications);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}