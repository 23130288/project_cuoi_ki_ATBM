package org.example.projectweb.controller.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.projectweb.service.OrderService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "DownloadOrderContent", value = "/DownloadOrderContent")
public class DownloadOrderContent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int oid = Integer.parseInt(request.getParameter("oid"));
        String fileName = "Order#" + oid + ".txt";

        OrderService os = new OrderService();
        String content = os.getOrderContents(oid);

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

