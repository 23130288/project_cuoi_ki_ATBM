package org.example.projectweb.cart;

import org.example.projectweb.model.Product;
import org.example.projectweb.model.ProductVariant;
import org.example.projectweb.model.Voucher;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Cart implements Serializable {
    private Map<Integer, CartItem> data;
    private Voucher voucher;

    public Cart() {
        data = new HashMap<>();
        voucher = null;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public String getDiscountStr() {
        if (voucher == null) return 0 + " đ";
        double discount = voucher.getDiscount();
        if (discount < 1 && discount > 0)
            return discount * 100 + " %";
        return discount * 100 + " đ";
    }

    public CartItem getItemByPid(int pid) {
        return data.get(pid);
    }

    public List<CartItem> getList() {
        return new ArrayList<>(data.values());
    }

    public void addProduct(Product p, ProductVariant pv, String mainImg, int quantity) {
        if (data.containsKey(p.getPid()))
            data.get(p.getPid()).upQuantity(quantity);
        else {
            CartItem item = new CartItem(p, pv, pv.getPrice(), mainImg, quantity);
            data.put(p.getPid(), item);
        }
    }

    public CartItem deleteProduct(int pid) {
        return data.remove(pid);
    }

    public List<CartItem> removeAll() {
        Collection<CartItem> values = data.values();
        data.clear();
        return new ArrayList<>(values);
    }

    public int getTotalQuantity() {
        AtomicInteger total = new AtomicInteger();
        data.values().stream().forEach(p -> {
            total.addAndGet(p.getQuantity());
        });
        return total.get();
    }

    public double getItemTotalPrice(int pid) {
        CartItem item = data.get(pid);
        return item.getPrice() * item.getQuantity();
    }

    public double getTotalPrice() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().stream().forEach(p -> total.updateAndGet(v -> (v + (p.getQuantity() * p.getPrice()))));
        return total.get();
    }

    public double getFinalPrice() {
        double total = getTotalPrice();
        if (voucher == null)
            return total;

        double discount = voucher.getDiscount();
        double condition = voucher.getCondition();
        if (discount < 1 && discount > 0)
            return total * (1 - discount);
        return total - discount;
    }

    public boolean updateVariant(int pid, ProductVariant pv) {
        if (data.containsKey(pid)) {
            CartItem item = getItemByPid(pid);
            item.setProductVariant(pv);
            item.setPrice(pv.getPrice());
            return true;
        }
        return false;
    }

    public int updateQuantity(int pid, int delta) {
        CartItem item = data.get(pid);
        if (item == null) return 0;
        return item.updateQuantity(delta);
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
