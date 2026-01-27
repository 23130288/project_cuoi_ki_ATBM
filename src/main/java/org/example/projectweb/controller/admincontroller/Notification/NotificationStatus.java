package org.example.projectweb.controller.admincontroller.Notification;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.dao.NotificationDao;
import org.example.projectweb.model.Notification;
import org.example.projectweb.model.User;

import java.io.IOException;

@WebServlet(name = "NotificationStatus", value = "/admin/product/toggle_Notification_status")
public class NotificationStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    final NotificationDao nDao = new NotificationDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

        int nid = Integer.parseInt(request.getParameter("nid"));

        Notification notification = nDao.getNotificationById(nid);
        if (notification == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Đảo trạng thái
        nDao.updateStatus(notification.getNid());

        response.setContentType("application/json");
        response.getWriter().write(
                "{\"message\":\"Cập nhật trạng thái thành công\"}"
        );
    }
}