package org.example.projectweb.service;

import jakarta.servlet.http.Part;
import org.example.projectweb.dao.ImageProductDao;
import org.example.projectweb.dao.ProductDao;
import org.example.projectweb.dao.ProductVariantDao;
import org.example.projectweb.model.ImageProduct;
import org.example.projectweb.model.Product;
import org.example.projectweb.model.ProductVariant;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService {
    private ProductDao pDao = new ProductDao();
    private ProductVariantDao pvDao = new ProductVariantDao();
    private ImageProductDao ipDao = new ImageProductDao();
    private NotificationService ns = new NotificationService();

    public boolean addProduct(String name, String type, String style, String material, String producer, String status, String description, List<Part> imageParts) {
        if (pDao.getProductByName(name) != null) {
            return false;
        }
        int pid = pDao.insertProduct(name, type, style, material, producer, status, description);

        // Xử lý ảnh
        int index = 1;
        for (Part part : imageParts) {
            if (part == null || part.getSize() == 0) continue;
            boolean isMain = (index == 1);

            // Lấy đuôi file
            String ext = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            ext = ext.substring(ext.lastIndexOf("."));

            // tên file: pid_index_isMain.jpg
            String fileName = pid + "_" + index + "_" + (isMain ? 1 : 0) + ext;
            String relativePath = "/images/product/" + pid + "/" + fileName;
            String realPath = getUploadDir(pid) + File.separator + fileName;

            try {
                part.write(realPath);
                // Lưu từng ảnh
                ipDao.insertProductImage(pid, relativePath, isMain);
                index++;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        // Tạo thông báo
        ns.createGlobalNotification("All", "Sản phẩm mới", "Sản phẩm \"" + name + "\" vừa ra mắt. Vào mua ngay thôi!");

        return true;
    }

    private String getUploadDir(int pid) {
        String BASE_UPLOAD_DIR = "D:/mio/projectWeb/src/main/webapp/images/product";
        String productDir = BASE_UPLOAD_DIR + File.separator + pid;

        File dir = new File(productDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return productDir;
    }

    public boolean addProductVariant(int pid, String size, String color, double price, int quantity) {
        // Check ProductVariant đã tồn tại
        if (pvDao.findVariant(pid, size, color) != null) {
            return false;
        }

        // Tạo ProductVariant mới
        pvDao.addProductVariant(pid, size, color, price, quantity);
        // Tạo thông báo
        ns.createGlobalNotification("All", "Biến thể mới", "Sản phẩm \"" + pid + "\" vừa ra mắt biến thể mới. Vào mua ngay thôi!");
        return true;
    }

    public List<Product> getAllProducts() {
        return pDao.getListProduct();
    }

    public List<Product> getAllProductsNameLike(String name) {
        return pDao.getAllProductNameLike(name);
    }

    public List<ProductVariant> searchVariantsByProductName(String name) {
        return pvDao.searchVariantsByProductName(name);
    }

    public List<ProductVariant> getAllProductsVariants() {
        List<Integer> lsPID = pDao.getListProductID();

        List<ProductVariant> re = new ArrayList<>();
        for (Integer pid : lsPID) {
            List<ProductVariant> tmp = getVariantsByPid(pid);
            if (tmp != null) re.addAll(tmp);
        }
        return re;
    }

    public Map<Integer, String> getProductNameMap() {
        return pDao.getProductNameMap();
    }

    public Map<Integer, String> getProductNameMaplike(String name) {
        return pDao.getProductNameMapLike(name);
    }

    public List<Product> getAllSreachProduct(String name) {
        return pDao.getAllProductNameLike(name);
    }

    public Product getProductById(int productId) {
        return pDao.getProductById(productId);
    }

    public ProductVariant getVariantByPvid(int pvid) {
        return pvDao.getVariantByPvid(pvid);
    }

    public List<ProductVariant> getVariantsByPid(int productId) {
        return pvDao.getVariantsForAdmin(productId);
    }

    public List<ImageProduct> getImgsByPid(int productId) {
        return ipDao.getImagesByProductId(productId);
    }

    public ImageProduct getMainImg(int productId) {
        return ipDao.getMainImageByProductId(productId);
    }

    public Product getProductDetail(int productId) {
        Product p = pDao.getProductById(productId);
        if (p == null) return null;
        p.setVariants(pvDao.getVariantsByProductId(productId));
        p.setImages(ipDao.getImagesByProductId(productId));
        return p;
    }

    public List<Product> searchInFilter(
        String producer,
        String category,
        String color,
        String size,
        String minPrice,
        String maxPrice,
        String sort
) {

    Double min = (minPrice == null || minPrice.isBlank())
            ? null : Double.parseDouble(minPrice);

    Double max = (maxPrice == null || maxPrice.isBlank())
            ? null : Double.parseDouble(maxPrice);

    List<Product> products = pDao.searchByFilter(producer, category, color, size, min, max, sort );

    // LOAD VARIANT + IMAGE (CỰC KỲ QUAN TRỌNG)
    for (Product p : products) {
        p.setVariants(pvDao.getVariantsByProductId(p.getPid()));
        p.setImages(ipDao.getImagesByProductId(p.getPid()));
    }

    return products;
}


    public List<Product> getHotProducts() {
        List<Product> list = pDao.getHotProducts();

        for (Product p : list) {
            p.setVariants(
                    pvDao.getVariantsByProductId(p.getPid())
            );
//            p.setImages(
//                ipDao.getImagesByProductId(p.getPid())
//        );
            List<ImageProduct> images = ipDao.getImagesByProductId(p.getPid());
            p.setImages(images);
        }

        return list;
    }

    public List<Product> getValiProducts() {
        List<Product> list = pDao.getValiProducts();

        for (Product p : list) {
            p.setVariants(
                    pvDao.getVariantsByProductId(p.getPid())
            );
//            p.setImages(
//                    ipDao.getImagesByProductId(p.getPid())
//            );
            List<ImageProduct> images = ipDao.getImagesByProductId(p.getPid());
            p.setImages(images);
        }
        return list;
    }

    public List<Product> getBaloProducts() {
        List<Product> list = pDao.getBaloProducts();

        for (Product p : list) {
            p.setVariants(
                    pvDao.getVariantsByProductId(p.getPid())
            );
//            p.setImages(
//                ipDao.getImagesByProductId(p.getPid())
//        );
            List<ImageProduct> images = ipDao.getImagesByProductId(p.getPid());
            p.setImages(images);


        }
        return list;
    }

    public void insertBatch(List<Product> products) {
        for (Product p : products) {
            // check tồn tại
            if (pDao.getProductByName(p.getName()) != null) {
                System.out.println("Bỏ qua dòng sản phẩm " + p.getName() + " do đã tồn tại.");
                continue;
            }

            pDao.addProduct(p.getName(), p.getType(), p.getStyle(), p.getMaterial(), p.getProducer(), p.getStatus(), p.getDescription());
        }
    }

    public void addProductVariant(List<ProductVariant> productVariants) {
        for (ProductVariant pv : productVariants) {
            // check đã có sản phẩm trước khi thêm biến thể
            if (pDao.getProductById(pv.getPid()) == null) {
                System.out.println("Bỏ qua dòng biến thể của sp_" + pv.getPid() + " size: " + pv.getSize() + " color:" + pv.getColor() + " do sản phẩm ko tồn tại.");
                continue;
            }
            // check tồn tại
            if (pvDao.findVariant(pv.getPid(), pv.getSize(), pv.getColor()) != null) {
                System.out.println("Bỏ qua dòng biến thể của sp_" + pv.getPid() + " size: " + pv.getSize() + " color:" + pv.getColor() + " do đã tồn tại.");
                continue;
            }
            pvDao.addProductVariant(pv.getPid(), pv.getSize(), pv.getColor(), pv.getPrice(), pv.getQuantity());
        }
    }
}
