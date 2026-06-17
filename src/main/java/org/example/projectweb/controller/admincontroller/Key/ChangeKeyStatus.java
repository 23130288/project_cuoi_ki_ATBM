package org.example.projectweb.controller.admincontroller.Key;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.User;
import org.example.projectweb.service.PublicKeyService;
import org.example.projectweb.service.SupportService;

import java.io.IOException;

@WebServlet(value = "/admin/KeyStatus")
public class ChangeKeyStatus extends HttpServlet {

    final PublicKeyService pks = new PublicKeyService();
    final SupportService sups = new SupportService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        int spid = Integer.parseInt(request.getParameter("spid"));

        pks.canUploadKey(uid);
        sups.createreply(spid, check.getUid(), String.valueOf(uid), "Xác nhận bạn đã mất khóa. Bạn có thể upload Public Key mới.");

        response.getWriter().print("success");
    }
}