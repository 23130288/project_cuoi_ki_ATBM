package org.example.projectweb.controller.admincontroller.Voucher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.service.VoucherService;

import java.io.IOException;

@WebServlet(name = "VoucherStatus", value = "/admin/voucher/toggle_voucher_status")
public class VoucherStatus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    final VoucherService vService = new VoucherService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        int vid = Integer.parseInt(req.getParameter("vid"));

        if (vService.updateVoucherStatus(vid)) {
            resp.getWriter().write("{\"message\":\"Cập nhật trạng thái thành công\"}");
        } else {
            resp.getWriter().write("{\"message\":\"Có lỗi đã sảy ra\"}");
        }
    }
}