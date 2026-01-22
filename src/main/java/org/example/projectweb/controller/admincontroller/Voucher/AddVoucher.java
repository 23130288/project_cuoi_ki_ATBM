package org.example.projectweb.controller.admincontroller.Voucher;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.service.VoucherService;
import java.io.IOException;

@WebServlet(name = "AddVoucher", value = "/admin/Voucher_add")
@MultipartConfig
public class AddVoucher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    final VoucherService vService = new VoucherService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String type = request.getParameter("type");
        String expiredDate = request.getParameter("expiredDate");
        String discountStr = request.getParameter("discount");
        String conditionStr = request.getParameter("condition");

        double discount = 0;
        double condition = 0;

        try {
            if (discountStr != null && !discountStr.isBlank()) {
                discount = Double.parseDouble(discountStr);
            }
            if (conditionStr != null && !conditionStr.isBlank()) {
                condition = Double.parseDouble(conditionStr);
            }
        } catch (NumberFormatException e) {
            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Giá trị số không hợp lệ\"}"
            );
            return;
        }

        boolean ok = vService.addVoucher(type, discount, condition, expiredDate);

        if (ok) {
            response.getWriter().write("{\"success\":true}");
        } else {
            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Không thể tạo voucher\"}"
            );
        }
    }
}