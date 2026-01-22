package org.example.projectweb.dao;

import org.example.projectweb.model.ProductVariant;

import java.util.List;

public class ProductVariantDao extends BaseDao {
    public void addProductVariant(int pid, String size, String color, double price, int quantity) {
        get().useHandle(h -> h.createUpdate("INSERT INTO product_variant (pid, size, color, price, quantity) VALUES (:pid, :size, :color, :price, :quantity)")
                .bind("pid", pid).bind("size", size).bind("color", color).bind("price", price).bind("quantity", quantity).execute()
        );
    }

    public ProductVariant findVariant(int pid, String size, String color) {
        return get().withHandle(h -> h.createQuery("SELECT pvid FROM product_variant WHERE pid = :pid AND size = :size AND color = :color")
                .bind("pid", pid).bind("size", size).bind("color", color)
                .mapToBean(ProductVariant.class).findOne().orElse(null)
        );
    }

    public List<ProductVariant> getVariantsByProductId(int productId) {
        return get().withHandle(h -> h.createQuery("select size, color, price, quantity from product_variant where pid = :pid")
                .bind("pid", productId)
                .mapToBean(ProductVariant.class).list());
    }

    public List<ProductVariant> getVariantsForAdmin(int productId) {
        return get().withHandle(h -> h.createQuery("select pvid, pid, size, color, price, quantity from product_variant where pid = :pid")
                .bind("pid", productId)
                .mapToBean(ProductVariant.class).list());
    }

    public List<ProductVariant> searchVariantsByProductName(String name) {
        return get().withHandle(h -> h.createQuery("SELECT pv.pvid, pv.pid, pv.size, pv.color, pv.price, pv.quantity FROM product_variant pv JOIN product p ON pv.pid = p.pid WHERE LOWER(p.name) LIKE LOWER(:name)")
                .bind("name", "%" + name + "%")
                .mapToBean(ProductVariant.class)
                .list()
        );
    }

    public ProductVariant getVariantByPvid(int pvid) {
        return get().withHandle(h -> h.createQuery("select pvid, size, color, price, quantity from product_variant where pvid = :pvid")
                .bind("pvid", pvid)
                .mapToBean(ProductVariant.class).first());
    }


}
