package org.example.projectweb.controller.admincontroller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.apache.poi.ss.usermodel.*;
import org.example.projectweb.model.Product;
import org.example.projectweb.model.User;
import org.example.projectweb.service.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddExcelProduct", value = "/admin/product_excel_add")
@MultipartConfig
public class AddProductExcel extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //check thẩm quyền
        HttpSession session = request.getSession();
        User check = (User) session.getAttribute("user");
        session.setAttribute("user", check);
        if (check == null) {
            request.getRequestDispatcher("/dang_nhap").forward(request, response);
            return;
        }

        if (!"admin".equalsIgnoreCase(check.getRole())) {
            request.getRequestDispatcher("/tham_quyen").forward(request, response);
            return;
        }

        response.setContentType("application/json;charset=UTF-8");

        try {
            // Lấy file Excel
            Part filePart = request.getPart("excelFile");
            if (filePart == null || filePart.getSize() == 0) {
                response.getWriter().write(
                        "{\"success\":false,\"message\":\"Không nhận được file Excel\"}"
                );
                return;
            }

            // Đọc Excel
            InputStream is = filePart.getInputStream();
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            List<Product> products = new ArrayList<>();

            // Parse từng dòng
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Product p = new Product();
                p.setName(getCellString(row.getCell(0)));
                p.setType(getCellString(row.getCell(1)));
                p.setStyle(getCellString(row.getCell(2)));
                p.setMaterial(getCellString(row.getCell(3)));
                p.setProducer(getCellString(row.getCell(4)));
                p.setStatus(getCellString(row.getCell(5)));
                p.setDescription(getCellString(row.getCell(6)));

                if (p.getName().isEmpty() || p.getType().isEmpty() ||p.getStyle().isEmpty() || p.getMaterial().isEmpty() ||p.getProducer().isEmpty() || p.getStatus().isEmpty()
                ) {
                    System.out.println("Bỏ qua dòng " + (i + 1) + " do thiếu dữ liệu bắt buộc");
                    continue;
                }
                products.add(p);
            }

            // 4. Insert DB
            ProductService ps = new ProductService();
            ps.insertBatch(products);

            // 5. Trả kết quả
            response.getWriter().write(
                    "{\"success\":true,\"message\":\"Import thành công " + products.size() + " sản phẩm\"}"
            );

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Lỗi xử lý file Excel\"}"
            );
        }
    }

    private String getCellString(Cell cell) {
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }
}