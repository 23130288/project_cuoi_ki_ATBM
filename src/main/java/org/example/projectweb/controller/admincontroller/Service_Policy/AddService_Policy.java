package org.example.projectweb.controller.admincontroller.Service_Policy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.example.projectweb.service.Service_PolicyService;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "AddService_Policy", value = "/admin/Service_Policy_add")
@MultipartConfig
public class AddService_Policy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    final Service_PolicyService sps = new Service_PolicyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        int type = Integer.parseInt(request.getParameter("type"));
        Part filePart = request.getPart("contentFile");
        boolean isService;
        isService = type == 1;

        if (filePart == null || filePart.getSize() == 0) {
            response.getWriter().write("{\"success\":false,\"message\":\"Chưa chọn file Word\"}");
            return;
        }
        StringBuilder content = new StringBuilder();

        try (InputStream is = filePart.getInputStream();
             XWPFDocument doc = new XWPFDocument(is)) {

            for (XWPFParagraph p : doc.getParagraphs()) {
                content.append(p.getText()).append("\n");
            }
        }

        if (sps.addServicePolicy(title, content.toString(), isService))
            response.getWriter().write("{\"success\":true}");
        else
            response.getWriter().write("{\"success\":false,\"message\":\"Lỗi xử lý dữ liệu\"}");
    }
}