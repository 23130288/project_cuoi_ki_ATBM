package org.example.projectweb.controller.admincontroller.Order;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.OrderDetail;
import org.example.projectweb.model.User;
import org.example.projectweb.service.OrderService;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/Product_Order_load")
public class GetListProductOrder extends HttpServlet {
    final OrderService os = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

        int oid = Integer.parseInt(request.getParameter("oid"));
        List<OrderDetail> details = os.getOrderDetailsByOid(oid);
        
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new Gson().toJson(details));
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}