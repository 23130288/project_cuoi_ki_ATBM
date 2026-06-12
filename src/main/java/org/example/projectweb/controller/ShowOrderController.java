package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;
import org.example.projectweb.model.Order;
import org.example.projectweb.model.OrderDetailView;
import org.example.projectweb.model.PublicKeyModel;
import org.example.projectweb.model.User;
import org.example.projectweb.service.OrderService;
import org.example.projectweb.service.PublicKeyService;
import org.example.projectweb.service.SignService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "ShowOrderController", value = "/show-order")
public class ShowOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("dang_nhap");
            return;
        }

        int oid = Integer.parseInt(request.getParameter("oid"));
        OrderService os = new OrderService();

        Order order = os.getOrderByOid(oid);
        List<OrderDetailView> orderDetails = os.getOrderDetailViewByOid(oid);

        // verify signature
        String fileName = "document.txt";
        saveFileOnProject(order, orderDetails, fileName);
        String signature = order.getSignatureHash();
        PublicKeyModel publicKeyModel = new PublicKeyService().getPublicKeyByPkId(order.getPkId());
        System.out.println(order.getSignatureHash());
        System.out.println(publicKeyModel.getPublicKeyStr());
        try {
            String tempFilePath = getServletContext().getRealPath("/files") + "\\" + fileName;
            boolean valid = new SignService().verifySign(tempFilePath, signature, publicKeyModel.getPublicKeyStr());
            File file = new File(tempFilePath);
            if (file.exists()) {
                file.delete();
            }
            if (!valid) {
                response.sendRedirect("trangChu");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("user", user);
        request.setAttribute("order", order);
        request.setAttribute("orderDetails", orderDetails);

        request.getRequestDispatcher("ct_Order/ct_Order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String getOrderContents(Order order, List<OrderDetailView> orderDetails) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < orderDetails.size(); i++) {
            res.append(i + 1).append(". ").append(orderDetails.get(i).toString()).append("\n\n");
        }
        res.append("Total price: ").append(order.getTotalPrice()).append("\n");
        if (order.getVoucher().getName() != null)
            res.append(order.getVoucher()).append("\n");
        else res.append("Voucher: null").append("\n");
        res.append("Final price: ").append(order.getFinalPrice());
        return res.toString();
    }

    private void saveFileOnProject(Order order, List<OrderDetailView> orderDetails, String fileName) throws IOException {
        // save file on the project
        String uploadDir = getServletContext().getRealPath("/files");
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir + "\\" + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getOrderContents(order, orderDetails).getBytes(StandardCharsets.UTF_8));
        fos.close();
    }
}

