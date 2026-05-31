package org.example.projectweb.cart;

import org.example.projectweb.model.Product;
import org.example.projectweb.model.ProductVariant;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class CartItem implements Serializable {
    private Product product;
    private ProductVariant productVariant;
    private double price;
    private String mainImg;
    private int quantity;

    private Set<String> variantColors;
    private Set<String> variantSizes;

    public CartItem() {
    }

    public CartItem(Product product, ProductVariant productVariant, double price, String mainImg, int quantity) {
        this.product = product;
        this.productVariant = productVariant;
        this.price = price;
        this.mainImg = mainImg;
        this.quantity = quantity;

        this.variantColors = new TreeSet<>();
        this.variantSizes = new TreeSet<>();
        for (ProductVariant pv : product.getVariants()) {
            this.variantColors.add(pv.getColor());
            this.variantSizes.add(pv.getSize());
        }
    }

    /**
     * Getters
     */
    public void upQuantity(int quantity) {
        if (quantity <= 0) quantity = 1;
        this.quantity += quantity;
    }

    public Product getProduct() {
        return product;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public double getPrice() {
        return price;
    }

    public String getMainImg() {
        return mainImg;
    }

    public int getQuantity() {
        return quantity;
    }

    public Set<String> getVariantColors() {
        return variantColors;
    }

    public Set<String> getVariantSizes() {
        return variantSizes;
    }


    /**
     * Setters
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public int updateQuantity(int delta) {
        this.quantity += delta;
        if (this.quantity <= 0)
            this.quantity = 1;
        return this.quantity;
    }

    @Override
    public String toString() {
        return product + "\n" +
                "\t" + productVariant + "\n" +
                "\tprice: " + price + "\n" +
                "\tquantity: " + quantity;
    }
}
