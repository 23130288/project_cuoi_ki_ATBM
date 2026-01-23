package org.example.projectweb.service;

import org.example.projectweb.dao.VoucherDao;
import org.example.projectweb.model.Voucher;

import java.util.List;

public class VoucherService {
    final VoucherDao vDao = new VoucherDao();
    final NotificationService ns = new NotificationService();

    public boolean addVoucher(String type, double discount, double condition, String expiredDate) {
        if ("phan_tram".equals(type)) {
            discount = discount / 100.0; // 44 → 0.44
        }

        String imagePath = getImageByType(type);
        boolean status = true;

        vDao.addVoucher( type, discount, condition, expiredDate, imagePath, status);
        // Tạo thông báo
        ns.createGlobalNotification("All", "Voucher mới", "Mã giảm giá mới vừa xuất hiện vào săn ngay!", -1);
        return true;
    }

    private String getImageByType(String type) {
        return switch (type) {
            case "mien_phi_van_chuyen" -> "/images/voucher/free_ship.png";
            case "giam_gia" -> "/images/voucher/sale_d.png";
            case "phan_tram" -> "/images/voucher/sale_p.png";
            default -> "/images/voucher/default.png";
        };
    }

    public List<Voucher> getAllVouchers() {
        return vDao.getListVoucher();
    }

    public boolean updateVoucherStatus(int vid) {
        Voucher voucher = vDao.getVoucherById(vid);
        //check tồn tại
        if (voucher == null) {
            return false;
        }

        //đảo trạng thái
        voucher.setStatus(!voucher.isStatus());
        vDao.updateStatus(voucher.getVid());
        return true;
    }

    public List<Voucher> getVouchersByUid(int uid) {
        return vDao.getVouchersByUid(uid);
    }

    public Voucher getVoucherByUvid(int uvid) {
        return vDao.getVoucherByUvid(uvid);
    }

    public void deleteVoucherUserByUvid(int uvid) {
        vDao.deleteUserVoucherByUvid(uvid);
    }
}

