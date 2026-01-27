package org.example.projectweb.controller.admincontroller.ProductVariant;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projectweb.model.ProductVariant;
import org.example.projectweb.model.User;
import org.example.projectweb.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SreachProductVariant", value = "/admin/Product_Variant_Sreach")
public class SreachProductVariant extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
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

        String name = request.getParameter("keyword");
        ProductService productService = new ProductService();
        List<ProductVariant> ProductVariants = productService.searchVariantsByProductName(name);
        Map<Integer, String> productNameMap = productService.getProductNameMaplike(name);

        JsonArray jsonArray = new JsonArray();

        for (ProductVariant pv : ProductVariants) {
            JsonObject obj = (JsonObject) JsonParser.parseString(new Gson().toJson(pv));
            obj.addProperty("productName", productNameMap.get(pv.getPid()));
            jsonArray.add(obj);
        }

        response.getWriter().write(jsonArray.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}