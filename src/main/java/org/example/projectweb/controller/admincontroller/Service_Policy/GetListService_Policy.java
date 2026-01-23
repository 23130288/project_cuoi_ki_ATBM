package org.example.projectweb.controller.admincontroller.Service_Policy;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.model.Service_Policy;
import org.example.projectweb.service.Service_PolicyService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetListService_Policy", value = "/admin/Service_Policy/Service_Policy_load")
public class GetListService_Policy extends HttpServlet {

    final Service_PolicyService sps = new Service_PolicyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        List<Service_Policy> Service_Policys = sps.getAllService_Policys();

        String json = new Gson().toJson(Service_Policys);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}