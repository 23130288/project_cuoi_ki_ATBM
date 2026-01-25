package org.example.projectweb.controller.admincontroller.Notification;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.dao.NotificationDao;
import org.example.projectweb.model.Notification;
import java.io.IOException;

@WebServlet(name = "NotificationStatus", value = "/admin/product/toggle_Notification_status")
public class NotificationStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    final NotificationDao nDao = new NotificationDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int nid = Integer.parseInt(req.getParameter("nid"));

        Notification notification = nDao.getNotificationById(nid);
        if (notification == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Đảo trạng thái
        nDao.updateStatus(notification.getNid());

        resp.setContentType("application/json");
        resp.getWriter().write(
                "{\"message\":\"Cập nhật trạng thái thành công\"}"
        );
    }
}