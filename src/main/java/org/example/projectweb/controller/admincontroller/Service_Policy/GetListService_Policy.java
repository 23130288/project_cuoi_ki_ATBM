package org.example.projectweb.controller.admincontroller.Service_Policy;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.Service_Policy;
import org.example.projectweb.model.User;
import org.example.projectweb.service.Service_PolicyService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetListService_Policy", value = "/admin/Service_Policy/Service_Policy_load")
public class GetListService_Policy extends HttpServlet {

    final Service_PolicyService sps = new Service_PolicyService();

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
        List<Service_Policy> Service_Policys = sps.getAllService_Policys();

        String json = new Gson().toJson(Service_Policys);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}