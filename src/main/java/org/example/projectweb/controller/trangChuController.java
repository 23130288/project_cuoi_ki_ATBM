package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.dao.VoucherDao;
import org.example.projectweb.model.Product;
import org.example.projectweb.model.User;
import org.example.projectweb.model.Voucher;
import org.example.projectweb.service.ProductService;
import org.example.projectweb.service.VoucherService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "trangChuController", value = "/trangChu")
public class trangChuController extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final VoucherDao voucherDao = new VoucherDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        VoucherService vs = new VoucherService();
//        List<Voucher> vouchers = voucherDao.getLoadVouchers();
        List<Voucher> vouchers;
        if (user == null) {
            vouchers = vs.getUseableVouchers();
        } else {
            vouchers = vs.getVouchersUserNotHave(user.getUid());
        }
        request.setAttribute("vouchers", vouchers);


        List<Product> hotProducts = productService.getHotProducts();
        List<Product> valiProducts = productService.getValiProducts();
        List<Product> baloProducts = productService.getBaloProducts();

        request.setAttribute("hotProducts", hotProducts);
        request.setAttribute("valiProducts", valiProducts);
        request.setAttribute("baloProducts", baloProducts);

        request.getRequestDispatcher("trang_chu/trang_chu.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}