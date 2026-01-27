package org.example.projectweb.controller.admincontroller.user;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.User;
import org.example.projectweb.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetListUser", value = "/admin/users")
public class GetListUser extends HttpServlet {

    final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //check thẩm quyền
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.setAttribute("user", user);
        if (user == null) {
            request.getRequestDispatcher("/dang_nhap").forward(request, response);
            return;
        }

        if (!"admin".equalsIgnoreCase(user.getRole())) {
            request.getRequestDispatcher("/tham_quyen").forward(request, response);
            return;
        }

        response.setContentType("application/json;charset=UTF-8");

        List<User> users = userService.getAllUsers();

        String json = new Gson().toJson(users);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}