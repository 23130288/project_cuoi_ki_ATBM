package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.dao.VoucherDao;
import org.example.projectweb.model.User;

import java.io.IOException;

@WebServlet(name = "ReceiveVoucherController", value = "/receiveVoucher")
public class ReceiveVoucherController extends HttpServlet {
    private final VoucherDao voucherDao = new VoucherDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

//        if (user == null) {
//            response.sendRedirect("dang_nhap/dang_nhap.jsp");
//            return;
//        }

        String vidRaw = request.getParameter("vid");
        if (vidRaw == null) {
            response.sendRedirect("trangChu");
            return;
        }

        int vid = Integer.parseInt(vidRaw);

        if (!voucherDao.isVoucherReceived(user.getUid(), vid)) {
            voucherDao.receiveVoucher(user.getUid(), vid);
        }

        response.sendRedirect("trangChu");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}