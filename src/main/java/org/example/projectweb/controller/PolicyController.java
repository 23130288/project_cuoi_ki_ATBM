package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.Service_Policy;
import org.example.projectweb.service.Service_PolicyService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Service_PolicyController", value = "/chinh_sach")
public class PolicyController extends HttpServlet {
    private final Service_PolicyService sps = new Service_PolicyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy danh sách policy (sidebar)
        List<Service_Policy> Policys = sps.getActivePolicyTitles();


        // 2. Lấy policy đang chọn
        String spidParam = request.getParameter("spid");
        Service_Policy current = null;

        if (spidParam != null) {
            int spid = Integer.parseInt(spidParam);
            current = sps.getService_PolicyById(spid);
        } else if (!Policys.isEmpty()) {
            current = sps.getService_PolicyById(Policys.get(0).getSpid());
        }

        // 3. Đẩy sang JSP
        request.setAttribute("policies", Policys);
        request.setAttribute("current", current);

        request.getRequestDispatcher("chinh_sach/chinh_sach.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}