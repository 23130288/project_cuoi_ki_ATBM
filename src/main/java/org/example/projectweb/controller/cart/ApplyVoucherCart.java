package org.example.projectweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;
import org.example.projectweb.model.User;
import org.example.projectweb.model.VoucherUser;
import org.example.projectweb.service.VoucherService;

import java.io.IOException;

@WebServlet(name = "ApplyVoucherCart", value = "/apply-voucher-cart")
public class ApplyVoucherCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null) {
            response.sendRedirect("dang_nhap");
            return;
        }
        Cart c = (Cart) session.getAttribute("cart");

        if (c == null) {
            c = new Cart();
        }

        // check null
        String uvidStr = request.getParameter("uvid");
        if (uvidStr == null || uvidStr.isEmpty()) {
            c.setVoucher(null);
            session.setAttribute("cart", c);
            response.getWriter().print("{"
                    + "\"success\":true,"
                    + "\"discount\":0,"
                    + "\"cartFinalPrice\":" + c.getFinalPrice()
                    + "}");
            return;
        }

        VoucherService vs = new VoucherService();
        int uvid = Integer.parseInt(uvidStr);
        VoucherUser v = vs.getVoucherUserByUvid(uvid);

        // check condition
        double totalPrice = c.getTotalPrice();
        if (totalPrice < v.getCondition()) {
            c.setVoucher(null);
            session.setAttribute("cart", c);
            response.getWriter().print("{" +
                    "\"success\":false," +
                    "\"cartTotalPrice\":" + c.getTotalPrice() + "," +
                    "\"message\":\"Đơn hàng chưa đủ điều kiện áp dụng voucher\"" +
                    "}");
            return;
        }

        // apply voucher
        c.setVoucher(v);
        session.setAttribute("cart", c);
        double finalPrice = c.getFinalPrice();
        response.getWriter().print("{" +
                "\"success\":true," +
                "\"voucherName\":\"" + c.getVoucher().getName() + "\"," +
                "\"discount\":" + c.getVoucher().getDiscount() + "," +
                "\"cartFinalPrice\":" + finalPrice +
                "}");
    }
}

