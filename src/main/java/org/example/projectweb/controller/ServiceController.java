package org.example.projectweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.model.Service_Policy;
import org.example.projectweb.service.Service_PolicyService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServiceController", value = "/dich_vu")
public class ServiceController extends HttpServlet {

    private final Service_PolicyService sps = new Service_PolicyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy danh sách dịch vụ (sidebar)
        List<Service_Policy> services = sps.getActiveServiceTitles();

        // 2. Lấy dịch vụ đang chọn
        String spidParam = request.getParameter("spid");
        Service_Policy current = null;

        if (spidParam != null) {
            int spid = Integer.parseInt(spidParam);
            current = sps.getService_PolicyById(spid);
        } else if (!services.isEmpty()) {
            current = sps.getService_PolicyById(services.get(0).getSpid());
        }

        // 3. Đẩy sang JSP
        request.setAttribute("services", services);
        request.setAttribute("current", current);

        request.getRequestDispatcher("dich_vu/dich_vu.jsp")
                .forward(request, response);
    }
}
