package org.example.projectweb.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.User;
import org.example.projectweb.service.HashService;
import org.example.projectweb.service.UserService;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "ChangePasswordController", value = "/change-password")
public class ChangePasswordController extends HttpServlet {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");

        UserService us = new UserService();
        HashService hs = new HashService();
        User user = us.getUserById(1);

        String oldPassword = request.getParameter("old_password");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        if (oldPassword == null || password == null || confirmPassword == null ||
                oldPassword.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            session.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            response.sendRedirect("tai_khoan?tab=change-password");
            return;
        }

        if (!hs.hashMd5(oldPassword).equals(user.getPassword())) {
            session.setAttribute("error", "Mật khẩu cũ không đúng");
            response.sendRedirect("tai_khoan?tab=change-password");
            return;
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            session.setAttribute("error", "Mật khẩu phải có ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt.");
            response.sendRedirect("tai_khoan?tab=change-password");
            return;
        }

        if (!password.equals(confirmPassword)) {
            session.setAttribute("error", "Mật khẩu nhập lại không khớp.");
            response.sendRedirect("tai_khoan?tab=change-password");
            return;
        }

        System.out.println("SUCCESS");
        us.updatePassword(user.getUid(), hs.hashMd5(password));
        session.setAttribute("success", "Đổi mật khẩu thành công");
        response.sendRedirect("tai_khoan?tab=change-password");
    }
}

