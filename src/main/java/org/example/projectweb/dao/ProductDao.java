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

    public int insertProduct(
            String name,
            String type,
            String style,
            String material,
            String producer,
            String status,
            String description
    ) {
        return get().withHandle(h ->
                h.createUpdate("""
                INSERT INTO product (name, type, style, material, producer, status, description)
                VALUES (:name, :type, :style, :material, :producer, :status, :description)
            """)
                        .bind("name", name)
                        .bind("type", type)
                        .bind("style", style)
                        .bind("material", material)
                        .bind("producer", producer)
                        .bind("status", status)
                        .bind("description", description)
                        .executeAndReturnGeneratedKeys("pid")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public List<Product> getListProduct() {
        return get().withHandle(h -> h.createQuery("select pid, name, producer, type, material, style, status from product")
                .mapToBean(Product.class).list());
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

    public List<Product> getAllProducts() {
        return get().withHandle(h -> h.createQuery(" select pid, name, producer, type, material, style, description, status from product ")
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> getProductByProducer(List<Product> list, String producer) {
        return get().withHandle(h -> h.createQuery(" select pid, name, producer, type, material, style, description, status from product where producer = :producer ")
                .bind("producer", producer)
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> getProductByCategory(List<Product> list, String category) {
        return get().withHandle(h -> h.createQuery(" select pid, name, producer, type, material, style, description, status from product where type = :category ")
                .bind("category", category)
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> getProductByColor(List<Product> list, String color) {
        return get().withHandle(h -> h.createQuery(" select distinct p.pid, p.name, p.producer, p.type, p.material, p.style, p.description, p.status from product p join product_variant v on p.pid = v.pid WHERE v.color = :color ")
                .bind("color", color)
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> getProductBySize(List<Product> list, String size) {
        return get().withHandle(h -> h.createQuery(" select distinct p.pid, p.name, p.producer, p.type, p.material, p.style, p.description, p.status from product p join product_variant v on p.pid = v.pid where v.size = :size ")
                .bind("size", size)
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> getProductByPrice(double min, double max) {
        return get().withHandle(h -> h.createQuery(" select distinct p.pid, p.name, p.producer, p.type, p.material, p.style, p.description, p.status from product p join product_variant v on p.pid = v.pid where v.price >= :min and v.price <= :max ")
                .bind("min", min)
                .bind("max", max)
                .mapToBean(Product.class)
                .list()
        );

    }

    public List<Product> sortByPrice(boolean asc) {
        String order = asc ? "ASC" : "DESC";
        return get().withHandle(h -> h.createQuery(" select distinct p.pid, p.name, p.producer, p.type, p.material, p.style, p.description, p.status from product p join product_variant v on p.pid = v.pid order by v.price " + order)
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> sortByName(boolean asc) {
        String order = asc ? "ASC" : "DESC";
        return get().withHandle(h -> h.createQuery(" select pid, name, producer, type, material, style, description, status from product order by name " + order)
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> sortByBestSeller() {
        return get().withHandle(h -> h.createQuery(" select p.pid, p.name, p.producer, p.type, p.material, p.style, p.description, p.status, sum(od.quantity) as totalSold from product p join order_detail od on p.pid = od.pid group by p.pid order by totalSold desc ")
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> sortByRating() {
        return get().withHandle(h -> h.createQuery(" select p.pid, p.name, p.producer, p.type, p.material, p.style, p.description, p.status, avg(r.rating) as avgRating from product p join review r on p.pid = r.pid group by p.pid order by avgRating desc ")
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> sortByHot() {
        return get().withHandle(h -> h.createQuery(" select pid, name, producer, type, material, style, description, status from product where status = 'HOT' order by pid desc ")
                .mapToBean(Product.class)
                .list()
        );
    }

    public List<Product> getProductBySort(List<Product> products, String sort) {

        if (sort == null || sort.isEmpty()) {
            return getAllProducts();
        }

        switch (sort) {
            case "price_Asc":
                return sortByPrice(true);

            case "price_Desc":
                return sortByPrice(false);

            case "nameA-Z":
                return sortByName(true);

            case "nameZ-A":
                return sortByName(false);

            case "bestSeller":
                return sortByBestSeller();

            case "rating":
                return sortByRating();

            case "hot":
                return sortByHot();

            default:
                return getAllProducts();
        }
    }
    public List<Product> getHotProducts() {
        return get().withHandle(h ->
                h.createQuery("SELECT pid, name, producer, type, material, style, description, status FROM product WHERE LOWER(status) = 'hot'")
                        .mapToBean(Product.class)
                        .list()
        );
    }
    public List<Product> getValiProducts() {
        return get().withHandle(h ->
                h.createQuery("SELECT pid, name, producer, type, material, style, description, status FROM product WHERE LOWER(type) = 'vali'")
                        .mapToBean(Product.class)
                        .list()
        );
    }
    public List<Product> getBaloProducts() {
        return get().withHandle(h ->
                h.createQuery("SELECT pid, name, producer, type, material, style, description, status FROM product WHERE LOWER(type) = 'balo'")
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
