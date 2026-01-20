package org.example.projectweb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private int pid;
    private String name;
    private String producer;
    private String type;
    private String material;
    private String style;
    private String description;
    private String status;

    private List<ProductVariant> variants;
    private List<ImageProduct> images;

    public Product() {

    }

    public Product(int pid, String name, String producer, String type, String material, String style, String description, String status) {
        this.pid = pid;
        this.name = name;
        this.producer = producer;
        this.type = type;
        this.material = material;
        this.style = style;
        this.description = description;
        this.status = status;

        variants = new ArrayList<>();
        images = new ArrayList<>();
    }

    /**
     * Getters
     * @return
     */
    public int getPid() {
        return this.pid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProducer() {
        return producer;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public String getStyle() {
        return style;
    }

    public String getStatus() {
        return status;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public List<ImageProduct> getImages() {
        return images;
    }

    /**
     * Setters
     * @param
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants;
    }

    public void setImages(List<ImageProduct> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", type='" + type + '\'' +
                ", material='" + material + '\'' +
                ", style='" + style + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
