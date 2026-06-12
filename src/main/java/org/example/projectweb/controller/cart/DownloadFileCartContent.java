package org.example.projectweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.cart.Cart;

import java.io.*;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "DownloadFileCartContent", value = "/download_file_cart_content")
public class DownloadFileCartContent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart c = (Cart) session.getAttribute("cart");

        String fileName = request.getParameter("fileName");
        String content = c.getContents();

        // download file
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        InputStream in = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        OutputStream out = response.getOutputStream();
        int i;
        byte[] read = new byte[1024];

        while ((i = in.read(read)) != -1) {
            out.write(read, 0, i);
        }
        out.flush();
    }
}

