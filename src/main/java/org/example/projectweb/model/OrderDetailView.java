package org.example.projectweb.model;

public class OrderDetailView {
    private int pvid;
    private String name;
    private String image;
    private String producer;
    private String type;
    private String material;
    private String style;
    private String color;
    private String size;
    private double price;
    private int quantity;

    public OrderDetailView() {
    }

    public OrderDetailView(int pvid, String name, String image, String producer, String type, String material, String style, String color, String size, double price, int quantity) {
        this.pvid = pvid;
        this.name = name;
        this.image = image;
        this.producer = producer;
        this.type = type;
        this.material = material;
        this.style = style;
        this.color = color;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * GETTERS
     */
    public int getPvid() {
        return pvid;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
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

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return this.price * this.quantity;
    }

    /**
     * SETTERS
     */
    public void setPvid(int pvid) {
        this.pvid = pvid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Product: " + name + "\n" +
                "\tproducer: " + producer + "\n" +
                "\ttype: " + type + "\n" +
                "\tmaterial: " + material + "\n" +
                "\tstyle: " + style + "\n" +
                "\tproductVariant: (size: " + size + ", color: " + color + ")\n" +
                "\tprice: " + price + "\n" +
                "\tquantity: " + quantity;
    }
}
