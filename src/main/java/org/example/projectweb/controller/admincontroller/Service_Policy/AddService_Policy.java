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
        boolean inList = false;

        try (InputStream is = filePart.getInputStream();
             XWPFDocument doc = new XWPFDocument(is)) {

            for (XWPFParagraph p : doc.getParagraphs()) {
                String text = p.getText().trim();
                if (text.isEmpty()) continue;
                String style = p.getStyle();

                // 1. Heading
                if (style != null && style.startsWith("Heading") || text.matches("^\\d+\\.\\s+.*")) {
                    if (inList) {
                        content.append("</ul>");
                        inList = false;
                    }
                    content.append("<h3>").append(text).append("</h3>");
                }

                // 2. Dòng kết thúc bằng ":" → mở danh sách
                else if (text.endsWith(":")) {
                    if (inList) {
                        content.append("</ul>");
                    }
                    content.append("<p>").append(text).append("</p>");
                    content.append("<ul>");
                    inList = true;
                }

                // 3. Item của danh sách
                else if (inList) {
                    content.append("<li>").append(text).append("</li>");
                }

                // 4. Paragraph thường
                else {
                    content.append("<p>").append(text).append("</p>");
                }
            }

            // Đóng ul nếu file kết thúc khi đang trong list
            if (inList) {
                content.append("</ul>");
            }
        }


        if (sps.addServicePolicy(title, content.toString(), isService))
            response.getWriter().write("{\"success\":true}");
        else
            response.getWriter().write("{\"success\":false,\"message\":\"Lỗi xử lý dữ liệu\"}");
    }
}