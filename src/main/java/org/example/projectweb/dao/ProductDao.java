package org.example.projectweb.dao;

import org.example.projectweb.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDao extends BaseDao {

    static Map<Integer, Product> data = new HashMap<>();

    public void addProduct(String name, String type, String style, String material, String producer, String status, String description) {
        get().useHandle(h -> h.createUpdate("INSERT INTO product (name, type, style, material, producer, status, description) VALUES (:name, :type, :style, :material, :producer, :status, :description)")
                .bind("name", name).bind("type", type).bind("style", style).bind("material", material).bind("producer", producer).bind("status", status).bind("description", description).execute()
        );
    }

    public int insertProduct(String name, String type, String style, String material, String producer, String status, String description) {
        return get().withHandle(h -> h.createUpdate("""
                            INSERT INTO product (name, type, style, material, producer, status, description)
                            VALUES (:name, :type, :style, :material, :producer, :status, :description)
                        """)
                .bind("name", name).bind("type", type).bind("style", style).bind("material", material)
                .bind("producer", producer).bind("status", status).bind("description", description)
                .executeAndReturnGeneratedKeys("pid")
                .mapTo(Integer.class).one()
        );
    }

    public List<Product> getListProduct() {
        return get().withHandle(h -> h.createQuery("select pid, name, producer, type, material, style, status from product")
                .mapToBean(Product.class).list());
    }

    public List<Product> getListProductForAdmin() {
        return get().withHandle(h -> h.createQuery("SELECT p.pid, p.name, p.producer, p.type, p.material, p.style, p.status, COALESCE(SUM(pv.quantity), 0) AS TotlaProduct " +
                        "FROM product p LEFT JOIN product_variant pv ON p.pid = pv.pid " +
                        "GROUP BY p.pid, p.name, p.producer, p.type, p.material, p.style, p.status;")
                .mapToBean(Product.class).list());
    }

    public Product getProductForEdit(int pid) {
        return get().withHandle(h -> h.createQuery("select pid, name, producer, type, material, style, status, description from product Where pid = :pid")
                .bind("pid", pid)
                .mapToBean(Product.class).first());
    }

    public boolean updateProduct(int pid, String name, String type, String style, String material, String producer, String status, String description) {
        return get().withHandle(h ->
                h.createUpdate("UPDATE product " +
                                "SET name = :name, type = :type, style = :style, material = :material, producer = :producer, status = :status, description = :description " +
                                "WHERE pid = :pid;")
                        .bind("pid", pid).bind("name", name).bind("type", type).bind("style", style)
                        .bind("material", material).bind("producer", producer).bind("status", status)
                        .bind("description", description).execute() > 0
        );
    }

    public boolean updateProductVariant(int pvid, int price, int quantity) {
        return get().withHandle(h ->
                h.createUpdate("UPDATE product_variant SET price = :price, quantity = :quantity WHERE pvid = :pvid;")
                        .bind("pvid", pvid).bind("price", price).bind("quantity", quantity).execute() > 0
        );
    }
    public String getBestSellingProductName() {
        return get().withHandle(h ->
                h.createQuery("""
            SELECT p.name
            FROM order_detail od
            JOIN product_variant pv ON od.pvid = pv.pvid
            JOIN product p ON pv.pid = p.pid
            GROUP BY p.pid, p.name
            ORDER BY SUM(od.quantity) DESC
            LIMIT 1
        """)
                        .mapTo(String.class)
                        .findOne()
                        .orElse("Không có dữ liệu")
        );
    }

    public List<Integer> getListProductID() {
        return get().withHandle(h ->
                h.createQuery("select pid from product")
                        .mapTo(Integer.class).list()
        );
    }

    public Map<Integer, String> getProductNameMap() {
        return get().withHandle(h ->
                h.createQuery("SELECT pid, name FROM product")
                        .map((rs, ctx) -> Map.entry(rs.getInt("pid"), rs.getString("name")))
                        .list().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );
    }

    public Map<Integer, String> getProductNameMapLike(String name) {
        return get().withHandle(h ->
                h.createQuery("SELECT pid, name FROM product where LOWER(name) LIKE LOWER(:name)")
                        .bind("name", "%" + name + "%")
                        .map((rs, ctx) -> Map.entry(rs.getInt("pid"), rs.getString("name")))
                        .list().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );
    }

    public Product getProductById(int productId) {
        return get().withHandle(h -> h.createQuery("select pid, name, producer, type, material, style, description, status from product where pid = :pid")
                .bind("pid", productId)
                .mapToBean(Product.class).first());
    }

    public Product getProductByName(String name) {
        return get().withHandle(h -> h.createQuery("select pid, name, producer, type, material, style, description, status from product where name = :name")
                .bind("name", name)
                .mapToBean(Product.class).findFirst().orElse(null)
        );
    }

    //lấy tất cả sản phẩm có chứa cụm String được nhâp
    public List<Product> getAllProductNameLike(String name) {
        return get().withHandle(h -> h.createQuery(" select pid, name, producer, type, material, style, description, status from product WHERE LOWER(name) LIKE LOWER(:name)")
                .bind("name", "%" + name + "%")
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> searchByFilter(
            String producer,
            String category,
            String color,
            String size,
            Double minPrice,
            Double maxPrice,
            String sort
    ) {

        String sql = " SELECT DISTINCT p.pid, p.name, p.producer, p.type, p.material, p.style, p.description, p.status FROM product p JOIN product_variant v ON p.pid = v.pid WHERE 1 = 1 ";

        if (producer != null && !producer.isBlank()) {
            sql += " AND LOWER(p.producer) LIKE LOWER(:producer) ";
        }

        if (category != null && !category.isBlank()) {
            sql += " AND LOWER(p.type) = LOWER(:category) ";
        }

        if (color != null && !color.isBlank()) {
            sql += " AND LOWER(v.color) = LOWER(:color) ";
        }

        if (size != null && !size.isBlank()) {
            sql += " AND v.size = :size ";
        }

        if (minPrice != null) {
            sql += " AND v.price >= :minPrice ";
        }

        if (maxPrice != null) {
            sql += " AND v.price <= :maxPrice ";
        }

        if ("price_asc".equals(sort)) {
            sql += " ORDER BY v.price ASC ";
        } else if ("price_desc".equals(sort)) {
            sql += " ORDER BY v.price DESC ";
        } else if ("name_asc".equals(sort)) {
            sql += " ORDER BY p.name ASC ";
        } else if ("name_desc".equals(sort)) {
            sql += " ORDER BY p.name DESC ";
        }

        String finalSql = sql;
        return get().withHandle(h -> {
            var query = h.createQuery(finalSql);

            if (producer != null && !producer.isBlank())
                query.bind("producer", "%" + producer + "%");

            if (category != null && !category.isBlank())
                query.bind("category", category);

            if (color != null && !color.isBlank())
                query.bind("color", color);

            if (size != null && !size.isBlank())
                query.bind("size", size);

            if (minPrice != null)
                query.bind("minPrice", minPrice);

            if (maxPrice != null)
                query.bind("maxPrice", maxPrice);

            return query.mapToBean(Product.class).list();
        });
    }

    public List<Product> getHotProducts() {
        return get().withHandle(h ->
                h.createQuery("SELECT pid, name, producer, type, material, style, description, status FROM product WHERE LOWER(status) = 'hot' LIMIT 12")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getValiProducts() {
        return get().withHandle(h ->
                h.createQuery("SELECT pid, name, producer, type, material, style, description, status FROM product WHERE LOWER(type) = 'vali' LIMIT 12")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getBaloProducts() {
        return get().withHandle(h ->
                h.createQuery("SELECT pid, name, producer, type, material, style, description, status FROM product WHERE LOWER(type) = 'balo' LIMIT 12")
                        .mapToBean(Product.class)
                        .list()
        );
    }


    public void updateStatus(int pid, String sta) {
        get().useHandle(h -> h.createUpdate("UPDATE product SET status = :sta WHERE pid = :pid")
                .bind("pid", pid).bind("sta", sta).execute()
        );
    }

    /**
     * WISHLIST
     */
    public List<Product> getProductsForWishlist(int userId) {
        return get().withHandle(h -> h.createQuery("select p.pid, p.name from product p join wishlist wl on p.pid = wl.pid " +
                        "where wl.uid = :uid")
                .bind("uid", userId)
                .mapToBean(Product.class).list());
    }

    public List<Integer> getQuantitesForWishlist(int userId) {
        return get().withHandle(h -> h.createQuery("""
                        select SUM(pv.quantity)
                        from wishlist wl join product_variant pv on wl.pid = wl.pid
                        where uid = :uid
                        group by wl.pid
                        """)
                .bind("uid", userId).mapTo(Integer.class).list());
    }
}
