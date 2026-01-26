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

@WebServlet(name = "GetListOrder", value = "/admin/Order_load")
public class GetListOrder extends HttpServlet {

    final OrderService os = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        List<Order> orders = os.getListOrderAdmin();

        String json = new Gson().toJson(orders);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}