package org.example.projectweb.controller.admincontroller.Voucher;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectweb.model.Voucher;
import org.example.projectweb.service.VoucherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetListVoucher", value = "/admin/Voucher/voucher_load")
public class GetListVoucher extends HttpServlet {

    final VoucherService vs = new VoucherService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        System.out.println("1111111111");
        List<Voucher> Vouchers = vs.getAllVouchers();
        System.out.println("aaaaaaaa");

        String json = new Gson().toJson(Vouchers);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}