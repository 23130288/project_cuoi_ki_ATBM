package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.User;
import org.example.projectweb.service.VoucherService;

import java.io.IOException;

@WebServlet(name = "GetVoucherController", value = "/get-voucher")
public class GetVoucherController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("dang_nhap");
            return;
        }

        int vid = Integer.parseInt(request.getParameter("vid"));

        VoucherService vs = new VoucherService();
        vs.receiveVoucher(user.getUid(), vid);

        // quay lại trang trước (voucher page)
        response.sendRedirect("trangChu");
        // hoặc:
        // response.sendRedirect("voucher");
    }
}

