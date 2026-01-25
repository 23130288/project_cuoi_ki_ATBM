package org.example.projectweb.controller.admincontroller.Order;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetail;
import org.example.projectweb.service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/Product_Order_load")
public class GetListProductOrder extends HttpServlet {
    final OrderService os = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int oid = Integer.parseInt(req.getParameter("oid"));
        List<OrderDetail> details = os.getOrderDetailsByOid(oid);
        
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(new Gson().toJson(details));
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}