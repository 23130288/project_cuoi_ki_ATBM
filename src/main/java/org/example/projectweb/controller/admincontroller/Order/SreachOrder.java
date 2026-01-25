package org.example.projectweb.controller.admincontroller.Order;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.model.Order;
import org.example.projectweb.service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SreachOrder", value = "/admin/Order_Sreach")
public class SreachOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        String Keyword = request.getParameter("keyword");
        OrderService os = new OrderService();
        List<Order> orders = os.orderSearch(Keyword);

        String json = new Gson().toJson(orders);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}