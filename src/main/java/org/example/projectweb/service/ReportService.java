package org.example.projectweb.service;

import org.example.projectweb.dao.OrderDao;
import org.example.projectweb.dao.ProductDao;
import org.example.projectweb.dao.UserDao;
import org.example.projectweb.model.ReportStatistics;

import java.time.LocalDate;

public class ReportService {

    final OrderDao orderDao = new OrderDao();
    final ProductDao productDao = new ProductDao();
    final UserDao userDao = new UserDao();

    public ReportStatistics getStatistics() {

        LocalDate now = LocalDate.now();

        ReportStatistics stats = new ReportStatistics();
        stats.setTotalOrders(orderDao.countAllOrders());
        stats.setCompletedOrders(orderDao.countCompletedOrders());
        stats.setMonthlyRevenue(orderDao.getMonthlyRevenue(now.getMonthValue(), now.getYear()));
        stats.setTotalProductsSold(orderDao.getTotalProductsSold());
        stats.setBestSellingProductName(productDao.getBestSellingProductName());
        stats.setTotalCustomers(userDao.countCustomers());

        return stats;
    }
}

