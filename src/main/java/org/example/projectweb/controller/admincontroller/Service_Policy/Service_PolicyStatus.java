package org.example.projectweb.controller.admincontroller.Service_Policy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.service.Service_PolicyService;
import org.example.projectweb.service.VoucherService;

import java.io.IOException;

@WebServlet(name = "Service_PolicyStatus", value = "/admin/Service_Policy/toggle_Service_Policy_status")
public class Service_PolicyStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    final Service_PolicyService sps = new Service_PolicyService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        int spid = Integer.parseInt(req.getParameter("spid"));

        if (sps.updateService_PolicyStatus(spid)) {
            resp.getWriter().write("{\"message\":\"Cập nhật trạng thái thành công\"}");
        } else {
            resp.getWriter().write("{\"message\":\"Có lỗi đã sảy ra\"}");
        }
    }
}