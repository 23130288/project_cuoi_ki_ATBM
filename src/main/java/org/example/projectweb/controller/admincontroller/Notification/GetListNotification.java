package org.example.projectweb.controller.admincontroller.Notification;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.Notification;
import org.example.projectweb.model.User;
import org.example.projectweb.service.NotificationService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetListNotification", value = "/admin/Notification_load")
public class GetListNotification extends HttpServlet {

    final NotificationService ns = new NotificationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //check thẩm quyền
        HttpSession session = request.getSession();
        User check = (User) session.getAttribute("user");
        session.setAttribute("user", check);
        if (check == null) {
            request.getRequestDispatcher("/dang_nhap").forward(request, response);
            return;
        }

        if (!"admin".equalsIgnoreCase(check.getRole())) {
            request.getRequestDispatcher("/tham_quyen").forward(request, response);
            return;
        }
        response.setContentType("application/json;charset=UTF-8");
        List<Notification> notifications = ns.getAllNotifications();

        String json = new Gson().toJson(notifications);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}