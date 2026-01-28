package org.example.projectweb.model;

import java.io.Serializable;

public class ReportStatistics implements Serializable {
    private int totalOrders;
    private int completedOrders;
    private String bestSellingProductName;
    private int totalProductsSold;
    private double monthlyRevenue;
    private int totalCustomers;

    public ReportStatistics() {}

    // ===== Getter & Setter =====
    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(int completedOrders) {
        this.completedOrders = completedOrders;
    }

    public String getBestSellingProductName() {
        return bestSellingProductName;
    }

    public void setBestSellingProductName(String bestSellingProductName) {
        this.bestSellingProductName = bestSellingProductName;
    }

    public int getTotalProductsSold() {
        return totalProductsSold;
    }

    public void setTotalProductsSold(int totalProductsSold) {
        this.totalProductsSold = totalProductsSold;
    }

    public double getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(double monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
}
