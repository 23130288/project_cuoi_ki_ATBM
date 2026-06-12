package org.example.projectweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;
import org.example.projectweb.model.PublicKeyModel;
import org.example.projectweb.model.User;
import org.example.projectweb.model.VoucherUser;
import org.example.projectweb.service.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "CheckOutController", value = "/checkout")
@MultipartConfig
public class CheckOutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Cart c = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (c == null || c.isEmpty()) {
            response.sendRedirect("cart");
            return;
        }
        String fileName = request.getParameter("fileName");
        String description = request.getParameter("note");

        VoucherService vs = new VoucherService();
        OrderService os = new OrderService();

        VoucherUser v = c.getVoucher();
        Integer uvid = null;
        if (v != null) {
            vs.setApplicable(v.getUvid(), 0);
            uvid = v.getUvid();
        }

        // verify signature
        saveFileOnProject(c, fileName);
        DataInputStream dis = new DataInputStream(new BufferedInputStream(request.getPart("sigFile").getInputStream()));
        String signature = dis.readUTF();
        PublicKeyModel publicKeyModel = new PublicKeyService().getPublicKeyByUid(user.getUid());
        try {
            String tempFilePath = getServletContext().getRealPath("/files") + "\\" + fileName;
            boolean valid = new SignService().verifySign(tempFilePath, signature, publicKeyModel.getPublicKeyStr());
            File file = new File(tempFilePath);
            if (file.exists()) {
                file.delete();
            }
            if (!valid) {
                response.getWriter().print("{"
                        + "\"success\":false"
                        + "}");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // create order
        int oid = os.createOrder(user.getUid(), uvid, description, signature, publicKeyModel.getPkId());
        os.createOrderDetails(oid, c);

        c.removeAll();
        c.setVoucher(null);
        response.getWriter().print("{"
                + "\"success\":true,"
                + "\"redirectUrl\":\"show-order?oid=" + oid + "\""
                + "}");
    }

    private void saveFileOnProject(Cart c, String fileName) throws IOException {
        // save file on the project
        String uploadDir = getServletContext().getRealPath("/files");
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir + "\\" + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(c.getContents().getBytes(StandardCharsets.UTF_8));
        fos.close();
    }
}

