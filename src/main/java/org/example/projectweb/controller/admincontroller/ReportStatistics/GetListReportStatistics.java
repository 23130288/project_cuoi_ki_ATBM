package org.example.projectweb.controller.admincontroller.ReportStatistics;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.ReportStatistics;
import org.example.projectweb.model.User;
import org.example.projectweb.service.ReportService;
import java.io.IOException;

@WebServlet(value = "/admin/ReportStatistics")
public class GetListReportStatistics extends HttpServlet {

    final ReportService rs = new ReportService();

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

        ReportStatistics stats = rs.getStatistics();

        String json = new Gson().toJson(stats);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}