package org.example.projectweb.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.User;
import org.example.projectweb.service.UserService;

import java.io.IOException;

@WebServlet(name = "ChangeInformationController", value = "/change-user-information")
public class ChangeInformationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            response.sendRedirect("dang_nhap");
//            return;
//        }
        UserService us = new UserService();
        User user = us.getUserById(1);

        String name = request.getParameter("name");
        String phone = request.getParameter("sdt");
        String address = request.getParameter("address");

        us.changeUserInfo(user.getUid(), name, phone, address);

        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);
//        session.setAttribute("user", user);

        response.sendRedirect("tai_khoan");
    }
}

