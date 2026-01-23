package org.example.projectweb.controller.admincontroller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.dao.UserDao;
import org.example.projectweb.model.User;

import java.io.IOException;

@WebServlet(name = "UserStatus", value = "/admin/user/toggle_user_status")
public class UserStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int uid = Integer.parseInt(req.getParameter("uid"));

        User user = userDao.getUserById(uid);
        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Đảo trạng thái
        user.setStatus(!user.isStatus());
        userDao.updateStatus(user.getUid());

        resp.setContentType("application/json");
        resp.getWriter().write(
                "{\"message\":\"Cập nhật trạng thái thành công\"}"
        );
    }
}