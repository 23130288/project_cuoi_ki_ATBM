package org.example.projectweb.controller.admincontroller.Service_Policy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.User;
import org.example.projectweb.service.Service_PolicyService;
import java.io.IOException;

@WebServlet(name = "Service_PolicyStatus", value = "/admin/Service_Policy/toggle_Service_Policy_status")
public class Service_PolicyStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    final Service_PolicyService sps = new Service_PolicyService();

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

        response.setContentType("application/json");
        int spid = Integer.parseInt(request.getParameter("spid"));

        if (sps.updateService_PolicyStatus(spid)) {
            response.getWriter().write("{\"message\":\"Cập nhật trạng thái thành công\"}");
        } else {
            response.getWriter().write("{\"message\":\"Có lỗi đã sảy ra\"}");
        }
    }
}