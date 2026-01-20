package org.example.projectweb.controller.admincontroller.ProductVariant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.poi.ss.usermodel.*;
import org.example.projectweb.model.ProductVariant;
import org.example.projectweb.service.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddExcelProductVariant", value = "/admin/product_Variant_excel_add")
@MultipartConfig
public class AddProductVariantExcel extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

            List<ProductVariant> productVariants = new ArrayList<>();

            // Parse từng dòng
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                ProductVariant pv = new ProductVariant();

                if (isCellEmpty(row.getCell(0)) ||
                        isCellEmpty(row.getCell(1)) ||
                        isCellEmpty(row.getCell(2)) ||
                        isCellEmpty(row.getCell(3)) ||
                        isCellEmpty(row.getCell(4))) {

                    System.out.println("Bỏ qua dòng " + (i + 1) + " do thiếu dữ liệu bắt buộc");
                    continue;
                }

                pv.setPid((int) row.getCell(0).getNumericCellValue());
                pv.setSize(getCellString(row.getCell(1)));
                pv.setColor(getCellString(row.getCell(2)));
                pv.setPrice(row.getCell(3).getNumericCellValue());
                pv.setQuantity((int) row.getCell(4).getNumericCellValue());

                productVariants.add(pv);
            }

            // 4. Insert DB
            ProductService ps = new ProductService();
            ps.addProductVariant(productVariants);

            // 5. Trả kết quả
            response.getWriter().write(
                    "{\"success\":true,\"message\":\"Import thành công " + productVariants.size() + " sản phẩm\"}"
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

    private boolean isCellEmpty(Cell cell) {
        if (cell == null) return true;

        if (cell.getCellType() == CellType.BLANK) return true;

        return cell.getCellType() == CellType.STRING &&
                cell.getStringCellValue().trim().isEmpty();
    }
}