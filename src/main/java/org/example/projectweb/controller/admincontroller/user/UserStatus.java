package org.example.projectweb.controller.admincontroller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

        int uid = Integer.parseInt(request.getParameter("uid"));

        User user = userDao.getUserById(uid);
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Đảo trạng thái
        userDao.updateStatus(user.getUid());

        response.setContentType("application/json");
        response.getWriter().write(
                "{\"message\":\"Cập nhật trạng thái thành công\"}"
        );
    }
}