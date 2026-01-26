package org.example.projectweb.dao;

import org.example.projectweb.model.ImageProduct;

import java.util.List;

public class ImageProductDao extends BaseDao {

    public void insertProductImage(int pid, String imagePath, boolean isMain) {
        get().useHandle(h ->
                h.createUpdate("""
                                    INSERT INTO image_product (pid, image, is_main)
                                    VALUES (:pid, :image, :isMain)
                                """)
                        .bind("pid", pid)
                        .bind("image", imagePath)
                        .bind("isMain", isMain)
                        .execute()
        );
    }

    public List<ImageProduct> getImagesByProductId(int productId) {
        return get().withHandle(h -> h.createQuery("select image from image_product where pid = :pid")
                .bind("pid", productId)
                .mapToBean(ImageProduct.class).list());

    }

    public List<ImageProduct> getImagesByProductIdFroAdmin(int productId) {
        return get().withHandle(h -> h.createQuery("select pid, image, is_main from image_product where pid = :pid")
                .bind("pid", productId)
                .mapToBean(ImageProduct.class).list());

    }

    public void deleteImagesByPid(int pid) {
        get().withHandle(h -> h.createUpdate("DELETE FROM image_product WHERE pid = :pid")
                        .bind("pid", pid).execute()
        );
    }

    public ImageProduct getMainImageByProductId(int productId) {
        return get().withHandle(h -> h.createQuery("select image from image_product where pid = :pid and is_main = 1")
                .bind("pid", productId)
                .mapToBean(ImageProduct.class).first());
    }

    /**
     * Wishlist
     */
    public List<ImageProduct> getMainImgsForWishlist(int userId) {
        return get().withHandle(h -> h.createQuery("select ip.image from wishlist wl join product p on wl.pid = p.pid " +
                        "join image_product ip on ip.pid = p.pid where wl.uid = :uid and is_main = 1")
                .bind("uid", userId)
                .mapToBean(ImageProduct.class).list());
    }
}
