package org.example.projectweb.controller.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.service.FileService;
import org.example.projectweb.service.PublicKeyService;
import org.example.projectweb.service.UserService;

import java.io.IOException;

@WebServlet(name = "ChangeKeyController", value = "/change-key")
@MultipartConfig
public class ChangeKeyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int uid = Integer.parseInt(request.getParameter("uid"));

        String key = FileService.readPublicKey(request.getPart("new-key").getInputStream());

        PublicKeyService pks = new PublicKeyService();
        UserService us = new UserService();
        pks.uploadKey(uid, key);
        us.changeCanUpKey(uid, 0);

        response.getWriter().print("{"
                + "\"success\":true,"
                + "\"newKey\":\"" + key + "\""
                + "}");
    }
}

