package org.example.projectweb.controller.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.model.PublicKeyModel;
import org.example.projectweb.model.User;
import org.example.projectweb.service.HashService;
import org.example.projectweb.service.OrderService;
import org.example.projectweb.service.PublicKeyService;
import org.example.projectweb.service.SignService;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

@WebServlet(name = "VerifyOrder", value = "/verify-order")
@MultipartConfig
public class VerifyOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int oid = Integer.parseInt(request.getParameter("oid"));

        OrderService os = new OrderService();
        HashService hs = new HashService();
        String hash = hs.hashMd5(os.getOrderContents(oid));

        // verify signature
        DataInputStream dis = new DataInputStream(new BufferedInputStream(request.getPart("sigFile").getInputStream()));
        String signature = dis.readUTF();
        PublicKeyModel publicKeyModel = new PublicKeyService().getPublicKeyByUid(user.getUid());
        try {
            boolean valid = new SignService().verifySign(hash, signature, publicKeyModel.getPublicKeyStr());
            if (!valid) {
                response.getWriter().print("{"
                        + "\"success\":false"
                        + "}");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        os.updateSignature(oid, signature, publicKeyModel.getPkId());
        os.updateOrderStatus(oid, "delivering");
        response.getWriter().print("{"
                + "\"success\":true,"
                + "\"redirectUrl\":\"show-order?oid=" + oid + "\""
                + "}");
    }
}

