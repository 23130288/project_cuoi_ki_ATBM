package org.example.projectweb.controller.admincontroller.Key;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.Support;
import org.example.projectweb.model.User;
import org.example.projectweb.service.SupportService;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/Keys")
public class GetListKey extends HttpServlet {

    final SupportService sups = new SupportService();

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
        String filter = request.getParameter("filter");
        List<Support> supports = sups.getSupportsKey(filter);

        String json = new Gson().toJson(supports);
        response.getWriter().write(json);
    }

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

//        int spid = Integer.parseInt(request.getParameter("spid"));
//        int uid = Integer.parseInt(request.getParameter("uid"));
//        String message = request.getParameter("message");
//        String uidStr = String.valueOf(uid);
//
//        sups.createreply(spid, check.getUid(), uidStr, message);
//
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write("{\"success\":true}");
    }
}